package com.management.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.management.configuration.Configurations;
import com.management.model.Company;


import jakarta.persistence.TypedQuery;

public class CompanyService {
	
	Configuration config;
	SessionFactory sessionFactory;
	
	/**
	 * Method init configs
	 */
	public CompanyService() {
	config = Configurations.getConfiguration();
	sessionFactory = Configurations.getSessionFactory();
	}
	
	/**
	 * Method gets Company by ID
	 * @param id
	 * @return
	 */
	public Company getById(int id) {	 
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 Company company = session.get(Company.class, id);
	     session.getTransaction().commit();
	     session.close();
	     return company;
	 }
	
	
	/**
	 * Method get's all Companies from table
	 * @return
	 */
	public List<Company> getAll() {
		 Session session = sessionFactory.openSession();
		 Query query = session.createQuery("FROM Company");
		 session.beginTransaction();
		 List<Company> companyList = query.list();
	     session.getTransaction().commit();
	     session.close();
	     return companyList;
	}
	
	/**
	 * Method gets Companies
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
    public	List<Company> get(int offset, int perPage, String sort) {
    	Session session = sessionFactory.openSession();
    	String jpqlQuery = "SELECT c FROM Company c WHERE c.id >= :offset ORDER BY p." + sort;
    	session.beginTransaction();
    	TypedQuery<Company> typedQuery = session.createQuery(jpqlQuery,Company.class);
    	typedQuery.setParameter("offset", offset);
    	typedQuery.setMaxResults(perPage);
    	List<Company> companyList = typedQuery.getResultList();
    	session.getTransaction().commit();
    	session.close();
    	return companyList;
    }
    
    /**
     * Method saves new Company
     * @param company
     * @return
     */
    public Company save(Company company) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(company);
    	session.getTransaction().commit();
    	session.close();
		return company;
    }
    
    /**
     * Method updates Company
     * @param id
     * @param company
     * @return
     */
    public Company update(int id,Company company) {
    	Session session = sessionFactory.openSession();
    	
    	session.beginTransaction();
    	company.setId(id);
    	session.update(company);
    	session.getTransaction().commit();
    	session.close();
		return company;
    }
    
    /**
     * Method deletes Company by ID
     * @param id
     */
    public void delete(int id) {
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.delete(session.get(Company.class,id));
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
