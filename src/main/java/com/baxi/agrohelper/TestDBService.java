package com.baxi.agrohelper;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.baxi.agrohelper.model.Orchard;


public class TestDBService {

public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgroHelperPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		DBService dbservice = new DBService(em);
		
		em.getTransaction().begin();
		Orchard orchard = dbservice.createOrchard("Teszt", LocalDate.now(), "asd123", "asd123", 5);		
		em.getTransaction().commit();
		
		
		em.close();
		emf.close();
	}
	
}
