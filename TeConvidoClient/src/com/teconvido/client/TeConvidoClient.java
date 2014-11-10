/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teconvido.client;

import com.google.gson.reflect.TypeToken;
import com.teconvido.bd.modelo.Car;
import com.teconvido.bd.modelo.TicketLogin;
import com.teconvido.bd.modelo.User;
import com.teconvido.common.TeConvidoConstantDB;
import com.teconvido.common.TypeNotificationPush;
import com.teconvido.common.TypeServiceServer;
import com.utilities.communication.socket.safesocket.gson.SafeClientSocket;
import com.utilities.gson.GsonS;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * TeConvidoClient
 * @author Alex
 */
public class TeConvidoClient implements TeConvidoConstantDB{
    private String host;
    private int port;
    
    private String email;
    private String password;
    private TicketLogin ticket;

    public TeConvidoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
 
/******************************* GET ******************************************/    
    
    public boolean login(String email,String password) throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        socket.send(TypeServiceServer.GET_ELEMENT); //typeService
        socket.send(GetDB.IS_CORRECT_LOGIN); // typeGet
        socket.send(GsonS.getGson().toJson(user)); //parameter
        socket.send(null); //ticket
        
        ticket = GsonS.getGson().fromJson(socket.receive(String.class),
                TicketLogin.class);
        if(ticket != null){
            this.email = email;
            this.password = password;
        }
        return ticket != null;
    }
    
    public List<Car> getCarsUser() throws IOException {
        SafeClientSocket socket = new SafeClientSocket(host,port);

        socket.send(TypeServiceServer.GET_ELEMENT); //typeService
        socket.send(GetDB.GET_CARS_USER); // typeGet
        socket.send(null); //parameter
        socket.send(ticket.getTicket()); //ticket
        
        String jsonS = socket.receive(String.class);
        System.out.println(jsonS);
        List<Car> cars = GsonS.getGson().fromJson(jsonS,
                new TypeToken<List<Car>>(){}.getType());

        return cars;
    }
    
    
    @Deprecated
    public boolean sendNotificationPush(String login,TypeNotificationPush type, 
    String message) throws IOException{
        SafeClientSocket socket = new SafeClientSocket(host,port);
        
        socket.send(TypeServiceServer.NOTIFICATION_PUSH);
        socket.send(login);
        socket.send(type);
        socket.send(message);
        
        return GsonS.getGson().fromJson(socket.receive(String.class), 
                Boolean.class);
    }
    
    
    public static void main(String[] argv) throws IOException{
        TeConvidoClient client = new TeConvidoClient("155.210.68.155",20000);
        //System.out.println(client.sendNotificationPush("xperiaJ", TypeNotificationPush.STANDARD, "Pato"));
        boolean returnL = client.login("alexsilva792@gmail.com", "65111992");
        
        for( Car car :client.getCarsUser()){
            System.out.println(returnL + " " + car.getBrand() +" "+ car.getModel());
        }
        
    }
}
