package com.baxi.agrohelper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.VarietyName;

/**
 * 
 * Implementation of the {@code VarietyNameService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class VarietyNameServiceImpl implements VarietyNameService {

	private static Logger logger = LoggerFactory.getLogger(VarietyNameServiceImpl.class);
	
	private GenericDaoInterface<VarietyName, Integer> varietyNameDao;
	
	/**
	 * Constructs a newly allocated {@code VarietyNameServiceImpl} object, and initializes its DAO..
	 * 
	 * @param varietyNameDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public VarietyNameServiceImpl(GenericDaoInterface<VarietyName, Integer> varietyNameDao) {
		this.varietyNameDao = varietyNameDao;
	}

	@Override
	public VarietyName createVarietyName(VarietyName varietyName) {
		logger.info("Creating VARIETYNAME {}", varietyName.getName());
		varietyNameDao.persist(varietyName);
		return varietyName;
	}

	@Override
	public VarietyName deleteVarietyName(int id) {
		VarietyName varietyName = varietyNameDao.findById(id);
		logger.warn("Deleting VARIETYNAME {}", varietyName.getName());
		varietyNameDao.delete(varietyName);
		return varietyName;
	}

	@Override
	public VarietyName findVarietyNameById(int id) {
		return varietyNameDao.findById(id);
	}

	@Override
	public VarietyName updateVarietyName(VarietyName varietyName) {
		logger.info("Updating VARIETYNAME {}", varietyName.getName());
		varietyNameDao.update(varietyName);
		return varietyName;
	}

	@Override
	public List<VarietyName> findAllVarietyNames() {
		return varietyNameDao.findAll();
	}

	@Override
	public List<String> getAllVarietyNames() {
		return varietyNameDao.findAll().stream()
						.map(VarietyName::getName)
						.distinct()
						.collect(Collectors.toList());
	}
	
}
