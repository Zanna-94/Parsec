package it.uniroma2.dicii.bdc.parsec.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * It Initializes a static entity manager of JPA for the entire project.
 **/
public class JPAInitializer {
    /**
     * Static field for entity manager.
     */
    private static EntityManager entityManager;

    /**
     * Static field for entity manager factory.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Initializes all the stuffs for JPA.
     */
    private JPAInitializer() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Parsec");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Close all the the stuffs used for JPA.
     */
    public static void shutdown() {
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Getter for the instance of entityManager. This is a singleton.
     *
     * @return the static entity manager instance.
     */
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            new JPAInitializer();
        }
        return entityManager;
    }
}
