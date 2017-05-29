package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.Crop;

/**
 * 
 * Service using Data Access Object to reach the database, manages the {@code Crop} entities.
 * 
 * @author Gergely Szabó
 *
 */
public interface CropService {
	
	/**
	 * Method for persisting a {@link com.baxi.agrohelper.model.Crop} object.
	 * @param crop the Crop object to be persisited
	 * @return the created Crop object
	 */
	public Crop createCrop(Crop crop);
	
	/**
	 * Method for updating a {@link com.baxi.agrohelper.model.Crop} object.
	 * @param crop the target Crop object
	 * @return the updated Crop object
	 */
	public Crop updateCrop(Crop crop);
	
	/**
	 * Method for removing a {@link com.baxi.agrohelper.model.Crop} object from the database.
	 * @param id id of the target Crop object
	 * @return the removed Crop object
	 */
	public Crop removeCrop(int id);
	
	/**
	 * Method for finding a {@link com.baxi.agrohelper.model.Crop} object.
	 * 
	 * @param id id of the target Crop object
	 * @return target Crop object if present, null otherwise
	 */
	public Crop findCropById(int id);
	
	/**
	 * Method for finding all {@link com.baxi.agrohelper.model.Crop} objects.
	 * 
	 * @return List with all of the Crop objects.
	 */
	public List<Crop> findAllCrops();
	
	/**
	 * Method for removing all {@link com.baxi.agrohelper.model.Crop} objects that have the same {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param id of the target Orchard
	 * @return List with the deleted Crops
	 */
	public List<Crop> deleteAllCropsForOrchard(int id);

}

