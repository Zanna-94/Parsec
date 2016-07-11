package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Class represents a measure of metallicity for a {@link Galaxy}
 */
@Entity
public class Metallicity extends Measure {


    @Column(columnDefinition = "real default -1")
    private Float error;

    /**
     * Default constructor
     */
    public Metallicity() {
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

    /**
     * @param toupdate Entity to update in the database
     * @see it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO#update(Metallicity)
     */
    public void update(Metallicity toupdate) {
        super.update(toupdate);
        this.error = toupdate.getError();
    }
}