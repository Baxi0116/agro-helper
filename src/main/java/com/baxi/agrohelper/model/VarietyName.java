package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Java abstraction of variety types.
 * 
 * This class is for segregating the variety types from the actual varieties for an orchard.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class VarietyName {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "VARIETY_NAME_ID")
	private int id;
	
	/**
	 * Designation of the work type.
	 */
	@Column(name = "VARIETY_NAME")
	private String name;
	
	/**
	 * Constructs a newly allocated {@code VarietyName} object.
	 */
	public VarietyName(){}
	
	/**
	 * Constructs a newly allocated {@code VarietyName} object, with the given parameters.
	 * 
	 * @param name name to set for this entity
	 */
	public VarietyName(String name){
		this.name = name;
	}

	/**
	 * Getter method for the {@code id}.
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
	 * Getter method for {@code name}.
	 * 
	 * @return name
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
	@Override
	public String toString() {
		return name;
	}
	
	
	
}
