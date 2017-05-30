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
	
	@Test
	public void testCreateOrchard(){
		Orchard orchard = new Orchard();
		orchard.setOrchardName("Teszt");
		
		when(mockDao.persist(orchard)).thenReturn(orchard);
		
		assertThat(orchardService.createOrchard(orchard), is(equalTo(orchard)));
	}
	
	@Test
	public void testUpdateOrchard(){
		Orchard orchard = new Orchard();
		orchard.setOrchardName("Teszt");
		
		when(mockDao.update(orchard)).thenReturn(orchard);
		
		assertThat(orchardService.updateOrchard(orchard), is(equalTo(orchard)));
	}
	
	@Test
	public void testDeleteOrchard(){
		Orchard orchard = new Orchard();
		orchard.setOrchardName("Teszt");
		orchard.setId(1);
		
		when(mockDao.findById(1)).thenReturn(orchard);
		when(mockDao.delete(orchard)).thenReturn(orchard);
		
		assertThat(orchardService.removeOrchard(1), is(equalTo(orchard)));
	}
		
}
