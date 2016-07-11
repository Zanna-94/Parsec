package it.uniroma2.dicii.bdc.parsec.model;

import java.sql.*;

/**
 * It Initializes the entity manager for JDBC
 */
public class JDBCInitializer {

    /**
     * @return A connection through which communicate with a database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver"); // set Driver for db
        String dbURI = "jdbc:postgresql://localhost:5432/parsec_db";
        String user = "postgres";
        String password = "password";
        Connection connection = DriverManager.getConnection(dbURI, user, password); // get connection
        return connection;
    }

}
