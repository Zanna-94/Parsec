package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;
import java.util.*;

/**
 * Class represents a location of a {@link Galaxy} in the space
 */
@Embeddable
public class Position {

    /**
     * Default constructor
     */
    public Position() {
    }

    public Position(Float distanceValue, Float redshift,
                    Declination declination, Ascension ascension) {
        this.distanceValue = distanceValue;
        this.redshift = redshift;
        this.declination = declination;
        this.ascension = ascension;
    }

    @Column(name = "distance", columnDefinition = "real default -1")
    private Float distanceValue;

    @Column(name = "redshift", columnDefinition = "real default -1")
    private Float redshift;

    /**
     * @see Ascension
     */
    @Embedded
    private Ascension ascension;

    /**
     * @see Declination
     */
    @Embedded
    private Declination declination;

    /* Getter and Setter */

    public Ascension getAscension() {
        return ascension;
    }

    public void setAscension(Ascension ascension) {
        this.ascension = ascension;
    }

    public Declination getDeclination() {
        return declination;
    }

    public void setDeclination(Declination declination) {
        this.declination = declination;
    }

    public Float getRedshift() {
        return redshift;
    }

    public void setRedshift(Float readshift) {
        redshift = readshift;
    }

    public Float getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(Float distanceValue) {
        this.distanceValue = distanceValue;
    }

}