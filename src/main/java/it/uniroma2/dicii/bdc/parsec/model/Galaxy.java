package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

/**
 * Class represent a Galaxy with a name, a position and a category associated
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
        this.alterName = "-1";
        this.category = "-1";
        this.position = new Position(-1f, -1f, new Declination('*', -1, -1, -1f), new Ascension(-1, -1, -1f));
    }

    @Id
    private String name;

    /**
     * Alternative name
     */
    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String alterName;

    /**
     * Each galaxy is classified according to the spectral characteristics
     */
    @Column(columnDefinition = "VARCHAR(255) default '-1'")
    private String category;

    /**
     * @see Position
     */
    @Embedded
    private Position position;

    /**
     * @param toupdate Entity to update in database
     * @see it.uniroma2.dicii.bdc.parsec.model.dao.GalaxyDAO#update(Galaxy)
     */
    public void update(Galaxy toupdate) {
        this.name = toupdate.getName();
        if (toupdate.getAlterName().compareTo("-1") != 0)
            this.alterName = toupdate.getAlterName();
        if (toupdate.getCategory().compareTo("-1") != 0)
            this.category = toupdate.getCategory();
        if (toupdate.getPosition().getDistanceValue() != -1)
            this.position = toupdate.getPosition();
    }

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