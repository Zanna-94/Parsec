package it.uniroma2.dicii.bdc.parsec.model;

import java.sql.*;

public class JDBCInitializer {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String dbURI = "jdbc:postgresql://localhost:5432/BDC";
        String user = "laura_trive";
        String password = "laura";
        Connection connection = DriverManager.getConnection(dbURI, user, password);
        return connection;
    }

}
