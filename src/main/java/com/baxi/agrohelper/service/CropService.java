package com.baxi.agrohelper.service;

import java.util.List;

import com.baxi.agrohelper.model.Crop;

public interface CropService {

	public Crop createCrop(String name);
	
	public void createCrop(Crop crop);
	
	public void updateCrop(Crop crop);
	
	public void removeCrop(int id);
	
	public Crop findCropById(int id);
	
	public List<Crop> findAllCrops();
	
	public void deleteAllCropsForOrchard(int id);

}

