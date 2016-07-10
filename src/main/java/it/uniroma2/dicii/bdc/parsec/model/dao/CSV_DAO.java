package it.uniroma2.dicii.bdc.parsec.model.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.uniroma2.dicii.bdc.parsec.controller.CSVManager;
import it.uniroma2.dicii.bdc.parsec.model.CSVFile;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.Predicate;


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

        if (entityManager.find(CSVManager.class, file.getName()) != null)
            throw new IllegalArgumentException("File already uploaded");

        entityManager.persist(file);

        entityManager.getTransaction().commit();

    }





}
