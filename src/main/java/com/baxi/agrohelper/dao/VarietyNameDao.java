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

import com.baxi.agrohelper.model.VarietyName;

/**
 * Implementation of a Data Access Object for {@code VarietyName} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class VarietyNameDao implements GenericDaoInterface<VarietyName, Integer>{

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	
	/**
	 * Constructs a newly allocated {@code VarietyNameDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public VarietyNameDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public VarietyName persist(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public VarietyName update(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public VarietyName findById(Integer id) {
		return entityManager.find(VarietyName.class, id);
	}

	@Override
	public VarietyName delete(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<VarietyName> findAll() {
		TypedQuery<VarietyName> query = entityManager.createQuery("SELECT v FROM com.baxi.agrohelper.model.VarietyName v",VarietyName.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<VarietyName> varietyNames = findAll();
		for(VarietyName varietyName : varietyNames){
			delete(varietyName);
		}
	}

}
