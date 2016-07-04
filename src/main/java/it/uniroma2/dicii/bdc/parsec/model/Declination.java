package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.Embeddable;
import java.util.*;

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

    /**
     * 
     */
    private Integer declinationSign;

    /**
     * 
     */
    private Integer declinationMin;

    /**
     * 
     */
    private Integer declinationDeg;

    /**
     * 
     */
    private Float declinationSec;


}