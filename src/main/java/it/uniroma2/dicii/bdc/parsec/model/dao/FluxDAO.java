package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Flux> findAllLinesByGalaxy(Galaxy galaxy) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();

            return entityManager.createQuery("select f from Flux f where " +
                        "(galaxy = :galaxy) and (typeFlux = 'l')", Flux.class)
                        .setParameter("galaxy", galaxy)
                        .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<Flux> findLinesByGalaxy(Galaxy galaxy, List<String> lines) {

        try {
            List<Flux> results = new ArrayList<Flux>();
            EntityManager entityManager = JPAInitializer.getEntityManager();

            Integer i = 0;
            while ( i < lines.size() ) {
                List<Flux> r = entityManager.createQuery("select distinct f from Flux f where " +
                        "(galaxy = :galaxy) and (typeFlux = 'l') and (atom = :atom)", Flux.class)
                        .setParameter("galaxy", galaxy)
                        .setParameter("atom", lines.get(i))
                        .getResultList();
                results.addAll(r);
                i++;
            }
            return results;
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<Flux> findLinesByCategory(String category) {

        try {
            List<Flux> results = new ArrayList<Flux>();
            EntityManager entityManager = JPAInitializer.getEntityManager();
            List<Galaxy> g = GalaxyDAO.findByCategory(category);

            Integer i = 0;
            while ( i < g.size() ) {
                List<Flux> r = entityManager.createQuery("select distinct f from Flux f where " +
                        "(galaxy = :galaxy) and (typeFlux = 'l')", Flux.class)
                        .setParameter("galaxy", g.get(i))
                        .getResultList();
                results.addAll(r);
                i++;
            }
            return results;
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<Flux> findLinesByCategoryAndAperture(String category, String aperture) {

        try {
            List<Flux> results = new ArrayList<Flux>();
            EntityManager entityManager = JPAInitializer.getEntityManager();
            List<Galaxy> g = GalaxyDAO.findByCategory(category);

            Integer i = 0;
            while ( i < g.size() ) {
                List<Flux> r = entityManager.createQuery("select distinct f from Flux f where " +
                        "(galaxy = :galaxy) and (typeFlux = 'l') " +
                        "and (resolution = :aperture)", Flux.class)
                        .setParameter("galaxy", g.get(i))
                        .setParameter("aperture", aperture)
                        .getResultList();
                results.addAll(r);
                i++;
            }
            return results;
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
