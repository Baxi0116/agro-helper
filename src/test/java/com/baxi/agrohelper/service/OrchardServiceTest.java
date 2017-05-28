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
import com.baxi.agrohelper.model.Orchard;

@RunWith(MockitoJUnitRunner.class)
public class OrchardServiceTest {

	private OrchardService orchardService;
	
	@Mock
	private GenericDaoInterface<Orchard, Integer> mockDao;
	
	@Before
	public void setUp(){
		orchardService = new OrchardServiceImpl(mockDao);
	}
	
	@Test
	public void testFindAllOrchards(){
		ArrayList<Orchard> orchardData = new ArrayList<Orchard>();
		Orchard orchard1 = new Orchard();
		Orchard orchard2 = new Orchard();
		Orchard orchard3 = new Orchard();
		orchardData.add(orchard1);
		orchardData.add(orchard2);
		orchardData.add(orchard3);
		
		when(mockDao.findAll()).thenReturn(orchardData);
		
		List<Orchard> orchardList = orchardService.findAllOrchards();
		
		assertThat(orchardList, hasSize(3));
	}
	
	@Test
	public void testFindOrchardById(){
		Orchard orchard = new Orchard();
		orchard.setId(8);
		orchard.setOrchardName("teszt");
		
		when(mockDao.findById(8)).thenReturn(orchard);
		
		assertThat(orchardService.findOrchardById(8).getOrchardName(), is(equalTo("teszt")));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
