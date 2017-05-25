package com.baxi.agrohelper.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

	private static EntityManagerFactory emf;
	
	static{
		emf = Persistence.createEntityManagerFactory("AgroHelperPersistenceUnit");
	}
	
	public static EntityManager provideEntityManager(){
		return emf.createEntityManager();
	}
	
	public static void closeConnection(){
		emf.close();
	}
	
}
