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
	
	@Column(name = "VARIETY_NAME", nullable = false)
	private String varietyName;
	
	@Column(name="VARIETY_YIELD")
	private double varietyYield;
	
	@Column(name="VARIETY_PRICE")
	private int varietyPrice;
	
	@Column(name="VARIETY_AREA")
	private double varietyArea;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Orchard.class)
	@JoinColumn(name="ORCHARD_ID", nullable=false)
	private Orchard orchard;
	
	public Variety() {
		this.varietyArea = 0;
	}

	public Variety(String name, double varietyArea) {
		this.varietyName = name;
		this.varietyArea = varietyArea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	public double getVarietyYield() {
		return varietyYield;
	}

	public void setVarietyYield(double varietyYield) {
		this.varietyYield = varietyYield;
	}

	public int getVarietyPrice() {
		return varietyPrice;
	}

	public void setVarietyPrice(int varietyPrice) {
		this.varietyPrice = varietyPrice;
	}

	public double getVarietyArea() {
		return varietyArea;
	}

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
