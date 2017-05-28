package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.Variety;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface VarietyService {
	
	/**
	 * Method for persisting a {@link com.baxi.agrohelper.model.Variety} object.
	 * @param variety the Variety object to be persisited
	 * @return the created Variety object
	 */
	public Variety createVariety(Variety variety);
	
	/**
	 * Method for updating a {@link com.baxi.agrohelper.model.Variety} object.
	 * @param variety the target Variety object
	 * @return the updated Variety object
	 */
	public Variety updateVariety(Variety variety);
	
	/**
	 * Method for removing a {@link com.baxi.agrohelper.model.Variety} object.
	 * @param id id of the target Variety object
	 * @return the removed Variety object
	 */
	public Variety removeVariety(int id);
	
	/**
	 * Method for finding a {@link com.baxi.agrohelper.model.Variety} object.
	 * 
	 * @param id id of the target Variety object
	 * @return target Variety object if present, null otherwise
	 */
	public Variety findVarietyById(int id);
	
	/**
	 * Method for finding all {@link com.baxi.agrohelper.model.Variety} objects.
	 * 
	 * @return List with all of the Variety objects.
	 */
	public List<Variety> findAllVarieties();
	
}
