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

package com.utilities.communication.socket.safesocket.gson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente.java
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
public class Cliente {
    public static void main(String[] argv){
        try {
            SafeSocket socket = new SafeClientSocket("localhost",20000);
            socket.send(TypeServiceServer.GET_ELEMENT);
            System.out.println(socket.receive(String.class));
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
