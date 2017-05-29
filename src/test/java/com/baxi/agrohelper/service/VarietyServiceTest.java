package com.baxi.agrohelper.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.baxi.agrohelper.dao.GenericDaoInterface;
import com.baxi.agrohelper.model.Variety;

@RunWith(MockitoJUnitRunner.class)
public class VarietyServiceTest {
	
	private VarietyService varietyService;
	
	@Mock
	private GenericDaoInterface<Variety, Integer> mockDao;
	
	@Before
	public void setUp(){
		varietyService = new VarietyServiceImpl(mockDao);
	}
	
	@Test
	public void testCreateVariety(){
		Variety variety = new Variety();
		
		when(mockDao.persist(variety)).thenReturn(variety);
		
		assertThat(varietyService.createVariety(variety), is(equalTo(variety)));
	}
	
	@Test
	public void testUpdateVariety(){
		Variety variety = new Variety();
		
		when(mockDao.update(variety)).thenReturn(variety);
		
		assertThat(varietyService.updateVariety(variety), is(equalTo(variety)));
	}
	
	@Test
	public void testDeleteVariety(){
		Variety variety = new Variety();
		variety.setId(3);
		
		when(mockDao.findById(3)).thenReturn(variety);
		when(mockDao.delete(variety)).thenReturn(variety);
		
		assertThat(varietyService.removeVariety(3), is(equalTo(variety)));
	}
	
	@Test
	public void testFindAllVarieties(){
		List<Variety> varietyList = new ArrayList<Variety>();
		Variety variety1 = new Variety();
		varietyList.add(variety1);
		Variety variety2 = new Variety();
		varietyList.add(variety2);	
		when(mockDao.findAll()).thenReturn(varietyList);
		
		assertThat(varietyService.findAllVarieties(), hasSize(2));	
	}
	
	@Test
	public void testFindFindVarietyById(){
		Variety variety = new Variety();
		variety.setId(5);
		
		when(mockDao.findById(5)).thenReturn(variety);
		
		assertThat(varietyService.findVarietyById(5), is(equalTo(variety)));
	}

}
