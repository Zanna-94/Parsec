package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Flux extends Measure {

    @Column(columnDefinition = "real default -1")
    private Float error;

    @Column(columnDefinition = "varchar(1) default '*'")
    private Character upperLimit;

    public Character getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Character upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

    /**
     * Default constructor
     */
    public Flux() {
    }

    /**
     * Data constructor
     */
    public Flux(Galaxy galaxy, String atom, Float value, Character upperLimit,
                Float error, String resolution, String type) {
        this.galaxy = galaxy;
        this.atom = atom;
        this.value = value;
        this.upperLimit = upperLimit;
        this.error = error;
        this.resolution = resolution;
        this.type = type;
    }

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String atom;

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String resolution;

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String type;

    /**
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO#update(Flux)}
     *
     * @param toupdate
     */
    public void update(Flux toupdate) {
        super.update(toupdate);
        this.atom = toupdate.getAtom();
        this.resolution = toupdate.getResolution();
        this.type = toupdate.getType();
        this.error = toupdate.getError();
        this.upperLimit = toupdate.getUpperLimit();
    }

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