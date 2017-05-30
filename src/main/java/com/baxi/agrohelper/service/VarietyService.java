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

import com.baxi.agrohelper.model.Variety;

/**
 * 
 * Service using Data Access Object to reach the database, manages {@code Variety} entities.
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
