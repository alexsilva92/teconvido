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
package com.teconvido.server.taskbuilder;

import com.taskserver.server.AbstractTaskManager;
import com.taskserver.server.TaskBuilder;
import com.taskserver.server.AbstractThreadRequests;
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.server.TaskManager;
import com.utilities.communication.socket.CommunicationSocket;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * TaskBuilderUpdateElementDB.java
 * @author Alejandro Silva
 */
public class TaskBuilderUpdateElementDB implements TaskBuilder{
    private static final Logger logger = 
            Logger.getLogger(TaskUpdateElementDB.class);
    
    public class TaskUpdateElementDB extends AbstractThreadRequests 
    implements TeConvidoConstantDB {       
       
        public TaskUpdateElementDB(AbstractTaskManager manager, 
                CommunicationSocket communication) {
            super(manager,communication);
        }

        @Override
        public Integer call() {

            try {
                UpdateDB typeUpdate = communication.receive(UpdateDB.class);
                String search = communication.receive(String.class);
                String element = communication.receive(String.class);
            
                try{
                String result = ((TaskManager)manager).getManagerRequestDB().update(
                        typeUpdate,search,element);

                logger.info("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | ACCION: " + 
                        typeUpdate.name() + " | ELEMENTO: " + element);

                communication.send(result);

                communication.close();
                }catch(Exception ex){
                    logger.error("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | ACCION: " + 
                        typeUpdate.name() + " | ELEMENTO: " + element + 
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
     public AbstractThreadRequests createTask(AbstractTaskManager manager, 
     CommunicationSocket communication){
         return new TaskUpdateElementDB(manager,communication);
     } 
}
