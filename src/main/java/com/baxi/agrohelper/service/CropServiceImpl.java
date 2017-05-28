package com.baxi.agrohelper.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Crop;
import com.baxi.agrohelper.util.EntityManagerProvider;

/**
 * 
 * Implementation of the {@code CropService} interface.
 * 
 * @author Gergely Szabó
 *
 */
public class CropServiceImpl implements CropService{	
	/**
	 * Static logger for debug purposes.
	 */
	private static Logger logger = LoggerFactory.getLogger(CropServiceImpl.class);
	
	/**
	 * Data Access Object for database management.
	 */
	private GenericDaoInterface<Crop, Integer> cropDao;
	
	/**
	 * Constructor.
	 * 
	 * @param cropDao {@link com.baxi.agrohelper.dao.GenericDaoInterface} object for initialization
	 */
	public CropServiceImpl(GenericDaoInterface<Crop, Integer> cropDao){
		this.cropDao = cropDao;
	}
	
	@Override
	public Crop createCrop(String name) {
		logger.info("Creating CROP {}", name);
		Crop crop = new Crop(name);
		cropDao.persist(crop);
		return crop;
	}

	@Override
	public void createCrop(Crop crop) {
		logger.info("Creating CROP {}", crop.getCropName());
		cropDao.persist(crop);
	}

	@Override
	public void updateCrop(Crop crop) {
		logger.info("Updating CROP {}", crop.getCropName());
		cropDao.update(crop);
	}

	@Override
	public void removeCrop(int id) {
		Crop crop = findCropById(id);
		logger.warn("Removing CROP {}", crop.getCropName());
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
