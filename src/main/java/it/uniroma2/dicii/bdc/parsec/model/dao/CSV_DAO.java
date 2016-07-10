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

    public static void store(CSVFile file) {

        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        if (entityManager.find(CSVFile.class, file.getName()) != null)
            throw new IllegalArgumentException("File already uploaded");

        entityManager.persist(file);

        entityManager.getTransaction().commit();

    }

    public static List<CSVFile> allFiles() {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("select f from files f ", CSVFile.class)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }



}
