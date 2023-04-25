package com.airplane.connections;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Connections{
	
	 	PropertiesConfiguration config;
	{
		try {
			config = new PropertiesConfiguration("database.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
    private final String URL = config.getString("jdbc.url");
    private final String USER_NAME = config.getString("jdbc.username");
    private final String PASSWORD = config.getString("jdbc.password");
    
    private static Connections instance = new Connections();
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
        return instance;
    }
    
    
    /**
     * Method get's connection
     * @return
     */
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
