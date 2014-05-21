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

import java.io.IOException;
import java.net.ServerSocket;


/**
 * AbstractServer.java
 * @author Alejandro Silva
 */
public abstract class AbstractServer
{
    
    public static final int DEFAULT_PORT = 20000;
    
    protected AbstractTaskManager manager;
    protected ServerSocket server ;
    
    public AbstractServer(int port,AbstractTaskManager manager) 
    throws IOException{
        
        this.manager = manager;
        server = new ServerSocket(port);
    }
    
    public AbstractServer(AbstractTaskManager manager) throws IOException{
        this(DEFAULT_PORT,manager);
    }
    
    public abstract void start() throws IOException;
}
