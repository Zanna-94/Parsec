package it.uniroma2.dicii.bdc.parsec.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

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


    public void update(User toUpdate) {
        // TODO: 30/06/16  
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

    public void setUserId(String username) {
        this.userId = username;
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