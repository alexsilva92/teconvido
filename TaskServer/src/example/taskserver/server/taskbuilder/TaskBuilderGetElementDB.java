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
package example.taskserver.server.taskbuilder;

import com.taskserver.db.exception.UnimplementedQueryException;
import example.taskserver.common.ConstantDB;
import example.taskserver.server.TaskManager;
import com.taskserver.server.AbstractTaskManager;
import com.taskserver.server.TaskBuilder;
import com.taskserver.server.AbstractThreadRequests;
import com.utilities.communication.socket.CommunicationSocket;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * TaskBuilderGetElementDB.java
 * @author Alejandro Silva
 */
public class TaskBuilderGetElementDB implements TaskBuilder{
    private static final Logger logger = 
            Logger.getLogger(TaskGetElementDB.class);
    
    public class TaskGetElementDB extends AbstractThreadRequests
    implements ConstantDB {       
       
        public TaskGetElementDB(AbstractTaskManager manager, 
        CommunicationSocket communication) {
            super(manager,communication);
        }

        @Override
        public Integer call() {

            try {
                GetDB typeGet =  communication.receive(GetDB.class);
                Object search = communication.receive(Object.class);

                
                Object result = ((TaskManager)manager).
                        getManagerDB().get(typeGet,search);

                logger.info("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + " | ACCION: " + 
                        typeGet.name() + " | BUSQUEDA: " + search);

                communication.send(result);

                communication.close();
                
            } catch (IOException ex) {
                logger.error("ACCESO | IP : " + communication.getSocket().
                        getRemoteSocketAddress().toString() + 
                        " | ACCION: GetElement | " + ex);
            } 
            
            return 0;
        }  
     }
    
     @Override
     public AbstractThreadRequests createTask(AbstractTaskManager manager, 
     CommunicationSocket communication){
         return new TaskGetElementDB(manager,communication);
     } 
}
