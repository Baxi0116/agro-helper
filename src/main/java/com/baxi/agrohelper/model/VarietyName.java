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
	
	@Column(name = "VARIETY_NAME")
	private String name;
	
	public VarietyName(){}
	
	public VarietyName(String name){
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

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
