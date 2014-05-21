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

import com.utilities.communication.socket.CommunicationSocket;
import com.utilities.cryptor.CryptorException;
import com.utilities.cryptor.CryptorAES;
import com.utilities.cryptor.CryptorRSA;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetSocketAddress;

/**
 * SafeSocket.java
 * @author Alejandro Silva
 * Clase encargada de la comunicacion Socket de forma encriptada
 */
@Deprecated
public abstract class SafeSocket implements CommunicationSocket {    
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;

    private CryptorAES aes;
    private CryptorRSA rsa;
    
    /**
     * Constructor que crea los descriptores para enviar y recibir
     * Tambíen se intercambian las claves RSA y AES para poder encriptar
     * los mensajes que se envien por el canal. Se intercambian de una manera
     * diferente si es servidor o cliente.
     * @param _socket
     * @throws IOException 
     */
    public SafeSocket(Socket _socket) throws IOException {
        this.socket = _socket;
        initialization();
        
    }
    
    /**
     * Constructor que crea los descriptores para enviar y recibir
     * Tambíen se intercambian las claves RSA y AES para poder encriptar
     * los mensajes que se envien por el canal. Se intercambian de una manera
     * diferente si es servidor o cliente. En la variable _timeout se pone
     * la cantidad de ms de TimeOut del Socket.
     * @param _hostname
     * @param _port
     * @param _timeout
     * @throws IOException 
     */
    public SafeSocket(String _hostname,int _port,int _timeout) 
    throws IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(_hostname,_port), _timeout);
        initialization();
    }
    
    /**
     * Constructor que crea los descriptores para enviar y recibir
     * Tambíen se intercambian las claves RSA y AES para poder encriptar
     * los mensajes que se envien por el canal. Se intercambian de una manera
     * diferente si es servidor o cliente.
     * @param _hostname
     * @param _puerto
     * @throws IOException 
     */ 
    public SafeSocket(String _hostname,int _puerto) 
    throws IOException{
        socket = new Socket(_hostname,_puerto);  
        initialization();
    }
    
    /**
     * Inicializa los descriptores para enviar y recibir, y inicializa los
     * encriptadores AES y RSA.
     * @throws IOException 
     */
    private void initialization() throws IOException{
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(new BufferedInputStream(
                    socket.getInputStream()));
            aes = new CryptorAES();
            rsa = new CryptorRSA();
        } catch (CryptorException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Envía un objeto criptado.
     * @param object
     * @throws IOException
     */
    @Override
    public void send(Object object) throws IOException {
        try {
            output.writeObject(aes.encrypt(object));
        } catch (CryptorException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Recibe el objeto encriptado y lo desencripta.
     * @return obj
     * @throws IOException
     */
    @Override
    public <T> T receive(Class<T> type) throws IOException {
        try {
            return (T) aes.decryptObject((byte[]) input.readObject());
        } catch (CryptorException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Envía la clave publica RSA. Se utiliza para envirla del servidor
     * al cliente.
     * @throws IOException 
     */
    protected void sendRsaKey() throws IOException {
      output.writeObject(rsa.getPublicKey());
    }

    /**
     * Recibe la clave publica RSA y se introduce en el EncriptadorRSA.
     * Este método se utiliza en el cliente.
     * @throws IOException 
     * @throws ClassNotFoundException
     * @throws CryptorException 
     */
    protected void receiveRsaKey() throws IOException,ClassNotFoundException, 
    CryptorException {
        byte[] key = (byte[]) input.readObject();
        rsa.setPublicKey(key);
    }

    /**
     * Envia la clave AES encriptada con la clave publica RSA. Este método se 
     * utiliza para enviar desde el cliente al servidor.
     * @throws IOException 
     * @throws CryptorException 
     */
    protected void sendAesKey() throws IOException, CryptorException {
        byte[] key = aes.getSecretKey();
        key = rsa.cryptWithPublic(key);
        output.writeObject(key);
    }

    /**
     * Recibe la clave AES encriptada y la desencripta con la clave RSA privada.
     * Este método se utiliza en el servidor.
     * @throws IOException 
     * @throws ClassNotFoundException
     * @throws CryptorException 
     */
    protected void receiveAesKey() throws IOException, CryptorException, 
    ClassNotFoundException {
            byte[] key = (byte[]) input.readObject();
            key = rsa.decryptWithPrivate(key);
            aes.setSecretKey(key);
    }
    
    /**
     * GetSocket
     * @return socket
     */
    @Override
    public Socket getSocket(){
        return socket;
    }
    
    /**
     * Cierra la comunicacion
     * @throws IOException 
     */
    @Override
    public void close() throws IOException{
      output.close();
      input.close();
      socket.close();
    }
}
