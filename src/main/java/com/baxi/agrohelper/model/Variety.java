package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Variety {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VARIETY_ID")
	private int id;
	
	@Column(name = "VARIETY_NAME", nullable = false)
	private String name;
	
	public Variety() {}

	public Variety(String name) {
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
		return "Variety [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
