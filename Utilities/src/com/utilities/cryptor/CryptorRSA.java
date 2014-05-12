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

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * CryptorRSA.java
 * @author Alejandro Silva
 * @author Félix Serna
 * @author CodeCrack(http://coding.westreicher.org/?p=23)
 * CryptorRSA
 */
public final class CryptorRSA {
    private KeyPairGenerator keyGen;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    private Cipher cipher;
    
    private static final String ALGORITHM_KG = "RSA";
    private static final String ALGORITHM_CRYPT = "RSA/ECB/PKCS1Padding";
    private static final String SECURITY_ALGORITHM = "SHA1PRNG";
    
    /**
     * Constructor que genera el par de claves publica y privada
     * @throws CryptorException 
     */
    public CryptorRSA() throws CryptorException{
        try {
            createKeys();
            this.cipher = Cipher.getInstance(ALGORITHM_CRYPT);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException ex) {
            throw new CryptorException(ex);
        }
    }
    
    /**
     * Genera el par de claves publica y privada.
     * @throws NoSuchAlgorithmException 
     */
    public void createKeys() throws NoSuchAlgorithmException {
        
        keyGen = KeyPairGenerator.getInstance(ALGORITHM_KG);
        SecureRandom random = SecureRandom.getInstance(SECURITY_ALGORITHM);
        random.setSeed(System.currentTimeMillis());
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    }
    
    /**
     * Cripta bytes con la clave privada
     * @param bytes
     * @return bytes criptados
     * @throws CryptorException 
     */
    public byte[] cryptWithPrivate(byte[] bytes) throws CryptorException{
        try {
            return encrypt(bytes, this.privateKey);
        } catch (InvalidKeyException | IllegalBlockSizeException | 
          BadPaddingException ex) {
            throw new CryptorException(ex);
        }
    }

    /**
     * Cripta bytes con la clave publica
     * @param bytes
     * @return bytes criptados
     * @throws CryptorException 
     */
    public byte[] cryptWithPublic(byte[] bytes) throws CryptorException{
        try {
            return encrypt(bytes, this.publicKey);
        } catch (InvalidKeyException | IllegalBlockSizeException | 
          BadPaddingException ex) {
            throw new CryptorException(ex);
        }
    }

    /**
     * Desencripta bytes con la clave privada
     * @param bytes
     * @return bytes desencriptados
     * @throws CryptorException 
     */
    public byte[] decryptWithPrivate(byte[] bytes) throws CryptorException 
    {
        try {
            return decrypt(bytes, this.privateKey);
        } catch (InvalidKeyException | IllegalBlockSizeException | 
          BadPaddingException ex) {
            throw new CryptorException(ex);
        }
    }
    
    /**
     * Desencripta bytes con clave p?blica
     * @param bytes
     * @return bytes desencriptados
     * @throws CryptorException 
     */
    public byte[] decryptWithPublic(byte[] bytes) 
    throws CryptorException{
        try {
            return decrypt(bytes, this.publicKey);
        } catch (InvalidKeyException | IllegalBlockSizeException | 
          BadPaddingException ex) {
            throw new CryptorException(ex);
        }
    }

    /**
     * getPublicKey
     * @return key publica
     */
    public byte[] getPublicKey() {
        return this.publicKey.getEncoded();
    }

    /**
     * setPublicKey
     * @param _key
     * @throws CryptorException 
     */
    public void setPublicKey(byte[] _key) throws CryptorException{
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(_key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_KG);

            this.publicKey = keyFactory.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            throw new CryptorException(ex);
        }
    }

    /**
     * getCipher
     * @return cipher
     */
    public Cipher getCipher(){
        return cipher;
    }

    /**
     * Cripta bytes dada una key(puede ser p?blica o privada)
     * @param bytes
     * @param key
     * @return bytes criptados
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    private byte[] encrypt(byte[] bytes, Key key) throws InvalidKeyException, 
    IllegalBlockSizeException,BadPaddingException {
        
        this.cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] crypted = this.blockCipher(bytes, Cipher.ENCRYPT_MODE); 
        return crypted;
    }

    /**
     * Desencripta bytes dada una key(puede ser p?blica o privada)
     * @param bytes
     * @param key
     * @return bytes desencriptados
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    private byte[] decrypt(byte[] bytes, Key key) throws InvalidKeyException, 
    IllegalBlockSizeException,BadPaddingException {
        
        this.cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decrypted = this.blockCipher(bytes, Cipher.DECRYPT_MODE);
        return decrypted;
    }

    /**
     * blockCipher
     * @param bytes
     * @param modoEncriptacion (Cipher.ENCRYPT_MODE o Cipher.DECRYPT_MODE)
     * @return bytes criptados o desencriptados, seg?n el m?todo elegido
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    private byte[] blockCipher(byte[] bytes, int modoEncriptacion)
    throws IllegalBlockSizeException,BadPaddingException{
        Cipher _cipher;
        _cipher = this.cipher;

        // Cadena inicializar dos buffers.
        // scrambled tendr? los resultados intermedios
        byte[] scrambled = new byte[0];
        // ToReturn tendr? el resultado total
        byte[] toReturn = new byte[0];
        // Si ciframos usamos 100 bloques largos de bytes. El descifrado 
        // requiere 128 bloques de bytes de largo (debido a RSA)
        int chunkLength = 0;
        if (modoEncriptacion == Cipher.ENCRYPT_MODE){
            chunkLength = 100;
        }else{
            chunkLength = 128;
        }

        // otro buffer. ?ste contendr? los bytes que tienen que ser 
        // modificados en este paso
        byte[] buffer = new byte[chunkLength];

        for (int i=0; i< bytes.length; i++){
            // Si llenamos nuestra matriz del b?fer tenemos nuestro bloque
            // listo para [de]cifrado
            if ((i > 0) && (i % chunkLength == 0)){
                scrambled = _cipher.doFinal(buffer);
                toReturn = append(toReturn,scrambled);
                int newlength = chunkLength;

                if (i + chunkLength > bytes.length) {
                    newlength = bytes.length - i;
                }
                buffer = new byte[newlength];
            }
            buffer[i%chunkLength] = bytes[i];
        }
        scrambled = _cipher.doFinal(buffer);

        toReturn = append(toReturn,scrambled);
        return toReturn;
    }
           
    /**
     * Anexa el "sufijo" matriz de bytes con el "prefijo" uno.
     * @param prefix
     * @param suffix
     * @return toReturn
     */
    private byte[] append(byte[] prefix, byte[] suffix){
        
        byte[] toReturn = new byte[prefix.length + suffix.length];
        System.arraycopy(prefix, 0, toReturn, 0, prefix.length);
        System.arraycopy(suffix, 0, toReturn, prefix.length, 
                suffix.length);
        return toReturn;
    }

}
