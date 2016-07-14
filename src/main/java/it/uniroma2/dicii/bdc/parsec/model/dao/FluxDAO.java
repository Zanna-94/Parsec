package it.uniroma2.dicii.bdc.parsec.model.dao;

import it.uniroma2.dicii.bdc.parsec.model.Flux;
import it.uniroma2.dicii.bdc.parsec.model.Galaxy;
import it.uniroma2.dicii.bdc.parsec.model.JDBCInitializer;
import it.uniroma2.dicii.bdc.parsec.model.JPAInitializer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FluxDAO {

    public static void store(Flux flux) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();
        em.persist(flux);
        em.getTransaction().commit();
    }

    public static void delete(Flux toDelete) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Flux fluxLoaded = em.find(Flux.class, toDelete.getId());
        em.remove(fluxLoaded);

        em.getTransaction().commit();
    }

    public static void update(Flux toUpdate) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        Flux userLoaded = em.find(Flux.class, toUpdate.getId());
        userLoaded.update(toUpdate);

        em.getTransaction().commit();
    }

    public static Flux findByID(Long id) {
        EntityManager em = JPAInitializer.getEntityManager();
        em.getTransaction().begin();

        return em.find(Flux.class, id);
    }

    public static List<List<String>> findFluxLinesValuesByGalaxy(String galaxyName)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select distinct g.name,f.atom,f.val,f.error,f.upperlimit " +
                        "from " +
                        "(flux as f join galaxy as g " +
                        "on f.galaxy_name=g.name) " +
                        "where g.name = ? and f.typeflux='l'";
        List<List<String>> results = new ArrayList<List<String>>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(new ArrayList<String>());
                results.get(i).add(rs.getString(1));
                results.get(i).add(rs.getString(2));
                results.get(i).add(Double.toString(rs.getDouble(3)));
                results.get(i).add(Float.toString(rs.getFloat(4)));
                results.get(i).add(rs.getString(5));
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
        return results;
    }

    public static List<Flux> findAllLinesByGalaxy(Galaxy galaxy) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();

            return entityManager.createQuery("select f from Flux f where " +
                    "(galaxy = :galaxy) and (typeFlux = 'l')", Flux.class)
                    .setParameter("galaxy", galaxy)
                    .getResultList();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    private static int findNumberOfRatioValuesByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select count(r1.val/r2.val) as r from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id ";
        Integer result = -1;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
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
        return result;
    }

    private static int findNumberOfRatioValuesByCategoryAndAperture(String category, String aperture) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select count(r1.val/r2.val) as r from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id ";
        Integer result = -1;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, aperture);
            statement.setString(3, category);
            statement.setString(4, aperture);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
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
        return result;
    }

    public static Double findAllFluxLinesMedianRatioByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        int counter = findNumberOfRatioValuesByCategory(category);
        String query =
                "select r1.val/r2.val as r from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id " +
                        "order by r " +
                        "LIMIT 1 OFFSET ? ";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            statement.setInt(3, counter/2);
            ResultSet rs = statement.executeQuery();
            if (rs.next() ) {
                return result = rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static Double findAllFluxLinesMedianRatioByCategoryAndAperture(String category, String aperture) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        int counter = findNumberOfRatioValuesByCategoryAndAperture(category, aperture);
        String query =
                "select r1.val/r2.val as r from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id " +
                        "order by r " +
                        "LIMIT 1 OFFSET ? ";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, aperture);
            statement.setString(3, category);
            statement.setString(4, aperture);
            statement.setInt(5, counter/2);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() ) {
                return result = rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static Double findAllFluxLinesDeviationByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select STDDEV(r1.val/r2.val) from ( " +
                        "select id,val,category, galaxy_name from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join ( " +
                        "select id,val,category, galaxy_name from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id ";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            ResultSet rs = statement.executeQuery();
            if (rs.next() ) {
                return result = rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static Double findAllFluxLinesDeviationByCategoryAndAperture(String category, String aperture) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select STDDEV(r1.val/r2.val) from ( " +
                        "select id,val,category, galaxy_name from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r1 " +
                        "cross join ( " +
                        "select id,val,category, galaxy_name from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id ";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, aperture);
            statement.setString(3, category);
            statement.setString(4, aperture);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static List<Double> findAllFluxLinesRatioByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val as r from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id order by r ";
        List<Double> results = new ArrayList<Double>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            ResultSet rs = statement.executeQuery();

            Integer rowCount = 0, i = 0;
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }
            while (rs.next() && !i.equals(rowCount)) {
            }
            results.add(rs.getDouble(1));
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
        return results;
    }

    public static List<Double> findAllFluxLinesRatioByCategoryAndAperture(
            String category, String aperture) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ? ) as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution= ? ) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        List<Double> results = new ArrayList<Double>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, aperture);
            statement.setString(3, category);
            statement.setString(4, aperture);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(rs.getDouble(i));
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
        return results;
    }

    public static Double findAverageFluxLinesRatioByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select avg(r1.val/r2.val) from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static HashMap<String, Double> ratioFluxContinuous(String atom, String galaxy) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement;
        String query =
                " select D1.val / D2.val as ratio, resolution " +
                        "from (" +
                        "   select r1.val,r1.resolution " +
                        "   from flux AS r1 " +
                        "   where r1.typeflux = 'l' and atom = ? and r1.galaxy_name = ?) as D1" +
                        " cross join  (" +
                        "   select r2.val" +
                        "   from flux AS r2" +
                        "   where r2.typeflux = 'c' and atom = ? and r2.galaxy_name = ?) as D2;";

        statement = connection.prepareStatement(query);
        statement.setString(1, atom);
        statement.setString(2, galaxy);
        statement.setString(3, atom);
        statement.setString(4, galaxy);
        ResultSet rs = statement.executeQuery();


        HashMap<String, Double> result = new HashMap<String, Double>();
        while (rs.next()) {
            result.put(rs.getString("resolution"), rs.getDouble("ratio"));
        }
        rs.close();

        // resources release
        statement.close();
        connection.close();
        return result;
    }

    public static Double findAverageFluxLinesRatioByCategoryAndAperture(String category, String aperture)
            throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select avg(r1.val/r2.val) from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l' and resolution = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, aperture);
            statement.setString(3, category);
            statement.setString(4, aperture);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            }
            return result;
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
        return result;
    }

    public static List<String> findRatioBetweenTwoLinesFluxValues(String galaxyName, String line1, String line2) throws SQLException, ClassNotFoundException {
        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val, r1.upperlimit, r2.upperlimit from (" +
                        "select id,val,upperlimit from " +
                        "    flux JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "   where galaxy_name = ? and typeflux='l' and atom = ?) as r1 " +
                        "cross join " +
                        "(select id,val,upperlimit from " +
                        "   flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "   where galaxy_name = ? and typeflux='l' and atom = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        List<String> results = new ArrayList<String>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            statement.setString(2, line1);
            statement.setString(3, galaxyName);
            statement.setString(4, line2);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(Double.toString(rs.getDouble(1)));
                results.add(rs.getString(2));
                results.add(rs.getString(3));
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
        return results;
    }
}
