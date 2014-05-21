/*
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
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

import com.taskserver.server.AbstractTaskManager;
import com.taskserver.server.AbstractThreadRequests;
import com.utilities.communication.socket.CommunicationSocket;
import example.taskserver.common.TypeService;

/**
 * ThreadRequest.java
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
public class ThreadRequests extends AbstractThreadRequests<TypeService>{

    public ThreadRequests(AbstractTaskManager<TypeService> manager, 
    CommunicationSocket communication) {
        super(manager, communication);
    }
    
    @Override
    public Integer call() throws Exception {
        TypeService type = communication.receive(TypeService.class);
        System.err.println(type);
        manager.addTask(type,communication);
        return 0;
    }   
}
