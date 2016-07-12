package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.LoginController;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.RegistrationForm;
import org.junit.Test;

public class REQFN_2_RegistrationTest {
    /**
     * REQ-FN-2: Register new user with name, surname, email, password, userId
     * as administrator or registered user
     */
    @Test
    public void userRegistration() {

        LoginController controller = LoginController.getInstance();

        RegistrationForm form = new RegistrationForm();
        form.setUserId("TestTest");
        form.setPassword("test");
        form.setFirstname("firstname");
        form.setLastname("lastname");
        form.setEmail("test@test.it");

        User user0 = controller.register(form);

        User user1 = UserDAO.findBy("TestTest");

        assert (user0 != null && user1 != null && user0 == user1);

    }
}