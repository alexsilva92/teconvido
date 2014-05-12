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

package com.teconvido.communication.googlepush;

import com.teconvido.common.TypeNotificationPush;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * NotificationPush.java
 * @author Alejandro Silva
 * @author Jul�an Guill�n
 */
public class NotificationPush {

    private static final String GCM_URL = 
            "https://android.googleapis.com/gcm/send";
    private static final String API_KEY = 
            "AIzaSyAGrDQ_kuUIaAti5dXsqPtDJ8QsgFvieW4";
    
    private static final int NOTIFICATION_SEND = 200;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int SERVER_TEMPORARILY_UNAVAILABLE = 503;
    private static final int API_KEY_INVALID = 401;
    
    public enum ReturnNotificationPush{NOTIFICATION_SEND,INTERNAL_SERVER_ERROR,
    SERVER_TEMPORARILY_UNAVAILABLE,API_KEY_INVALID,UNKNOWN_ERROR}
    
    
    public static ReturnNotificationPush send(String gcmCode,
    TypeNotificationPush type, String message) throws IOException {        
            String msg = "registration_id=" + gcmCode +
                         "&collapse_key=" + new Date().toString() +
                         "&data.type=" + type.name() +
                         "&data.msg=" + message;

            URL url = new URL(GCM_URL);
            HttpURLConnection uc = (HttpURLConnection)url.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Content-Length",
                    Integer.toString(msg.getBytes().length));
            uc.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded"); 
            uc.setRequestProperty("Authorization", "key=" + API_KEY);
            uc.connect();
            
            //enviar el request
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());
            out.writeBytes(msg);
            out.flush();
            out.close();
            
            //recoger el response
            uc.getErrorStream();
            int responseCode = uc.getResponseCode();
           
            uc.disconnect();
            
            return changeEnum(responseCode);
    }
    
    private static ReturnNotificationPush changeEnum(int responseCode){
        switch(responseCode){
            case NOTIFICATION_SEND:
                return ReturnNotificationPush.NOTIFICATION_SEND;
            case INTERNAL_SERVER_ERROR:
                return ReturnNotificationPush.INTERNAL_SERVER_ERROR;
            case SERVER_TEMPORARILY_UNAVAILABLE:
                return ReturnNotificationPush.SERVER_TEMPORARILY_UNAVAILABLE;
            case API_KEY_INVALID:
                return ReturnNotificationPush.API_KEY_INVALID;
            default:
                return ReturnNotificationPush.UNKNOWN_ERROR;
        }
    }
    
    public static void main(String[] argv){
        try {
            System.out.println(NotificationPush.send("APA91bE0G5oiSFUD6Gah2QR5UgSXQuC2EJrLDYeFIXseq91oxk30ykK2ogYx6Mpoj837y-l1DztyT6BDs5cj_RgYZRSjRkOgZ0kNR2OxC5DNIzrYxs08qRaZjkmqXDSaLy8QfDl4-ViA9DQlngai2jSBx9X49_-k6Q", 
                    TypeNotificationPush.STANDARD, "Pato"));
        } catch (IOException ex) {
            Logger.getLogger(NotificationPush.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

