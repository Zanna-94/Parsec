package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.RegistrationForm;

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
     * @param userId   username
     * @param password password
     * @return {@link User}
     */
    public User login(String userId, String password) {

        try {

            return UserDAO.findBy(userId, password);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param form {@link RegistrationForm}
     */
    public User register(RegistrationForm form) {

        // The user-Id is already used in the System
        if (UserDAO.findBy(form.getUserId()) != null)
            return null;

        User user = new User();
        user.setFirstname(form.getFirstname());
        user.setLastname(form.getLastname());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setUserId(form.getUserId());

        // set the user a an administrator
        if (form.isAdministrator()) {
            user.setAdministrator(true);
        }

        UserDAO.store(user);

        return user;

    }

}

