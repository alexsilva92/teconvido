/**
 *  Copyright 2013 Alejandro Silva
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
package com.taskserver.server;

import com.utilities.safesocket.SafeServerSocket;
import com.utilities.safesocket.SafeSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * AbstractServer.java
 * @author Alejandro Silva
 */
public abstract class AbstractServer<T>
{
    
    public static final int DEFAULT_PORT = 20000;
    
    private AbstractTaskManager manager;
    private ServerSocket server ;
    
    public AbstractServer(int port,AbstractTaskManager manager) 
    throws IOException{
        
        this.manager = manager;
        server = new ServerSocket(port);
    }
    
    public void start() throws IOException{
        
        Socket client;

        while(true){
            client = server.accept();
            SafeSocket communication = new SafeServerSocket(client);
            manager.addTask(new ThreadRequests<T>(manager,communication));
        }
    }
}
