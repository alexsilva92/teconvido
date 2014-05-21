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

import com.utilities.communication.socket.CommunicationSocket;
import java.util.concurrent.Callable;

/**
 * ThreadRequests.java
 * @author Alejandro Silva
 */
public abstract class AbstractThreadRequests<T> implements Callable<Integer>{
    
    protected AbstractTaskManager<T> manager;
    protected CommunicationSocket communication;
    
    public AbstractThreadRequests(AbstractTaskManager<T> manager,
    CommunicationSocket 
    communication){
        this.manager = manager;
        this.communication = communication;
    }
    
    public AbstractTaskManager<T> getManager(){
        return manager;
    }
    
    public CommunicationSocket getCommunication(){
        return communication;
    }  
} 
