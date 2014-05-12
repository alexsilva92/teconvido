/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teconvido.client;

import com.teconvido.bd.modelo.User;
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TypeNotificationPush;
import com.teconvido.common.TypeServiceServer;
import com.utilities.safesocket.SafeClientSocket;
import java.io.IOException;

/**
 *
 * @author Alex
 */
public class TeConvidoClient implements TeConvidoConstantDB{
    private String host;
    private int port;

    public TeConvidoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public boolean login(String email,String password) throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        
        socket.send(TypeServiceServer.GET_ELEMENT);
        socket.send(GetDB.IS_CORRECT_LOGIN);
        socket.send(user);
        
        return(boolean) socket.receive();  
    }
    
    public boolean insertClient(User user) throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        socket.send(TypeServiceServer.INSERT_ELEMENT);
        socket.send(InsertDB.INSERT_USER);
        socket.send(user);
        
        return(boolean) socket.receive();
    }
    
    public boolean updateGcmCode(String email, String gcmCode) 
    throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        socket.send(TypeServiceServer.UPDATE_ELEMENT);
        socket.send(UpdateDB.UPDATE_GCM_CODE);
        socket.send(email);
        socket.send(gcmCode);
        
        return(boolean) socket.receive();
    }
    
    public boolean sendNotificationPush(String login,TypeNotificationPush type, 
    String message) throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        socket.send(TypeServiceServer.NOTIFICATION_PUSH);
        socket.send(login);
        socket.send(type);
        socket.send(message);
        
        return(boolean) socket.receive();  
    }
    
    
    public static void main(String[] argv) throws IOException{
        TeConvidoClient client = new TeConvidoClient("localhost",20000);
        System.out.println(client.sendNotificationPush("xperiaS", TypeNotificationPush.STANDARD, "Pato"));
    }
}
