package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

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

    @ManyToOne
    protected Galaxy galaxy;

    @Column(columnDefinition = "real default -1")
    protected Float val;

    /**
     * @param toupdate
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

    public Float getVal() {
        return val;
    }

    public void setVal(Float val) {
        this.val = val;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}