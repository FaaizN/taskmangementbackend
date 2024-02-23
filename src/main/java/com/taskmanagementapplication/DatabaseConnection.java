package com.taskmanagementapplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    
    private static Connection getConnection() throws URISyntaxException, SQLException {
        Connection connection = null;
        

        try {

            // Retrieve username, password, host, port and database name from URI
            URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

            // Extract username, password, host, port and database name from uri
            String username = jdbUri.getUserInfo().split(":")[0];
            String password = jdbUri.getUserInfo().split(":")[1];
            String port = String.valueOf(jdbUri.getPort());

            // Create the jdbc url
            String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

            // Establish a connection
            connection =  DriverManager.getConnection(jdbUrl, username, password);

            logger.info("Database connection established successfully.");

        } catch (SQLException e) {
            logger.severe("Error connecting to database: " + e.getMessage());
            throw e;
        } finally {

            // Ensure the connection is closed even if an exception occurs
            if (connection != null) {
                connection.close();
            }
        }
        
        return connection;
    }
    
}
