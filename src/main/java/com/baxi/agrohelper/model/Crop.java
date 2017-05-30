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
	 * @return id of this object
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
	 * @return cropName of this object
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
	 * @return orchard of this object
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
