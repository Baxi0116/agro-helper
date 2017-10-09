package com.baxi.agrohelper.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;

public class StatementUtil {
	
	private static Logger logger = LoggerFactory.getLogger(StatementUtil.class);
	
	public static double countExpensesForOrchard(Orchard orchard) {
		logger.info("Counting expenses for Orchard: {}", orchard.getOrchardName());
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getTotalPrice();
		}
		return expenses;
	}

	public static double countIncomeForOrchard(Orchard orchard) {
		logger.info("Counting income for Orchard: {}", orchard.getOrchardName());
		List<Variety> varietyList = orchard.getVarieties();
		double income = 0;
		for(Variety variety : varietyList){
			income += countVarietyIncome(variety);
		}
		return income;
	}

	public static double countProfitForOrchard(Orchard orchard) {
		logger.info("Counting profit for Orchard: {}", orchard.getOrchardName());
		double profit = countIncomeForOrchard(orchard) - countExpensesForOrchard(orchard);
		return profit;
	}

	public static double countExpensesForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting expenses for all Orchard");
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public static double countIncomeForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting income for all Orchard");
		double income = 0;
		for(Orchard orchard : orchardList){
			income += countIncomeForOrchard(orchard);
		}
		return income;
	}

	public static double countProfitForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting profit for all Orchard");
		double profit = 0;
		for(Orchard orchard : orchardList){
			profit += countProfitForOrchard(orchard);
		}
		return profit;
	}

	public static double countMaterialExpensesForOrchard(Orchard orchard) {
		logger.info("Counting material expenses for Orchard: {}", orchard.getOrchardName());
		List<AgWork> workList = orchard.getWorks();
		double expenses = 0;
		for(AgWork work : workList){
			expenses += work.getMaterialPrice();
		}
		return expenses;
	}

	public static double countMaterialExpensesForAllOrchard(List<Orchard> orchardList) {
		logger.info("Counting material expenses for all Orchard");
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countMaterialExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public static double countWorkExpensesForOrchard(Orchard orchard) {
		double expenses = 0;
		expenses = countExpensesForOrchard(orchard) - countMaterialExpensesForOrchard(orchard);
		return expenses;
	}

	public static double countWorkExpensesForAllOrchard(List<Orchard> orchardList) {
		double expenses = 0;
		for(Orchard orchard : orchardList){
			expenses += countWorkExpensesForOrchard(orchard);
		}
		return expenses;
	}

	public static double countVarietyIncome(Variety variety) {
		
		return (variety.getVarietyYield() * variety.getVarietyPrice());
	}
	
	public static double countVarietyHarvest(Variety variety) {
		
		return (variety.getVarietyYield() / 1000)/variety.getVarietyArea();
	}


}
