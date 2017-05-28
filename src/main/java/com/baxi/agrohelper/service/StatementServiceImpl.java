package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.dao.StatementDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;

public class StatementServiceImpl implements StatementService {

	private GenericDaoInterface<FStatement, Integer> statementDao;
	
	public StatementServiceImpl(GenericDaoInterface<FStatement, Integer> statementDao){
		this.statementDao = statementDao;
	}
	
	@Override
	public FStatement createStatement(LocalDate date, double expenses, double income, double profit) {
		FStatement statement = new FStatement(date, expenses, income, profit);
		statementDao.persist(statement);
		return statement;
	}

	@Override
	public void createStatement(FStatement statement) {
		statementDao.persist(statement);
	}

	@Override
	public void updateStatement(FStatement statement) {
		statementDao.update(statement);
	}

	@Override
	public void removeStatement(int id) {
		FStatement statement = statementDao.findById(id);
		statementDao.delete(statement);
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
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getWorkPrice();
		}
		return expenses;
	}

	@Override
	public double countIncomeForOrchard(Orchard orchard) {
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
		double profit = countIncomeForOrchard(orchard) - countExpensesForOrchard(orchard);
		return profit;
	}

}
