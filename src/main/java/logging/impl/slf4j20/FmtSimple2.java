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

package logging.impl.slf4j20;

// slf4j 2.0.x
public class FmtSimple2 {}

//import org.slf4j.Marker;
//import org.slf4j.event.Level;
//import org.slf4j.helpers.AbstractLogger;
//
///** An implementation of the SLF4J framework.
// *  Investigation of configuration and binding.
// */
//public class FmtSimple2 extends AbstractLogger {
//    //extends LegacyAbstractLogger {
//
//    // LegacyAbstractLogger : maps "marker" to no marker.
//
//    public FmtSimple2(String n) {}
//
//    @Override
//    public boolean isTraceEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isTraceEnabled(Marker marker) {
//        return false;
//    }
//
//    @Override
//    public boolean isDebugEnabled() {
//        return false;
//    }
//
//    @Override
//    public boolean isDebugEnabled(Marker marker) {
//        return false;
//    }
//
//    @Override
//    public boolean isInfoEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean isInfoEnabled(Marker marker) {
//        return true;
//    }
//
//    @Override
//    public boolean isWarnEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean isWarnEnabled(Marker marker) {
//        return true;
//    }
//
//    @Override
//    public boolean isErrorEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean isErrorEnabled(Marker marker) {
//        return true;
//    }
//
//    @Override
//    protected String getFullyQualifiedCallerName() {
//        return null;
//    }
//
//    @Override
//    protected void handleNormalizedLoggingCall(Level level, Marker marker, String msg, Object[] arguments, Throwable throwable) {
//        if ( marker != null )
//            System.out.printf("[%s] **Simple** %s\n", marker.getName(), msg);
//        else
//            System.out.println("[Simple] "+msg);
//    }
//}
