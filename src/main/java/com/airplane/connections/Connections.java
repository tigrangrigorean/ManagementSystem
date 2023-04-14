package com.airplane.connections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connections{
	
    private static final String URL = "jdbc:postgresql://localhost:5432/ManagementSystem";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    /**
     * Method get Connection
     * @return
     */
    public static Connection getConnection() {
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
    public static void closeConnection() {
        if (connection == null) {
            throw new NullPointerException("Connection is null:");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Method read text in CompanyTxt and senc infomation to DB
     */
    public static void readFromCompanyTxt() {

        try {
            getConnection();
            File file = new File("C:/Users/Tiko/Desktop/homework_JDBC/companies.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                    String[] dateList = line.split(",");
                    String a1 = dateList[0];
                    String a2 = dateList[1];
                    PreparedStatement pst = connection.prepareStatement("INSERT INTO company (company_name, founding_date) VALUES(?,?)");
                    pst.setString(1, a1);
                    pst.setString(2, a2);
                line = reader.readLine();
                pst.executeUpdate();
        }
            closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * Method read text in PassengersTxt and senc infomation to DB
     */
    public static void readFromPassengersTxt() {

        try {
            getConnection();
            File file = new File("C:/Users/Tiko/Desktop/homework_JDBC/passengers.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] dateList = line.split(",");
                String a1 = dateList[0];
                String a2 = dateList[1];
                String a3 = dateList[2];
                String a4 = dateList[3];
                PreparedStatement pst = connection.prepareStatement("INSERT INTO passengers (name, phone, country,city) VALUES(?,?,?,?)");
                pst.setString(1, a1);
                pst.setString(2, a2);
                pst.setString(3, a3);
                pst.setString(4, a4);
                line = reader.readLine();
                pst.executeUpdate();
            }
            closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public static void readFromTripTxt() {

        try {
            getConnection();
            File file = new File("C:/Users/Tiko/Desktop/homework_JDBC/trip.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] dateList = line.split(",");
                String a1 = dateList[0];
                String a2 = dateList[1];
                String a3 = dateList[2];
                String a4 = dateList[3];
                String a5 = dateList[4];
                String a6 = dateList[5];
                String a7 = dateList[6];
                PreparedStatement pst = connection.prepareStatement("INSERT INTO trip (id,company_id,airplane_name,town_from,town_to,time_out,time_in) VALUES(?,?,?,?,?,?,?);");
                pst.setInt(1, Integer.parseInt(a1));
                pst.setInt(2, Integer.parseInt(a2));
                pst.setString(3, a3);
                pst.setString(4, a4);
                pst.setString(5, a5);
                pst.setString(6, a6);
                pst.setString(7, a7);
                line = reader.readLine();
                pst.executeUpdate();
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(IOException c) {
        	c.printStackTrace();
        }
    }

}
