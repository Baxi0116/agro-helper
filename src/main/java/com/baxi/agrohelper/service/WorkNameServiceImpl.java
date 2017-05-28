package com.baxi.agrohelper.service;

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
	 * Constructor.
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
	public WorkName updateWork(WorkName workName) {
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
