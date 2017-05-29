package com.baxi.agrohelper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Variety;

/**
 * 
 * Implementation of the {@code VarietyService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class VarietyServiceImpl implements VarietyService {

	private static Logger logger = LoggerFactory.getLogger(VarietyNameServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<Variety, Integer> varietyDao;
	
	/**
	 * Constructs a newly allocated {@code VarietyServiceImpl} object, and initializes its DAO..
	 * 
	 * @param varietyDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public VarietyServiceImpl(GenericDaoInterface<Variety, Integer> varietyDao) {
		this.varietyDao = varietyDao;
	}

	@Override
	public Variety createVariety(Variety variety) {
		logger.info("Creating VARIETY {}", variety.getVarietyName());
		varietyDao.persist(variety);
		return variety;
	}

	@Override
	public Variety updateVariety(Variety variety) {
		logger.info("Updating VARIETY {}", variety.getVarietyName());
		varietyDao.update(variety);
		return variety;
	}

	@Override
	public Variety removeVariety(int id) {
		Variety variety = varietyDao.findById(id);
		logger.warn("Deleting VARIETY {}", variety.getVarietyName());
		varietyDao.delete(variety);
		return variety;
	}

	@Override
	public Variety findVarietyById(int id) {
		return varietyDao.findById(id);
	}

	@Override
	public List<Variety> findAllVarieties() {
		return varietyDao.findAll();
	}
}
