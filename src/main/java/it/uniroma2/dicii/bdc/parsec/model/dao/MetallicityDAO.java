package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;

import javax.persistence.EntityManager;

/**
 * Created by laura_trive on 08/07/16.
 */
public class MetallicityDAO {

    public static void store(Metallicity metallicity) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(metallicity);
        em.getTransaction().commit();
    }
}
