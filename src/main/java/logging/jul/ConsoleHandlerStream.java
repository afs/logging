/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package logging.jul;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.*;

/** Console handler that modifies {@link java.util.logging.ConsoleHandler}.
 * Supports the configuration parameters of {@link ConsoleHandler} -- {@code .level},
 * {@code .filter}, {@code .formatter} and {@code .encoding}.
 * <p>
 * Defaults:
 * <ul>
 * <li>Stdout, rather than stderr, by default.</li>
 * <li>{@link TextFormatter} rather than {@link java.util.logging.SimpleFormatter}</li>
 * <li>UTF-8, rather than platform charset</li>
 * </ul>
 * Example:
 * <pre>
 * handlers=logging.jul.ConsoleHandlerStream</pre>
 * or to configure the formatter as well:
 * <pre>
 * handlers=logging.jul.ConsoleHandlerStream
 * logging.jul.TextFormatter.format = %5$tT %3$-5s %2$-20s -- %6$s</pre>
 */
public class ConsoleHandlerStream extends StreamHandler {

    // We can't use ConsoleHandler.
    // The setOutputStream() operation closes the previous stream but when the only
    // constructor ConsoleHandler() runs, it sets output to System.err.
    // So System.err is then closed in the app!
    // We need to chose the output in the constructor and ConsoleHandler does not allow that,
    // hence going straight to StreamHandler and having to provide the functionality here.

    /** Don't close {@code System.err} or {@code System.out} */
    private static OutputStream protectStdOutput(OutputStream outputStream) {
        if ( outputStream == System.err || outputStream == System.out )
            return new CloseProtectedOutputStream(outputStream);
        return outputStream;
    }

    public ConsoleHandlerStream() {
        this(System.out);
    }

    public ConsoleHandlerStream(OutputStream outputStream) {
        // default Level.INFO
        super(protectStdOutput(outputStream), new TextFormatter());
        // Change default to all.
        // This avoid the effect of not getting sub-INFO output when no level is set.
        setLevel(Level.ALL);

        LogManager manager = LogManager.getLogManager();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String cname = getClass().getName();

        // Necessary, because Handler set defaults
        // which causes it to ignore "formatter" setting.
        // (Level and Filter are actually OK as is.)

        // -- Level
        Level level = Level.INFO;
        String pLevel = getProperty(manager, cname, ".level");
        if ( pLevel != null )
            level = Level.parse(pLevel);
        setLevel(level);

        // -- Formatter
        // Necessary, because we gave a default to Handler in super()
        // which causes it to ignore "formatter" setting.
        String pFormatter = getProperty(manager, cname, ".formatter");
        if ( pFormatter != null ) {
            try {
                Class<?> cls = classLoader.loadClass(pFormatter);
                @SuppressWarnings("deprecation")
                Formatter fmt = (Formatter)cls.newInstance();
                setFormatter(fmt);
            } catch (Exception ex) {
                System.err.println("Problems setting the logging formatter");
                ex.printStackTrace(System.err);
            }
        }

        // -- Filter
        String pFilter = getProperty(manager, cname, ".filter");
        if ( pFilter != null ) {
            try {
                Class<?> cls = classLoader.loadClass(pFilter);
                @SuppressWarnings("deprecation")
                Filter filter = (Filter) cls.newInstance();
                setFilter(filter);
            } catch (Exception ex) {
                System.err.println("Problems setting the logging filter");
                ex.printStackTrace(System.err);
            }
        }

        // -- Encoding : Default UTF-8
        String pEncoding = getProperty(manager, cname, "encoding");
        if ( pEncoding == null )
            pEncoding = StandardCharsets.UTF_8.name();
        try { setEncoding(pEncoding); }
        catch (Exception e) {
            // That should work for UTF-8 as it is a required charset.
            System.err.print("Failed to set encoding: "+e.getMessage());
        }
    }

    private static String getProperty(LogManager manager, String cname, String pname) {
        if ( ! pname.startsWith(".") )
            pname = "."+pname;
        return manager.getProperty(cname+pname);
    }

    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }

    /** Flush but do not close on close(). */
    private static class CloseProtectedOutputStream extends FilterOutputStream {
        // c.f. Apache Commons IO CloseShieldOutputStream but we don't want
        // dependencies for this module just for one simple class.
        public CloseProtectedOutputStream(OutputStream out) {
            super(out);
        }

        @Override public void close() throws IOException {
            flush();
        }
    }
}
