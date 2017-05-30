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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Java abstraction of work types.
 * 
 * This class is for segregating the work types from the actual works for an orchard.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class WorkName {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NAME_ID")
	private int id;
	
	/**
	 * Designation of the work type.
	 */
	@Column(name="WORK_NAME", nullable=false)
	private String name;
	
	/**
	 * Constructs a newly allocated {@code WorkName} object, with the given parameters.
	 * 
	 * @param name name to set for this entity
	 */
	public WorkName(String name){
		this.name = name;
	}
	
	/**
	 * Constructs a newly allocated {@code WorkName} object, with the given parameters.
	 */
	public WorkName(){
		
	}

	/**
	 * Getter method for the {@code id}.
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
	 * Getter method for {@code name}.
	 * 
	 * @return name of this object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for {@code name}.
	 * 
	 * @param name name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	public String toString(){
		return name;
	}
	
}
