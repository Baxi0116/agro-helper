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
import com.baxi.agrohelper.model.VarietyName;

@RunWith(MockitoJUnitRunner.class)
public class VarietyNameServiceTest {

	private VarietyNameService varietyNameService;
	
	@Mock
	private GenericDaoInterface<VarietyName, Integer> mockDao;
	
	@Before
	public void setUp(){
		varietyNameService = new VarietyNameServiceImpl(mockDao);
	}
	
	@Test
	public void testCreateVarietyName(){
		VarietyName varietyName = new VarietyName();
		
		when(mockDao.persist(varietyName)).thenReturn(varietyName);
		
		assertThat(varietyNameService.createVarietyName(varietyName), is(equalTo(varietyName)));
	}
	
	@Test
	public void testUpdateVarietyName(){
		VarietyName varietyName = new VarietyName();
		
		when(mockDao.update(varietyName)).thenReturn(varietyName);
		
		assertThat(varietyNameService.updateVarietyName(varietyName), is(equalTo(varietyName)));
	}
	
	@Test
	public void testDeleteVarietyName(){
		VarietyName varietyName = new VarietyName();
		varietyName.setId(7);
		
		when(mockDao.findById(7)).thenReturn(varietyName);
		when(mockDao.delete(varietyName)).thenReturn(varietyName);
		
		assertThat(varietyNameService.deleteVarietyName(7), is(equalTo(varietyName)));
	}
	
	@Test
	public void testFindVarietyNameById(){
		VarietyName varietyName = new VarietyName();
		varietyName.setId(8);
		
		when(mockDao.findById(8)).thenReturn(varietyName);
		
		assertThat(varietyNameService.findVarietyNameById(8), is(equalTo(varietyName)));
	}
	
	@Test
	public void testFindAllVarietyNames(){
		VarietyName varietyName1 = new VarietyName();
		VarietyName varietyName2 = new VarietyName();
		VarietyName varietyName3 = new VarietyName();
		List<VarietyName> varietyNameList = new ArrayList<VarietyName>();
		varietyNameList.add(varietyName1);
		varietyNameList.add(varietyName2);
		varietyNameList.add(varietyName3);
		
		when(mockDao.findAll()).thenReturn(varietyNameList);
		
		assertThat(varietyNameService.findAllVarietyNames(), hasSize(3));
	}
	
	@Test
	public void testGetAllVarietyNames(){
		VarietyName varietyName1 = new VarietyName();
		varietyName1.setName("Teszt1");
		VarietyName varietyName2 = new VarietyName();
		varietyName2.setName("Teszt2");
		VarietyName varietyName3 = new VarietyName();
		varietyName3.setName("Teszt3");
		List<VarietyName> varietyNameList = new ArrayList<VarietyName>();
		varietyNameList.add(varietyName1);
		varietyNameList.add(varietyName2);
		varietyNameList.add(varietyName3);
		
		when(mockDao.findAll()).thenReturn(varietyNameList);
		
		List<String> expectedNamesList = new ArrayList<String>();
		expectedNamesList.add("Teszt1");
		expectedNamesList.add("Teszt2");
		expectedNamesList.add("Teszt3");
		
		assertThat(varietyNameService.getAllVarietyNames(), hasSize(3));
		assertThat(varietyNameService.getAllVarietyNames(), is(equalTo(expectedNamesList)));
	}
	
}
