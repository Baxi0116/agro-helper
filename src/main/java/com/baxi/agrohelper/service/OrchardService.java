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

import com.baxi.agrohelper.model.Orchard;

/**
 * 
 * Service using Data Access Object to reach the database, manages the {@code Orchard} entities.
 * 
 * @author Gergely Szabó
 *
 */
public interface OrchardService {
	
	/**
	 * Method to create an {@link com.baxi.agrohelper.model.Orchard} in the database from an existing object.
	 * 
	 * @param orchard the target Orchard
	 * @return the created Orchard
	 */
	public Orchard createOrchard(Orchard orchard);
	
	/**
	 * Method to update an {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param orchard the target Orchard
	 * @return the updated Orchard
	 */	
	public Orchard updateOrchard(Orchard orchard);
	
	/**
	 * Method to delete an {@link com.baxi.agrohelper.model.Orchard} from the database.
	 * 
	 * @param id id of the target Orchard
	 * @return the deleted Orchard
	 */
	public Orchard removeOrchard(int id);
	
	/**
	 * Method to find an {@link com.baxi.agrohelper.model.Orchard} in the database by its id.
	 * 
	 * @param id id of the target Orchard
	 * @return the found Orchard if present, or null if not
	 */
	public Orchard findOrchardById(int id);
	
	/**
	 * Method to find all {@link com.baxi.agrohelper.model.Orchard} objects in the database.
	 * 
	 * @return List with the found Orchard objects
	 */
	public List<Orchard> findAllOrchards();
	
}
