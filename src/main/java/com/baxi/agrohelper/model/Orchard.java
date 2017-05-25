package com.baxi.agrohelper.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Orchard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORCHARD_ID")
	private int id;
	
	@Column(name = "ORCHARD_NAME", nullable = false)
	private String orchardName;
	
	@Column(name = "DATE_OF_PLANTATION")
	private LocalDate yearOfPlantation;
	
	@Column(name= "TOPOGRAPHIC_NUMBER", nullable=false)
	private String topographicNumber;
	
	@Column(name = "MEPAR_CODE", nullable=false)
	private String meparCode;
	
	@Column(name = "NUMBER_OF_TREES")
	private int numberOfTrees;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="orchard")
	private List<Crop> crops;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orchard")
	private List<AgWork> works;

	public Orchard(String orchardName, LocalDate yearOfPlantation, String topographicNumber, String meparCode, int numberOfTrees) {
		this.orchardName = orchardName;
		this.yearOfPlantation = yearOfPlantation;
		this.topographicNumber = topographicNumber;
		this.meparCode = meparCode;
		this.numberOfTrees = numberOfTrees;
	}
	
	public Orchard(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrchardName() {
		return orchardName;
	}

	public void setOrchardName(String orchardName) {
		this.orchardName = orchardName;
	}

	public LocalDate getYearOfPlantation() {
		return yearOfPlantation;
	}

	public void setYearOfPlantation(LocalDate yearOfPlantation) {
		this.yearOfPlantation = yearOfPlantation;
	}

	public String getTopographicNumber() {
		return topographicNumber;
	}

	public void setTopographicNumber(String topographicNumber) {
		this.topographicNumber = topographicNumber;
	}

	public String getMeparCode() {
		return meparCode;
	}

	public void setMeparCode(String meparCode) {
		this.meparCode = meparCode;
	}
	
	public List<Crop> getCrops() {
		return crops;
	}

	public void setCrops(List<Crop> crops) {
		this.crops = crops;
	}

	public int getNumberOfTrees() {
		return numberOfTrees;
	}

	public void setNumberOfTrees(int numberOfTrees) {
		this.numberOfTrees = numberOfTrees;
	}

	public List<AgWork> getWorks() {
		return works;
	}

	public void setWorks(List<AgWork> works) {
		this.works = works;
	}

	@Override
	public String toString() {
		return "Orchard [id=" + id + ", orchardName=" + orchardName + ", yearOfPlantation=" + yearOfPlantation
				+ ", topographicNumber=" + topographicNumber + ", meparCode=" + meparCode + ", numberOfTrees="
				+ numberOfTrees + "]";
	}

	
	
	
	
	
}
