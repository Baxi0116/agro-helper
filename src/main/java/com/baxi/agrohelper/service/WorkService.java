package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.AgWork;

public interface WorkService {

	public AgWork createWork(String workDesignation, int workPrice, String workNote);
	
	public AgWork createWork(String workDesignation, int workPrice);
	
	public void createWork(AgWork work);
	
	public void deleteWork(int id);
	
	public AgWork findWorkById(int id);
	
	public void updateWork(AgWork work);
	
	public List<AgWork> findAllWorks();
	
}
