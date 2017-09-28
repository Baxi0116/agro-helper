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

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Java abstraction of agricultural work entities.
 * 
 * @author Gergely Szabó
 *
 */

//TODO OPTIONAL EXPENSES
@Entity
public class AgWork {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORK_ID")
	private int id;
	
	/**
	 * Name of the work.
	 */
	@Column(name="WORK_DESIGNATION", nullable=false)
	private String workDesignation;
	
	/**
	 * Price of the work.
	 */
	@Column(name="WORK_PRICE", nullable=false)
	private int workPrice;
	
	/**
	 * Notes given to this work.
	 */
	@Column(name="WORK_NOTE")
	private String workNote;
	
	/**
	 * Date of the work.
	 */
	@Column(name="WORK_DATE", nullable=false)
	private LocalDate workDate;

	/**
	 * Constructs a newly allocated {@code AgWork} object, with the given parameters, note included.
	 * 
	 * @param workDesignation name of the work
	 * @param workPrice	cost of the work
	 * @param workDate	date of the work
	 */
	public AgWork(String workDesignation, int workPrice, LocalDate workDate) {
		this.workDesignation = workDesignation;
		this.workPrice = workPrice;
		this.workNote = "";
		this.workDate = workDate;
	}
	
	/**
	 *  Constructs a newly allocated {@code AgWork} object, with the given parameters without note.
	 * 
	 * @param workDesignation name of the work
	 * @param workPrice	cost of the work
	 * @param workNote notes given to the work
	 * @param workDate	date of the work
	 */
	public AgWork(String workDesignation, int workPrice, String workNote, LocalDate workDate) {
		this.workDesignation = workDesignation;
		this.workPrice = workPrice;
		this.workNote = workNote;
		this.workDate = workDate;
	}
	
	/**
	 * Many to one relation with the {@link com.baxi.agrohelper.model.Orchard} Entity.
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Orchard.class)
	@JoinColumn(name = "ORCHARD_ID", nullable = false)
	private Orchard orchard;
	
	/**
	 *  Constructs a newly allocated {@code AgWork} object.
	 */
	public AgWork() {
	}
	
	/**
	 * Getter method for the {@code id}.
	 * @return id of the object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for the {@code id}.
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for the {@code workDesignation}.
	 * @return workDesignation of this object
	 */
	public String getWorkDesignation() {
		return workDesignation;
	}

	/**
	 * Setter method for the {@code workDesignation}.
	 * @param workDesignation work designation to set
	 */
	public void setWorkDesignation(String workDesignation) {
		this.workDesignation = workDesignation;
	}

	/**
	 * Getter method for the {@code workPrice}.
	 * @return workPrice of this object
	 */
	public int getWorkPrice() {
		return workPrice;
	}

	/**
	 * Setter method for the {@code workPrice}.
	 * @param workPrice work price to set
	 */
	public void setWorkPrice(int workPrice) {
		this.workPrice = workPrice;
	}

	/**
	 * Getter method for the {@code orchard}.
	 * @return orchard of this object
	 */
	public Orchard getOrchard() {
		return orchard;
	}

	/**
	 * Setter method for the {@code orchard}.
	 * @param orchard orchard to set
	 */
	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	/**
	 * Getter method for {@code workDate}.
	 * @return workDate of this object
	 */
	public LocalDate getWorkDate() {
		return workDate;
	}
	
	/**
	 * Setter method for {@code workDate}.
	 * @param workDate work date to set
	 */
	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

	/**
	 * Getter method for {@code workNote}.
	 * @return workNote of this object
	 */
	public String getWorkNote() {
		return workNote;
	}
	
	/**
	 * Setter method for {@code workNote}.
	 * @param workNote work note to set
	 */
	public void setWorkNote(String workNote) {
		this.workNote = workNote;
	}

	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	@Override
	public String toString() {
		return workDesignation;
	}
	
}
