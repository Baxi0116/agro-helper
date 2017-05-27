package com.baxi.agrohelper.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Provides Data Access Object abstraction for basic CRUD operations on the database.
 * 
 * @author Gergely Szabó
 * 
 */
public interface GenericDaoInterface <T, Id extends Serializable>{

	/*
	 * Method for persisting a database entity.
	 * 
	 * @param entity to be saved in the database
	 */
	public void persist(T entity);
	
	/*
	 * Method for updating a database entity.
	 * 
	 * @param entity to be updated
	 */
	public void update(T entity);
	
	/*
	 * Method for getting an entity from the database.
	 * 
	 * @param id of the target entity
	 */
	public T findById(Id id);
	
	/**
	 * Method for removing an entity from the database.
	 * 
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * Method for getting all entities with the same type.
	 * 
	 * @return List<T> with all T typed entities
	 */
	public List<T> findAll();
	
	/**
	 * Method for removing all entities with the same type from the database.
	 */
	public void deleteAll();
	
}
