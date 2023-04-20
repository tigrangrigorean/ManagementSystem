package com.management.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import com.management.configuration.Configurations;
import com.management.model.PassInTrip;
import com.management.model.Passenger;
import com.management.model.Trip;

import jakarta.persistence.TypedQuery;


public class PassengerService {
	
	Configuration config;
	SessionFactory sessionFactory;
	
	
	/**
	 * Constructor init configs
	 */
	
	public PassengerService() {
	config = Configurations.getConfiguration();
	sessionFactory = Configurations.getSessionFactory();
	}
	
	/**
	 * Method gets Passenger by ID
	 * @param id
	 * @return
	 */
	public Passenger getById(int id) {	 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 Passenger passenger = session.get(Passenger.class, id);
	     session.getTransaction().commit();
	     session.close();
	     return passenger;
	 }
	
	
	/**
	 * Method get's all passengers from table
	 * @return
	 */
	public List<Passenger> getAll() {
		 Session session = sessionFactory.openSession();
		 Query query = session.createQuery("FROM Passenger");
		 session.beginTransaction();
		 List<Passenger> passengersList = query.list();
	     session.getTransaction().commit();
	     session.close();
	     return passengersList;
	}
	
	/**
	 * Method gets Passengers
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
    public	List<Passenger> get(int offset, int perPage, String sort) {
    	Session session = sessionFactory.openSession();
    	String jpqlQuery = "SELECT p FROM Passenger p WHERE p.id >= :offset ORDER BY p." + sort;
    	session.beginTransaction();
    	TypedQuery<Passenger> typedQuery = session.createQuery(jpqlQuery,Passenger.class);
    	typedQuery.setParameter("offset", offset);
    	typedQuery.setMaxResults(perPage);
    	List<Passenger> passengersList = typedQuery.getResultList();
    	session.getTransaction().commit();
    	session.close();
    	return passengersList;
    }
    
    /**
     * Method saves new passenger
     * @param passenger
     * @return
     */
    public Passenger save(Passenger passenger) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(passenger);
    	session.getTransaction().commit();
    	session.close();
		return passenger;
    }
    
    /**
     * Method updates passenger
     * @param id
     * @param passenger
     * @return
     */
    public Passenger update(int id,Passenger passenger) {
    	Session session = sessionFactory.openSession();
    	
    	session.beginTransaction();
    	passenger.setId(id);
    	session.update(passenger);
    	session.getTransaction().commit();
    	session.close();
		return passenger;
    }
    
    /**
     * Method deletes by ID
     * @param id
     */
    public void delete(int id) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.delete(getById(id));
    	session.getTransaction().commit();
    	session.close();
    }
    
    /**
     * Method returns passengers of Trip by Trip Number
     * @param tripNumber
     * @return
     */
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        String jpqlQuery = "SELECT p FROM Passenger p JOIN PassInTrip pt ON p.id = pt.passengerId WHERE pt.tripId = :tripNumber";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TypedQuery<Passenger> typedQuery = session.createQuery(jpqlQuery, Passenger.class);
        typedQuery.setParameter("tripNumber", tripNumber);
        List<Passenger> passengersOfTripList = typedQuery.getResultList();
        session.getTransaction().commit();
        session.close();
        return passengersOfTripList;
    }

    /**
     * Method register new Trip
     * @param tripId
     * @param trip
     * @param passenger
     */
    public void registerTrip(int tripId,Trip trip, Passenger passenger) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(passenger);
    	trip.setId(tripId);
    	session.save(trip);
    	
    	PassInTrip passInTrip = new PassInTrip(trip.getId(),passenger.getId(),"10,10.2023","5a");
    	session.save(passInTrip);
    	session.getTransaction().commit();
    	session.close();
    }
    
    /**
     * Method cancels Trip
     * @param passengerId
     * @param tripNumber
     */
    public void cancelTrip(int passengerId, int tripNumber) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	Trip trip = session.get(Trip.class, tripNumber);
    	Passenger passenger = session.get(Passenger.class, passengerId);
    	PassInTrip passInTrip = session.get(PassInTrip.class, tripNumber);
    	
    	session.delete(trip);
    	session.delete(passenger);
    	session.delete(passInTrip);
    	
    	session.getTransaction().commit();
    	session.close();
    }
    
    
	 
	/**
	 * Method closes All Configs
	 */
	 public void closeAllConfigs() {
		 Configurations.closeSessionFactory();
		 Configurations.closeConfiguration();
	 }

	@Override
	protected void finalize() throws Throwable {
		try { 
			closeAllConfigs();
		}finally {
		super.finalize();
		}
	}
	 
	 
	
}
