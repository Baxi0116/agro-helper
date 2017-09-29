package com.baxi.agrohelper.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.WorkNameDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.WorkNameServiceImpl;

public class StatementUtil {
	
	private static Logger logger = LoggerFactory.getLogger(StatementUtil.class);
	
	WorkNameServiceImpl nameService = new WorkNameServiceImpl(new WorkNameDao(EntityManagerProvider.provideEntityManager()));
	
	public double countExpensesForOrchard(Orchard orchard) {
		logger.info("Counting expenses for Orchard: {}", orchard.getOrchardName());
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getWorkPrice();
		}
		return expenses;
	}

	public double countIncomeForOrchard(Orchard orchard) {
		logger.info("Counting income for Orchard: {}", orchard.getOrchardName());
		List<Variety> varietyList = orchard.getVarieties();
		double income = 0;
		for(Variety variety : varietyList){
			double varietyYieldForHectars = variety.getVarietyArea() * variety.getVarietyYield();
			income += (varietyYieldForHectars * variety.getVarietyPrice());
		}
		return income;
	}

	public double countProfitForOrchard(Orchard orchard) {
		logger.info("Counting profit for Orchard: {}", orchard.getOrchardName());
		double profit = countIncomeForOrchard(orchard) - countExpensesForOrchard(orchard);
		return profit;
	}

	public double countExpensesForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting expenses for all Orchard");
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public double countIncomeForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting income for all Orchard");
		double income = 0;
		for(Orchard orchard : orchardList){
			income += countIncomeForOrchard(orchard);
		}
		return income;
	}

	public double countProfitForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting profit for all Orchard");
		double profit = 0;
		for(Orchard orchard : orchardList){
			profit += countProfitForOrchard(orchard);
		}
		return profit;
	}

	public double countMaterialExpensesForOrchard(Orchard orchard) {
		logger.info("Counting material expenses for Orchard: {}", orchard.getOrchardName());
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getMaterialPrice();
		}
		return expenses;
	}

	public double countMaterialExpensesForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting material expenses for all Orchard");
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countMaterialExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public double countWorkExpensesForOrchard(Orchard orchard) {
		double expenses = 0;
		expenses = countExpensesForOrchard(orchard) - countMaterialExpensesForOrchard(orchard);
		return expenses;
	}

	public double countWorkExpensesForAllOrchard(List<Orchard> orchardList) {
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countWorkExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public HashMap<String, List<Integer>> groupWorksWithPrices(Orchard orchard){
		HashMap<String, List<Integer>> workMap = new HashMap<>();
		List<AgWork> workList = orchard.getWorks();
		List<String> workNameList = nameService.getAllWorkNames();

		for(String name : workNameList){
			int workPrice = 0;
			int materialPrice = 0;
			int totalPrice = 0;
			List<Integer> priceList = new ArrayList<Integer>();
			for(AgWork work : workList){
				if(work.getWorkDesignation() == name){
					workPrice += work.getWorkPrice();
					materialPrice = work.getMaterialPrice();
					totalPrice += work.getTotalPrice();
				}
			}
			priceList.add(workPrice);
			priceList.add(materialPrice);
			priceList.add(totalPrice);
			workMap.put(name, priceList);
		}
				
		return workMap;
	}



}
