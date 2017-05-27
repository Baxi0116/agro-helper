package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.WorkName;

/** 
 * Implementation of a Data Access Object for {@code WorkName} type entities.
 * 
 * @author gszabo97
 *
 */
public class WorkNameDao implements GenericDaoInterface<WorkName, Integer> {

	private EntityManager entityManager;
	
	public WorkNameDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public WorkName findById(Integer id) {
		return entityManager.find(WorkName.class, id);
	}

	@Override
	public void delete(WorkName entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
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
