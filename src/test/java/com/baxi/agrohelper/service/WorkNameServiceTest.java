package com.baxi.agrohelper.service;

/*-
 * #%L
 * agro-helper
 * %%
 * Copyright (C) 2017 University of Debrecen, Faculty of Informatics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

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
import com.baxi.agrohelper.model.WorkName;

@RunWith(MockitoJUnitRunner.class)
public class WorkNameServiceTest {

	private WorkNameService workNameService;
	
	@Mock
	private GenericDaoInterface<WorkName, Integer> mockDao;
	
	@Before
	public void setUp(){
		workNameService = new WorkNameServiceImpl(mockDao);
	}
	
	@Test
	public void testCreateWorkName(){
		WorkName workName = new WorkName();
		
		when(mockDao.persist(workName)).thenReturn(workName);
		
		assertThat(workNameService.createWorkName(workName), is(equalTo(workName)));
	}
	
	@Test
	public void testUpdateWorkName(){
		WorkName workName = new WorkName();
		
		when(mockDao.update(workName)).thenReturn(workName);
		
		assertThat(workNameService.updateWorkName(workName), is(equalTo(workName)));
	}
	
	@Test
	public void testDeleteWorkNameById(){
		WorkName workName = new WorkName();
		workName.setId(7);
		
		when(mockDao.findById(7)).thenReturn(workName);
		when(mockDao.delete(workName)).thenReturn(workName);
		
		assertThat(workNameService.deleteWorkName(7), is(equalTo(workName)));
	}
	
	@Test
	public void testFindWorkNameById(){
		WorkName workName = new WorkName();
		workName.setId(8);
		
		when(mockDao.findById(8)).thenReturn(workName);
		
		assertThat(workNameService.findWorkNameById(8), is(equalTo(workName)));
	}
	
	@Test
	public void testFindAllWorkNames(){
		WorkName workName1 = new WorkName();
		WorkName workName2 = new WorkName();
		WorkName workName3 = new WorkName();
		List<WorkName> workNameList = new ArrayList<WorkName>();
		workNameList.add(workName1);
		workNameList.add(workName2);
		workNameList.add(workName3);
		
		when(mockDao.findAll()).thenReturn(workNameList);
		
		assertThat(workNameService.findAllWorkNames(), hasSize(3));
	}
	
	@Test
	public void testGetAllWorkNames(){
		WorkName workName1 = new WorkName();
		workName1.setName("Teszt1");
		WorkName workName2 = new WorkName();
		workName2.setName("Teszt2");
		WorkName workName3 = new WorkName();
		workName3.setName("Teszt3");
		List<WorkName> workNameList = new ArrayList<WorkName>();
		workNameList.add(workName1);
		workNameList.add(workName2);
		workNameList.add(workName3);
		
		when(mockDao.findAll()).thenReturn(workNameList);
		
		List<String> expectedNamesList = new ArrayList<String>();
		expectedNamesList.add("Teszt1");
		expectedNamesList.add("Teszt2");
		expectedNamesList.add("Teszt3");
		
		assertThat(workNameService.getAllWorkNames(), hasSize(3));
		assertThat(workNameService.getAllWorkNames(), is(equalTo(expectedNamesList)));
	}
}
