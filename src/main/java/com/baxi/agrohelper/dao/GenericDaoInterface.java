package com.baxi.agrohelper.dao;

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
	 */
	public void persist(T entity);
	
	/**
	 * Method for updating a database entity.
	 * 
	 * @param entity the entity to be updated
	 */
	public void update(T entity);
	
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
	 */
	public void delete(T entity);
	
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
