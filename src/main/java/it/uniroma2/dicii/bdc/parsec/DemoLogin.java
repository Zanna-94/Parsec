package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.Administrator;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;

public class DemoLogin {

    public static void main(String args[]) {

        User user0 = new User("Zanna", "password");
        user0.setFirstname("Emanuele");
        user0.setLastname("Vannacci");

        Administrator administrator1 = new Administrator();
        user0.setRole(administrator1);

        User user1 = new User("Triv", "pass");
        user1.setFirstname("Laura");
        user1.setLastname("Trivelloni");

        Administrator administrator2 = new Administrator();
        user1.setRole(administrator2);

        try {
            UserDAO.store(user0);
            UserDAO.store(user1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);

    }
}