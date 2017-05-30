package com.baxi.agrohelper.dao;

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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.Orchard;

/**
 * Implementation of a Data Access Object for {@code Orchard} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class OrchardDao implements GenericDaoInterface<Orchard, Integer> {
	
	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code OrchardDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public OrchardDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Override
	public Orchard persist(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public Orchard update(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public Orchard findById(Integer id) {
		Orchard orchard = entityManager.find(Orchard.class, id);
		return orchard;
	}

	@Override
	public Orchard delete(Orchard entity) {	
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<Orchard> findAll() {
		TypedQuery<Orchard> query = entityManager.createQuery("SELECT o FROM com.baxi.agrohelper.model.Orchard o", Orchard.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Orchard> orchardList = findAll();
		for(Orchard orchard : orchardList){
			delete(orchard);
		}
	}

}
