package it.uniroma2.dicii.bdc.parsec.model;

import java.util.*;

/**
 * 
 */
public abstract class Measure {

    /**
     * Default constructor
     */
    public Measure() {
    }

    /**
     * 
     */
    public Long id;

    /**
     * 
     */
    private Galaxy galaxy;

    /**
     * 
     */
    private Float value;

    /**
     * 
     */
    private Float error;

    /**
     * 
     */
    private Char limit;


}