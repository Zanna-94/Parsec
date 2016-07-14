package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.LoginController;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.RegistrationForm;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.NoResultException;

public class REQFN_2_RegistrationTest {
    /**
     * REQ-FN-2: Register new user with name, surname, email, password, userId
     * as administrator or registered user
     */
    @Test
    public void userRegistration() {

        LoginController controller = LoginController.getInstance();

        RegistrationForm form = new RegistrationForm();
        form.setUserId("TestTest1");
        form.setPassword("test");
        form.setFirstname("firstname");
        form.setLastname("lastname");
        form.setEmail("test@test.it");


        boolean alreadyExist = form.validate();


        boolean found = false;

        try {

            UserDAO.findBy(form.getUserId());
            found = true;

        } catch (NoResultException e) {
            e.printStackTrace();
            found = false;
        }

        System.out.print(alreadyExist);
        System.out.print(found);
        Assert.assertTrue((alreadyExist && found) || (!alreadyExist && found));

    }
}
