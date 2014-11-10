/*
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
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

package com.utilities.communication.postrequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * PostRequest.java
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */
public class PostRequest {
    
    private static final String REQUEST_METHOD = "POST";
    
    private static final String PROPERTY_LANGUAGE = "Accept-Language";
    private static final String PROPERTY_LENGHT = "Content-Length";
    private static final String PROPERTY_AUTHORIZATION = "Authorization";
    
    private static final String REQUEST_LANGUAGE = "en-US,en;q=0.5";
    
    private static final int RESPONSE_OK = 200;

    
    public static PostResponse send(PostParameter parameter) 
    throws MalformedURLException, IOException {

        HttpURLConnection con = (HttpURLConnection) 
                parameter.getUrl().openConnection();

        con.setRequestMethod(REQUEST_METHOD);
        con.setRequestProperty(PROPERTY_LANGUAGE, REQUEST_LANGUAGE);
        if(parameter.getAuthorization() != null){
            con.setRequestProperty(PROPERTY_AUTHORIZATION, 
                    parameter.getAuthorization());
        }
        con.setDoOutput(true);

        if(parameter.getParameter() != null){
            con.setRequestProperty(PROPERTY_LENGHT, Integer.toString(
                    parameter.getParameter().getBytes().length));
            send(con,parameter);
        }
        
        return receive(con);      
    }
    
    private static void send(HttpURLConnection con,PostParameter parameter) 
    throws IOException{
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameter.getParameter());
        wr.flush();
        wr.close();
    }
    
    private static PostResponse receive(HttpURLConnection con) 
    throws IOException{
        if(con.getResponseCode() == RESPONSE_OK){
         
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

            StringBuilder response = new StringBuilder();

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            return new PostResponse(response.toString(),con.getResponseCode());
        }else{
            return new PostResponse(null,con.getResponseCode());
        }
    }
    
//    public static void main(String[] argv) throws IOException{
//        String parameter_s = "origin=TERUEL&destination=MORA DE RUBIELOS&deviation=10";
//        URL url = new URL("http","localhost","/controllers/googleapi/google_request.php");
//        PostParameter parameter = new PostParameter();
//        parameter.setUrl(url);
//        parameter.setParameter(parameter_s);
//        PostResponse response;
//        try {
//            response = PostRequest.send(parameter);
//            String json = response.getResponse();
//            System.out.println(json);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}
