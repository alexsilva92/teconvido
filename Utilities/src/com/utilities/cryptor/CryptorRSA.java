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
        return publicKey.getEncoded();
    }

    /**
     * setPublicKey
     * @param _key
     * @throws CryptorException 
     */
    public void setPublicKey(byte[] _key) throws CryptorException{
        try {
            publicKey =  KeyFactory.getInstance(ALGORITHM_KG).
                    generatePublic(new X509EncodedKeySpec(_key));
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
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(bytes); 
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
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(bytes);
    }
}
