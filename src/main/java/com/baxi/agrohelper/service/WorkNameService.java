package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.WorkName;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface WorkNameService {
	
	/**
	 * Method for persisting a {@link com.baxi.agrohelper.model.WorkName} object.
	 * @param workName the WorkName object to be persisited
	 * @return the created WorkName object
	 */
	public WorkName createWorkName(WorkName workName);
	
	/**
	 * Method for removing a {@link com.baxi.agrohelper.model.WorkName} object from the database.
	 * @param id id of the target WorkName object
	 * @return the removed WorkName object
	 */
	public WorkName deleteWorkName(int id);
	
	/**
	 * Method for finding a {@link com.baxi.agrohelper.model.WorkName} object.
	 * 
	 * @param id id of the target WorkName object
	 * @return target WorkName object if present, null otherwise
	 */
	public WorkName findWorkNameById(int id);
	
	/**
	 * Method for updating a {@link com.baxi.agrohelper.model.WorkName} object.
	 * @param workName the target WorkName object
	 * @return the updated WorkName object
	 */
	public WorkName updateWorkName(WorkName workName);
	
	/**
	 * Method for finding all {@link com.baxi.agrohelper.model.WorkName} objects.
	 * 
	 * @return List with all of the WorkName objects.
	 */
	public List<WorkName> findAllWorkNames();
	
	/**
	 * Method for finding all the names in {@link com.baxi.agrohelper.model.WorkName} objects.
	 * 
	 * @return List with all of the names of WorkName objects.
	 */
	public List<String> getAllWorkNames();
	
}
