package com.baxi.agrohelper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;

/**
 * 
 * Implementation of the {@code StatementService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class StatementServiceImpl implements StatementService {
	
	private static Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);

	private GenericDaoInterface<FStatement, Integer> statementDao;
	
	/**
	 * Constructs a newly allocated {@code StatementServiceImpl} object, and initializes its DAO..
	 * 
	 * @param statementDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initializtaion
	 */
	public StatementServiceImpl(GenericDaoInterface<FStatement, Integer> statementDao){
		this.statementDao = statementDao;
	}

	@Override
	public FStatement createStatement(FStatement statement) {
		logger.info("Creating STATEMENT for orchard: {}", statement.getOrchard().getOrchardName());
		statementDao.persist(statement);
		return statement;
	}

	@Override
	public FStatement updateStatement(FStatement statement) {
		logger.info("Updating STATEMENT for orchard: {}", statement.getOrchard().getOrchardName());
		statementDao.update(statement);
		return statement;
	}

	@Override
	public FStatement removeStatement(int id) {
		FStatement statement = statementDao.findById(id);
		logger.warn("Removing STATEMENT for orchard: {}", statement.getOrchard().getOrchardName());
		statementDao.delete(statement);
		return statement;
	}

	@Override
	public FStatement findStatementById(int id) {
		return statementDao.findById(id);
	}

	@Override
	public List<FStatement> findAllStatement() {
		return statementDao.findAll();
	}

	@Override
	public double countExpensesForOrchard(Orchard orchard) {
		logger.info("Counting expenses for Orchard: {}", orchard.getOrchardName());
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getWorkPrice();
		}
		return expenses;
	}

	@Override
	public double countIncomeForOrchard(Orchard orchard) {
		logger.info("Counting income for Orchard: {}", orchard.getOrchardName());
		List<Variety> varietyList = orchard.getVarieties();
		double income = 0;
		for(Variety variety : varietyList){
			double varietyYieldForHectars = variety.getVarietyArea() * variety.getVarietyYield();
			income += (varietyYieldForHectars * variety.getVarietyPrice());
		}
		return income;
	}

	@Override
	public double countProfitForOrchard(Orchard orchard) {
		logger.info("Counting profit for Orchard: {}", orchard.getOrchardName());
		double profit = countIncomeForOrchard(orchard) - countExpensesForOrchard(orchard);
		return profit;
	}

}
