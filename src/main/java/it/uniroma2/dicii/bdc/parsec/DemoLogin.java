package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;

public class DemoLogin {

    public static void main(String args[]) {

        User user0 = new User("Zanna", "password");
        user0.setFirstname("Emanuele");
        user0.setLastname("Vannacci");

        User user1 = new User("Triv", "pass");
        user0.setFirstname("Laura");
        user0.setLastname("Trivelloni");

        UserDAO.store(user0);
        UserDAO.store(user1);

        JPAInitializer.getEntityManager();
    }
}