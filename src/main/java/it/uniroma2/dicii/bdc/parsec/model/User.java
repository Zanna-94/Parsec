package it.uniroma2.dicii.bdc.parsec.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Registered User Class.
 */
@Entity
public class User {

    /**
     * Default constructor
     */
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String email;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Role role;


    public void update(User toUpdate){
        // TODO: 30/06/16  
    }
    
    /* Getter and Setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}