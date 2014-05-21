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

import com.taskserver.server.AbstractServer;
import com.taskserver.server.AbstractTaskManager;
import com.teconvido.db.ManagerDB;
import com.utilities.communication.socket.safesocket.gson.SafeServerSocket;
import com.utilities.communication.socket.safesocket.gson.SafeSocket;
import com.utilities.cryptor.CryptorException;
import java.io.IOException;
import java.net.Socket;
import javax.xml.bind.JAXBException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Server.java
 * @author Alejandro Silva
 */
public class TeConvidoServer extends AbstractServer{
    
    private static final Logger LOOGER = Logger.getLogger(
            TeConvidoServer.class);
    
    private static final String SERVER_LOGGER_PROPERTIES = 
            "logger/server.logger.properties";
    
    public TeConvidoServer(int port,AbstractTaskManager manager) 
    throws IOException{
        super(port,manager);
        LOOGER.info("SERVIDOR | Servidor iniciado en el puerto: " 
                + port);
    }
    
    public TeConvidoServer(AbstractTaskManager manager) throws IOException{
        super(DEFAULT_PORT,manager);
        LOOGER.info("SERVIDOR | Servidor iniciado en el puerto: " 
                + DEFAULT_PORT);
    }
    
    public static void main(String[] argv){
        try {
            PropertyConfigurator.configure(SERVER_LOGGER_PROPERTIES);
        }
        catch (Exception e){
            BasicConfigurator.configure();
        }
        ManagerDB managerDB = null;
        try {
            managerDB = new ManagerDB();
            
        } catch (JAXBException ex) {
            LOOGER.error("SERVIDOR | El archivo xml de preferencias "
                    + "esta corrupto", ex);
            System.exit(-1);
        } catch (IOException ex) {
            LOOGER.error("SERVIDOR | No se tiene acceso al archivo "
                    + "de preferencias", ex);
            System.exit(-1);
        } catch (CryptorException ex) {
            LOOGER.error("SERVIDOR | La clave de encriptacion es invalida", ex);
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            LOOGER.error("SERVIDOR | No se ha encontrado el driver "
                    + "de conexion", ex);
            System.exit(-1);
        }
        TaskManager taskManager = new TaskManager(managerDB);
        
        TeConvidoServer server = null;
        try {
            server = new TeConvidoServer(taskManager);
            
            server.start();
        } catch (IOException ex) {
            LOOGER.error("SERVIDOR | Error al iniciar el servidor", ex);
            System.exit(-1);
        }        
    }

    @Override
    public void start() throws IOException {
        Socket client;
        
        while(true){
            client = server.accept();
            SafeSocket communication = new SafeServerSocket(client);
            manager.addTask(new ThreadRequests(manager,communication));
        }
    }
}
