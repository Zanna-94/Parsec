package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Entity;

@Entity
public class Metallicity extends Measure {

    /**
     * Default constructor
     */
    public Metallicity() {
    }

    /**
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO#update(Metallicity)}
     *
     * @param toupdate
     */
    public void update(Metallicity toupdate) {
        super.update(toupdate);
    }
}