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
import com.baxi.agrohelper.model.AgWork;

@RunWith(MockitoJUnitRunner.class)
public class WorkServiceTest {

	private WorkService workService;
	
	@Mock
	private GenericDaoInterface<AgWork, Integer> mockDao;
	
	@Before
	public void setUp(){
		workService = new WorkServiceImpl(mockDao);
	}
	
	@Test
	public void testCreateWork(){
		AgWork work = new AgWork();
		
		when(mockDao.persist(work)).thenReturn(work);
		
		assertThat(workService.createWork(work), is(equalTo(work)));
	}
	
	@Test
	public void testUpdateWork(){
		AgWork work = new AgWork();
		
		when(mockDao.update(work)).thenReturn(work);
		
		assertThat(workService.updateWork(work), is(equalTo(work)));
	}
	
	@Test
	public void testDeleteWork(){
		AgWork work = new AgWork();
		work.setId(5);
		
		when(mockDao.findById(5)).thenReturn(work);
		when(mockDao.delete(work)).thenReturn(work);
		
		assertThat(workService.deleteWork(5), is(equalTo(work)));
	}
	
	@Test
	public void testFindAllWorks(){
		List<AgWork> workList = new ArrayList<AgWork>();
		AgWork work1 = new AgWork();
		workList.add(work1);
		AgWork work2 = new AgWork();
		workList.add(work2);
		
		when(mockDao.findAll()).thenReturn(workList);
		
		assertThat(workService.findAllWorks(), hasSize(2));
	}
	
	@Test
	public void testFindWorkById(){
		AgWork work = new AgWork();
		work.setId(3);
		
		when(mockDao.findById(3)).thenReturn(work);
		
		assertThat(workService.findWorkById(3), is(equalTo(work)));
	}
}
