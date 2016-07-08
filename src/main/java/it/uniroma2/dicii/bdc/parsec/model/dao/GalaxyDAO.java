package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

public class GalaxyDAO {

    public static void store(Galaxy galaxy) {

        EntityManager em = JPAInitializer.getEntityManager();

        if (em.find(Galaxy.class, galaxy.getName()) == null) {
            em.getTransaction().begin();
            em.persist(galaxy);
            em.getTransaction().commit();
        } else {
            update(galaxy);
        }
    }

    public static void delete(Galaxy toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy galaxyLoaded = em.find(Galaxy.class, toDelete.getName());
        em.remove(galaxyLoaded);

        em.getTransaction().commit();
    }

    public static void update(Galaxy toUpdate) {
        // TODO: 09/07/16 NO UPDATE VALUE
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy userLoaded = em.find(Galaxy.class, toUpdate.getName());

        em.remove(userLoaded);

        userLoaded.update(toUpdate);

        em.persist(userLoaded);

        em.getTransaction().commit();
    }

    public static Galaxy findByName(String galaxyName) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Galaxy t where " +
                    " (name = :name)", Galaxy.class)
                    .setParameter("name", galaxyName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}