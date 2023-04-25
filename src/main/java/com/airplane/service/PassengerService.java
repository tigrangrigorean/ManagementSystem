package com.airplane.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import com.airplane.connections.Connections;
import com.airplane.model.Passenger;
import com.airplane.model.Trip;

public class PassengerService {
	
	/**
	 * Method gets Passenger by id
	 * @param id
	 * @return
	 */
	public Passenger getById(int id) {
		Passenger passenger = null;
		try (Connection connection = Connections.getInstance().getConnection();
			Statement statement = connection.createStatement()){
			
			String sql = "SELECT * FROM passengers WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			passenger = new Passenger();
			
			if(resultSet.next()) {
				passenger.setId(resultSet.getInt("id"));
				passenger.setName(resultSet.getString("name"));
				passenger.setPhone(resultSet.getString("phone"));
				passenger.setCountry(resultSet.getString("country"));
				passenger.setCity(resultSet.getString("city"));
			}else {
				throw new NoSuchElementException();
			}
		} catch (SQLException a) {
			System.out.println("SQL Command Exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		return passenger;
	}
	
	
	/**
	 * Method gets all passengers from Table
	 * @return
	 */
	public Set<Passenger> getAll() {
		 Set<Passenger> allUsers = new LinkedHashSet<Passenger>();
			try(Connection connection = Connections.getInstance().getConnection();
				     Statement statement = connection.createStatement()) {
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
				System.out.println("SQL Command Exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
			return allUsers;
			
	 }
	
	/**
	 * Method returns a Set starting from a certain id
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
	public Set<Passenger> get(int offset, int perPage, String sort) {
		Set<Passenger> passengers = new LinkedHashSet<Passenger>();
		
		
		try(Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT * FROM passengers WHERE id >= ? ORDER BY " + sort + " LIMIT ?")) {
			statement.setInt(1, offset);
			statement.setInt(2, perPage);
			
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Passenger passenger = new Passenger();
				passenger.setId(resultSet.getInt("id"));
				passenger.setName(resultSet.getString("name"));
				passenger.setPhone(resultSet.getString("phone"));
				passenger.setCountry(resultSet.getString("country"));
				passenger.setCity(resultSet.getString("city"));
				passengers.add(passenger);
			}
		} catch (SQLException e) {
			System.out.println("SQL Command Exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		return passengers;
	}
	 
	
	/**
	 * Method saves new Passenger
	 * @param passenger
	 * @return
	 */
	public Passenger save(Passenger passenger) {
		 
		 try (Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("INSERT INTO passengers(name,phone,country,city) VALUES(?,?,?,?)")){
			
			statement.setString(1, passenger.getName());
			statement.setString(2, passenger.getPhone());
			statement.setString(3, passenger.getCountry());
			statement.setString(4, passenger.getCity());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL command exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
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
		 		 
		 try (Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("UPDATE passengers SET name = ?,phone = ?, country = ?,city = ? WHERE id = " + id)) {
			
			ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM passengers WHERE id = " + id);
			if(rs.next()) {
				statement.setString(1, passenger.getName());
				statement.setString(2, passenger.getPhone());
				statement.setString(3, passenger.getCountry());
				statement.setString(4, passenger.getCity());
				statement.executeUpdate();
			} else {
				throw new NoSuchElementException();
			}
			
			
		} catch (SQLException e) {
			System.out.println("SQL command exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		 return passenger;
	 }
	 
	 /**
	  * Method removes passanger by id
	  * @param passengerId
	  */
	 public void delete(int passengerId) {
		 try (Connection connection = Connections.getInstance().getConnection();
					Statement statement = connection.createStatement()){
				String sql = "DELETE FROM passengers WHERE id = " + passengerId;
				ResultSet rs = statement.executeQuery("SELECT from passengers WHERE id = " + passengerId);
				if(rs.next()) {
				statement.executeUpdate(sql);
				}else {
					throw new NoSuchElementException();
				}
			} catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NoSuchElementException b) {
				throw new NoSuchElementException("Can't find element in database");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			} 
	 }
	 
	 /**
	  * Method get Passengers Of Trip
	  * @param tripNumber
	  * @return
	  */
	 public List<Passenger> getPassengersOfTrip(int tripNumber) {
		 
		 List <Passenger> passengersOfTrip= new ArrayList<Passenger>();
		 try (Connection connection = Connections.getInstance().getConnection();
					Statement statement = connection.createStatement()){
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
				System.out.println("SQL command exception");
			} catch(NoSuchElementException b) {
				throw new NoSuchElementException("Can't find element in database");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			} 
		 return passengersOfTrip;
	 }
	 
	 /**
	  * Method register new Trip, and passenger
	  * @param trip
	  * @param passenger
	  */
	 public void registerTrip(Trip trip, Passenger passenger) {
		 try (Connection connection = Connections.getInstance().getConnection();
				 PreparedStatement statementForCreateTrip = connection.prepareStatement("INSERT INTO trip(id,company_id,aircraft_model,town_from,town_to,time_out,time_in) VALUES(?,?,?,?,?,?,?)")) {
			
				statementForCreateTrip.setInt(1, trip.getId());
				statementForCreateTrip.setInt(2, trip.getCompanyId());
				statementForCreateTrip.setString(3, trip.getAircraftModel());
				statementForCreateTrip.setString(4, trip.getTownFrom());
				statementForCreateTrip.setString(5, trip.getTownTo());
				statementForCreateTrip.setString(6, trip.getTimeOut());
				statementForCreateTrip.setString(7, trip.getTimeIn());
				statementForCreateTrip.executeUpdate();
				
				Statement statement = connection.createStatement();
				
				ResultSet resultSet2 = statement.executeQuery("SELECT * FROM passengers ORDER BY id DESC LIMIT 1");
				if(resultSet2.next()) {
				passenger.setId(resultSet2.getInt("id"));
				}
				/**
				 * Calling our save method
				 */
				save(passenger);
				
				String sql2 = "INSERT INTO pass_in_trip (trip_id, passenger_id, date, place) VALUES(?,?,?,?)";
				PreparedStatement statementForPassInTrip = connection.prepareStatement(sql2);
				statementForPassInTrip.setInt(1, trip.getId());
				statementForPassInTrip.setInt(2, passenger.getId() + 1);
				statementForPassInTrip.setString(3, "1900-01-01 08:01:00.000");
				statementForPassInTrip.setString(4, "7a");
				
				statementForPassInTrip.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
	 }
	 
	 /**
	  * Method cancel Trip, deletes passenger and trip
	  * @param passengerId
	  * @param tripNumber
	  */
	 public void cancelTrip(int passengerId, long tripNumber) {
		 
		 try (Connection connection = Connections.getInstance().getConnection();
					Statement statement = connection.createStatement()){
			 String sql = "DELETE from pass_in_trip WHERE trip_id = "  + tripNumber;
			 ResultSet rs = statement.executeQuery("SELECT * FROM pass_in_trip WHERE trip_id = " + tripNumber);
			 statement.executeUpdate(sql);
			 sql = "DELETE FROM trip WHERE id = " + tripNumber;
			 
			 if(rs.next()) {
			 statement.executeUpdate(sql);
			 } else {
				 throw new NoSuchElementException();
			 }
			 /**
			  * Calling our passenger delete method
			  */
			 delete(passengerId);
		} catch (SQLException e) {
			System.out.println("SQL command exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
	 }
	
}
