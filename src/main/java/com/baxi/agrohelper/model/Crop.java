package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Crop {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CROP_ID")
	private int id;
	
	@Column(name = "CROP_NAME", nullable=false)
	private String cropName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORCHARD_ID", nullable = false)
	private Orchard orchard;

	public Crop(String cropName) {
		this.cropName = cropName;
	}
	
	public Crop(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String nev) {
		this.cropName = nev;
	}

	@Override
	public String toString() {
		return cropName;
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

}
