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

/**
 * Helper code when using slf4j.
 * <ul>
 * <li>{@link logging.LogFmt} -- Formatted logging using java's printf style formats
 * <li>{@link logging.LogCtl} -- Change logging levels during runtime (e.g. development)
 * <li>{@link logging.LoggingSystem} -- Logging setup, with configuration from file, classpath or a default.
 * <li>{@link logging.Log} -- static logging calls for convenient inclusion of a few
 * messages, e.g warnings and errors.
 * </ul>
 * Logging systems supported:
 * <ul>
 * <li><a href="https://logging.apache.org/log4j/2.x/">Apache Log4j 2</a>
 * <li><a href="https://logging.apache.org/log4j/1.2/">Apache Log4j 1</a> (log4j1 is end-of-life - choose log4j2 if possible)
 * <li>Java Util Logging (JUL)
 * </ul>
 *
 */
package logging;