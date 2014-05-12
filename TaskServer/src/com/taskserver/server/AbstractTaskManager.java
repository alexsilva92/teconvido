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

import com.utilities.safesocket.SafeSocket;
import com.taskserver.db.AbstractManagerDB;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * AbstractTaskManager.java
 * @author Alejandro Silva
 */
public abstract class AbstractTaskManager<T> {

    private ExecutorService poolThreads;
    
    protected Map<T,TaskBuilder> taskBuilders;
        
    public static final int DEFAULT_THREAD = 20;
    
    public AbstractTaskManager(int executionThreads){
        poolThreads = Executors.newFixedThreadPool(executionThreads);
        
        taskBuilders = new HashMap<>();
        
        initializeTaskBuilders();
        
    }
    
    protected abstract void initializeTaskBuilders();

    public synchronized void addTask(T typeService,SafeSocket communication){      
        poolThreads.submit(taskBuilders.get(typeService).createTask(
                this,communication));
    }
    
    public synchronized void addTask(Callable<Integer> thread){      
        poolThreads.submit(thread);
    } 
}
