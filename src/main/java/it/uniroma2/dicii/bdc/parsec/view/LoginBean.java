package it.uniroma2.dicii.bdc.parsec.view;

import java.util.*;

/**
 *
 */
public class LoginBean {

    private String username;

    private String password;

    /**
     * Indicates if an user has administration permission
     */
    private boolean administrator = false;

    /**
     * Indicates if an User is logged on
     */
    private boolean isLogged = false;


    public boolean validate() {
        isLogged = true;
        // TODO: 30/06/16
        return true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

}