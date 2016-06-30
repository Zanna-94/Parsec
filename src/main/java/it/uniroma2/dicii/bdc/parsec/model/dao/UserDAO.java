package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class UserDAO {


    public static void store(User user) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    public static void delete(User toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User personLoaded = entityManager.find(User.class, toDelete.getId());
        entityManager.remove(personLoaded);

        entityManager.getTransaction().commit();
    }

    public static void update(User toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User userLoaded = entityManager.find(User.class, toUpdate.getId());
        userLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    public static User findById(Long id) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.find(User.class, id);

        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    @SuppressWarnings("JpaQlInspection")
    public static User findByUserName(String userName) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from User where (username = :name)", User.class)
                    .setParameter("name", userName)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    @SuppressWarnings("JpaQlInspection")
    public static User findByNameAndPassword(String userName, String passWord) throws NoResultException {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select t from Person t where " +
                    " (username = :name) and (password = :pass) ", User.class)
                    .setParameter("name", userName)
                    .setParameter("pass", passWord)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

}


