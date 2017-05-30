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

import com.baxi.agrohelper.model.WorkName;

/** 
 * Implementation of a Data Access Object for {@code WorkName} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class WorkNameDao implements GenericDaoInterface<WorkName, Integer> {

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code WorkNameDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public WorkNameDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public WorkName persist(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public WorkName update(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public WorkName findById(Integer id) {
		return entityManager.find(WorkName.class, id);
	}

	@Override
	public WorkName delete(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	@Override
	public List<WorkName> findAll() {
		TypedQuery<WorkName> query = entityManager.createQuery("SELECT w FROM com.baxi.agrohelper.model.WorkName w", WorkName.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<WorkName> workNames = findAll();
		for(WorkName workName : workNames){
			delete(workName);
		}
		
	}

	
	
}
