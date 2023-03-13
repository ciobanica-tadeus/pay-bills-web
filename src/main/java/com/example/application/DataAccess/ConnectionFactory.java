package com.example.application.DataAccess;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost/billing_system_db";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final ConnectionFactory signalInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {

        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        }

        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return signalInstance.createConnection();
    }

    public static void close(Connection connection){

        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                System.err.println("An SQL Exception occured. Details are provided below:");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet){

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }
}
