package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.*;

/**
 * An {@link User} who has the role of Administrator has granted more functionality in the System
 */
@Entity
@DiscriminatorValue("administrator")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Administrator extends Role {

    /**
     * Default constructor
     */
    public Administrator() {
    }

}