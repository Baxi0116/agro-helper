package com.baxi.agrohelper.service;

/*-
 * #%L
 * agro-helper
 * %%
 * Copyright (C) 2017 University of Debrecen, Faculty of Informatics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

import java.util.List;

import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;

/**
 * 
 * Service using Data Access Object to reach the database, manages {@code FStatement} entities.
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
	
	public double countExpensesForAllOrchard(List<Orchard> orchardList);
	
	public double countIncomeForAllOrchard(List<Orchard> orchardList);
	
	public double countProfitForAllOrchard(List<Orchard> orchardList);
	
	public double countMaterialExpensesForOrchard(Orchard orchard);
	
	public double countMaterialExpensesForAllOrchard(List<Orchard> orchardList);
	
	public double countWorkExpensesForOrchard(Orchard orchard);
	
	public double countWorkExpensesForAllOrchard(List<Orchard> orchardList);
	
}
