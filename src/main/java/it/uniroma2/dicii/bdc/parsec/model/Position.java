package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;
import java.util.*;

/**
 *
 */
@Embeddable
public class Position {

    /**
     * Default constructor
     */
    public Position() {
    }

    public Position(Float distanceValue, Integer distanceReference, Float redshift,
                    Declination declination, Ascension ascension) {
        this.distanceValue = distanceValue;
        this.distanceReference = distanceReference;
        this.redshift = redshift;
        this.declination = declination;
        this.ascension = ascension;
    }

    /**
     *
     */
    @Column(name = "distance")
    private Float distanceValue;

    /**
     *
     */
    @Column(name = "reference")
    private Integer distanceReference;

    /**
     *
     */
    @Column(name = "redshift")
    private Float redshift;

    @Embedded
    private Ascension ascension;

    @Embedded
    private Declination declination;

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

    public Float getReadshift() {
        return redshift;
    }

    public void setReadshift(Float readshift) {
        redshift = readshift;
    }

    public Float getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(Float distanceValue) {
        this.distanceValue = distanceValue;
    }

    public Integer getDistanceReference() {
        return distanceReference;
    }

    public void setDistanceReference(Integer distanceReference) {
        this.distanceReference = distanceReference;
    }

}