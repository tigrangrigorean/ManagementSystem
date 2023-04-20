package com.management.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Configurations {
	
	/**
	 * Private Constructor
	 */
	
	private Configurations() {
		
	}
	
	private static Configurations instance;
	private static Configuration config;
	private static SessionFactory sessionFactory;
	
	/**
	 * Method for gets instance of Configurations class. SINGLETON
	 * @return
	 */
	public static Configurations getInstance() {
		if(instance == null) {
			instance = new Configurations();
			return instance;
		}else {
			return instance;
		}
	}
	
	/**
	 * Method return Configuration
	 * @return
	 */
	public static Configuration getConfiguration() {
		if(config == null) {
			config = new Configuration().configure();
			return config;
		}else {
			return config;
		}
	}
	
	/**
	 * Method closes Configurations
	 */
	public static void closeConfiguration() {
		if(config != null) {
			config = null;
		}
	}
	
	/**
	 * Method gets Session Factory
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = config.buildSessionFactory();
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
	
	/**
	 * Method closes SessionFactory
	 */
	public static void closeSessionFactory() {
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}

}
