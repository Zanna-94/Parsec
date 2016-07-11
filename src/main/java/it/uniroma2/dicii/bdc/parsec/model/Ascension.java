package it.uniroma2.dicii.bdc.parsec.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * @see Position#ascension
 */
@Embeddable
public class Ascension {

    /**
     * Default constructor
     */
    public Ascension() {
    }

    public Ascension(Integer ascensionHour, Integer ascensionMin, Float ascensionSec) {
        this.ascensionHour = ascensionHour;
        this.ascensionMin = ascensionMin;
        this.ascensionSec = ascensionSec;
    }

    /**
     * ascension hour
     */
    @Column(columnDefinition = "int default -1")
    private Integer ascensionHour;

    /**
     * ascension minutes
     */
    @Column(columnDefinition = "int default -1")
    private Integer ascensionMin;

    /**
     * ascension seconds
     */
    @Column(columnDefinition = "real default -1")
    private Float ascensionSec;


    public Integer getAscensionHour() {
        return ascensionHour;
    }

    public void setAscensionHour(Integer ascensionHour) {
        this.ascensionHour = ascensionHour;
    }

    public Integer getAscensionMin() {
        return ascensionMin;
    }

    public void setAscensionMin(Integer ascensionMin) {
        this.ascensionMin = ascensionMin;
    }

    public Float getAscensionSec() {
        return ascensionSec;
    }

    public void setAscensionSec(Float ascensionSec) {
        this.ascensionSec = ascensionSec;
    }
}