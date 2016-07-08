package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;

public class FluxDAO {

    public static void store(Flux flux) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(flux);
        em.getTransaction().commit();
    }

    public static void delete(Flux toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Flux fluxLoaded = em.find(Flux.class, toDelete.getId());
        em.remove(fluxLoaded);

        em.getTransaction().commit();
    }

    public static void update(Flux toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Flux userLoaded = em.find(Flux.class, toUpdate.getId());
        userLoaded.update(toUpdate);

        em.getTransaction().commit();
    }

    public static Flux findByID(Long id) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        return em.find(Flux.class, id);
    }
}
