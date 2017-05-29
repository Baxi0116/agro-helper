package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.FStatement;

/**
 * Implementation of a Data Access Object for {@code FStatement} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class StatementDao implements GenericDaoInterface<FStatement, Integer> {

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code StatementDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public StatementDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public FStatement persist(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public FStatement update(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public FStatement findById(Integer id) {
		return entityManager.find(FStatement.class, id);
	}

	@Override
	public FStatement delete(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<FStatement> findAll() {
		TypedQuery<FStatement> query = entityManager.createQuery("SELECT s from com.baxi.agrohelper.model.FStatement s", FStatement.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<FStatement> list = findAll();
		for(FStatement statement : list){
			delete(statement);
		}
	}
	
}
