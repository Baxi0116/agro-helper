package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VarietyName {

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

	@Override
	public String toString() {
		return name;
	}
	
	
	
}
