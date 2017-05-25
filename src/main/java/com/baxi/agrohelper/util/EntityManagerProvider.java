package com.baxi.agrohelper.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static EntityManagerFactory emf;
	
	private static EntityManager em;
	
	static{
		emf = Persistence.createEntityManagerFactory("AgroHelperPersistenceUnit");
		em = emf.createEntityManager();
	}
	
	public static EntityManager provideEntityManager(){
		return em;
	}
	
	public static void closeConnection(){
		em.close();
		emf.close();
	}
	
}
