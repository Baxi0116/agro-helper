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

import java.io.Serializable;
import java.util.List;

/**
 * Provides Data Access Object abstraction for basic CRUD operations on the database.
 * 
 * @author Gergely Szabó
 * @param <T> type of the entity this object will manage
 * @param <Id> type of the id of that entity
 * 
 */
public interface GenericDaoInterface <T, Id extends Serializable>{

	/**
	 * Method for persisting a database entity.
	 * 
	 * @param entity the entity to be saved in the database
	 * @return the persisted T entity
	 */
	public T persist(T entity);
	
	/**
	 * Method for updating a database entity.
	 * 
	 * @param entity the entity to be updated
	 * @return the updated T entity
	 */
	public T update(T entity);
	
	/**
	 * Method for getting an entity from the database.
	 * 
	 * @param id  the id of the target entity
	 * @return T a {@code T} typed entity
	 */
	public T findById(Id id);
	
	/**
	 * Method for removing an entity from the database.
	 * 
	 * @param entity the entity to be removed
	 * @return the removed T entity
	 */
	public T delete(T entity);
	
	/**
	 * Method for getting all entities with the same type.
	 * 
	 * @return List {@link java.util.List} with all the entities
	 */
	public List<T> findAll();
	
	/**
	 * Method for removing all entities with the same type from the database.
	 */
	public void deleteAll();
	
}
