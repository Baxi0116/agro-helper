package com.baxi.agrohelper.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Crop;

@RunWith(MockitoJUnitRunner.class)
public class CropServiceTest {

	private CropService cropService;
	
	@Mock
	private GenericDaoInterface<Crop, Integer> mockDao;
	
	@Before
	public void setUp(){
		cropService = new CropServiceImpl(mockDao);
	}
	
	@Test
	public void testFindAllCrops(){
		ArrayList<Crop> cropData = new ArrayList<Crop>();
		cropData.add(new Crop());
		cropData.add(new Crop());
		cropData.add(new Crop());
		
		when(mockDao.findAll()).thenReturn(cropData);
		
		List<Crop> cropList = cropService.findAllCrops();
		
		assertThat(cropList, hasSize(3));
	}
	
	@Test
	public void testFindCropById(){
		Crop crop = new Crop();
		crop.setId(10);
		crop.setCropName("teszt");
		
		when(mockDao.findById(10)).thenReturn(crop);
		
		assertThat(cropService.findCropById(10).getCropName(), is(equalTo("teszt")));
	}
	
	@Test
	public void testCreateCrop(){
		Crop crop= new Crop();
		crop.setCropName("Teszt");
		
		when(mockDao.persist(crop)).thenReturn(crop);
		
		assertThat(cropService.createCrop(crop).getCropName(), is(equalTo("Teszt")));
	}
	
	@Test
	public void testUpdateCrop(){
		Crop crop= new Crop();
		crop.setCropName("Teszt");
		
		when(mockDao.update(crop)).thenReturn(crop);
		
		assertThat(cropService.updateCrop(crop).getCropName(), is(equalTo("Teszt")));
	}
	
	@Test
	public void testDeleteCrop(){
		Crop crop= new Crop();
		crop.setCropName("Teszt");
		crop.setId(1);
		
		when(mockDao.findById(1)).thenReturn(crop);
		when(mockDao.delete(crop)).thenReturn(crop);
		
		assertThat(cropService.removeCrop(1).getCropName(), is(equalTo("Teszt")));
	}
	
}
