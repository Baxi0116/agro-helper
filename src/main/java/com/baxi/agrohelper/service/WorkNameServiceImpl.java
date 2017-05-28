package com.baxi.agrohelper.service;

import java.util.List;
import java.util.stream.Collectors;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.dao.WorkNameDao;
import com.baxi.agrohelper.model.WorkName;

public class WorkNameServiceImpl implements WorkNameService {

	private GenericDaoInterface<WorkName, Integer> workNameDao;
	
	public WorkNameServiceImpl(GenericDaoInterface<WorkName, Integer> workNameDao) {
		this.workNameDao = workNameDao;
	}
	
	@Override
	public WorkName createWorkName(String workName) {
		WorkName name = new WorkName(workName);
		workNameDao.persist(name);
		return name;
	}

	@Override
	public void createWorkName(WorkName workName) {
		workNameDao.persist(workName);
	}

	@Override
	public void deleteWorkName(int id) {
		WorkName workName = workNameDao.findById(id);
		workNameDao.delete(workName);
	}

	@Override
	public WorkName findWorkNameById(int id) {
		return workNameDao.findById(id);
	}

	@Override
	public void updateWork(WorkName workName) {
		workNameDao.update(workName);
		
	}

	@Override
	public List<WorkName> findAllWorkNames() {
		return workNameDao.findAll();
	}

	@Override
	public List<String> getAllWorkNames() {
		List<WorkName> works = findAllWorkNames();
		return works.stream()
					.map(WorkName::getName)
					.distinct()
					.collect(Collectors.toList());
	}

}
