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
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.WorkName;

/**
 * 
 * Implementation of the {@code WorkNameService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class WorkNameServiceImpl implements WorkNameService {

	/**
	 * Static logger for debug purposes.
	 */
	private static Logger logger = LoggerFactory.getLogger(WorkNameServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<WorkName, Integer> workNameDao;
	
	/**
	 * Constructs a newly allocated {@code WorkNameService} object, and initializes its DAO..
	 * 
	 * @param workNameDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public WorkNameServiceImpl(GenericDaoInterface<WorkName, Integer> workNameDao) {
		this.workNameDao = workNameDao;
	}

	@Override
	public WorkName createWorkName(WorkName workName) {
		logger.info("Creating WORKNAME {}", workName.getName());
		workNameDao.persist(workName);
		return workName;
	}

	@Override
	public WorkName deleteWorkName(int id) {
		WorkName workName = workNameDao.findById(id);
		logger.warn("Deleting WORKNAME {}", workName.getName());
		workNameDao.delete(workName);
		return workName;
	}

	@Override
	public WorkName findWorkNameById(int id) {
		return workNameDao.findById(id);
	}

	@Override
	public WorkName updateWorkName(WorkName workName) {
		logger.info("Updating WORKNAME {}", workName.getName());
		workNameDao.update(workName);
		return workName;
		
	}

	@Override
	public List<WorkName> findAllWorkNames() {
		return workNameDao.findAll();
	}

	@Override
	public List<String> getAllWorkNames() {
		List<WorkName> works = findAllWorkNames();
		return works.stream()
					.map(WorkName::getName)
					.distinct()
					.collect(Collectors.toList());
	}

}
