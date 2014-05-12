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
package com.taskserver.db;

import com.taskserver.db.exception.UnimplementedQueryException;
import com.utilities.cryptor.CryptorException;
import com.utilities.jaxb.config.bd.MangerConfigDB;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;


/**
 * AbstractManagerBD.java
 * @author Alejandro Silva
 */
public abstract class AbstractManagerDB<G,I,U,R> {

    protected java.sql.Connection connectionBD;
    private MangerConfigDB preferences;
    protected Map<G,GetElementDB> gets;
    protected Map<I,InsertElementDB> inserts;
    protected Map<U,UpdateElementDB> updates;
    protected Map<R,RemoveElementDB> removes;
    
    public AbstractManagerDB(String configRoute) throws JAXBException, 
    IOException, CryptorException, ClassNotFoundException {
        preferences = new MangerConfigDB(configRoute);

        Class.forName(preferences.getDriver());

        gets = new HashMap<>();
        initializeGets();
        
        inserts = new HashMap<>();  
        initializeInserts();
        
        updates = new HashMap<>();  
        initializeUpdates();
        
        removes = new HashMap<>();
        initializeRemoves();
    }
    
    public AbstractManagerDB() throws JAXBException, IOException, 
    ClassNotFoundException, CryptorException{
        this(MangerConfigDB.DEFAULT_DIRECTORY);
    }
    
    protected void crearConexionBD() throws SQLException{
        connectionBD = DriverManager.getConnection(
                    preferences.getJdbc(),
                    preferences.getUser(),
                    preferences.getPassword());
        connectionBD.setAutoCommit(false);
    }
    
    protected abstract void initializeGets();
    
    protected abstract void initializeInserts();
    
    protected abstract void initializeUpdates();
    
    protected abstract void initializeRemoves();
    
/******************************* GET ******************************************/
    
    public synchronized Object get(G typeGet,Object search) 
    throws UnimplementedQueryException{
        if(typeGet != null && gets.containsKey(typeGet)){
            return gets.get(typeGet).get(search);
        } else {
            throw new UnimplementedQueryException("No esta implementada "
                    + "la opcion \"" + typeGet + "\"");
        } 
    }
    
/******************************* INSERT ***************************************/
   
    public synchronized Object insert(I typeInsert,Object element) 
    throws UnimplementedQueryException{
        if(typeInsert != null && inserts.containsKey(typeInsert)){
            return inserts.get(typeInsert).insert(element);
        } else {
            throw new UnimplementedQueryException("No esta implementada "
                    + "la opcion \"" + typeInsert + "\"");
        } 
    }
    
/******************************* UPDATE ***************************************/
    
    public synchronized Object update(U typeUpdate, Object search,
    Object element) throws UnimplementedQueryException{
        if(typeUpdate != null && updates.containsKey(typeUpdate)){
            return updates.get(typeUpdate).update(search,element);
        } else {
            throw new UnimplementedQueryException("No esta implementada "
                    + "la opcion \"" + typeUpdate + "\"");
        } 
    }
    
/******************************* REMOVE ***************************************/
    
    public synchronized Object remove(R typeRemove, Object element) 
    throws UnimplementedQueryException{
        if(typeRemove != null && removes.containsKey(typeRemove)){
            return removes.get(typeRemove).remove(element);
        } else {
            throw new UnimplementedQueryException("No esta implementada "
                    + "la opcion \"" +  typeRemove + "\"");
        }
    }
}
