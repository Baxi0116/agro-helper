package com.baxi.agrohelper.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FStatement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="STATEMENT_ID")
	private int id;
	
	@Column(name="STATEMENT_DATE")
	private LocalDate statementDate;
	
	@Column(name="EXPENSES")
	private double expenses;
	
	@Column(name="INCOMES")
	private double income;
	
	@Column(name="PROFIT")
	private double profit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORCHARD_ID", nullable = false)
	private Orchard orchard;
	
	
	public FStatement(){}
	
	public FStatement(LocalDate date, double expenses, double incomes, double profit){
		this.statementDate = date;
		this.expenses = expenses;
		this.income = incomes;
		this.profit = profit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(LocalDate statementDate) {
		this.statementDate = statementDate;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double incomes) {
		this.income = incomes;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	@Override
	public String toString() {
		return "Statement [statementDate=" + statementDate + ", expenses=" + expenses + ", incomes=" + income
				+ ", profit=" + profit + "]";
	}

}
