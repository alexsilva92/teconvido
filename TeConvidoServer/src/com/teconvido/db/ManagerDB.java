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
package com.teconvido.db;

import com.taskserver.db.GetElementDB;
import com.taskserver.db.InsertElementDB;
import com.taskserver.db.RemoveElementDB;
import com.taskserver.db.UpdateElementDB;
import com.teconvido.bd.modelo.User;
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TeConvidoConstantDB.GetDB;
import com.teconvido.common.TeConvidoConstantDB.InsertDB;
import com.teconvido.common.TeConvidoConstantDB.RemoveDB;
import com.teconvido.common.TeConvidoConstantDB.UpdateDB;
import com.utilities.cryptor.CryptorException;
import com.utilities.gson.GsonS;
import com.utilities.xml.configdb.MangerConfigDB;
import example.taskserver.common.ConstantDB;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerDB implements TeConvidoConstantDB{
    
    protected java.sql.Connection connectionBD;
    private MangerConfigDB preferences;
    protected Map<GetDB,GetElementDB<String,String>> gets;
    protected Map<InsertDB,InsertElementDB<String,String>> inserts;
    protected Map<UpdateDB,UpdateElementDB<String,String,String>> updates;
    protected Map<RemoveDB,RemoveElementDB<String,String>> removes;
    
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
    
    protected void initializeGets() {
        gets.put(GetDB.IS_CORRECT_LOGIN, new IsCorrectLogin());
        gets.put(GetDB.GET_GCM_ID, new GetGcmId());
    }

    protected void initializeInserts() {
        inserts.put(InsertDB.INSERT_USER, new InsertUser());
    }

    protected void initializeUpdates() {
        updates.put(UpdateDB.UPDATE_GCM_CODE, new UpdateGcmCode());
    }

    protected void initializeRemoves() {

    }
    
    protected void crearConexionBD() throws SQLException{
        connectionBD = DriverManager.getConnection(
                    preferences.getJdbc(),
                    preferences.getUser(),
                    preferences.getPassword());
        connectionBD.setAutoCommit(false);
    }
    
    
    public synchronized String get(GetDB typeGet,String search)
    {
        if(typeGet != null && gets.containsKey(typeGet)){
            return gets.get(typeGet).get(search);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeGet + "\"");
        } 
    }
   
    public synchronized String insert(InsertDB typeInsert,String element) {
        if(typeInsert != null && inserts.containsKey(typeInsert)){
            return inserts.get(typeInsert).insert(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeInsert + "\"");
        } 
    }
    
    public synchronized String update(UpdateDB typeUpdate, String search,
    String element){
        if(typeUpdate != null && updates.containsKey(typeUpdate)){
            return updates.get(typeUpdate).update(search,element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeUpdate + "\"");
        } 
    }
    
    public synchronized String remove(RemoveDB typeRemove, String element){
        if(typeRemove != null && removes.containsKey(typeRemove)){
            return removes.get(typeRemove).remove(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" +  typeRemove + "\"");
        }
    }
    
/********************* GETS ***************************************************/
    
    private class IsCorrectLogin implements GetElementDB<String,String>{

        private static final String SQL = "SELECT * FROM User u "
                + "WHERE u.email = ? AND u.password = sha1(?)";

        @Override
        public String get(String search) {
            User user = GsonS.getGson().fromJson(search,User.class);
            boolean login = false;
            try {
                PreparedStatement ps = null;

                crearConexionBD();

                ps = connectionBD.prepareStatement(SQL);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    login = true;
                }

                ps.close();
                connectionBD.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally{
                return GsonS.getGson().toJson(login);
            }
        }
    }
    
    private class GetGcmId implements GetElementDB<String,String>{
         private static final String SQL = "SELECT u.gcmCode FROM User u "
                + "WHERE u.login = ?";

        @Override
        public String get(String search) {
            String login =  search;
            String gcmCode = null;
            try {
                PreparedStatement ps = null;

                crearConexionBD();

                ps = connectionBD.prepareStatement(SQL);
                ps.setString(1, login);

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    gcmCode = rs.getString(1);
                }

                ps.close();
                connectionBD.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally{
                return gcmCode;
            }
        }
        
    }
    
/********************* INSERTS ************************************************/
    private class InsertUser implements InsertElementDB<String,String>{

        private static final String SQL = 
            "INSERT INTO User " +
            "(login, email, password) " + 
            "VALUES (?,?,sha1(?))";
        
        @Override
        public String insert(String element) {
            User user = GsonS.getGson().fromJson(element,User.class);
            try {
                PreparedStatement ps = null;

                crearConexionBD();
                
                ps = connectionBD.prepareStatement(SQL);
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPassword());

                ps.executeUpdate();

                ps.close();

                connectionBD.commit();
                connectionBD.close();

                return GsonS.getGson().toJson(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return GsonS.getGson().toJson(false);
            }
        }
    }
    
/********************* UPDATES ************************************************/
    
    private class UpdateGcmCode 
    implements UpdateElementDB<String,String,String>{

        private static final String SQL = 
                "UPDATE User SET gcmCode = ? " + 
                "WHERE email = ?";
        
        @Override
        public String update(String busqueda, String elemento) {
            boolean actualizado = false;
            
            String email = busqueda;
            String gcmCode = elemento;
            try{
                crearConexionBD();
                
                PreparedStatement ps = connectionBD.prepareStatement(SQL);
                ps.setString(1, gcmCode);
                ps.setString(2, email);
                
                ps.executeUpdate();
                
                ps.close();
                connectionBD.commit();
                connectionBD.close();
                
                actualizado = true;
            }catch(SQLException ex){
                ex.printStackTrace();
            }finally{
                return GsonS.getGson().toJson(actualizado);
            }
        }
    }
}
