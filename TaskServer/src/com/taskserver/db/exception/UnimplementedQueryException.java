/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taskserver.db.exception;

/**
 *
 * @author Alex
 */
public class UnimplementedQueryException extends Exception{
    
    private String message;
    
    public UnimplementedQueryException(String message){
        super();
        this.message = message;
    }
    
    @Override
    public String toString(){
        return message;
    }
}
