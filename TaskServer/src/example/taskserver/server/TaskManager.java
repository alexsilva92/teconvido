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
package example.taskserver.server;

import example.taskserver.db.ManagerDB;
import example.taskserver.server.taskbuilder.TaskBuilderGetElementDB;
import example.taskserver.server.taskbuilder.TaskBuilderInsertElementDB;
import example.taskserver.server.taskbuilder.TaskBuilderRemoveElementDB;
import example.taskserver.server.taskbuilder.TaskBuilderUpdateElementDB;
import example.taskserver.common.TypeService;
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
