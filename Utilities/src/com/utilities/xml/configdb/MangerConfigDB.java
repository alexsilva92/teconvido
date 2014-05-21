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

package com.utilities.xml.configdb;

import com.utilities.cryptor.CryptorException;
import com.utilities.cryptor.CryptorTDES;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * MangerConfigDB.java
 * @author Alejandro Silva
 */
public class MangerConfigDB {

    public static final String DEFAULT_DIRECTORY = "resource/xml/configDB.xml";

    private ConfigDB config;
    private Marshaller marshaller;
    private Unmarshaller umMarshaller;
    
    private CryptorTDES cryptor;

    private File xmlFile;

    public MangerConfigDB(String directory) throws JAXBException, IOException,
            CryptorException{ 
                
        cryptor = new CryptorTDES();
        
        JAXBContext context = JAXBContext.newInstance(ConfigDB.class);

        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");

        umMarshaller = context.createUnmarshaller();

        xmlFile = new File(directory);

        if(!xmlFile.exists()){
            config = new ConfigDB();
            save();

        }else{
            config = (ConfigDB) umMarshaller.unmarshal(new FileReader(xmlFile));
        }  
    }

    public MangerConfigDB() throws JAXBException, IOException, 
    CryptorException{
        this(DEFAULT_DIRECTORY);
    } 
    
    
    public String getApplication() {
        return config.getApplication();
    }
    
    public String getVersion() {
        return config.getVersion();
    }

    public String getAuthor() {
        return config.getAuthor();
    }

    public String getDriver() {
        return config.getDriver();
    }
    
    public String getPassword(){
        return cryptor.decrypt(config.getPassword());
    }
    
    public String getUser(){
        return config.getUser();
    }
    
    public String getJdbc(){
        return config.getJdbc();
    }
    
    public void setPassword(String password){
        try {
            config.setPassword(cryptor.encrypt(password));
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setApplication(String application){
        try {
            config.setApplication(application); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setUser(String user){
        try {
            config.setUser(user); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setJdbc(String jdbc){
        try {
            config.setJdbc(jdbc); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setVersion(String version){
        try {
            config.setVersion(version); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setAuthor(String author){
        try {
            config.setAuthor(author); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void setDriver(String driver){
        try {
            config.setDriver(driver); 
            save();
        } catch (IOException | JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ConfigDB getConfig(){
        return config;
    }
    
    public final void save() throws IOException, JAXBException{
       try (Writer w = new FileWriter(xmlFile)) {
           marshaller.marshal(config, w);
       }
    }
    

    public static void main(String[] argv) throws JAXBException, IOException, 
    CryptorException {
       MangerConfigDB config = new MangerConfigDB("configDB.xml");
       config.setApplication("TeConvido");
       config.setAuthor("Alejandro Silva");
       config.setJdbc("jdbc:mysql://155.210.68.155:3306/teconvido");
       config.setDriver("com.mysql.jdbc.Driver");
       config.setUser("admin");
       config.setPassword("admin");
       config.setVersion("1.0");
    }
}
