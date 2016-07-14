package it.uniroma2.dicii.bdc.parsec.controller;


import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.RegistrationForm;

import javax.persistence.NoResultException;

/**
 * Class controller offers services for Login/in and Registration of a new user
 */
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

            // find User in database to check if it's registered
            return UserDAO.findBy(userId, password);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;    // the user is not registered
        }

    }

    /**
     * Save a new user instance in the System
     *
     * @param form {@link RegistrationForm}
     * @return The new User. Null if the {@link User#userId} is not valid
     */
    public User register(RegistrationForm form) {


        // Create a new user instance
        User user = new User();
        user.setFirstname(form.getFirstname());
        user.setLastname(form.getLastname());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setUserId(form.getUserId());

        // set the user as an administrator
        if (form.isAdministrator()) {
            user.setAdministrator(true);
        }

        // store in db
        try {
            UserDAO.store(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;

    }

}

