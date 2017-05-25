package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.AgWork;

public class WorkDao implements GenericDaoInterface<AgWork, Integer>{

	private EntityManager entityManager;
	
	public WorkDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public AgWork findById(Integer id) {
		return entityManager.find(AgWork.class, id);
	}

	@Override
	public void delete(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<AgWork> findAll() {
		TypedQuery<AgWork> query = entityManager.createQuery("SELECT w FROM com.baxi.agrohelper.model.AgWork w", AgWork.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<AgWork> workList = findAll();
		for(AgWork work : workList){
			delete(work);
		}
	}

}
