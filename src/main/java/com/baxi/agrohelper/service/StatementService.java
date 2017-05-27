package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;

public interface StatementService {
	
	public FStatement createStatement(LocalDate date, double expenses, double income, double profit);
	
	public void createStatement(FStatement statement);
	
	public void updateStatement(FStatement statement);
	
	public void removeStatement(int id);
	
	public FStatement findStatementById(int id);
	
	public List<FStatement> findAllStatement();
	
	public double countExpensesForOrchard(Orchard orchard);
	
	public double countIncomeForOrchard(Orchard orchard);
	
	public double countProfitForOrchard(Orchard orchard);
	
}
