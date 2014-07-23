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
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TeConvidoConstantDB.GetDB;
import com.teconvido.common.TeConvidoConstantDB.InsertDB;
import com.teconvido.common.TeConvidoConstantDB.RemoveDB;
import com.teconvido.common.TeConvidoConstantDB.UpdateDB;
import com.utilities.communication.postrequest.PostParameter;
import com.utilities.communication.postrequest.PostRequest;
import com.utilities.communication.postrequest.PostResponse;
import com.utilities.cryptor.CryptorException;
import com.utilities.xml.configdb.MangerConfigDB;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerRequestDB implements TeConvidoConstantDB{
    
    private MangerConfigDB preferences;
    
    private static final String PATH_DB = "db/";
    private static final String PATH_GET = "get/";
    private static final String PATH_INSERT = "insert/";
    private static final String PATH_UPDATE = "update/";
    private static final String PATH_REMOVE = "remove/";
    
    private static final String TAG_SEARCH = "search=";
    private static final String TAG_ELEMENT = "element=";
    
    public ManagerRequestDB(String configPath) throws JAXBException, 
    IOException, CryptorException{
        preferences = new MangerConfigDB(configPath);
    }
    
    public ManagerRequestDB() throws JAXBException, IOException, CryptorException,
    ClassNotFoundException{
        this(MangerConfigDB.DEFAULT_DIRECTORY);
    }

    public synchronized String get(GetDB typeGet,String search)
    {
        if(typeGet != null){
            URL url;
            try {
                url = new URL("http","localhost","/db/get/is_correct_login.php");
                PostParameter parameter = new PostParameter();
                parameter.setUrl(url);
                parameter.setParameter(TAG_SEARCH + search);
                PostResponse response;
                response = PostRequest.send(parameter);
                return response.getResponse();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                return null;
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeGet + "\"");
        } 
    }
   
    public synchronized String insert(InsertDB typeInsert,String element) {
        if(typeInsert != null){
            return null;//inserts.get(typeInsert).insert(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeInsert + "\"");
        } 
    }
    
    public synchronized String update(UpdateDB typeUpdate, String search,
    String element){
        if(typeUpdate != null){
            return null;//updates.get(typeUpdate).update(search,element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeUpdate + "\"");
        } 
    }
    
    public synchronized String remove(RemoveDB typeRemove, String element){
        if(typeRemove != null){
            return null;//removes.get(typeRemove).remove(element);
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" +  typeRemove + "\"");
        }
    }
    
/********************* GETS ***************************************************/
    
//    private class IsCorrectLogin implements GetElementDB<String,String>{
//
//        @Override
//        public String get(String search) {
//            try {
//                PostParameter parameter = new PostParameter();
//                parameter.setUrl("http://localhost/db/get/login.php");
//                parameter.setParameter("json=" + search);
//                PostResponse response = PostRequest.send(parameter);
//                return response.getResponse();
//            } catch (IOException ex) {
//                return null;
//            }
//        }
//    }
    
//    private class GetGcmId implements GetElementDB<String,String>{
//         private static final String SQL = "SELECT u.gcmCode FROM User u "
//                + "WHERE u.login = ?";
//
//        @Override
//        public String get(String search) {
//            String login =  search;
//            String gcmCode = null;
//            try {
//                PreparedStatement ps = null;
//
//                crearConexionBD();
//
//                ps = connectionBD.prepareStatement(SQL);
//                ps.setString(1, login);
//
//                ResultSet rs = ps.executeQuery();
//                if(rs.next()){
//                    gcmCode = rs.getString(1);
//                }
//
//                ps.close();
//                connectionBD.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } finally{
//                return gcmCode;
//            }
//        }
//        
//    }
//    
///********************* INSERTS ************************************************/
//    private class InsertUser implements InsertElementDB<String,String>{
//
//        private static final String SQL = 
//            "INSERT INTO User " +
//            "(login, email, password) " + 
//            "VALUES (?,?,sha1(?))";
//        
//        @Override
//        public String insert(String element) {
//            User user = GsonS.getGson().fromJson(element,User.class);
//            try {
//                PreparedStatement ps = null;
//
//                crearConexionBD();
//                
//                ps = connectionBD.prepareStatement(SQL);
//                ps.setString(1, user.getLogin());
//                ps.setString(2, user.getEmail());
//                ps.setString(3, user.getPassword());
//
//                ps.executeUpdate();
//
//                ps.close();
//
//                connectionBD.commit();
//                connectionBD.close();
//
//                return GsonS.getGson().toJson(true);
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                return GsonS.getGson().toJson(false);
//            }
//        }
//    }
//    
///********************* UPDATES ************************************************/
//    
//    private class UpdateGcmCode 
//    implements UpdateElementDB<String,String,String>{
//
//        private static final String SQL = 
//                "UPDATE User SET gcmCode = ? " + 
//                "WHERE email = ?";
//        
//        @Override
//        public String update(String busqueda, String elemento) {
//            boolean actualizado = false;
//            
//            String email = busqueda;
//            String gcmCode = elemento;
//            try{
//                crearConexionBD();
//                
//                PreparedStatement ps = connectionBD.prepareStatement(SQL);
//                ps.setString(1, gcmCode);
//                ps.setString(2, email);
//                
//                ps.executeUpdate();
//                
//                ps.close();
//                connectionBD.commit();
//                connectionBD.close();
//                
//                actualizado = true;
//            }catch(SQLException ex){
//                ex.printStackTrace();
//            }finally{
//                return GsonS.getGson().toJson(actualizado);
//            }
//        }
//    }
}
