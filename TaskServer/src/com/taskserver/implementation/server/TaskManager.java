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
package com.taskserver.implementation.server;

import com.taskserver.implementation.db.ManagerDB;
import com.taskserver.db.AbstractManagerDB;
import com.taskserver.implementation.server.taskbuilder.TaskBuilderGetElementDB;
import com.taskserver.implementation.server.taskbuilder.TaskBuilderInsertElementDB;
import com.taskserver.implementation.server.taskbuilder.TaskBuilderRemoveElementDB;
import com.taskserver.implementation.server.taskbuilder.TaskBuilderUpdateElementDB;
import com.taskserver.implementation.common.TypeService;
import com.taskserver.server.AbstractTaskManager;


/**
 * TaskManager.java
 * @author Alejandro Silva
 */
public class TaskManager extends AbstractTaskManager<TypeService>{

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
        taskBuilders.put(TypeService.GET_ELEMENT, 
                new TaskBuilderGetElementDB());
        taskBuilders.put(TypeService.INSERT_ELEMENT, 
                new TaskBuilderInsertElementDB());
        taskBuilders.put(TypeService.UPDATE_ELEMENT, 
                new TaskBuilderUpdateElementDB());
        taskBuilders.put(TypeService.REMOVE_ELEMENT, 
                new TaskBuilderRemoveElementDB());
    }

    public ManagerDB getManagerDB() {
        return managerDB;
    }    
}
