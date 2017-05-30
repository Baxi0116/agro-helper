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

import com.baxi.agrohelper.model.AgWork;

/**
 * 
 * Service using Data Access Object to reach the database, manages {@code AgWork} entities.
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
