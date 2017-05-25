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
public class AgWork {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORK_ID")
	private int id;
	
	@Column(name="WORK_DESIGNATION", nullable=false)
	private String workDesignation;
	
	@Column(name="WORK_PRICE", nullable=false)
	private int workPrice;

	public AgWork(String workDesignation, int workPrice) {
		this.workDesignation = workDesignation;
		this.workPrice = workPrice;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Orchard.class)
	@JoinColumn(name = "ORCHARD_ID", nullable = false)
	private Orchard orchard;
	
	public AgWork() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkDesignation() {
		return workDesignation;
	}

	public void setWorkDesignation(String workDesignation) {
		this.workDesignation = workDesignation;
	}

	public int getWorkPrice() {
		return workPrice;
	}

	public void setWorkPrice(int workPrice) {
		this.workPrice = workPrice;
	}

	@Override
	public String toString() {
		return "AgWork [workDesignation=" + workDesignation + ", workPrice=" + workPrice + "]";
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	
	
	
	
	
}
