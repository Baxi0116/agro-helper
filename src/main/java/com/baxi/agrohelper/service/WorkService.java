package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.AgWork;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface WorkService {
	
	/**
	 * Method for persisting a {@link com.baxi.agrohelper.model.AgWork} object.
	 * @param work the AgWork object to be persisited
	 * @return the created AgWork object
	 */
	public AgWork createWork(AgWork work);
	
	/**
	 * Method for deleting a {@link com.baxi.agrohelper.model.AgWork} object from the database.
	 * @param id id of the target work
	 * @return the deleted AgWork object
	 */
	public AgWork deleteWork(int id);
	
	/**
	 * Method for finding a {@link com.baxi.agrohelper.model.AgWork} object.
	 * 
	 * @param id id of the target AgWork object
	 * @return target AgWork object if present, null otherwise
	 */
	public AgWork findWorkById(int id);
	
	/**
	 * Method for updated a {@link com.baxi.agrohelper.model.AgWork} object.
	 * @param work the target AgWork object
	 * @return the updated AgWork object
	 */
	public AgWork updateWork(AgWork work);
	
	/**
	 * Method for finding all {@link com.baxi.agrohelper.model.AgWork} objects.
	 * 
	 * @return List with all of the AgWork objects.
	 */
	public List<AgWork> findAllWorks();
	
}
