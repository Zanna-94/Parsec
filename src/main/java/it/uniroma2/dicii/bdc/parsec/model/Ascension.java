package it.uniroma2.dicii.bdc.parsec.model;

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

    /**
     * 
     */
    private Integer ascensionHour;

    /**
     * 
     */
    private Integer ascensionMin;

    /**
     * 
     */
    private Integer ascensionSec;


}