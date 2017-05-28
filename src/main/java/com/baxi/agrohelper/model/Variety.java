package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Java abstraction of plant variety entities.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class Variety {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VARIETY_ID")
	private int id;
	
	/**
	 * Name of the variety.
	 */
	@Column(name = "VARIETY_NAME", nullable = false)
	private String varietyName;
	
	/**
	 * Total yield of this kind of variety in kg/ha.
	 */
	@Column(name="VARIETY_YIELD")
	private double varietyYield;
	
	/**
	 * Price of this variety in Ft/kg.
	 */
	@Column(name="VARIETY_PRICE")
	private int varietyPrice;
	
	/**
	 * Total area this variety occurs in the {@link com.baxi.agrohelper.model.Orchard}.
	 */
	@Column(name="VARIETY_AREA")
	private double varietyArea;
	
	/**
	 * Many to one connection with the {@link com.baxi.agrohelper.model.Orchard} entity.
	 */
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Orchard.class)
	@JoinColumn(name="ORCHARD_ID", nullable=false)
	private Orchard orchard;
	
	/**
	 * Constructor.
	 */
	public Variety() {
		this.varietyArea = 0;
	}

	/**
	 * Constructor.
	 * 
	 * @param name name to set
	 * @param varietyArea area to set
	 */
	public Variety(String name, double varietyArea) {
		this.varietyName = name;
		this.varietyArea = varietyArea;
	}

	/**
	 * Getter method for {@code id}.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter method for {@code id}.
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for {@code varietyName}.
	 * @return varietyName
	 */
	public String getVarietyName() {
		return varietyName;
	}

	/**
	 * Setter method for {@code varietyName}.
	 * @param varietyName variety name to set
	 */
	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	/**
	 * Getter method for {@code orchard}.
	 * @return orchard
	 */
	public Orchard getOrchard() {
		return orchard;
	}

	/**
	 * Setter method for {@code orchard}.
	 * @param orchard orchard to set
	 */
	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	/**
	 * Getter method for {@code varietyYield}.
	 * @return varietyYield
	 */
	public double getVarietyYield() {
		return varietyYield;
	}

	/**
	 * Setter method for {@code varietyYield}.
	 * @param varietyYield varietyYield to set
	 */
	public void setVarietyYield(double varietyYield) {
		this.varietyYield = varietyYield;
	}

	/**
	 * Getter method for {@code varietyPrice}.
	 * @return varietyPrice
	 */
	public int getVarietyPrice() {
		return varietyPrice;
	}

	/**
	 * Setter method for {@code varietyPrice}.
	 * @param varietyPrice varietyPrice to set
	 */
	public void setVarietyPrice(int varietyPrice) {
		this.varietyPrice = varietyPrice;
	}

	/**
	 * Getter method for {@code varietyArea}.
	 * @return varietyArea
	 */
	public double getVarietyArea() {
		return varietyArea;
	}

	/**
	 * Setter method for {@code varietyArea}.
	 * @param varietyArea varietyArea to set
	 */
	public void setVarietyArea(double varietyArea) {
		this.varietyArea = varietyArea;
	}
	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	@Override
	public String toString() {
		return varietyName;
	}
}
