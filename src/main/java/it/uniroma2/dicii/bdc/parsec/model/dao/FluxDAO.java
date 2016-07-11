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

    public static List<Double> findAllFluxLinesRatioByCategory(String category) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val from (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r1 " +
                        "cross join (" +
                        "select id,val,category from " +
                        "flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "where category = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        List<Double> results = new ArrayList<Double>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, category);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(rs.getDouble(i));
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
        return null;
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
            if ( rs != null ) {
                result = rs.getDouble(0);
            }
            return result;
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
            if ( rs != null ) {
                result = rs.getDouble(0);
            }
            return result;
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


    public static List<List<String>> findAllFluxLinesRatioByGalaxy(String galaxyName) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val, r1.upperlimit, r2.upperlimit from (" +
                        "select id,val,upperlimit from " +
                        "    flux JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "   where galaxy_name = ? and typeflux='l') as r1 " +
                        "cross join " +
                        "(select id,val,upperlimit from " +
                        "   flux inner JOIN galaxy on flux.galaxy_name = galaxy.name " +
                        "   where galaxy_name = ? and typeflux='l') as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r1.id<>r2.id";
        List<List<String>> results = new ArrayList<List<String>>();
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            statement.setString(2, galaxyName);
            ResultSet rs = statement.executeQuery();
            Integer i = 0;
            while (rs.next()) {
                results.add(new ArrayList<String>());
                results.get(i).add(Double.toString(rs.getDouble(1)));
                results.get(i).add(rs.getString(2));
                results.get(i).add(rs.getString(3));
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

    public static Double findTwoFluxLinesRatio(
            String galaxyName, String line1, String line2) throws SQLException, ClassNotFoundException {

        Connection connection = JDBCInitializer.getConnection();
        PreparedStatement statement = null;
        String query =
                "select r1.val/r2.val from (" +
                        "select distinct val from flux " +
                        "   join galaxy on flux.galaxy_name = galaxy.name " +
                        "   where galaxy_name = ? and typeflux='l' and atom = ?) as r1 " +
                        "cross join (" +
                        "select distinct val from flux " +
                        "join galaxy on flux.galaxy_name = galaxy.name " +
                        "where galaxy_name = ? and typeflux='l' and atom = ?) as r2 " +
                        "where r1.val<>-1 and r2.val<>-1 and r2.val<>0";
        Double result = -1d;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, galaxyName);
            statement.setString(2, line1);
            statement.setString(3, galaxyName);
            statement.setString(4, line2);
            ResultSet rs = statement.executeQuery();
            if ( rs != null ) {
                result = rs.getDouble(0);
            }

            return result;
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
        } catch (SQLException e ) {
            e.printStackTrace();
        } finally {
            // release resources
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
            return null;
        }
    }

    @SuppressWarnings("JpaQlInspection")
    public static List<Flux> findLine(String fluxLine, String galaxy) throws NoResultException {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        try {
            return entityManager.createQuery("select f from Flux f " +
                    "where galaxy_name = :galaxy and atom = :line and typeFlux != 'c'", Flux.class)
                    .setParameter("galaxy", galaxy)
                    .setParameter("line", fluxLine)
                    .getResultList();

        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    @SuppressWarnings("JpaQlInspection")
    public static Flux findContinuousFlux(String fluxLine, String galaxy) throws NoResultException {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        try {
            return entityManager.createQuery("select f from Flux f " +
                    "where galaxy_name = :galaxy and atom = :line and typeFlux = 'c'", Flux.class)
                    .setParameter("galaxy", galaxy)
                    .setParameter("line", fluxLine)
                    .getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    public static List<Flux> findLinesByGalaxy(Galaxy galaxy, List<String> lines) {

        try {
            List<Flux> results = new ArrayList<Flux>();
            EntityManager entityManager = JPAInitializer.getEntityManager();

            Integer i = 0;
            while (i < lines.size()) {
                List<Flux> r = entityManager.createQuery("select distinct f from Flux f where " +
                        "(galaxy = :galaxy) and (typeFlux = 'l') and (atom = :atom)", Flux.class)
                        .setParameter("galaxy", galaxy)
                        .setParameter("atom", lines.get(i))
                        .getResultList();
                results.addAll(r);
                i++;
            }
            return results;
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<Double> findLinesByCategory(String category) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            List<Double> results = new ArrayList<Double>();

            List<Flux> f = entityManager.createQuery("" +
                    "select distinct f from Flux f " +
                    "inner join f.galaxy " +
                    "where (f.galaxy.category = :category) " +
                    "and (f.typeFlux = 'l')", Flux.class)
                    .setParameter("category", category)
                    .getResultList();

            if (f.size() > 0) {
                Integer i;
                for (i = 0; i < f.size(); i++) {
                    results.add(f.get(i).getVal());
                }
            }
            return results;

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public static List<Double> findLinesByCategoryAndAperture(String category, String aperture) {

        try {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            List<Double> results = new ArrayList<Double>();

            List<Flux> f = entityManager.createQuery("" +
                    "select distinct f from Flux f " +
                    "inner join f.galaxy " +
                    "where (f.galaxy.category = :category) " +
                    "and (f.resolution = :aperture) and (f.typeFlux = 'l')", Flux.class)
                    .setParameter("category", category)
                    .setParameter("aperture", aperture)
                    .getResultList();

            if (f.size() > 0) {
                Integer i;
                for (i = 0; i < f.size(); i++) {
                    Double v = f.get(i).getVal();
                    if (!v.equals(-1d))
                        results.add(v);
                }
            }
            return results;

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
}
