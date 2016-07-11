package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.LoginController;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.RegistrationForm;
import org.junit.Test;


public class REQFN_1_LoginTest {

    /**
     * REQ-FN-1: Registered user access with user id and password
     */
    @Test
    public void userLogin() {

        LoginController controller = LoginController.getInstance();

        User user0 = controller.login("Zanna", "password");

        User user1 = UserDAO.findBy("Zanna");

        assert ((user0 == null && user1 == null) || (user1 == user0));

    }
}
