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
import com.teconvido.common.TypeNotificationPush;
import com.teconvido.communication.googlepush.NotificationPush;
import com.teconvido.server.TaskManager;
import com.utilities.communication.socket.CommunicationSocket;
import com.utilities.gson.GsonS;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * TaskBuilderGetElementDB.java
 * @author Alejandro Silva
 */
public class TaskBuilderNotificationPush implements TaskBuilder{
    private static final Logger logger = 
            Logger.getLogger(TaskNotificationPush.class);
    
    public class TaskNotificationPush extends AbstractThreadRequests
    implements TeConvidoConstantDB {       
       
        public TaskNotificationPush(AbstractTaskManager manager, 
        CommunicationSocket communication) {
            super(manager,communication);
        }

        @Override
        public Integer call() {

            try {
                String login = communication.receive(String.class);
                TypeNotificationPush type = 
                        communication.receive(TypeNotificationPush.class);
                String mensaje = communication.receive(String.class);
                
                String gcmId = (String) ((TaskManager)manager).getManagerRequestDB().get(GetDB.GET_GCM_ID,login,null);

                if(gcmId != null){
                    NotificationPush.send(gcmId,type, mensaje);
                    communication.send(GsonS.getGson().toJson(true));
                }else{
                    communication.send(GsonS.getGson().toJson(false));
                }
                communication.close();
                
            } catch (IOException ex) {
                logger.error("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + 
                        " | ACCION: NotificationPush | " + ex);
            } 
            
            return 0;
        }  
     }
    
     @Override
     public AbstractThreadRequests createTask(AbstractTaskManager manager, 
     CommunicationSocket communication){
         return new TaskNotificationPush(manager,communication);
     } 
}
