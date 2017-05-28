package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.VarietyName;

/**
 * Implementation of a Data Access Object for {@code VarietyName} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class VarietyNameDao implements GenericDaoInterface<VarietyName, Integer>{

	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	
	/**
	 * Constructor.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public VarietyNameDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public VarietyName persist(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public VarietyName update(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public VarietyName findById(Integer id) {
		return entityManager.find(VarietyName.class, id);
	}

	@Override
	public VarietyName delete(VarietyName entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<VarietyName> findAll() {
		TypedQuery<VarietyName> query = entityManager.createQuery("SELECT v FROM com.baxi.agrohelper.model.VarietyName v",VarietyName.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<VarietyName> varietyNames = findAll();
		for(VarietyName varietyName : varietyNames){
			delete(varietyName);
		}
	}

}
