package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Luminosity;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;



public class LuminosityDAO {

    public static void store(Luminosity luminosity) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(luminosity);
        em.getTransaction().commit();
    }

    public static void delete(Luminosity toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Luminosity fluxLoaded = em.find(Luminosity.class, toDelete.getId());
        em.remove(fluxLoaded);

        em.getTransaction().commit();
    }

    public static void update(Luminosity toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Luminosity lumLoaded = em.find(Luminosity.class, toUpdate.getId());
        lumLoaded.update(toUpdate);

        em.getTransaction().commit();
    }

}
