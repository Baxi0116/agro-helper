package com.baxi.agrohelper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AgWork {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORK_ID")
	private int id;
	
	@Column(name="WORK_DESIGNATION", nullable=false)
	private String munkavegzesNeve;
	
	@Column(name="WORK_PRICE", nullable=false)
	private int munkavegzesAra;

	public AgWork(String munkavegzesNeve, int munkavegzesAra) {
		this.munkavegzesNeve = munkavegzesNeve;
		this.munkavegzesAra = munkavegzesAra;
	}
	
	public AgWork() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMunkavegzesNeve() {
		return munkavegzesNeve;
	}

	public void setMunkavegzesNeve(String munkavegzesNeve) {
		this.munkavegzesNeve = munkavegzesNeve;
	}

	public int getMunkavegzesAra() {
		return munkavegzesAra;
	}

	public void setMunkavegzesAra(int munkavegzesAra) {
		this.munkavegzesAra = munkavegzesAra;
	}

	@Override
	public String toString() {
		return "Work [id=" + id + ", munkavegzesNeve=" + munkavegzesNeve + ", munkavegzesAra=" + munkavegzesAra + "]";
	}
	
	
	
	
}
