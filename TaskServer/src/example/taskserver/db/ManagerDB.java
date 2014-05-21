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
package example.taskserver.db;

import com.taskserver.db.GetElementDB;
import com.taskserver.db.InsertElementDB;
import com.taskserver.db.RemoveElementDB;
import com.taskserver.db.UpdateElementDB;
import example.taskserver.common.ConstantDB.GetDB;
import example.taskserver.common.ConstantDB.InsertDB;
import example.taskserver.common.ConstantDB.RemoveDB;
import example.taskserver.common.ConstantDB.UpdateDB;
import com.utilities.cryptor.CryptorException;
import com.utilities.xml.configdb.MangerConfigDB;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerDB {
    
    protected java.sql.Connection connectionBD;
    private MangerConfigDB preferences;
    protected Map<GetDB,GetElementDB> gets;
    protected Map<InsertDB,InsertElementDB> inserts;
    protected Map<UpdateDB,UpdateElementDB> updates;
    protected Map<RemoveDB,RemoveElementDB> removes;
    
    public ManagerDB(String configRoute) throws JAXBException, IOException, 
    CryptorException, ClassNotFoundException{
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
    
    public ManagerDB() throws JAXBException, IOException, CryptorException,
    ClassNotFoundException{
        this(MangerConfigDB.DEFAULT_DIRECTORY);
    }

    private void initializeGets() {

    }

    private void initializeInserts() {

    }

    private void initializeUpdates() {

    }

    private void initializeRemoves() {

    }
    
    /******************************* GET ******************************************/
    
    public synchronized Object get(GetDB typeGet,Object search)
    {
        if(typeGet != null && gets.containsKey(typeGet)){
            return gets.get(typeGet).get(search);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeGet + "\"");
        } 
    }
    
    

    /******************************* INSERT ***************************************/
   
    public synchronized Object insert(InsertDB typeInsert,Object element) {
        if(typeInsert != null && inserts.containsKey(typeInsert)){
            return inserts.get(typeInsert).insert(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeInsert + "\"");
        } 
    }
    
/******************************* UPDATE ***************************************/
    
    public synchronized Object update(UpdateDB typeUpdate, Object search,
    Object element){
        if(typeUpdate != null && updates.containsKey(typeUpdate)){
            return updates.get(typeUpdate).update(search,element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeUpdate + "\"");
        } 
    }
    
/******************************* REMOVE ***************************************/
    
    public synchronized Object remove(RemoveDB typeRemove, Object element){
        if(typeRemove != null && removes.containsKey(typeRemove)){
            return removes.get(typeRemove).remove(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" +  typeRemove + "\"");
        }
    }
    
}
