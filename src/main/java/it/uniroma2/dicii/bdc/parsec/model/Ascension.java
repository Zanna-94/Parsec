package it.uniroma2.dicii.bdc.parsec.model;

import org.hibernate.loader.entity.CascadeEntityJoinWalker;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.*;

/**
 * 
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
     * 
     */
    @Column(columnDefinition = "int default -1")
    private Integer ascensionHour;

    /**
     * 
     */
    @Column(columnDefinition = "int default -1")
    private Integer ascensionMin;

    /**
     * 
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