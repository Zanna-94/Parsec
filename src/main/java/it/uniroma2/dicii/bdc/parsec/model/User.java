package it.uniroma2.dicii.bdc.parsec.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Generated;

import javax.annotation.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Registered User Class.
 */
@Entity
@Table(name = "Consumer")
@SuppressWarnings("JpaDataSourceORMInspection")
public class User {

    /**
     * Default constructor
     */
    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @Id
    private String userId;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Role role;


    /**
     * @param toUpdate entity to update in database
     * @see it.uniroma2.dicii.bdc.parsec.model.dao.UserDAO#update(User)
     */
    public void update(User toUpdate) {
        this.userId = toUpdate.getUserId();
        this.password = toUpdate.getPassword();
        this.email = toUpdate.getEmail();
        this.firstname = toUpdate.getFirstname();
        this.lastname = toUpdate.getLastname();
        this.role = toUpdate.getRole();
    }

    public boolean isAdministrator() {
        if (role instanceof Administrator)
            return true;

        return false;
    }

    /* Getter and Setter */

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}