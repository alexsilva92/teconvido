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

package com.utilities.cryptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


/**
 * CryptorAES.java
 * @author Alejandro Silva
 * @author Félix Serna
 * EncriptadorAES
 */
public class CryptorAES {
    private static String ALGORITHM_KG = "AES";
    private static String ALGORITHM_CRYPT = "AES/ECB/PKCS5Padding";
    private Cipher cipher;
    private KeyGenerator keygen;
    private SecretKey key;
    private byte[] keyBytes;
    
    /**
     * Constructor que recibe la ruta del fichero donde guardara la 
     * clave secreta
     * @throws CryptorException 
     */
    public CryptorAES() throws CryptorException{
        try {
            keygen = KeyGenerator.getInstance(ALGORITHM_KG);
            key = keygen.generateKey();            
            cipher = Cipher.getInstance(ALGORITHM_CRYPT); 
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ex) {
            throw new CryptorException(ex);
        }
    }
    
    /**
     * getSecretKey
     * @return key en bytes
     */
    public byte[] getSecretKey(){
        if(keyBytes == null){
            try {
                ByteArrayOutputStream bs= new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(bs);
                
                os.writeObject(key);
                keyBytes = bs.toByteArray();
                
                bs.close();
                os.close();
                
            } catch (IOException ex) { throw new RuntimeException(ex); }
        }
        
        return keyBytes;
    }

    /**
     * setSecretKey
     * @param _key
     * @throws CryptorException 
     */
    public void setSecretKey(byte[] _key) throws CryptorException{
        try {
            ObjectInputStream is = new ObjectInputStream(
                    new ByteArrayInputStream(_key));
            
            key = (SecretKey) is.readObject();
            
            is.close();
            
        } catch (ClassNotFoundException ex){ throw new CryptorException(ex);
        } catch(IOException ex) {throw new RuntimeException(ex); }
        
    }
    
    /**
     * Cripta bytes con la clave AES
     * @param _bytes
     * @return bytes criptados
     * @throws CryptorException 
     */
    public byte[] encrypt(byte[] _bytes) throws CryptorException{
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(_bytes); 
        } catch (InvalidKeyException |IllegalBlockSizeException | 
                 BadPaddingException ex) { 
            throw new CryptorException(ex); 
        }
    }
    
    /**
     * Cripta objetos serializables con la clave AES
     * @param _object
     * @return bytes criptados
     * @throws CryptorException 
     */
    public byte[] encrypt(Object _object) throws CryptorException{
        try {
            ByteArrayOutputStream bs= new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bs);
            
            os.writeObject(_object);
            byte[] bytesCrypt = encrypt(bs.toByteArray());
            
            bs.close();
            os.close();
            
            return bytesCrypt;  
        } catch (IOException ex) { throw new RuntimeException(ex); }
    }
    
    /**
     * Cripta una String con la clave AES
     * @param _txt
     * @return bytes criptados
     * @throws CryptorException 
     */
    public byte[] encrypt(String _txt) throws CryptorException { 
        return encrypt(_txt.getBytes());
    }
    
    /**
     * Desencripta bytes con la clave AES
     * @param crypted
     * @return bytes desencriptados
     * @throws CryptorException 
     */
    public byte[] decrypt (byte[] crypted) throws CryptorException{   
        try {     
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            return cipher.doFinal(crypted);
        } catch (IllegalBlockSizeException | BadPaddingException 
                 | InvalidKeyException ex) {
            throw new CryptorException(ex);
        }
    }
    
    /**
     * Desencripta bytes con la clave AES y forma un objeto a partir de 
     * esos bytes desencriptados.
     * @param crypted
     * @return objeto desencriptado
     * @throws CryptorException 
     */
    public Object decryptObject(byte[] crypted) throws CryptorException{
        try {
            ByteArrayInputStream bs = new ByteArrayInputStream(
                    decrypt(crypted));
            ObjectInputStream is = new ObjectInputStream(bs);
            Object obj = is.readObject();
            
            bs.close();
            is.close();
            
            return obj;
        } catch (ClassNotFoundException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Desencripta bytes y forma una string a partir de esos bytes 
     * desencriptados.
     * @param crypted
     * @return string desencriptada
     * @throws CryptorException 
     */
    public String decryptString(byte[] crypted) throws CryptorException{       
        String txtDescrypt = new String (decrypt(crypted));
              
        return txtDescrypt;
    }
}
