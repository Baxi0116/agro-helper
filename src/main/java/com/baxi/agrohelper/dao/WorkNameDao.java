package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.WorkName;

/** 
 * Implementation of a Data Access Object for {@code WorkName} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class WorkNameDao implements GenericDaoInterface<WorkName, Integer> {

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code WorkNameDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public WorkNameDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public WorkName persist(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public WorkName update(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public WorkName findById(Integer id) {
		return entityManager.find(WorkName.class, id);
	}

	@Override
	public WorkName delete(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	@Override
	public List<WorkName> findAll() {
		TypedQuery<WorkName> query = entityManager.createQuery("SELECT w FROM com.baxi.agrohelper.model.WorkName w", WorkName.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<WorkName> workNames = findAll();
		for(WorkName workName : workNames){
			delete(workName);
		}
		
	}

	
	
}
