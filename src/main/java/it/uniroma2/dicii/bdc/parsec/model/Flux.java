package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Column;
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
        this.typeFlux = type;
    }

    @Column(columnDefinition = "real default -1")
    private Float error;

    @Column(columnDefinition = "varchar(1) default '*'")
    private Character upperLimit;

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String atom;

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String resolution;

    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String typeFlux;

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
     * Used by {@link it.uniroma2.dicii.bdc.parsec.model.dao.FluxDAO#update(Flux)}
     *
     * @param toupdate
     */
    public void update(Flux toupdate) {
        super.update(toupdate);
        this.atom = toupdate.getAtom();
        this.resolution = toupdate.getResolution();
        this.typeFlux = toupdate.getTypeFlux();
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

    public String getTypeFlux() {
        return typeFlux;
    }

    public void setTypeFlux(String type) {
        this.typeFlux = type;
    }

}