package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Entity;

/**
 * 
 */
@Entity
public class Luminosity extends Measure {

    /**
     * Default constructor
     */
    public Luminosity() {
    }

    /**
     * 
     */
    private String atom;

    /**
     * 
     */
    private Integer reference;

}