package com.baxi.agrohelper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.AgWork;

/**
 * 
 * Implementation of the {@code WorkService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class WorkServiceImpl implements WorkService {

	/**
	 * Static logger for debug purposes.
	 */
	private static Logger logger = LoggerFactory.getLogger(WorkServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<AgWork, Integer> workDao;
	
	/**
	 * Constructs a newly allocated {@code WorkServiceImpl} object, and initializes its DAO..
	 * 
	 * @param workDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public WorkServiceImpl(GenericDaoInterface<AgWork, Integer> workDao) {
		this.workDao = workDao;
	}

	@Override
	public AgWork createWork(AgWork work) {
		logger.info("Creating AGWORK {}", work.getWorkDesignation());
		workDao.persist(work);
		return work;
	}

	@Override
	public AgWork deleteWork(int id) {
		AgWork work = workDao.findById(id);
		logger.warn("Deleting AGWORK {}", work.getWorkDesignation());
		workDao.delete(work);
		return work;
		
	}

	@Override
	public AgWork findWorkById(int id) {
		return workDao.findById(id);
	}

	@Override
	public AgWork updateWork(AgWork work) {
		logger.info("Updating AGWORK {}", work.getWorkDesignation());
		workDao.update(work);
		return work;
	}

	@Override
	public List<AgWork> findAllWorks() {
		return workDao.findAll();
	}
	
}
