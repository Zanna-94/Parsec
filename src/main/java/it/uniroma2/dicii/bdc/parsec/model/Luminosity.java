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


    private String atom;

    /**
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO#update(Luminosity)}
     *
     * @param toupdate
     */
    public void update(Luminosity toupdate) {
        super.update(toupdate);
        this.atom = toupdate.getAtom();
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

}