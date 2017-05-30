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

import com.baxi.agrohelper.model.Crop;

/**
 * Implementation of a Data Access Object for {@code Crop} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class CropDao implements GenericDaoInterface<Crop, Integer>{

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code CropDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public CropDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	/**
	 * Method for persisting a Crop entity.
	 * 
	 * @param entity 	entity to be persisted
	 * @return the perisited entity
	 */
	@Override
	public Crop persist(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	/**
	 * Method for updating a Crop entity.
	 * 
	 * @param entity	 entity to be updated
	 * @return the updated Crop entity;
	 */
	@Override
	public Crop update(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	/**
	 * Method for looking up a Crop object in the database.
	 * 
	 * @param id 	id of the target Crop object
	 * 
	 * @return crop	the target Crop object if exists
	 */
	@Override
	public Crop findById(Integer id) {
		Crop crop = entityManager.find(Crop.class, id);
		return crop;
	}

	/**
	 * Method for deleting a Crop object from the database.
	 * 
	 * @param entity	Crop entity to be removed
	 * @return the removed Crop entity
	 */
	@Override
	public Crop delete(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	/**
	 * Method for getting all Crop entities from the database.
	 */
	@Override
	public List<Crop> findAll() {
		TypedQuery<Crop> query = entityManager.createQuery("SELECT c FROM com.baxi.agrohelper.model.Crop c", Crop.class);
		return query.getResultList();
	}

	/**
	 * Method for deleting all Crop objects from the database.
	 */
	public void deleteAll() {
		List<Crop> cropList = findAll();
		for(Crop crop : cropList){
			delete(crop);
		}
	}

}
