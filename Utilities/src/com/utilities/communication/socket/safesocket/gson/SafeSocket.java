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

package com.utilities.communication.socket.safesocket.gson;

import com.utilities.communication.socket.CommunicationSocket;
import com.utilities.cryptor.CryptorException;
import com.utilities.cryptor.CryptorAES;
import com.utilities.cryptor.CryptorRSA;
import com.utilities.gson.GsonS;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

/**
 * SafeSocket.java
 * @author Alejandro Silva
 * Clase encargada de la comunicacion Socket de forma encriptada
 */
public abstract class SafeSocket implements CommunicationSocket {    
    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    private CryptorAES aes;
    private CryptorRSA rsa;
    
    /**
     * Constructor que crea los descriptores para enviar y recibir
     * Tamb�en se intercambian las claves RSA y AES para poder encriptar
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
     * Tamb�en se intercambian las claves RSA y AES para poder encriptar
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
     * Tamb�en se intercambian las claves RSA y AES para poder encriptar
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
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(new BufferedInputStream(
                    socket.getInputStream()));
            aes = new CryptorAES();
            rsa = new CryptorRSA();
        } catch (CryptorException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Env�a un objeto criptado.
     * @param object
     * @throws IOException
     */
    @Override
    public void send(Object object) throws IOException {
        try {
            byte[] bytes = aes.encrypt(GsonS.getGson().
                    toJson(object).getBytes());
            send(bytes);
        } catch (CryptorException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void send(byte[] bytes) throws IOException{
        output.writeInt(bytes.length);
        output.write(bytes);
        output.flush();
    }

    /**
     * Recibe el objeto encriptado y lo desencripta.
     * @return obj
     * @throws IOException
     */

    @Override
    public <T> T receive(Class<T> type) throws IOException {
        byte[] bytes = receive();
        if(bytes != null){
            try {
                bytes = aes.decrypt(bytes); 
                return GsonS.getGson().fromJson(new String(bytes), type);
            } catch (CryptorException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            return null;
        }   
    }
    
    private byte[] receive() throws IOException{
        int length = input.readInt();
        if(length > 0){
            byte[] bytes = new byte[length];
            input.read(bytes, 0, length);
            return bytes;
        }else{
            return null;
        }  
    }

    /**
     * Env�a la clave publica RSA. Se utiliza para envirla del servidor
     * al cliente.
     * @throws IOException 
     */
    protected void sendRsaKey() throws IOException {
        send(rsa.getPublicKey());
    }

    /**
     * Recibe la clave publica RSA y se introduce en el EncriptadorRSA.
     * Este m�todo se utiliza en el cliente.
     * @throws IOException 
     * @throws ClassNotFoundException
     * @throws CryptorException 
     */
    protected void receiveRsaKey() throws IOException,ClassNotFoundException, 
    CryptorException {
        rsa.setPublicKey(receive());
    }

    /**
     * Envia la clave AES encriptada con la clave publica RSA. Este m�todo se 
     * utiliza para enviar desde el cliente al servidor.
     * @throws IOException 
     * @throws CryptorException 
     */
    protected void sendAesKey() throws IOException, CryptorException {
        byte[] key = aes.getSecretKey();
        key = rsa.cryptWithPublic(key);
        send(key);
    }

    /**
     * Recibe la clave AES encriptada y la desencripta con la clave RSA privada.
     * Este m�todo se utiliza en el servidor.
     * @throws IOException 
     * @throws ClassNotFoundException
     * @throws CryptorException 
     */
    protected void receiveAesKey() throws IOException, CryptorException, 
    ClassNotFoundException {
        byte[] key = rsa.decryptWithPrivate(receive());
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
