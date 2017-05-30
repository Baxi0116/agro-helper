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

import com.baxi.agrohelper.model.VarietyName;

/**
 * 
 * Service using Data Access Object to reach the database, manages {@code VarietyName} entities.
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
	public VarietyName updateVarietyName(VarietyName varietyName);
	
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
