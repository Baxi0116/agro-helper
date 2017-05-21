package com.baxi.agrohelper.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Crop {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CROP_ID")
	private int id;
	
	@Column(name = "CROP_NAME", nullable=false)
	private String nev;
	
	@Column(name="ORCHARD_ID")
	private int orchardId;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "VARIETY_ID", referencedColumnName = "CROP_ID")
	private List<Variety> fajtak;

	public Crop(String nev) {
		this.nev = nev;
	}
	
	public Crop(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	@Override
	public String toString() {
		return "Crop [id=" + id + ", nev=" + nev + "]";
	}
	
	
	

}
