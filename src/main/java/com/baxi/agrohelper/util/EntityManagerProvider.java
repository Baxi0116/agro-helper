package com.baxi.agrohelper.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Provides database connection and {@link javax.persistence.EntityManager} for Data Access Objects.
 * <p>
 * 
 * Creates an {@link javax.persistence.EntityManagerFactory} and an {@link javax.persistence.EntityManager}
 * and provides it to the requesters.
 * 
 * @author Gergely Szabó
 *
 */
public class EntityManagerProvider {

	private static EntityManagerFactory emf;
	
	private static EntityManager em;
	
	static{
		emf = Persistence.createEntityManagerFactory("AgroHelperPersistenceUnit");
		em = emf.createEntityManager();
	}
	
	/**
	 * Provides {@code EntityManager} for the requester.
	 * 
	 * @return EntityManager object initialized by this class
	 */
	public static EntityManager provideEntityManager(){
		return em;
	}
	
	/**
	 * Closes the connection to the database.
	 * <p>
	 * Closes the connection by invoking the {@code close()} method of the {@link javax.persistence.EntityManagerFactory} 
	 * and {@link javax.persistence.EntityManager} classes.
	 */
	public static void closeConnection(){
		em.close();
		emf.close();
	}
	
}
