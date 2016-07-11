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

    public static List<Galaxy> findInRange(Ascension ascension, Declination declination, Integer howMany)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement;
        String query =
                "select" +
                        "  H.name," +
                        "  altername," +
                        "  category," +
                        "  dist " +
                        "from galaxy as H join (" +
                        "                        select" +
                        "                          G.name," +
                        "                          acos(" +
                        "                              sin(15 * ( ? + ? / 60 + ? / 3600)) *" +
                        "                              sin(15 * (G.ascensionhour + G.ascensionmin / 60 + G.ascensionsec / 3600)) +" +
                        "                              cos(15 * ( ? + ? / 60 + ? / 3600)) *" +
                        "                              cos(15 * (G.ascensionhour + G.ascensionmin / 60 + G.ascensionsec / 3600)) *" +
                        "                              cos(( ? + ? / 60 + ? / 3600) -" +
                        "                                  (G.declinationdeg + G.declinationmin / 60 + G.declinationsec / 3600))" +
                        "                          ) as dist" +
                        "                        from galaxy as G" +
                        "                      ) as D on H.name = D.name " +
                        "order by dist";

        statement = connection.prepareStatement(query);
        statement.setInt(1, ascension.getAscensionHour());
        statement.setInt(2, ascension.getAscensionMin());
        statement.setFloat(3, ascension.getAscensionSec());
        statement.setInt(4, ascension.getAscensionHour());
        statement.setInt(5, ascension.getAscensionMin());
        statement.setFloat(6, ascension.getAscensionSec());

        Integer declinationdeg = declination.getDeclinationDeg();
        if (declination.getDeclinationSign() == '-')
            declinationdeg *= -1;

        statement.setInt(7, declinationdeg);
        statement.setInt(8, declination.getDeclinationMin());
        statement.setFloat(9, declination.getDeclinationSec());
        ResultSet rs = statement.executeQuery();

        ArrayList<Galaxy> galaxies = new ArrayList<Galaxy>();
        int i = 0;
        while (rs.next()) {
            Galaxy galaxy = new Galaxy();
            galaxy.setName(rs.getString("name"));
            galaxy.setAlterName(rs.getString("altername"));
            galaxy.setCategory(rs.getString("category"));
            galaxies.add(galaxy);

            i++;
            if (i >= howMany) {
                rs.close();
                break;
            }
        }

        return galaxies;
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
                        "galaxy as g join luminosity as l on g.name=l.galaxy_name" +
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
                results.get(i).add(Integer.toString(rs.getInt(2)));
                results.get(i).add(Integer.toString(rs.getInt(3)));
                results.get(i).add(Float.toString(rs.getFloat(4)));
                results.get(i).add(rs.getString(5));
                results.get(i).add(Integer.toString(rs.getInt(6)));
                results.get(i).add(Integer.toString(rs.getInt(7)));
                results.get(i).add(Float.toString(rs.getFloat(8)));
                results.get(i).add(Float.toString(rs.getFloat(9)));
                results.get(i).add(Float.toString(rs.getFloat(10)));
                results.get(i).add(Double.toString(rs.getDouble(11)));
                results.get(i).add(Double.toString(rs.getDouble(12)));
                results.get(i).add(Float.toString(rs.getFloat(13)));
                i++;
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // release resources
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
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

}