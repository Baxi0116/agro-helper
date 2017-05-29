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
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceTest {

	private StatementService statementService;
	
	@Mock
	private GenericDaoInterface<FStatement, Integer> mockDao;
	
	@Before
	public void setUp(){
		statementService = new StatementServiceImpl(mockDao);
	}
	
	@Test
	public void testCreateStatement(){
		FStatement statement = new FStatement();
		Orchard orchard = new Orchard();
		statement.setId(4);
		statement.setOrchard(orchard);
		
		when(mockDao.persist(statement)).thenReturn(statement);
		
		assertThat(statementService.createStatement(statement).getId(), is(equalTo(4)));
	}
	
	@Test
	public void testDeleteStatement(){
		FStatement statement = new FStatement();
		Orchard orchard = new Orchard();
		statement.setId(4);
		statement.setOrchard(orchard);
		
		when(mockDao.findById(4)).thenReturn(statement);
		when(mockDao.delete(statement)).thenReturn(statement);
		
		assertThat(statementService.removeStatement(4), is(equalTo(statement)));
	}
	
	@Test
	public void testUpdateStatement(){
		FStatement statement = new FStatement();
		Orchard orchard = new Orchard();
		statement.setId(4);
		statement.setOrchard(orchard);
		
		when(mockDao.update(statement)).thenReturn(statement);
		
		assertThat(statementService.updateStatement(statement).getId(), is(equalTo(4)));
	}
	
	@Test
	public void testFindAllStatements(){
		ArrayList<FStatement> statementData = new ArrayList<FStatement>();
		FStatement statement1 = new FStatement();
		FStatement statement2 = new FStatement();
		statementData.add(statement1);
		statementData.add(statement2);
		
		when(mockDao.findAll()).thenReturn(statementData);
		
		List<FStatement> statementList = statementService.findAllStatement();
		assertThat(statementList, hasSize(2));	
	}
	
	@Test
	public void testFindStatement(){
		FStatement statement = new FStatement();
		statement.setId(5);
		
		when(mockDao.findById(5)).thenReturn(statement);
		
		assertThat(statementService.findStatementById(5), is(equalTo(statement)));
	}
	
	@Test
	public void testCountExpensesForOrchardReturnShouldBe2000Point0(){
		Orchard orchard = new Orchard();
		List<AgWork> works = new ArrayList<AgWork>();
		AgWork work1 = new AgWork();
		work1.setWorkPrice(1000);
		AgWork work2 = new AgWork();
		work2.setWorkPrice(1000);
		works.add(work1);
		works.add(work2);
		orchard.setWorks(works);
		
		assertThat(statementService.countExpensesForOrchard(orchard), is(equalTo(2000.0)));
	}
	
	@Test
	public void testCountExpensesForOrchardWithNoWorksReturnShouldBe0Point0(){
		Orchard orchard = new Orchard();
		List<AgWork> works = new ArrayList<AgWork>();
		orchard.setWorks(works);
	
		assertThat(statementService.countExpensesForOrchard(orchard), is(equalTo(0.0)));
	}
	
	@Test
	public void testCountIncomesForOrchardReturnShouldBe4000Point0(){
		Orchard orchard = new Orchard();
		List<Variety> varietyList = new ArrayList<Variety>();
		Variety variety1 = new Variety();
		variety1.setVarietyArea(2);
		variety1.setVarietyPrice(20);
		variety1.setVarietyYield(100);
		varietyList.add(variety1);
		orchard.setVarieties(varietyList);
		
		assertThat(statementService.countIncomeForOrchard(orchard), is(equalTo(4000.0)));
	}
	
	@Test
	public void testCountIncomesForOrchardReturnShouldBe0(){
		Orchard orchard = new Orchard();
		List<Variety> varietyList = new ArrayList<Variety>();
		orchard.setVarieties(varietyList);
		
		assertThat(statementService.countIncomeForOrchard(orchard), is(equalTo(0.0)));
	}
	
	@Test
	public void testCountProfitForOrchardReturnShouldBe2000Point0(){
		Orchard orchard = new Orchard();
		List<AgWork> works = new ArrayList<AgWork>();
		AgWork work1 = new AgWork();
		work1.setWorkPrice(1000);
		AgWork work2 = new AgWork();
		work2.setWorkPrice(1000);
		works.add(work1);
		works.add(work2);
		orchard.setWorks(works);
		List<Variety> varietyList = new ArrayList<Variety>();
		Variety variety1 = new Variety();
		variety1.setVarietyArea(2);
		variety1.setVarietyPrice(20);
		variety1.setVarietyYield(100);
		varietyList.add(variety1);
		orchard.setVarieties(varietyList);
		
		assertThat(statementService.countProfitForOrchard(orchard), is(equalTo(2000.0)));

	}
	@Test
	public void testCountProfitForOrchardReturnShouldBe0Point0(){
		Orchard orchard = new Orchard();
		List<AgWork> works = new ArrayList<AgWork>();
		orchard.setWorks(works);
		List<Variety> varietyList = new ArrayList<Variety>();
		orchard.setVarieties(varietyList);
		
		assertThat(statementService.countProfitForOrchard(orchard), is(equalTo(0.0)));

	}
}
