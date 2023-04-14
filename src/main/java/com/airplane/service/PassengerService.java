package com.airplane.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.airplane.connections.Connections;
import com.airplane.model.Passenger;

public class PassengerService {
	
	/**
	 * Method gets Passenger by id
	 * @param id
	 * @return
	 */
	public Passenger getById(int id) {
		Passenger passenger = null;
		try {
			Connection connection = Connections.getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM passengers WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			passenger = new Passenger();
			
			if(resultSet.next()) {
				passenger.setId(resultSet.getInt("id"));
				passenger.setName(resultSet.getString("name"));
				passenger.setPhone(resultSet.getString("phone"));
				passenger.setCountry(resultSet.getString("country"));
				passenger.setCity(resultSet.getString("city"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connections.closeConnection();
		}
		return passenger;
	}
	
	
	/**
	 * Method gets all passengers from Table
	 * @return
	 */
	public Set<Passenger> getAll() {
		 Set<Passenger> allUsers = new LinkedHashSet<Passenger>();
			try {
				Connection connection = Connections.getConnection();
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM passengers";
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Passenger passenger = new Passenger();
					passenger.setId(resultSet.getInt("id"));
					passenger.setName(resultSet.getString("name"));
					passenger.setPhone(resultSet.getString("phone"));
					passenger.setCountry(resultSet.getString("country"));
					passenger.setCity(resultSet.getString("city"));
					allUsers.add(passenger);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return allUsers;
			
	 }
	 
	
	/**
	 * Method saves new Passenger
	 * @param passenger
	 * @return
	 */
	public Passenger save(Passenger passenger) {
		 
		 try {
			Connection connection = Connections.getConnection();
			String sql = "INSERT INTO passengers(name,phone,country,city) VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, passenger.getName());
			statement.setString(2, passenger.getPhone());
			statement.setString(3, passenger.getCountry());
			statement.setString(4, passenger.getCity());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return passenger;
	 }
	
	/**
	 * Method updates passanger by ID
	 * @param id
	 * @param passenger
	 * @return
	 */
	 public Passenger update(int id,Passenger passenger) {
		 		 
		 try {
			Connection connection = Connections.getConnection();
			String sql = "UPDATE passengers SET name = ?,phone = ?, country = ?,city = ? WHERE id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, passenger.getName());
			statement.setString(2, passenger.getPhone());
			statement.setString(3, passenger.getCountry());
			statement.setString(4, passenger.getCity());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return passenger;
	 }
	 
	 /**
	  * Method removes passanger by id
	  * @param passengerId
	  */
	 public void delete(int passengerId) {
		 try {
				Connection connection = Connections.getConnection();
				Statement statement = connection.createStatement();
				String sql = "DELETE FROM passengers WHERE id = " + passengerId;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 /**
	  * Method get Passengers Of Trip
	  * @param tripNumber
	  * @return
	  */
	 public List<Passenger> getPassengersOfTrip(int tripNumber) {
		 
		 List <Passenger> passengersOfTrip= new ArrayList<Passenger>();
		 try {
				Connection connection = Connections.getConnection();
				Statement statement = connection.createStatement();
				String sql = "SELECT p.id, p.name, p.phone, p.country, p.city "
						+ "FROM passengers p "
						+ "JOIN pass_in_trip pit ON p.id = pit.passenger_id "
						+ "WHERE pit.trip_id = " + tripNumber;
				ResultSet resultSet = statement.executeQuery(sql);
				
				while(resultSet.next()) {
					Passenger passenger = new Passenger(resultSet.getString("name"),resultSet.getString("phone"),resultSet.getString("country"),resultSet.getString("city"));
					passenger.setId(resultSet.getInt("id"));
					passengersOfTrip.add(passenger);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return passengersOfTrip;
	 }
	 
	 
	
}
