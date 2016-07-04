package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Flux extends Measure {

    /**
     * Default constructor
     */
    public Flux() {
    }

    /**
     *
     */
    private String atom;

    /**
     *
     */
    private String resolution;

    /**
     *
     */
    private String type;

    /* Getter and Setter */

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}