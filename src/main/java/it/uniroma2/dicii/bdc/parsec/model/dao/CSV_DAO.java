package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.CSVFile;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.*;
import java.util.List;


public class CSV_DAO {

    private String name;

    public CSV_DAO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Boolean store(CSVFile file) {

        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        if (entityManager.find(CSVFile.class, file.getName()) != null) {
            entityManager.getTransaction().commit();
            return false;
        }

        entityManager.persist(file);

        entityManager.getTransaction().commit();

        return true;
    }

    public static List<CSVFile> allFiles() {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select f from CSVFile f ", CSVFile.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

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
}
