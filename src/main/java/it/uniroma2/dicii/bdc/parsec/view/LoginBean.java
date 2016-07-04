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


    public boolean validate() {

        if (userId == null || password == null)
            return false;

        LoginController controller = LoginController.getInstance();
        User user = controller.login(userId, password);

        if (user == null)
            return false;

        isLogged = true;
        isAdministrator = user.isAdministrator();

        return true;
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