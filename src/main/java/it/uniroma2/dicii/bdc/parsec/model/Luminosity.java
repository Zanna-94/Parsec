package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

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

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

}