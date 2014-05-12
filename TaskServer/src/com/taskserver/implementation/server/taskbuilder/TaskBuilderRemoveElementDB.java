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
package com.taskserver.implementation.server.taskbuilder;

import com.taskserver.implementation.common.ConstantDB;
import com.taskserver.implementation.server.TaskManager;
import com.taskserver.server.AbstractTaskManager;
import com.taskserver.server.TaskBuilder;
import com.taskserver.server.ThreadRequests;
import com.utilities.safesocket.SafeSocket;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * TaskBuilderRemoveElementDB.java
 * @author Alejandro Silva
 */
public class TaskBuilderRemoveElementDB implements TaskBuilder{
    private static final Logger logger = 
            Logger.getLogger(TaskRemoveElementDB.class);
    
    public class TaskRemoveElementDB extends ThreadRequests 
    implements ConstantDB {       
       
        public TaskRemoveElementDB(AbstractTaskManager manager, 
                SafeSocket communication) {
            super(manager,communication);
        }

        @Override
        public Integer call() {

            try {
                RemoveDB typeRemove = (RemoveDB) communication.receive();
                Object element = communication.receive();
            
                try{
                Object result = ((TaskManager)manager).getManagerDB().remove(
                        typeRemove,element);

                logger.info("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | ACCION: " + 
                        typeRemove.name() + " | ELEMENTO: " + element);

                communication.send(result);

                communication.close();
                }catch(Exception ex){
                    logger.error("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | ACCION: " + 
                        typeRemove.name() + " | ELEMENTO: " + element + 
                        " | " + ex);
                }
            } catch(IOException ex){
                logger.error("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | " + ex);
            }
            return 0;
        }  
     }
    
     @Override
     public ThreadRequests createTask(AbstractTaskManager manager, 
     SafeSocket communication){
         return new TaskRemoveElementDB(manager,communication);
     } 
}
