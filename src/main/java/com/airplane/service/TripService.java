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
import com.airplane.model.Trip;

public class TripService {

	/**
	 * Method gets by id Trip
	 * @param id
	 * @return
	 */
	public Trip getById(int id) {
		Trip trip = null;
		try (Connection connection = Connections.getInstance().getConnection();
			     Statement statement = connection.createStatement()){
			String sql = "SELECT * FROM trip WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			trip = new Trip();
			if(resultSet.next()) {
				trip.setId(resultSet.getInt("id"));
				trip.setCompanyId(resultSet.getInt("company_id"));
				trip.setAircraftModel(resultSet.getString("aircraft_model"));
				trip.setTownFrom(resultSet.getString("town_from"));
				trip.setTownTo(resultSet.getString("town_to"));
				trip.setTimeOut(resultSet.getString("time_out"));
				trip.setTimeIn(resultSet.getString("time_in"));
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
		return trip;
	}
	
	/**
	 * Method gets all Trips from Table
	 * @return
	 */
	public Set<Trip> getAll() {
		 Set<Trip> allTrips = new LinkedHashSet<Trip>();
			try (Connection connection = Connections.getInstance().getConnection();
				     Statement statement = connection.createStatement()) {
				String sql = "SELECT * FROM trip";
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Trip trip = new Trip();
					trip.setId(resultSet.getInt("id"));
					trip.setCompanyId(resultSet.getInt("company_id"));
					trip.setAircraftModel(resultSet.getString("aircraft_model"));
					trip.setTownFrom(resultSet.getString("town_from"));
					trip.setTownTo(resultSet.getString("town_to"));
					trip.setTimeOut(resultSet.getString("time_out"));
					trip.setTimeIn(resultSet.getString("time_in"));
					allTrips.add(trip);
				}
			}  catch (SQLException e) {
				System.out.println("SQL Command Exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
			return allTrips;
	 }
	
	/**
	 * Method returns a Set starting from a certain id
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
	public Set<Trip> get(int offset, int perPage, String sort) {
		Set<Trip> allTrips = new LinkedHashSet<Trip>();
		
		try (Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT * FROM trip WHERE id >= ? ORDER BY " + sort + " LIMIT ?")){
			
			statement.setInt(1, offset);
			statement.setInt(2, perPage);
			
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Trip trip = new Trip();
				trip.setId(resultSet.getInt("id"));
				trip.setCompanyId(resultSet.getInt("company_id"));
				trip.setAircraftModel(resultSet.getString("aircraft_model"));
				trip.setTownFrom(resultSet.getString("town_from"));
				trip.setTownTo(resultSet.getString("town_to"));
				trip.setTimeOut(resultSet.getString("time_out"));
				trip.setTimeIn(resultSet.getString("time_in"));
				allTrips.add(trip);
			}
		} catch (SQLException e) {
			System.out.println("SQL Command Exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		return allTrips;
	}
	 
	/**
	 * Method saves new Trip
	 * @param trip
	 * @return
	 */
	public Trip save(Trip trip) {
		 
		 try (Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("INSERT INTO trip(company_id,aircraft_model,town_from,town_to,time_out,time_in) VALUES(?,?,?,?,?,?)")){
			
			statement.setInt(1, trip.getCompanyId());
			statement.setString(2, trip.getAircraftModel());
			statement.setString(3, trip.getTownFrom());
			statement.setString(4, trip.getTownTo());
			statement.setString(5, trip.getTimeOut());
			statement.setString(6, trip.getTimeIn());
			statement.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("SQL Command Exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		  return trip;
	 }
	
	/**
	 * Method updates trip by ID
	 * @param id
	 * @param trip
	 * @return
	 */
	 public Trip update(int id,Trip trip) {
		 		 
		 try (Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("UPDATE trip SET company_id = ?,aircraft_model = ?,town_from = ?,town_to = ?, time_out = ?, time_in = ? WHERE id = ")){
			
			Statement stResult =  connection.createStatement();
			ResultSet rs = stResult.executeQuery("SELECT * FROM trip WHERE id = " + id);
			if(rs.next()) {
			statement.setInt(1, trip.getCompanyId());
			statement.setString(2, trip.getAircraftModel());
			statement.setString(3, trip.getTownFrom());
			statement.setString(4, trip.getTownTo());
			statement.setString(5, trip.getTimeOut());
			statement.setString(6, trip.getTimeIn());
			statement.executeUpdate();
			}else {
				throw new NoSuchElementException();
			}
		}  catch (SQLException e) {
			System.out.println("SQL command exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		 return trip;
	 }
	
	 /**
	  * Method removes trip by id
	  * @param tripId
	  */
	 public void delete(int tripId) {
		 try (Connection connection = Connections.getInstance().getConnection();
			     Statement statement = connection.createStatement()){
				ResultSet rs = statement.executeQuery("SELECT * FROM trip WHERE id = " + tripId);
				if(rs.next()) {
				String sql = "DELETE FROM trip WHERE id = " + tripId;
				statement.executeUpdate(sql);
				}else {
					throw new NoSuchElementException();
				}
			}  catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NoSuchElementException b) {
				throw new NoSuchElementException("Can't find element in database");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");		
			}
	 }
	 
	 /**
	  * Method Gets all Trips from city
	  * @param city
	  * @return
	  */
	 public List<Trip> getTripsFrom(String city) {
		  
		  List<Trip> tripsFromList = new ArrayList<Trip>();
		  
		  Trip trip = null;
			try (Connection connection = Connections.getInstance().getConnection();
				     Statement statement = connection.createStatement()){
				String sql = "SELECT * FROM trip WHERE town_from = " + city;
				ResultSet resultSet = statement.executeQuery(sql);
				
				trip = new Trip();
				while(resultSet.next()) {
					trip.setId(resultSet.getInt("id"));
					trip.setCompanyId(resultSet.getInt("company_id"));
					trip.setAircraftModel(resultSet.getString("aircraft_model"));
					trip.setTownFrom(resultSet.getString("town_from"));
					trip.setTownTo(resultSet.getString("town_to"));
					trip.setTimeOut(resultSet.getString("time_out"));
					trip.setTimeIn(resultSet.getString("time_in"));
					tripsFromList.add(trip);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
			return tripsFromList;
		}
		  
	  /**
	   *  Method Gets all Trips to city
	   * @param city
	   * @return
	   */
	 public List<Trip> getTripsTo(String city) {
		  
		  List<Trip> tripsToList = new ArrayList<Trip>();
		  
		  Trip trip = null;
			try(Connection connection = Connections.getInstance().getConnection();
		     Statement statement = connection.createStatement()) {
				String sql = "SELECT * FROM trip WHERE town_to = " + city;
				ResultSet resultSet = statement.executeQuery(sql);
				
				trip = new Trip();
				while(resultSet.next()) {
					trip.setId(resultSet.getInt("id"));
					trip.setCompanyId(resultSet.getInt("company_id"));
					trip.setAircraftModel(resultSet.getString("aircraft_model"));
					trip.setTownFrom(resultSet.getString("town_from"));
					trip.setTownTo(resultSet.getString("town_to"));
					trip.setTimeOut(resultSet.getString("time_out"));
					trip.setTimeIn(resultSet.getString("time_in"));
					tripsToList.add(trip);
				}
				
			} catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
			return tripsToList;
		}
	
}
