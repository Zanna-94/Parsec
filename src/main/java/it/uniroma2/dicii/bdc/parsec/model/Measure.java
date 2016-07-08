package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;
import java.util.*;

/**
 *
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("JpaDataSourceORMInspection")
public abstract class Measure {

    /**
     * Default constructor
     */
    public Measure() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;


    /**
     *
     */
    @ManyToOne
    protected Galaxy galaxy;

    /**
     *
     */
    protected Float value;

    /**
     *
     */
    protected Float error;

    /**
     *
     */
    protected Character upperLimit;


    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getError() {
        return error;
    }

    public void setError(Float error) {
        this.error = error;
    }

    public Character getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Character upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}