package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.dao.VarietyDao;
import com.baxi.agrohelper.model.Variety;

public class VarietySeviceImpl implements VarietyService {

	private VarietyDao varietyDao;
	
	public VarietySeviceImpl(VarietyDao varietyDao) {
		this.varietyDao = varietyDao;
	}
	
	@Override
	public Variety createVariety(String name) {
		Variety variety = new Variety(name);
		varietyDao.persist(variety);
		return variety;
	}

	@Override
	public void createVariety(Variety variety) {
		varietyDao.persist(variety);
	}

	@Override
	public void updateVariety(Variety variety) {
		varietyDao.update(variety);
	}

	@Override
	public void removeVariety(int id) {
		Variety variety = varietyDao.findById(id);
		varietyDao.delete(variety);
	}

	@Override
	public Variety findVarietyById(int id) {
		return varietyDao.findById(id);
	}

	@Override
	public List<Variety> findAllVarieties() {
		return varietyDao.findAll();
	}
}
