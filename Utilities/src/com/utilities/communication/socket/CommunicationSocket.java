/*
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.utilities.communication.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * Communication.java
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
public interface CommunicationSocket {
    public void send(Object object) throws IOException;
    public <T> T receive(Class<T> type) throws IOException;
    public void close() throws IOException;
    public Socket getSocket();
}
