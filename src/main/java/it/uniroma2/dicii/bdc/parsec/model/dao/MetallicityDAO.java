package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.Metallicity;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class MetallicityDAO {

    public static void store(Metallicity metallicity) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(metallicity);
        em.getTransaction().commit();
    }

    public static void delete(Metallicity toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Metallicity fluxLoaded = em.find(Metallicity.class, toDelete.getId());
        em.remove(fluxLoaded);

        em.getTransaction().commit();
    }

    public static void update(Metallicity toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Metallicity userLoaded = em.find(Metallicity.class, toUpdate.getId());
        userLoaded.update(toUpdate);

        em.getTransaction().commit();
    }

    public static Metallicity findByID(Long id) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        return em.find(Metallicity.class, id);
    }

    public static List<Metallicity> findByGalaxy(Galaxy galaxy) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select m from Metallicity m where " +
                    " (galaxy = :galaxy)", Metallicity.class)
                    .setParameter("galaxy", galaxy)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
