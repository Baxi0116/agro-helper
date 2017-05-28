package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.AgWork;

/**
 * Implementation of a Data Access Object for {@code AgWork} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class WorkDao implements GenericDaoInterface<AgWork, Integer>{

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructor.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public WorkDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public AgWork persist(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public AgWork update(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public AgWork findById(Integer id) {
		return entityManager.find(AgWork.class, id);
	}

	@Override
	public AgWork delete(AgWork entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
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
