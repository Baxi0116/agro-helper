package com.baxi.agrohelper.model;

import java.time.LocalDate;

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
	
	@Column(name="WORK_NOTE")
	private String workNote;
	
	@Column(name="WORK_DATE", nullable=false)
	private LocalDate workDate;

	public AgWork(String workDesignation, int workPrice, LocalDate workDate) {
		this.workDesignation = workDesignation;
		this.workPrice = workPrice;
		this.workNote = "";
		this.workDate = workDate;
	}
	
	public AgWork(String workDesignation, int workPrice, String workNote, LocalDate workDate) {
		this.workDesignation = workDesignation;
		this.workPrice = workPrice;
		this.workNote = workNote;
		this.workDate = workDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Orchard.class)
	@JoinColumn(name = "ORCHARD_ID", nullable = false)
	private Orchard orchard;
	
	public AgWork() {
	}

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
		return workDesignation;
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

	public String getWorkNote() {
		return workNote;
	}

	public void setWorkNote(String workNote) {
		this.workNote = workNote;
	}
	
	

}
