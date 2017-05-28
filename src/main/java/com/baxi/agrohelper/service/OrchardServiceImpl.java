package com.baxi.agrohelper.service;

import java.time.LocalDate;
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
	
	private static Logger logger = LoggerFactory.getLogger(OrchardServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<Orchard, Integer> orchardDao;
	
	/**
	 * Constructor
	 * 
	 * @param orchardDao {@link com.baxi.agrohelper.dao.OrchardDao} object for initialization
	 */
	public OrchardServiceImpl(GenericDaoInterface<Orchard, Integer> orchardDao){
		this.orchardDao = orchardDao;
	}
	
	/**
	 * Implementation of the {@code createOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param orchardName name of the Orchard
	 * @param plantationYear year of plantation Orchard
	 * @param topographicNumber topographic number of the code Orchard
	 * @param meparCode MEPAR code of the Orchard
	 * @param numberOfTrees number of trees in the code Orchard
	 * 
	 * @return Orchard object created in the database
	 */
	@Override
	public Orchard createOrchard(String orchardName, LocalDate plantationYear, String topographicNumber, String meparCode,
			int numberOfTrees) {
		logger.info("Creating orchard {}", orchardName);
		Orchard orchard = new Orchard();
		orchard.setOrchardName(orchardName);
		orchard.setYearOfPlantation(plantationYear);
		orchard.setTopographicNumber(topographicNumber);
		orchard.setMeparCode(meparCode);
		orchard.setNumberOfTrees(numberOfTrees);
		orchardDao.persist(orchard);
		return orchard;

	}

	/**
	 * Implementation of the {@code removeOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param id id of the target Orchard
	 */
	@Override
	public void removeOrchard(int id) {
		Orchard orchard = orchardDao.findById(id);
		logger.info("Removing orchard {}", orchard.getOrchardName());
		orchardDao.delete(orchard);
	}

	/**
	 * Implementation of the {@code findOrchardById} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param id Orchard to be found
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
	 */
	@Override
	public void updateOrchard(Orchard orchard) {
		orchardDao.update(orchard);
		
	}

	/**
	 * Implementation of the {@code createOrchard} method defined in {@link com.baxi.agrohelper.service.OrchardService}.
	 * 
	 * @param orchard orchard to create
	 */
	@Override
	public void createOrchard(Orchard orchard) {
		orchardDao.persist(orchard);
	}
}
