package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

/**
 *
 */
@Entity
public class Galaxy {

    /**
     * Default constructor
     */
    public Galaxy() {
    }

    /**
     * Reference constructor
     */
    public Galaxy(String name) {
        this.name = name;
    }

    @Id
    private String name;

    /**
     * Alternative name
     */
    private String alterName;

    private String category;

    @Embedded
    private Position position;


    /* Getter and Setter */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlterName() {
        return alterName;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}