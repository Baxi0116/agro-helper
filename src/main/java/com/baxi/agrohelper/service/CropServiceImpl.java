package com.baxi.agrohelper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.baxi.agrohelper.dao.CropDao;
import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Crop;
import com.baxi.agrohelper.util.EntityManagerProvider;


public class CropServiceImpl implements CropService{

	private GenericDaoInterface<Crop, Integer> cropDao;
	
	public CropServiceImpl(GenericDaoInterface<Crop, Integer> cropDao){
		this.cropDao = cropDao;
	}
	
	@Override
	public Crop createCrop(String name) {
		Crop crop = new Crop(name);
		cropDao.persist(crop);
		return crop;
	}

	@Override
	public void createCrop(Crop crop) {
		cropDao.persist(crop);
	}

	@Override
	public void updateCrop(Crop crop) {
		cropDao.update(crop);
	}

	@Override
	public void removeCrop(int id) {
		Crop crop = findCropById(id);
		cropDao.delete(crop);
	}

	@Override
	public Crop findCropById(int id) {
		return cropDao.findById(id);
	}

	@Override
	public List<Crop> findAllCrops() {
		return cropDao.findAll();
	}

	@Override
	public void deleteAllCropsForOrchard(int id) {
		EntityManager em = EntityManagerProvider.provideEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("DELETE FROM Crop WHERE ORCHARD_ID = :oid");
		query.setParameter("oid", id);
		query.executeUpdate();
		em.getTransaction().commit();
		
	}

}
