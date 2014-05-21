/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teconvido.bd.modelo;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class User implements Serializable {
    private String login;
    private String email;
    private String password;
    private String gcmCode;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGcmCode() {
        return gcmCode;
    }

    public void setGcmCode(String gcmCode) {
        this.gcmCode = gcmCode;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.login == null && other.login != null) || 
            (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.teconvido.bd.modelo.User[ login=" + login + " ]";
    }
    
}
