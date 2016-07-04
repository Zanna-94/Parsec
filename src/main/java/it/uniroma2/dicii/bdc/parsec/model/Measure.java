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
    private Character upperLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}