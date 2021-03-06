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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Orchard;

/**
 * 
 * Implementation of the {@code OrchardService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class OrchardServiceImpl implements OrchardService{
	
	/**
	 * Static logger for debug purposes.
	 */
	private static Logger logger = LoggerFactory.getLogger(OrchardServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<Orchard, Integer> orchardDao;
	
	/**
	 * Constructs a newly allocated {@code OrchardServiceImpl} object, and initializes its DAO.
	 * 
	 * @param orchardDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public OrchardServiceImpl(GenericDaoInterface<Orchard, Integer> orchardDao){
		this.orchardDao = orchardDao;
	}

	/**
	 * Implementation of the {@code removeOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param id id of the target Orchard
	 * @return the removed Orchard
	 */
	@Override
	public Orchard removeOrchard(int id) {
		Orchard orchard = orchardDao.findById(id);
		logger.warn("Removing orchard {}", orchard.getOrchardName());
		orchardDao.delete(orchard);
		return orchard;
	}

	/**
	 * Implementation of the {@code findOrchardById} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param id Orchard to be found
	 * @return the found Orchard if present, null otherwise
	 */
	@Override
	public Orchard findOrchardById(int id) {
		return orchardDao.findById(id);
	}

	/**
	 * Implementation of the {@code findAllOrchards} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @return List of all orchard objects
	 */
	@Override
	public List<Orchard> findAllOrchards() {
		return orchardDao.findAll();

	}
	
	/**
	 * Implementation of the {@code updateOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param orchard Orchard to be updated
	 * @return the updated Orchard
	 */
	@Override
	public Orchard updateOrchard(Orchard orchard) {
		orchardDao.update(orchard);
		return orchard;
	}

	/**
	 * Implementation of the {@code createOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param orchard orchard to create
	 * @return the created Orchard
	 */
	@Override
	public Orchard createOrchard(Orchard orchard) {
		orchardDao.persist(orchard);
		return orchard;
	}
}
