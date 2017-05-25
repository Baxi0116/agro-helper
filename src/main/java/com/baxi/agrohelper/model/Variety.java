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
public class Variety {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VARIETY_ID")
	private int id;
	
	@Column(name = "VARIETY_NAME", nullable = false)
	private String varietyName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CROP_ID", nullable=false)
	private Crop crop;
	
	public Variety() {}

	public Variety(String name) {
		this.varietyName = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return varietyName;
	}

	public String getVarietyName() {
		return varietyName;
	}

	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}

	public Crop getCrop() {
		return crop;
	}

	public void setCrop(Crop crop) {
		this.crop = crop;
	}
	
	
	
}
