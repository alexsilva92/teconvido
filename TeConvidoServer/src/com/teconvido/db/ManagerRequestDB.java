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

import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TeConvidoConstantDB.GetDB;
import com.teconvido.common.TeConvidoConstantDB.InsertDB;
import com.teconvido.common.TeConvidoConstantDB.RemoveDB;
import com.teconvido.common.TeConvidoConstantDB.UpdateDB;
import com.utilities.communication.postrequest.PostParameter;
import com.utilities.communication.postrequest.PostRequest;
import com.utilities.communication.postrequest.PostResponse;
import com.utilities.gson.GsonS;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ManagerDB.java
 * @author Alejandro Silva
 */
public class ManagerRequestDB implements TeConvidoConstantDB{
    
    
    private static final String PATH_ROOT = "/";
    private static final String PATH_CONTROLLERS = "controllers/";
    private static final String PATH_DB = "database/";
    private static final String PATH_GET = "get/";
    private static final String PATH_INSERT = "insert/";
    private static final String PATH_UPDATE = "update/";
    private static final String PATH_REMOVE = "remove/";
    
    private static final String TAG_HTTP = "http";
    private static final String TAG_SEARCH = "search=";
    private static final String TAG_ELEMENT = "element=";
    
    private static final String EXT_PHP = ".php";
    
    private String ip;
    
    public ManagerRequestDB(String ip) {
        this.ip = ip;
    }
    
    public synchronized String get(GetDB typeGet,String search,String ticket) 
    throws IOException {
        URL url;

        if(typeGet != null){
            try {
                String get = typeGet.name().toLowerCase() + EXT_PHP;
                url = new URL(TAG_HTTP,ip,PATH_ROOT + PATH_CONTROLLERS +
                        PATH_DB + PATH_GET + get );
                
                PostParameter parameter = new PostParameter();
                parameter.setUrl(url);
                parameter.setParameter(TAG_SEARCH + search);
                if(ticket != null){
                    parameter.setAuthorization(GsonS.getGson().toJson(ticket,
                            String.class));
                }
                
                PostResponse response;
                response = PostRequest.send(parameter);
                System.out.println("Code: " + response.getCode());
                return response.getResponse();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
            
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeGet + "\"");
        } 
    }
   
    public synchronized String insert(InsertDB typeInsert,String element,
    String ticket) throws IOException {
        if(typeInsert != null){
            URL url;
            try {
                String insert = typeInsert.name().toLowerCase() + EXT_PHP;
                url = new URL(TAG_HTTP,ip,PATH_ROOT + PATH_CONTROLLERS +
                        PATH_DB + PATH_INSERT + insert );
                
                PostParameter parameter = new PostParameter();
                parameter.setUrl(url);
                parameter.setParameter(TAG_ELEMENT + element);
                
                if(ticket != null) {
                    parameter.setAuthorization(GsonS.getGson().toJson(ticket,
                            String.class));
                }
                
                PostResponse response;
                response = PostRequest.send(parameter);
                return response.getResponse();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeInsert + "\"");
        } 
    }
    
    public synchronized String update(UpdateDB typeUpdate, String search,
    String element,String ticket) throws IOException {
        if(typeUpdate != null){
            URL url;
            try {
                String update = typeUpdate.name().toLowerCase() + EXT_PHP;
                url = new URL(TAG_HTTP,ip,PATH_ROOT + PATH_CONTROLLERS +
                        PATH_DB + PATH_UPDATE + update );
                
                PostParameter parameter = new PostParameter();
                parameter.setUrl(url);
                parameter.setParameter(TAG_SEARCH + search);
                parameter.setParameter(TAG_ELEMENT + element);
                
                if(ticket != null) {
                    parameter.setAuthorization(GsonS.getGson().toJson(ticket,
                            String.class));
                }
                  
                PostResponse response;
                response = PostRequest.send(parameter);
                return response.getResponse();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeUpdate + "\"");
        } 
    }
    
    public synchronized String remove(RemoveDB typeRemove, String search,
    String ticket) throws IOException{
        URL url;

        if(typeRemove != null){
            try {
                String remove = typeRemove.name().toLowerCase() + EXT_PHP;
                url = new URL(TAG_HTTP,ip,PATH_ROOT + PATH_CONTROLLERS +
                        PATH_DB + PATH_REMOVE + remove );
                
                PostParameter parameter = new PostParameter();
                parameter.setUrl(url);
                parameter.setParameter(TAG_SEARCH + search);
                if(ticket != null){
                    parameter.setAuthorization(GsonS.getGson().toJson(ticket,
                            String.class));
                }
                
                PostResponse response;
                response = PostRequest.send(parameter);
                System.out.println("Code: " + response.getCode());
                return response.getResponse();
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
            
        } else {
            throw new RuntimeException("No esta implementada "
                    + "la opcion \"" + typeRemove + "\"");
        } 
    }
}
