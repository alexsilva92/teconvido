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
package com.utilities.communication.socket.safesocket;

import com.utilities.cryptor.CryptorException;
import java.io.IOException;
import java.net.Socket;

/**
 * SafeServerSocket.java
 * @author Alejandro Silva
 * Clase encargada de la comunicacion Socket de forma encriptada
 * desde el Servidor
 */
@Deprecated
public class SafeServerSocket extends SafeSocket{
    
    /**
     * Constructor que llama al constructor de SafeSocket y luego
     * envia la clave RSA publica generada y espera a recibir la clave AES
     * criptada con la clave RSA publica enviada
     * @param _socket
     * @throws IOException 
     */
    public SafeServerSocket(Socket _socket) throws IOException{
        super(_socket);
        exchangeKeys();
    }

    /**
     * Constructor que llama al constructor de SafeSocket y luego
     * recibe la clave RSA publica del servidor y con esta cripta la clave AES
     * generada y la envia al servidor. También cambia el timeout
     * @param _hostname
     * @param _puerto
     * @param _timeout
     * @throws IOException 
     */
    public SafeServerSocket(String _hostname,int _puerto,int _timeout) 
    throws IOException{
        super(_hostname,_puerto,_timeout);  
        exchangeKeys();
    }
    
    /**
     * Constructor que llama al constructor de SafeSocket y luego
     * envia la clave RSA publica generada y espera a recibir la clave AES
     * criptada con la clave RSA publica enviada
     * @param _hostname
     * @param _puerto
     * @throws IOException 
     */
    public SafeServerSocket(String _hostname,int _puerto) 
    throws IOException{
        super(_hostname,_puerto);  
        exchangeKeys();
    }
    
    /**
     * Envia la clave RSA publica al Cliente y recibe la clave AES encriptada
     * @throws IOException 
     */
    private void exchangeKeys() throws IOException{
        try {
            sendRsaKey();
            receiveAesKey();
        } catch (CryptorException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
