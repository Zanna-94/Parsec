package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
public class Declination {

    /**
     * Default constructor
     */
    public Declination() {
    }

    public Declination(Character declinationSign, Integer declinationMin, Integer declinationDeg, Float declinationSec) {
        this.declinationSign = declinationSign;
        this.declinationMin = declinationMin;
        this.declinationDeg = declinationDeg;
        this.declinationSec = declinationSec;
    }

    /**
     *
     */
    @Column(columnDefinition = "varchar(1) default '*'")
    private Character declinationSign;

    /**
     *
     */
    @Column(columnDefinition = "int default -1")
    private Integer declinationMin;

    /**
     *
     */
    @Column(columnDefinition = "int default -1")
    private Integer declinationDeg;

    /**
     *
     */
    @Column(columnDefinition = "real default -1")
    private Float declinationSec;


    public Float getDeclinationSec() {
        return declinationSec;
    }

    public void setDeclinationSec(Float declinationSec) {
        this.declinationSec = declinationSec;
    }

    public Character getDeclinationSign() {
        return declinationSign;
    }

    public void setDeclinationSign(Character declinationSign) {
        this.declinationSign = declinationSign;
    }

    public Integer getDeclinationMin() {
        return declinationMin;
    }

    public void setDeclinationMin(Integer declinationMin) {
        this.declinationMin = declinationMin;
    }

    public Integer getDeclinationDeg() {
        return declinationDeg;
    }

    public void setDeclinationDeg(Integer declinationDeg) {
        this.declinationDeg = declinationDeg;
    }
}