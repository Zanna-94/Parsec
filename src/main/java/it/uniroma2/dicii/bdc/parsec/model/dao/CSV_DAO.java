package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.CSVFile;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.*;
import java.util.List;

/**
 * Class manages csv filename persistence in db. In this way it's possible check if a file
 * that an user want import, is already present and imported
 */
public class CSV_DAO {

    /**
     * Filename
     */
    private String name;

    public CSV_DAO() {
    }

    /**
     * Save file in db
     *
     * @param file file
     * @return true if procedure ends successful
     */
    public static Boolean store(CSVFile file) {

        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        // check if file already in db
        if (entityManager.find(CSVFile.class, file.getName()) != null) {
            entityManager.getTransaction().commit();
            return false;
        }

        entityManager.persist(file);

        entityManager.getTransaction().commit();

        return true;
    }

    /**
     * Used to check if a file is already imported in the System
     *
     * @return A list of all files in the db
     */
    public static List<CSVFile> allFiles() {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select f from CSVFile f ", CSVFile.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    /**
     * Search a file by its name
     *
     * @param name filename
     * @return list of {@link CSVFile} instances
     */
    public static List<CSVFile> searchFile(String name) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select f from CSVFile f where (name = :name)",
                    CSVFile.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
