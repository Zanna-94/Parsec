package it.uniroma2.dicii.bdc.parsec.view;

import it.uniroma2.dicii.bdc.parsec.controller.LoginController;
import it.uniroma2.dicii.bdc.parsec.model.User;

public class LoginBean {

    private String userId;

    private String password;

    /**
     * Indicates if an user has administration permission
     */
    private boolean isAdministrator = false;

    /**
     * Indicates if an User is logged on
     */
    private boolean isLogged = false;

    /**
     * syntactic checks for loggin
     *
     * @return true if procedure ends successful and user exists in the System
     */
    public boolean validate() {

        if (userId == null || password == null)
            return false;

        LoginController controller = LoginController.getInstance(); // get controller
        User user = controller.login(userId, password); // login return user instance

        if (user == null) // if user is null the entity is not in db
            return false;

        isLogged = true; // loggin
        isAdministrator = user.isAdministrator(); // Check if user has administrator permission

        return true;
    }

    public void logout() {
        isLogged = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }
}