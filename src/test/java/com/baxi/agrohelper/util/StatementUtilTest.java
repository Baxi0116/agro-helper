package com.baxi.agrohelper.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;

@RunWith(MockitoJUnitRunner.class)
public class StatementUtilTest {
	
	private Orchard testOrchard;
	private List<Orchard> testOrchardList;
	private Variety testVariety;
	
	@Before
	public void setUp() {
		testOrchard = new Orchard();
		testOrchardList = new ArrayList<Orchard>();
		
		Orchard orchard2 = new Orchard();
		
		AgWork work1 = new AgWork();
		AgWork work2 = new AgWork();
		
		testVariety = new Variety();
		Variety variety2 = new Variety();
		
		work1.setWorkPrice(10);
		work1.setMaterialPrice(10);
		work1.setTotalPrice(20);
		
		work2.setWorkPrice(10);
		work2.setMaterialPrice(10);
		work2.setTotalPrice(20);
		
		testVariety.setVarietyArea(2);
		testVariety.setVarietyPrice(10);
		testVariety.setVarietyYield(10);
		testVariety.setTotalHarvest(0.005);
		testVariety.setTotalIncome(100);
		
		variety2.setVarietyArea(2);
		variety2.setVarietyPrice(10);
		variety2.setVarietyYield(10);
		variety2.setTotalHarvest(0.005);
		variety2.setTotalIncome(100);
		
		List<AgWork> workList = new ArrayList<AgWork>();
		workList.add(work1);
		workList.add(work2);
		
		List<Variety> varietyList = new ArrayList<Variety>();
		varietyList.add(testVariety);
		varietyList.add(variety2);
		
		testOrchard.setWorks(workList);
		testOrchard.setVarieties(varietyList);
		
		orchard2.setVarieties(varietyList);
		orchard2.setWorks(workList);
		
		testOrchardList.add(testOrchard);
		testOrchardList.add(orchard2);
	}
	
	@Test
	public void testCountExpensesForOrchard() {
		assertThat(StatementUtil.countExpensesForOrchard(testOrchard), is(equalTo(40.0)));
	}
	
	@Test
	public void testCountIncomeForOrchard() {
		assertThat(StatementUtil.countIncomeForOrchard(testOrchard), is(equalTo(200.0)));
	}
	
	@Test
	public void testCountProfitForOrchard() {
		assertThat(StatementUtil.countProfitForOrchard(testOrchard), is(equalTo(160.0)));
	}
	
	@Test
	public void testCountMaterialExpensesForOrchard() {
		assertThat(StatementUtil.countMaterialExpensesForOrchard(testOrchard), is(equalTo(20.0)));
	}
	
	@Test
	public void testCountWorkExpensesForOrchard() {
		assertThat(StatementUtil.countWorkExpensesForOrchard(testOrchard), is(equalTo(20.0)));
	}
	
	@Test
	public void testCountExpensesForAllOrchard() {
		assertThat(StatementUtil.countExpensesForAllOrchard(testOrchardList), is(equalTo(80.0)));
	}
	
	@Test
	public void testCountIncomeForAllOrchard() {
		assertThat(StatementUtil.countIncomeForAllOrchard(testOrchardList), is(equalTo(400.0)));
	}
	
	@Test
	public void testCountProfitForAllOrchard() {
		assertThat(StatementUtil.countProfitForAllOrchard(testOrchardList), is(equalTo(320.0)));
	}
	
	@Test
	public void testCountMaterialExpensesForAllOrchard() {
		assertThat(StatementUtil.countMaterialExpensesForAllOrchard(testOrchardList), is(equalTo(40.0)));
	}
	
	@Test
	public void testCountWorkExpensesForAllOrchard() {
		assertThat(StatementUtil.countWorkExpensesForAllOrchard(testOrchardList), is(equalTo(40.0)));
	}
	
	@Test
	public void testCountVarietyIncome() {
		assertThat(StatementUtil.countVarietyIncome(testVariety), is(equalTo(100.0)));
	}
	
	@Test
	public void testCountVarietyHarvest() {
		assertThat(StatementUtil.countVarietyHarvest(testVariety), is(equalTo(0.005)));
	}
}
