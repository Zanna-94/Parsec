package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.Luminosity;

import javax.persistence.EntityManager;

/**
 * Created by laura_trive on 08/07/16.
 */
public class LuminosityDAO {

    public static void store(Luminosity luminosity) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(luminosity);
        em.getTransaction().commit();
    }
}
