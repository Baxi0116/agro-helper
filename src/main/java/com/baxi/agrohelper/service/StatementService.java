package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface StatementService {
	
	/**
	 * Method to persist an {@link com.baxi.agrohelper.model.FStatement} in the database from an existing object.
	 * 
	 * @param statement statement to persist
	 * @return the created FStatement object
	 */
	public FStatement createStatement(FStatement statement);
	
	/**
	 * Method to update an {@link com.baxi.agrohelper.model.FStatement} object.
	 * 
	 * @param statement statement to update
	 * @return the updated FStatement object
	 */
	public FStatement updateStatement(FStatement statement);
	
	/**
	 * Method to remove an {@link com.baxi.agrohelper.model.FStatement} object from the database.
	 * 
	 * @param id id of the target statement
	 * @return the removed FStatement object
	 */
	public FStatement removeStatement(int id);
	
	/**
	 * Method to find an {@link com.baxi.agrohelper.model.FStatement} object.
	 * 
	 * @param id id of the target statement
	 * @return the target FStatement object when found, null otherwise
	 */
	public FStatement findStatementById(int id);
	
	/**
	 * Method to find all {@link com.baxi.agrohelper.model.FStatement} objects.
	 * 
	 * @return List with the found objects
	 */
	public List<FStatement> findAllStatement();
	
	/**
	 * Method to count the expenses of the target {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param orchard target Orchard object
	 * @return the expenses of the target Orchard object
	 */
	public double countExpensesForOrchard(Orchard orchard);
	
	/**
	 * Method to count the income of the target {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param orchard target Orchard object
	 * @return the income of the target Orchard object
	 */
	public double countIncomeForOrchard(Orchard orchard);
	
	/**
	 * Method to count the profit of the target {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param orchard target Orchard object
	 * @return the profit of the target Orchard object
	 */
	public double countProfitForOrchard(Orchard orchard);
	
}
