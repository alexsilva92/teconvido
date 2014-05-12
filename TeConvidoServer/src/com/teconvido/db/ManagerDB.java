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

import com.taskserver.db.AbstractManagerDB;
import com.taskserver.db.GetElementDB;
import com.taskserver.db.InsertElementDB;
import com.taskserver.db.UpdateElementDB;
import com.teconvido.bd.modelo.User;
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TeConvidoConstantDB.GetDB;
import com.teconvido.common.TeConvidoConstantDB.InsertDB;
import com.teconvido.common.TeConvidoConstantDB.RemoveDB;
import com.teconvido.common.TeConvidoConstantDB.UpdateDB;
import com.utilities.cryptor.CryptorException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBException;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerDB extends AbstractManagerDB<GetDB,InsertDB,UpdateDB,
RemoveDB> implements TeConvidoConstantDB{
    
    public ManagerDB(String rutaConfig) throws JAXBException, IOException, 
    CryptorException, ClassNotFoundException{
        super(rutaConfig);
    }
    
    public ManagerDB() throws JAXBException, IOException, CryptorException,
    ClassNotFoundException{
        super();
    }

    @Override
    protected void initializeGets() {
        gets.put(GetDB.IS_CORRECT_LOGIN, new IsCorrectLogin());
        gets.put(GetDB.GET_GCM_ID, new GetGcmId());
    }

    @Override
    protected void initializeInserts() {
        inserts.put(InsertDB.INSERT_USER, new InsertUser());
    }

    @Override
    protected void initializeUpdates() {
        updates.put(UpdateDB.UPDATE_GCM_CODE, new UpdateGcmCode());
    }

    @Override
    protected void initializeRemoves() {

    }
    
    /********************* GETS ***********************************************/
    
    private class IsCorrectLogin implements GetElementDB{

        private static final String SQL = "SELECT * FROM User u "
                + "WHERE u.email = ? AND u.password = sha1(?)";

        @Override
        public Object get(Object search) {
            User user = (User) search;
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
                return login;
            }
        }
    }
    
    private class GetGcmId implements GetElementDB{
         private static final String SQL = "SELECT u.gcmCode FROM User u "
                + "WHERE u.login = ?";

        @Override
        public Object get(Object search) {
            String login = (String) search;
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
    
    /********************* INSERTS ********************************************/
    private class InsertUser implements InsertElementDB{

        private static final String SQL = 
            "INSERT INTO User " +
            "(login, email, password) " + 
            "VALUES (?,?,sha1(?))";
        
        @Override
        public Object insert(Object element) {
            User user = (User) element;
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

                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
    
    /********************* UPDATES ********************************************/
    
    private class UpdateGcmCode implements UpdateElementDB{

        private static final String SQL = 
                "UPDATE User SET gcmCode = ? " + 
                "WHERE email = ?";
        
        @Override
        public Object update(Object busqueda, Object elemento) {
            boolean actualizado = false;
            
            String email = (String) busqueda;
            String gcmCode = (String) elemento;
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
                return actualizado;
            }
        }
    }
}
