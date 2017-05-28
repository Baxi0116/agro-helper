package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.VarietyName;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface VarietyNameService {
	
	/**
	 * Method for persisting a {@link com.baxi.agrohelper.model.VarietyName} object.
	 * @param varietyName the VarietyName object to be persisited
	 * @return the created VarietyName object
	 */
	public VarietyName createVarietyName(VarietyName varietyName);
	
	/**
	 * Method for removing a {@link com.baxi.agrohelper.model.VarietyName} object from the database.
	 * @param id id of the VarietyName object to be removed
	 * @return the deleted VarietyName object
	 */
	public VarietyName deleteVarietyName(int id);
	
	/**
	 * Method for finding a {@link com.baxi.agrohelper.model.VarietyName} object.
	 * 
	 * @param id id of the target VarietyName object
	 * @return target VarietyName object if present, null otherwise
	 */
	public VarietyName findVarietyNameById(int id);
	
	/**
	 * Method for updating a {@link com.baxi.agrohelper.model.VarietyName} object.
	 * 
	 * @param varietyName target varietyName object
	 * @return the updated VarietyName object
	 */
	public VarietyName updateVariety(VarietyName varietyName);
	
	/**
	 * Method for finding all {@link com.baxi.agrohelper.model.VarietyName} objects.
	 * 
	 * @return List with all of the varietyName objects.
	 */
	public List<VarietyName> findAllVarietyNames();
	
	/**
	 * Method for getting all the names stored in {@code com.baxi.agrohelper.model.VarietyName} objects.
	 * 
	 * @return List with all the names
	 */
	public List<String> getAllVarietyNames();
	
}
