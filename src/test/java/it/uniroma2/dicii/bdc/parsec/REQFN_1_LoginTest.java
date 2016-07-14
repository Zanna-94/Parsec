package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.controller.LoginController;
import it.uniroma2.dicii.bdc.parsec.model.User;
import it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO;
import it.uniroma2.dicii.bdc.parsec.view.LoginBean;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.NoResultException;


public class REQFN_1_LoginTest {

    @Test
    public void loginTest() {

        boolean validate;
        boolean exist = false;

        LoginBean bean = new LoginBean();
        bean.setUserId("Zanna94");
        bean.setPassword("password");

        // If user is in db
        validate = bean.validate();


        Assert.assertTrue((validate && bean.isLogged()) || (!bean.isLogged() && !validate));

    }

    /**
     * REQ-FN-1: Registered user access with user id and password
     */
    @Test
    public void userLogin() {

        User user0;
        User user1;

        LoginController controller = LoginController.getInstance();

        try {
            user0 = controller.login("Zanna94", "password");
        } catch (NoResultException e) {
            e.printStackTrace();
            user0 = null;
        }

        try {
            user1 = UserDAO.findBy("Zanna94");
        } catch (NoResultException e) {
            e.printStackTrace();
            user1 = null;
        }

        Assert.assertTrue((user0 == null && user1 == null) || (user0.equals(user1)));

    }
}
