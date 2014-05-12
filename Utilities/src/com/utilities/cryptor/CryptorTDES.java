package com.utilities.cryptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * CryptorTDES.java
 * @author Alejandro Silva
 */
public class CryptorTDES {
 
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String ENCRYPT_SCHEME = "DESede";
    private static final String ENCRYPT_KEY = "NKBJF3CBXMCPDMJRB7YFXTHC";
    
    private KeySpec keySpec;
    private SecretKeyFactory secretKeyFactory;
    private Cipher cipher;
    private SecretKey key;
 
    /**
     * Constructor con parametro
     * @param _key
     * @throws CryptorException 
     */
    public CryptorTDES(String _key) throws CryptorException{
        try {
            keySpec = new DESedeKeySpec(_key.getBytes(UNICODE_FORMAT));
            secretKeyFactory = SecretKeyFactory.getInstance(ENCRYPT_SCHEME);
            cipher = Cipher.getInstance(ENCRYPT_SCHEME);
            key = secretKeyFactory.generateSecret(keySpec);
        } catch(NoSuchPaddingException | 
                 NoSuchAlgorithmException | InvalidKeyException | 
                 UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        } catch(InvalidKeySpecException ex){
            throw new CryptorException(ex);
        }
    }
    
    /**
     * Constructor por defecto
     * @throws CryptorException 
     */
    public CryptorTDES() throws CryptorException{
        this(ENCRYPT_KEY);
    }
 
    /**
     * Cripta un texto y lo devuelve criptado
     * @param _txt
     * @return texto criptado
     * @throws CryptorException 
     */
    public String encrypt(String _txt) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(_txt.getBytes(UNICODE_FORMAT));
            BASE64Encoder base64encoder = new BASE64Encoder();       
            return base64encoder.encode(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException | 
                 UnsupportedEncodingException | InvalidKeyException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Decripta un texto criptado y lo devuelve
     * @param _txt
     * @return texto decriptado
     * @throws CryptorException 
     */
    public String decrypt(String _txt){
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            BASE64Decoder base64decoder = new BASE64Decoder();
            byte[] bytes = cipher.doFinal(base64decoder.decodeBuffer(_txt));
            return new String(bytes);
        } catch (IOException | IllegalBlockSizeException | BadPaddingException | 
          InvalidKeyException ex) {
            throw new RuntimeException(ex);
        }
    }
}