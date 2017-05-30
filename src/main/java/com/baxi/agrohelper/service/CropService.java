package com.baxi.agrohelper.service;

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

