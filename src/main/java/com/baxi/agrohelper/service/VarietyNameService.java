package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.VarietyName;

public interface VarietyNameService {

	public VarietyName createVarietyName(String name);
	
	public void createVarietyName(VarietyName varietyName);
	
	public void deleteVarietyName(int id);
	
	public VarietyName findVarietyNameById(int id);
	
	public void updateVariety(VarietyName varietyName);
	
	public List<VarietyName> findAllVarietyNames();
	
	public List<String> getAllVarietyNames();
	
}
