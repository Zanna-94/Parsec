package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;

import javax.persistence.NoResultException;

public class LoginController {

    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    }

    /**
     * Load the {@link User} matching username and password from database
     *
     * @param username username
     * @param password password
     * @return {@link User}
     */
    public User login(String username, String password) {

        try {

            return UserDAO.findBy(username, password);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }
}

