package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.Variety;

public interface VarietyService {

public Variety createVariety(String name);
	
	public void createVariety(Variety variety);
	
	public void updateVariety(Variety variety);
	
	public void removeVariety(int id);
	
	public Variety findVarietyById(int id);
	
	public List<Variety> findAllVarieties();
	
}
