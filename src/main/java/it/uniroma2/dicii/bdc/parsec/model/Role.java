package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

/**
 * Class representing a role in the System for an User.
 *
 * @see Administrator
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@SuppressWarnings("JpaDataSourceORMInspection")
public abstract class Role {

    /**
     * Default constructor
     */
    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}