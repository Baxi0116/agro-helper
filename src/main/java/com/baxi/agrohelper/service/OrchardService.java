package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.model.Orchard;

public interface OrchardService {

	public Orchard createOrchard(String nev, LocalDate telepitesEve, String helyrajziSzam, String meparKod, int fakSzama);
	
	public void createOrchard(Orchard orchard);
	
	public void updateOrchard(Orchard orchard);
	
	public void removeOrchard(int id);
	
	public Orchard findOrchardById(int id);
	
	public List<Orchard> findAllOrchards();
	
	public int countExpensesForOrchard(int id);
	
	public int countExpensesForAllOrchard();
	
}
