package com.baxi.agrohelper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Crop;

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
	public Crop createCrop(Crop crop) {
		logger.info("Creating CROP {}", crop.getCropName());
		cropDao.persist(crop);
		return crop;
	}

	@Override
	public Crop updateCrop(Crop crop) {
		logger.info("Updating CROP {}", crop.getCropName());
		cropDao.update(crop);
		return crop;
	}

	@Override
	public Crop removeCrop(int id) {
		Crop crop = findCropById(id);
		logger.warn("Removing CROP {}", crop.getCropName());
		cropDao.delete(crop);
		return crop;
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
	public List<Crop> deleteAllCropsForOrchard(int id) {
		List<Crop> cropList = findAllCrops();
		List<Crop> resultList = new ArrayList<Crop>();
		List<Crop> forDelete = cropList.stream()
			.filter(c -> c.getOrchard().getId() == id)
			.collect(Collectors.toList());
		for(Crop crop : forDelete){
			cropDao.delete(crop);
			resultList.add(crop);
		}return resultList;
	}

}
