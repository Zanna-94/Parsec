package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;

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
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.MetallicityDAO#update(Metallicity)}
     *
     * @param toupdate
     */
    public void update(Metallicity toupdate) {
        super.update(toupdate);
        this.error = toupdate.getError();
    }
}