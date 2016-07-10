package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.*;
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

    public static List<List<String>> findDescriptionByName(String galaxyName)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select distinct name, ascensionhour, ascensionmin, ascensionsec, " +
                "declinationsign,declinationdeg,declinationmin,declinationsec," +
                "distance, redshift, l.val, m.val, m.error " +
                "from (" +
                "galaxy as g join luminosity as l on g.name=l.galaxy_name\n" +
                "join metallicity as m on l.galaxy_name=m.galaxy_name) " +
                "where g.name = ?";
        List<List<String>> results = new ArrayList<List<String>>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(new ArrayList<String>());
                results.get(i).add(rs.getString(1));
                results.get(i).add(Integer.toString(rs.getInt("ascensionhour")));
                results.get(i).add(Integer.toString(rs.getInt("ascensionmin")));
                results.get(i).add(Float.toString(rs.getFloat("ascensionsec")));
                results.get(i).add(rs.getString("declinationsign"));
                results.get(i).add(Integer.toString(rs.getInt("declinationdeg")));
                results.get(i).add(Integer.toString(rs.getInt("declinationmin")));
                results.get(i).add(Float.toString(rs.getFloat("declinationsec")));
                results.get(i).add(Float.toString(rs.getFloat("distance")));
                results.get(i).add(Float.toString(rs.getFloat("redshift")));
                results.get(i).add(Double.toString(rs.getDouble(11)));
                results.get(i).add(Double.toString(rs.getDouble("val")));
                results.get(i).add(Float.toString(rs.getFloat("error")));
                i++;
            }
            return results;
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally{
            // release resources
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return null;
    }

    public static void findFluxLinesValues(String galaxyName)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String coffeeName = rs.getString("name");
                int supplierID = rs.getInt("ascensionhour");
                System.out.println(coffeeName + "\t" + supplierID +
                        "\t" );
                // TODO: 11/07/16  
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally{
            // release resources
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
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