package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.Crop;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface CropService {

	/**
	 * Method for creating {@link com.baxi.agrohelper.model.Crop} object in the database.
	 * 
	 * @param name name of the Crop
	 * @return Crop object created int the database
	 */
	public Crop createCrop(String name);
	
	public void createCrop(Crop crop);
	
	public void updateCrop(Crop crop);
	
	public void removeCrop(int id);
	
	public Crop findCropById(int id);
	
	public List<Crop> findAllCrops();
	
	public void deleteAllCropsForOrchard(int id);

}

