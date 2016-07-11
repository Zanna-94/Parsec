package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

/**
 * Class represents a measure of a quantity like {@link Luminosity} {@link Metallicity} or {@link Flux}
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

    @ManyToOne
    @JoinColumn
    protected Galaxy galaxy;

    /**
     * Value of the measured quantity
     */
    @Column(columnDefinition = "real default -1")
    protected Double val;

    /**
     * @param toupdate Entity to update in the database
     */
    public void update(Measure toupdate) {
        this.galaxy = toupdate.getGalaxy();
        this.val = toupdate.getVal();
    }


    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public Double getVal() {
        return val;
    }

    public void setVal(Double val) {
        this.val = val;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}