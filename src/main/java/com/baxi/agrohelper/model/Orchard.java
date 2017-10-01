package com.baxi.agrohelper.model;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Java abstraction of orchard entities.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class Orchard {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORCHARD_ID")
	private int id;
	
	/**
	 * Name of the {@code Orchard}.
	 */
	@Column(name = "ORCHARD_NAME", nullable = false)
	private String orchardName;
	
	/**
	 * Plantation date of the {@code Orchard}.
	 */
	@Column(name = "DATE_OF_PLANTATION")
	private LocalDate yearOfPlantation;
	
	/**
	 * Topographic number of the {@code Orchard}.
	 */
	@Column(name = "TOPOGRAPHIC_NUMBER", nullable=false)
	private String topographicNumber;
	
	/**
	 * MEPAR code of the {@code Orchard}.
	 */
	@Column(name = "MEPAR_CODE", nullable=false)
	private String meparCode;
	
	/**
	 * Number of trees in the {@code Orchard}.
	 */
	@Column(name = "NUMBER_OF_TREES")
	private int numberOfTrees;
	
	/**
	 * One to many relation with {@link com.baxi.agrohelper.model.Crop} objects.
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="orchard")
	private List<Crop> crops;

	/**
	 * One to many relation with {@link com.baxi.agrohelper.model.AgWork} objects.
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orchard")
	private List<AgWork> works;
	
	/**
	 * One to many relation with {@link com.baxi.agrohelper.model.Variety} objects.
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orchard")
	private List<Variety> varieties;
	
	/**
	 * Constructs a newly allocated {@code Orchard} object, with the given parameters.
	 * 
	 * @param orchardName name of the Orchard
	 * @param yearOfPlantation plantation date of the Orchard
	 * @param topographicNumber topographic number of the Orchard
	 * @param meparCode MEPAR code of the Orchard
	 * @param numberOfTrees number of trees in the Orchard
	 */
	public Orchard(String orchardName, LocalDate yearOfPlantation, String topographicNumber, String meparCode, int numberOfTrees) {
		this.orchardName = orchardName;
		this.yearOfPlantation = yearOfPlantation;
		this.topographicNumber = topographicNumber;
		this.meparCode = meparCode;
		this.numberOfTrees = numberOfTrees;
		this.works = new ArrayList<AgWork>();
		this.crops = new ArrayList<Crop>();
		this.varieties = new ArrayList<Variety>();
	}
	
	/**
	 * Constructs a newly allocated {@code Orchard} object.
	 */
	public Orchard(){
		this.orchardName = "";
		this.meparCode = "";
		this.topographicNumber = "";
		this.works = new ArrayList<AgWork>();
		this.crops = new ArrayList<Crop>();
		this.varieties = new ArrayList<Variety>();
	}
	
	/**
	 * Getter method for the {@code id}.
	 * 
	 * @return id of this object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for the {@code id}.
	 * 
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for the {@code orchardName}.
	 * 
	 * @return orchardName of this object
	 */
	public String getOrchardName() {
		return orchardName;
	}

	/**
	 * Setter method for the {@code orchardName}.
	 * 
	 * @param orchardName orchardName to set
	 */
	public void setOrchardName(String orchardName) {
		this.orchardName = orchardName;
	}

	/**
	 * Getter method for the {@code yearOfPlantation}.
	 * 
	 * @return yearOfPlantation of this object
	 */
	public LocalDate getYearOfPlantation() {
		return yearOfPlantation;
	}

	/**
	 * Setter method for the {@code yearOfPlantation}.
	 * 
	 * @param yearOfPlantation yearOfPlantation to set
	 */
	public void setYearOfPlantation(LocalDate yearOfPlantation) {
		this.yearOfPlantation = yearOfPlantation;
	}

	/**
	 * Getter method for the {@code topographicNumber}.
	 * 
	 * @return topographicNumber of this object
	 */
	public String getTopographicNumber() {
		return topographicNumber;
	}

	/**
	 * Setter method for the {@code topographicNumber}.
	 * 
	 * @param topographicNumber topographicNumber to set
	 */
	public void setTopographicNumber(String topographicNumber) {
		this.topographicNumber = topographicNumber;
	}

	/**
	 * Getter method for the {@code meparCode}.
	 * 
	 * @return meparCode of this object
	 */
	public String getMeparCode() {
		return meparCode;
	}

	/**
	 * Setter method for the {@code meparCode}.
	 * 
	 * @param meparCode meparCode to set
	 */
	public void setMeparCode(String meparCode) {
		this.meparCode = meparCode;
	}
	
	/**
	 * Getter method for the {@code crops}.
	 * 
	 * @return crops of this object
	 */
	public List<Crop> getCrops() {
		return crops;
	}

	/**
	 * Setter method for the {@code crops}.
	 * 
	 * @param crops list of crops to set
	 */
	public void setCrops(List<Crop> crops) {
		this.crops = crops;
	}

	/**
	 * Getter method for the {@code numberOfTrees}.
	 * 
	 * @return numberOfTrees of this object
	 */
	public int getNumberOfTrees() {
		return numberOfTrees;
	}

	/**
	 * Setter method for the {@code numberOfTrees}.
	 * 
	 * @param numberOfTrees numberOfTrees to set
	 */
	public void setNumberOfTrees(int numberOfTrees) {
		this.numberOfTrees = numberOfTrees;
	}

	/**
	 * Getter method for the {@code works}.
	 *  
	 * @return works of this object
	 */
	public List<AgWork> getWorks() {
		return works;
	}

	/**
	 * Setter method for the {@code works}.
	 * 
	 * @param works list of works to set
	 */
	public void setWorks(List<AgWork> works) {
		this.works = works;
	}

	/**
	 * Getter method for the {@code varieties}.
	 * 
	 * @return varieties of this object
	 */
	public List<Variety> getVarieties() {
		return varieties;
	}

	/**
	 * Setter method for the {@code varieties}.
	 * 
	 * @param varieties list of varieties to set
	 */
	public void setVarieties(List<Variety> varieties) {
		this.varieties = varieties;
	}

	
	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	@Override
	public String toString() {
		return orchardName;
	}
}
