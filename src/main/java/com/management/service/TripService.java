package com.management.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.management.configuration.Configurations;
import com.management.model.Trip;

import jakarta.persistence.TypedQuery;

public class TripService {
	
	Configuration config;
	SessionFactory sessionFactory;
	
	/**
	 * Method init configs
	 */
	public TripService() {
	config = Configurations.getConfiguration();
	sessionFactory = Configurations.getSessionFactory();
	}
	
	/**
	 * Method gets Trip by ID
	 * @param id
	 * @return
	 */
	public Trip getById(int id) {	 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 Trip trip = session.get(Trip.class, id);
	     session.getTransaction().commit();
	     session.close();
	     return trip;
	 }
	
	
	/**
	 * Method get's all Trips from table
	 * @return
	 */
	public List<Trip> getAll() {
		 Session session = sessionFactory.openSession();
		 Query query = session.createQuery("FROM Trip");
		 session.beginTransaction();
		 List<Trip> tripList = query.list();
	     session.getTransaction().commit();
	     session.close();
	     return tripList;
	}
	
	/**
	 * Method gets Trips
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
    public	List<Trip> get(int offset, int perPage, String sort) {
    	Session session = sessionFactory.openSession();
    	String jpqlQuery = "SELECT c FROM Trip c WHERE c.id >= :offset ORDER BY p." + sort;
    	session.beginTransaction();
    	TypedQuery<Trip> typedQuery = session.createQuery(jpqlQuery,Trip.class);
    	typedQuery.setParameter("offset", offset);
    	typedQuery.setMaxResults(perPage);
    	List<Trip> tripList = typedQuery.getResultList();
    	session.getTransaction().commit();
    	session.close();
    	return tripList;
    }
    
    /**
     * Method saves new Trip
     * @param trip
     * @return
     */
    public Trip save(Trip trip) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(trip);
    	session.getTransaction().commit();
    	session.close();
		return trip;
    }
    
    /**
     * Method updates Trip
     * @param id
     * @param trip
     * @return
     */
    public Trip update(int id,Trip trip) {
    	Session session = sessionFactory.openSession();
    	
    	session.beginTransaction();
    	trip.setId(id);
    	session.update(trip);
    	session.getTransaction().commit();
    	session.close();
		return trip;
    }
    
    /**
     * Method deletes Trip by ID
     * @param id
     */
    public void delete(int id) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.delete(session.get(Trip.class,id));
    	session.getTransaction().commit();
    	session.close();
    }
    
    /**
     * Method gets Trips From city
     * @param city
     * @return
     */
    public List<Trip> getTripsFrom(String city) {
    	 Session session = sessionFactory.openSession();
		 Query query = session.createQuery("Select t FROM Trip t WHERE t.townFrom = :city");
		 session.beginTransaction();
		 query.setParameter("city", city);
		 List<Trip> tripList = query.list();
	     session.getTransaction().commit();
	     session.close();
	     return tripList;
    }
    
    /**
     * Method gets Trips To city
     * @param city
     * @return
     */
    public List<Trip> getTripsTo(String city) {
    	 Session session = sessionFactory.openSession();
		 Query query = session.createQuery("Select t FROM Trip t WHERE t.townTo = :city");
		 session.beginTransaction();
		 query.setParameter("city", city);
		 List<Trip> tripList = query.list();
	     session.getTransaction().commit();
	     session.close();
	     return tripList;
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
