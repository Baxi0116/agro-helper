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
import com.baxi.agrohelper.model.Orchard;

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
		Crop crop = new Crop();
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
	
	@Test 
	public void testDeleteAllCropsForOrchard(){
		Orchard orchard1 = new Orchard();
		Orchard orchard2 = new Orchard();
		orchard1.setId(2);
		orchard2.setId(3);
		Crop crop1 = new Crop();
		Crop crop2 = new Crop();
		Crop crop3 = new Crop();
		List<Crop> allCropList = new ArrayList<Crop>();
		crop1.setOrchard(orchard1);
		crop2.setOrchard(orchard1);
		crop3.setOrchard(orchard2);
		allCropList.add(crop1);
		allCropList.add(crop2);
		allCropList.add(crop3);
		
		when(mockDao.findAll()).thenReturn(allCropList);
		when(mockDao.delete(crop1)).thenReturn(null);
		when(mockDao.delete(crop2)).thenReturn(null);
		
		List<Crop> resultList = cropService.deleteAllCropsForOrchard(2);
		List<Crop> knownDeletedCrops = new ArrayList<Crop>();
		knownDeletedCrops.add(crop1);
		knownDeletedCrops.add(crop2);
		
		assertThat(resultList, hasSize(2));
		assertThat(resultList, is(equalTo(knownDeletedCrops)));
		
	}
	
}
