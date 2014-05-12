/**
 *  Copyright 2014 Alejandro Silva
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
package com.teconvido.server;

import com.taskserver.server.AbstractTaskManager;
import com.teconvido.common.TypeServiceServer;
import com.teconvido.db.ManagerDB;
import com.teconvido.server.taskbuilder.TaskBuilderGetElementDB;
import com.teconvido.server.taskbuilder.TaskBuilderInsertElementDB;
import com.teconvido.server.taskbuilder.TaskBuilderNotificationPush;
import com.teconvido.server.taskbuilder.TaskBuilderRemoveElementDB;
import com.teconvido.server.taskbuilder.TaskBuilderUpdateElementDB;


/**
 * TaskManager.java
 * @author Alejandro Silva
 */
public class TaskManager extends AbstractTaskManager<TypeServiceServer>{

    private ManagerDB managerDB;
    
    public TaskManager(int executionThreads,ManagerDB managerDB){
        super(executionThreads);
        
        this.managerDB = managerDB;
    }
    
    public TaskManager(ManagerDB managerDB){
        this(DEFAULT_THREAD,managerDB);
    }

    @Override
    protected void initializeTaskBuilders() {
        taskBuilders.put(TypeServiceServer.GET_ELEMENT, 
                new TaskBuilderGetElementDB());
        taskBuilders.put(TypeServiceServer.INSERT_ELEMENT, 
                new TaskBuilderInsertElementDB());
        taskBuilders.put(TypeServiceServer.UPDATE_ELEMENT, 
                new TaskBuilderUpdateElementDB());
        taskBuilders.put(TypeServiceServer.REMOVE_ELEMENT, 
                new TaskBuilderRemoveElementDB());
        taskBuilders.put(TypeServiceServer.NOTIFICATION_PUSH, 
                new TaskBuilderNotificationPush());
    }

    public ManagerDB getManagerDB() {
        return managerDB;
    }    
}
