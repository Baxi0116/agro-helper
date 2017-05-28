package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.model.Orchard;

/**
 * 
 * Service using Data Access Object to reach the database.
 * 
 * @author Gergely Szabó
 *
 */
public interface OrchardService {

	/**
	 * Method to create an {@link com.baxi.agrohelper.model.Orchard} object in the database.
	 * 
	 * @param orchardName name of the Orchard
	 * @param plantationYear year of plantation Orchard
	 * @param topographicNumber topographic number of the code Orchard
	 * @param meparCode MEPAR code of the Orchard
	 * @param numberOfTrees number of trees in the code Orchard
	 * 
	 * @return Orchard object created in the database
	 */
	public Orchard createOrchard(String orchardName, LocalDate plantationYear, String topographicNumber, String meparCode, int numberOfTrees);
	
	/**
	 * Method to create an {@link com.baxi.agrohelper.model.Orchard} in the database from an existing object.
	 * 
	 * @param orchard the target Orchard
	 */
	public void createOrchard(Orchard orchard);
	
	/**
	 * Method to update an {@link com.baxi.agrohelper.model.Orchard}.
	 * 
	 * @param orchard the target Orchard
	 */	
	public void updateOrchard(Orchard orchard);
	
	/**
	 * Method to delete an {@link com.baxi.agrohelper.model.Orchard} from the database.
	 * 
	 * @param id id of the target Orchard
	 */
	public void removeOrchard(int id);
	
	/**
	 * Method to find an {@link com.baxi.agrohelper.model.Orchard} in the database by its id.
	 * 
	 * @param id id of the target Orchard
	 */
	public Orchard findOrchardById(int id);
	
	/**
	 * Method to find all {@link com.baxi.agrohelper.model.Orchard} objects in the database.
	 * 
	 * @return List with the found Orchard objects
	 */
	public List<Orchard> findAllOrchards();
	
}
