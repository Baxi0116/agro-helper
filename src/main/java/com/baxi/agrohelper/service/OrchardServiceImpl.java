package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;

public class OrchardServiceImpl implements OrchardService{
	
	private static Logger logger = LoggerFactory.getLogger(OrchardServiceImpl.class);
	
	private OrchardDao orchardDao;
	
	public OrchardServiceImpl(OrchardDao orchardDao){
		this.orchardDao = orchardDao;
	}
	
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

	@Override
	public void removeOrchard(int id) {
		Orchard orchard = orchardDao.findById(id);
		logger.info("Removing orchard {}", orchard.getOrchardName());
		orchardDao.delete(orchard);
	}

	@Override
	public Orchard findOrchardById(int id) {
		return orchardDao.findById(id);
	}

	@Override
	public List<Orchard> findAllOrchards() {
		return orchardDao.findAll();

	}

	@Override
	public int countExpensesForOrchard(int id) {
		Orchard orchard = orchardDao.findById(id);
		int expenses = 0;
		for(AgWork work : orchard.getWorks()){
			expenses += work.getWorkPrice();
		}
		return expenses;
	}

	@Override
	public int countExpensesForAllOrchard() {
		int expenses = 0;
		for(Orchard orchard : orchardDao.findAll()){
			expenses += countExpensesForOrchard(orchard.getId());
		}	
		return expenses;
	}

	@Override
	public void updateOrchard(Orchard orchard) {
		orchardDao.update(orchard);
		
	}

	@Override
	public void createOrchard(Orchard orchard) {
		orchardDao.persist(orchard);
	}

}
