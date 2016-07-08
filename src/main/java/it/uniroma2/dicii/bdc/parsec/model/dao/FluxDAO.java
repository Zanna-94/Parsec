package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;

/**
 * Created by laura_trive on 08/07/16.
 */
public class FluxDAO {

    public static void store(Flux flux) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(flux);
        em.getTransaction().commit();
    }
}
