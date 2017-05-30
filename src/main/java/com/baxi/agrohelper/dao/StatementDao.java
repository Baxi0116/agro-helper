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

import com.baxi.agrohelper.model.FStatement;

/**
 * Implementation of a Data Access Object for {@code FStatement} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class StatementDao implements GenericDaoInterface<FStatement, Integer> {

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code StatementDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public StatementDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public FStatement persist(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public FStatement update(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public FStatement findById(Integer id) {
		return entityManager.find(FStatement.class, id);
	}

	@Override
	public FStatement delete(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<FStatement> findAll() {
		TypedQuery<FStatement> query = entityManager.createQuery("SELECT s from com.baxi.agrohelper.model.FStatement s", FStatement.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<FStatement> list = findAll();
		for(FStatement statement : list){
			delete(statement);
		}
	}
	
}
