package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;
import it.uniroma2.dicii.bdc.parsec.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * Class manages {@link User} persistence
 */
public class UserDAO {


    /**
     * Save a new user instance in the db
     *
     * @param user instance to save
     */
    public static void store(User user) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
    }

    /**
     * Delete an user instance
     *
     * @param toDelete user to delete
     */
    public static void delete(User toDelete) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User personLoaded = entityManager.find(User.class, toDelete.getUserId());
        entityManager.remove(personLoaded);

        entityManager.getTransaction().commit();
    }

    /**
     * update an user instance
     *
     * @param toUpdate user to update
     */
    public static void update(User toUpdate) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        User userLoaded = entityManager.find(User.class, toUpdate.getUserId());
        userLoaded.update(toUpdate);

        entityManager.getTransaction().commit();
    }

    /**
     * find an user from its user-id
     *
     * @param username {@link User#userId}
     * @return {@link User}
     * @throws NoResultException
     */
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

    /**
     * find an user instance from its user-id and password.
     *
     * @param username user-id
     * @param password {@link User#password}
     * @return {@link User}
     * @throws NoResultException
     */
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


