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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor.java
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
public class Servidor {
    
    private ServerSocket server ;
    
    public Servidor(int port) 
    throws IOException{
        server = new ServerSocket(port);
    }
    
    public void start() throws IOException {
        
        Socket client;

        while(true){
            client = server.accept();
            SafeSocket socket = new SafeServerSocket(client);
            TypeServiceServer type =  socket.receive(TypeServiceServer.class);
            System.out.println(type);
            socket.send("recibido");   
        }
    }
    
    public static void main(String[] argv) throws IOException{
        Servidor servidor = new Servidor(20000);
        servidor.start();
    }
}
