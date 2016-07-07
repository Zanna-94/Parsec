package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class UserDAO {


    public static void store(User user) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        if (entityManager.find(User.class, user.getUserId()) != null)
            throw new IllegalArgumentException("Entity already in db");

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    public static void delete(User toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User personLoaded = entityManager.find(User.class, toDelete.getUserId());
        entityManager.remove(personLoaded);

        entityManager.getTransaction().commit();
    }

    public static void update(User toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User userLoaded = entityManager.find(User.class, toUpdate.getUserId());
        userLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("JpaQlInspection")
    public static User findBy(String username) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from User where (userId = :name)", User.class)
                    .setParameter("name", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    @SuppressWarnings("JpaQlInspection")
    public static User findBy(String username, String password) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from User t where " +
                    " (userId = :name) and (password = :pass) ", User.class)
                    .setParameter("name", username)
                    .setParameter("pass", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

}


