package com.baxi.agrohelper.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Orchard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORCHARD_ID")
	private int id;
	
	@Column(name = "ORCHARD_NAME", nullable = false)
	private String nev;
	
	@Column(name = "DATE_OF_PLANTATION")
	private LocalDate telepitesEve;
	
	@Column(name= "TOPOGRAPHIC_NUMBER", nullable=false)
	private String helyrajziSzam;
	
	@Column(name = "MEPAR_CODE", nullable=false)
	private String meparKod;
	
	@Column(name = "NUMBER_OF_TREES")
	private int fakSzama;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ORCHARD_CROP", catalog = "agrohelperdb", 
				joinColumns = @JoinColumn(name = "ORCHARD_ID"),
				inverseJoinColumns = @JoinColumn(name = "CROP_ID"))
	private List<Crop> kulturak;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ORCHARD_WORK", catalog = "agrohelperdb", 
				joinColumns = @JoinColumn(name = "ORCHARD_ID"), 
				inverseJoinColumns = @JoinColumn(name = "WORK_ID"))
	private List<AgWork> munkalatok;

	public Orchard(String nev, LocalDate telepitesEve, String helyrajziSzam, String meparKod, int fakSzama) {
		this.nev = nev;
		this.telepitesEve = telepitesEve;
		this.helyrajziSzam = helyrajziSzam;
		this.meparKod = meparKod;
		this.fakSzama = fakSzama;
	}
	
	public Orchard(){}

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

	public LocalDate getTelepitesEve() {
		return telepitesEve;
	}

	public void setTelepitesEve(LocalDate telepitesEve) {
		this.telepitesEve = telepitesEve;
	}

	public String getHelyrajziSzam() {
		return helyrajziSzam;
	}

	public void setHelyrajziSzam(String helyrajziSzam) {
		this.helyrajziSzam = helyrajziSzam;
	}

	public String getMeparKod() {
		return meparKod;
	}

	public void setMeparKod(String meparKod) {
		this.meparKod = meparKod;
	}
	
	public List<Crop> getKulturak() {
		return kulturak;
	}

	public void setKulturak(List<Crop> kulturak) {
		this.kulturak = kulturak;
	}

	public int getFakSzama() {
		return fakSzama;
	}

	public void setFakSzama(int fakSzama) {
		this.fakSzama = fakSzama;
	}

	public List<AgWork> getMunkalatok() {
		return munkalatok;
	}

	public void setMunkalatok(List<AgWork> munkalatok) {
		this.munkalatok = munkalatok;
	}

	@Override
	public String toString() {
		return "Field [id=" + id + ", nev=" + nev + ", telepitesEve=" + telepitesEve + ", helyrajziSzam="
				+ helyrajziSzam + ", meparKod=" + meparKod + ", fakSzama=" + fakSzama
				+  "]";
	}
	
	
	
	
}
