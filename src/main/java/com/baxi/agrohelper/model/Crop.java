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
 * Java abstraction of crop entities.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class Crop {
	
	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CROP_ID")
	private int id;
	
	/**
	 * Name of the crop.
	 */
	@Column(name = "CROP_NAME", nullable=false)
	private String cropName;
	
	/**
	 * Many to one relation with {@link com.baxi.agrohelper.model.Orchard}.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORCHARD_ID", nullable = false)
	private Orchard orchard;

	/**
	 *  Constructs a newly allocated {@code Crop} object, with the given parameters.
	 * 
	 * @param cropName cropName to set
	 */
	public Crop(String cropName) {
		this.cropName = cropName;
	}
	
	/**
	 * Constructs a newly allocated {@code Crop} object.
	 */
	public Crop(){}

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
	 * Getter method for {@code cropName}.
	 * @return cropName
	 */
	public String getCropName() {
		return cropName;
	}
	
	/**
	 * Setter method for {@code cropName}.
	 * 
	 * @param name cropName to set
	 */
	public void setCropName(String name) {
		this.cropName = name;
	}

	/**
	 * Getter method for {@code orchard}.
	 * 
	 * @return orchard
	 */
	public Orchard getOrchard() {
		return orchard;
	}
	
	/**
	 * Setter method for {@code orchard}.
	 * 
	 * @param orchard orchard to set
	 */
	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	@Override
	public String toString() {
		return cropName;
	}
}
