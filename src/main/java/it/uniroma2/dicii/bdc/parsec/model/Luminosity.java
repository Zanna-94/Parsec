package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 *
 */
@Entity
public class Luminosity extends Measure {

    @Column(columnDefinition = "varchar(255) default '-1'")
    private String atom;

    @Column(columnDefinition = "varchar(1) default '*'")
    private Character upperLimit;

    /**
     * Default constructor
     */
    public Luminosity() {
    }

    /**
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.LuminosityDAO#update(Luminosity)}
     *
     * @param toupdate
     */
    public void update(Luminosity toupdate) {
        super.update(toupdate);
        this.atom = toupdate.getAtom();
        this.upperLimit = toupdate.getUpperLimit();
    }

    public String getAtom() {
        return atom;
    }

    public void setAtom(String atom) {
        this.atom = atom;
    }

    public Character getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Character upperLimit) {
        this.upperLimit = upperLimit;
    }
}