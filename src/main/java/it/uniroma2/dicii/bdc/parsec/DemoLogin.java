package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;

public class DemoLogin {

    public static void main(String args[]) {

        User user0 = new User("Zanna94", "password");
        user0.setFirstname("Emanuele");
        user0.setLastname("Vannacci");
        user0.setAdministrator(true);


        User user1 = new User("Triv", "pass");
        user1.setFirstname("Laura");
        user1.setLastname("Trivelloni");
        user1.setAdministrator(true);


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