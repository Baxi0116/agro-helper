package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.FStatement;

public class StatementDao implements GenericDaoInterface<FStatement, Integer> {

	private EntityManager entityManager;
	
	public StatementDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public FStatement findById(Integer id) {
		return entityManager.find(FStatement.class, id);
	}

	@Override
	public void delete(FStatement entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
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