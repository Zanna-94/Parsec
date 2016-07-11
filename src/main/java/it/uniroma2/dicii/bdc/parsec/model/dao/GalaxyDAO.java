package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.*;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.*;
import java.util.*;

/**
 * Class manages {@link Galaxy} persistence in the database
 *
 * @see Galaxy
 */
@SuppressWarnings("ALL")
public class GalaxyDAO {

    /**
     * Save a new instance of Galaxy
     *
     * @param galaxy instance to persist
     */
    public static void store(Galaxy galaxy) {

        EntityManager em = JPAInitializer.getEntityManager();

        em.getTransaction().begin();

        // if the instance is already present in the db, just update
        Galaxy old;
        if ((old = em.find(Galaxy.class, galaxy.getName())) != null) {
            old.update(galaxy);
            em.getTransaction().commit();
            return;
        }

        em.persist(galaxy);
        em.getTransaction().commit();
    }

    /**
     * Delete an instance of Galaxy from the database
     *
     * @param toDelete instance to delete
     */
    public static void delete(Galaxy toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy galaxyLoaded = em.find(Galaxy.class, toDelete.getName());
        em.remove(galaxyLoaded);

        em.getTransaction().commit();
    }

    /**
     * Update an instance of Galaxy already present in the database
     *
     * @param toUpdate instance to update
     */
    public static void update(Galaxy toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Galaxy userLoaded = em.find(Galaxy.class, toUpdate.getName());

        userLoaded.update(toUpdate);

        em.getTransaction().commit();
    }

    /**
     * @param name galaxy's id
     * @return the instance of Galaxy found
     */
    public static Galaxy findByName(String name) {
        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            return entityManager.createQuery("from Galaxy where (name = :name)", Galaxy.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    /**
     * Given a spatial position it finds the n-nearest galaxies.
     * Using jdbc because jpql doens't have trigonometric functions
     *
     * @param ascension   {@link Position#ascension}
     * @param declination {@link Position#declination}
     * @param howMany     Indicates how many instance of Galaxy must be returned most
     * @return a list of Galaxy instances
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<Galaxy> findInRange(Ascension ascension, Declination declination, Integer howMany)
            throws SQLException, ClassNotFoundException {

        // Create db connection
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
                        "                      ) as D on H.name = D.name WHERE ascensionsec != -1 AND ascensionmin != -1" +
                        "                            AND H.ascensionhour != -1 AND H.declinationsec != -1 " +
                        "                            AND H.declinationdeg != -1 AND H.declinationmin != -1" +
                        "                               order by dist";

        // set parameters for the query
        statement = connection.prepareStatement(query);
        statement.setInt(1, ascension.getAscensionHour());
        statement.setInt(2, ascension.getAscensionMin());
        statement.setFloat(3, ascension.getAscensionSec());
        statement.setInt(4, ascension.getAscensionHour());
        statement.setInt(5, ascension.getAscensionMin());
        statement.setFloat(6, ascension.getAscensionSec());

        // include the sign of declination in the degree
        Integer declinationdeg = declination.getDeclinationDeg();
        if (declination.getDeclinationSign() != null)
            if (declination.getDeclinationSign() == '-')
                declinationdeg *= -1;

        statement.setInt(7, declinationdeg);
        statement.setInt(8, declination.getDeclinationMin());
        statement.setFloat(9, declination.getDeclinationSec());
        ResultSet rs = statement.executeQuery();

        // Declare result variable
        ArrayList<Galaxy> galaxies = new ArrayList<Galaxy>();
        int i = 0;
        // scroll the cursor and create new
        // instance of galaxy with found information
        while (rs.next()) {
            Galaxy galaxy = new Galaxy();
            galaxy.setName(rs.getString("name"));
            galaxy.setAlterName(rs.getString("altername"));
            galaxy.setCategory(rs.getString("category"));
            // add galaxy to results list
            galaxies.add(galaxy);

            i++;
            // close the cursor and break
            if (i >= howMany) {
                rs.close();
                break;
            }
        }

        return galaxies;
    }

    /**
     * Find all information about a Galaxy specified by its name
     *
     * @param galaxyName galaxy's id
     * @return a List of tuples. Each tuple has Galaxy's name, Position, distance,
     * Luminosity and Metallicity
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static List<List<String>> findDescriptionByName(String galaxyName)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select distinct name, ascensionhour, ascensionmin, ascensionsec, " +
                        "declinationsign,declinationdeg,declinationmin,declinationsec," +
                        "distance, redshift, l.val, m.val, m.error " +
                        "from (" +
                        "galaxy as g join luminosity as l on g.name=l.galaxy_name " +
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

    /**
     * Find All Galaxy belongs to a category
     *
     * @param category {@link Galaxy#category}
     * @return list of galaxy instances
     * @throws NoResultException if no instances found in db
     */
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


    /**
     * Find all Galaxy that has a value of {@link Position#redshift} lower that specified one
     *
     * @param redshiftValue value for redshift
     * @return list of galaxy instance
     * @throws NoResultException if no instances found in db
     */
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

    /**
     * Find all Galaxy that has a value of {@link Position#redshift} greater that specified one
     *
     * @param redshiftValue value for redshift
     * @return list of galaxy instance
     * @throws NoResultException if no instances found in db
     */
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

    /**
     * Find the names of the Galaxy instance in the database
     *
     * @return the list of galaxies'names
     */
    public static List<String> findAllName() {

        EntityManager entityManager = JPAInitializer.getEntityManager();
        return entityManager.createQuery("select t.name from Galaxy t", String.class)
                .getResultList();

    }

}