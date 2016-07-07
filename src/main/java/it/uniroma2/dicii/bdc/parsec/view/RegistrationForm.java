package it.uniroma2.dicii.bdc.parsec.view;


import it.uniroma2.dicii.bdc.parsec.controller.LoginController;

public class RegistrationForm {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String userId;

    private String confirmPassword;

    private boolean administrator;

    public boolean validate() {

        if (firstname == null || lastname == null || email == null || password == null)
            return false;

        LoginController controller = LoginController.getInstance();

        if (controller.register(this) == null)
            return false;

        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
}