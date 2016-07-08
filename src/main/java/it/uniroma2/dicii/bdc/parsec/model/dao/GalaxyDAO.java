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
        Galaxy g;
        em.getTransaction().begin();

        if ((g = em.find(Galaxy.class, galaxy.getName())) == null)
            em.persist(galaxy);
        else {
            g.setName(galaxy.getName());
            g.setCategory(galaxy.getCategory());
            g.setAlterName(galaxy.getAlterName());
            g.setPosition(galaxy.getPosition());
        }
        em.getTransaction().commit();
    }

    public static void delete(Galaxy toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy galaxyLoaded = em.find(Galaxy.class, toDelete.getName());
        em.remove(galaxyLoaded);

        em.getTransaction().commit();
    }

    public static void update(Galaxy toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy userLoaded = em.find(Galaxy.class, toUpdate.getName());
        userLoaded.update(toUpdate);

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