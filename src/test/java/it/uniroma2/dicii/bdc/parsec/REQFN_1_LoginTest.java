package it.uniroma2.dicii.bdc.parsec;

import it.uniroma2.dicii.bdc.parsec.view.LoginBean;
import org.junit.Assert;
import org.junit.Test;



/**
 * REQ-FN-1: Registered user access with user id and password
 */

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
}
