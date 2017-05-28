package com.baxi.agrohelper.service;

import java.util.List;
import java.util.stream.Collectors;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.VarietyName;

public class VarietyNameServiceImpl implements VarietyNameService {

	private GenericDaoInterface<VarietyName, Integer> varietyNameDao;
	
	public VarietyNameServiceImpl(GenericDaoInterface<VarietyName, Integer> varietyNameDao) {
		this.varietyNameDao = varietyNameDao;
	}
	
	@Override
	public VarietyName createVarietyName(String name) {
		VarietyName varietyName = new VarietyName(name);
		varietyNameDao.persist(varietyName);
		return varietyName;
	}

	@Override
	public void createVarietyName(VarietyName varietyName) {
		varietyNameDao.persist(varietyName);
	}

	@Override
	public void deleteVarietyName(int id) {
		VarietyName varietyName = varietyNameDao.findById(id);
		varietyNameDao.delete(varietyName);
	}

	@Override
	public VarietyName findVarietyNameById(int id) {
		return varietyNameDao.findById(id);
	}

	@Override
	public void updateVariety(VarietyName varietyName) {
		varietyNameDao.update(varietyName);
	}

	@Override
	public List<VarietyName> findAllVarietyNames() {
		return varietyNameDao.findAll();
	}

	@Override
	public List<String> getAllVarietyNames() {
		return varietyNameDao.findAll().stream()
						.map(VarietyName::getName)
						.distinct()
						.collect(Collectors.toList());
	}


	
}
