package com.airplane.connections;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class Connections{
	
    private static final String URL = "jdbc:postgresql://localhost:5432/ManagementSystem";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "admin";
    
    private static Connections instance;
    private static Connection connection;
    
    /**
     * Private constructor
     */
    private Connections() {
    	
    }

    /**
     * Method get Connection
     * @return
     */
    
    public static Connections getInstance() {
        if (instance == null) {
            instance = new Connections();
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Method close Connection
     */
    public void closeConnection() {
        if (connection == null) {
            throw new NullPointerException("Connection is null:");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    


}
