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
    @Column(name = "readshift")
    private Float Readshift;

    @Embedded
    private Ascension ascension;

    @Embedded
    private Declination declination;


}