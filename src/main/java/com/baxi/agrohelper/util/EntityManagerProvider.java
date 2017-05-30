package com.baxi.agrohelper.util;

/*-
 * #%L
 * agro-helper
 * %%
 * Copyright (C) 2017 University of Debrecen, Faculty of Informatics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

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
