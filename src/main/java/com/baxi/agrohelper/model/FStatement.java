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

/**
 * Java abstraction of financial statement entities.
 * 
 * @author Gergely Szabó
 *
 */
@Entity
public class FStatement {

	/**
	 * Id of this entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="STATEMENT_ID")
	private int id;
	
	/**
	 * Creation date of the statement. 
	 */
	@Column(name="STATEMENT_DATE")
	private LocalDate statementDate;
	
	/**
	 * Expenses of the {@link com.baxi.agrohelper.model.Orchard} this statement is about.
	 */
	@Column(name="EXPENSES")
	private double expenses;
	
	/**
	 * Income of the {@link com.baxi.agrohelper.model.Orchard} this statement is about.
	 */
	@Column(name="INCOMES")
	private double income;
	
	/**
	 * Profit depending on the income and expenses.
	 */
	@Column(name="PROFIT")
	private double profit;
	
	/**
	 * Many to one relation with the {@link com.baxi.agrohelper.model.Orchard} entity.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORCHARD_ID", nullable = false)
	private Orchard orchard;
	
	/**
	 * Constructs a newly allocated {@code FStatement} object.
	 */
	public FStatement(){}
	
	/**
	 * Constructs a newly allocated {@code FStatement} object, with the given parameters.
	 * 
	 * @param date date of creation
	 * @param expenses expenses of the orchard
	 * @param incomes income of the orchard
	 * @param profit profit of the orchard
	 */
	public FStatement(LocalDate date, double expenses, double incomes, double profit){
		this.statementDate = date;
		this.expenses = expenses;
		this.income = incomes;
		this.profit = profit;
	}

	/**
	 * Getter method for the {@code id}.
	 * 
	 * @return id of this object
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for the {@code id}.
	 * 
	 * @param id id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for the {@code statementDate}.
	 *  
	 * @return statementDate of this object
	 */
	public LocalDate getStatementDate() {
		return statementDate;
	}

	/**
	 * Setter method for the {@code statementDate}.
	 * 
	 * @param statementDate statementDate to set
	 */
	public void setStatementDate(LocalDate statementDate) {
		this.statementDate = statementDate;
	}

	/**
	 * Getter method for {@code expenses}.
	 *  
	 * @return expenses of this object
	 */
	public double getExpenses() {
		return expenses;
	}

	/**
	 * Setter method for {@code expenses}.
	 * 
	 * @param expenses expenses to set
	 */
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	/**
	 * Getter method for {@code income}.
	 *  
	 * @return income of this object
	 */
	public double getIncome() {
		return income;
	}

	/**
	 * Setter method for {@code income}.
	 * 
	 * @param incomes income to set
	 */
	public void setIncome(double incomes) {
		this.income = incomes;
	}

	/**
	 * Getter method for {@code profit}.
	 *  
	 * @return profit of this object
	 */
	public double getProfit() {
		return profit;
	}

	/**
	 * Setter method for {@code profit}.
	 * 
	 * @param profit profit to set
	 */
	public void setProfit(double profit) {
		this.profit = profit;
	}

	/**
	 * Getter method for {@code orchard}.
	 *  
	 * @return orchard of this object
	 */
	public Orchard getOrchard() {
		return orchard;
	}

	/**
	 * Setter method for {@code orchard}.
	 * 
	 * @param orchard orchard to set
	 */
	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	/**
	 * Creates a {@code String} representation for the object.
	 * @return the String representation of the object
	 */
	@Override
	public String toString() {
		return "Statement [statementDate=" + statementDate + ", expenses=" + expenses + ", incomes=" + income
				+ ", profit=" + profit + "]";
	}

}
