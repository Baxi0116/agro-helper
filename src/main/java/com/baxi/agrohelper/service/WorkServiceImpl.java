package com.baxi.agrohelper.service;

import java.time.LocalDate;
import java.util.List;

import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.model.AgWork;

public class WorkServiceImpl implements WorkService {

	private WorkDao workDao;
	
	public WorkServiceImpl(WorkDao workDao) {
		this.workDao = workDao;
	}
	
	@Override
	public AgWork createWork(String workDesignation, int workPrice, String workNote, LocalDate workDate) {
		AgWork work = new AgWork(workDesignation, workPrice, workNote, workDate);
		workDao.persist(work);
		return work;
	}

	@Override
	public AgWork createWork(String workDesignation, int workPrice, LocalDate workDate) {
		AgWork work = new AgWork(workDesignation, workPrice, workDate);
		workDao.persist(work);
		return work;
	}

	@Override
	public void createWork(AgWork work) {
		workDao.persist(work);
	}

	@Override
	public void deleteWork(int id) {
		AgWork work = workDao.findById(id);
		workDao.delete(work);
		
	}

	@Override
	public AgWork findWorkById(int id) {
		return workDao.findById(id);
	}

	@Override
	public void updateWork(AgWork work) {
		workDao.update(work);
	}

	@Override
	public List<AgWork> findAllWorks() {
		return workDao.findAll();
	}

	
	
}
