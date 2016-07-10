package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

public class GalaxyDAO {

    public static void store(Galaxy galaxy) {

        EntityManager em = JPAInitializer.getEntityManager();

        em.getTransaction().begin();

        Galaxy old;
        if ((old = em.find(Galaxy.class, galaxy.getName())) != null) {
            old.update(galaxy);
            em.getTransaction().commit();
            return;
        }

        em.persist(galaxy);
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

    public static List<Galaxy> findByCategory(String category) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Galaxy t " +
                    "where (category = :category)", Galaxy.class)
                    .setParameter("category", category)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static List<Galaxy> findLower(Float redshiftValue) throws NoResultException {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Galaxy t " +
                    "where (redshift <= :r) ORDER BY redshift", Galaxy.class)
                    .setParameter("r", redshiftValue)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static List<Galaxy> findGreater(Float redshiftValue) throws NoResultException {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Galaxy t " +
                    "where (redshift >= :r) ORDER BY redshift", Galaxy.class)
                    .setParameter("r", redshiftValue)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<String> findAllName() {

        EntityManager entityManager = JPAInitializer.getEntityManager();
        return entityManager.createQuery("select t.name from Galaxy t", String.class)
                .getResultList();

    }

    public static List<Galaxy> searchInRange(Ascension ascension, Declination declination, Integer howMany)
            throws NoResultException{

        List<Galaxy> galaxy = new ArrayList<Galaxy>();
        return galaxy;

        // TODO: 10/07/16
    }
}