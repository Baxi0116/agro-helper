package com.baxi.agrohelper.model;

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
	 * One to many relation with {@link com.baxi.agrohelper.model.FStatement} objects.
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orchard")
	private List<FStatement> statements;
	
	/**
	 * Constructor.
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
		this.statements = new ArrayList<FStatement>();
	}
	
	/**
	 * Constructor.
	 */
	public Orchard(){
		this.orchardName = "";
		this.meparCode = "";
		this.topographicNumber = "";
		this.works = new ArrayList<AgWork>();
		this.crops = new ArrayList<Crop>();
		this.varieties = new ArrayList<Variety>();
		this.statements = new ArrayList<FStatement>();
	}
	
	/**
	 * Getter method for the {@code id}.
	 * 
	 * @return id
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
	 * @return orchardName
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
	 * @return yearOfPlantation
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
	 * @return topographicNumber
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
	 * @return meparCode
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
	 * @return crops
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
	 * @return numberOfTrees
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
	 * @return works
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
	 * @return varieties
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
	 * Getter method for the {@code statements}.
	 * 
	 * @return statements
	 */
	public List<FStatement> getStatements() {
		return statements;
	}

	/**
	 * Setter method for the {@code statements}.
	 * 
	 * @param statements list of statements to set
	 */
	public void setStatements(List<FStatement> statements) {
		this.statements = statements;
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
