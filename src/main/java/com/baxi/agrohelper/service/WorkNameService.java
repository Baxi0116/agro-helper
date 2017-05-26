package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.WorkName;

public interface WorkNameService {

	public WorkName createWorkName(String workName);
	
	public void createWorkName(WorkName workName);
	
	public void deleteWorkName(int id);
	
	public WorkName findWorkNameById(int id);
	
	public void updateWork(WorkName workName);
	
	public List<WorkName> findAllWorkNames();
	
	public List<String> getAllWorkNames();
	
}
