package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.Orchard;

/**
 * Implementation of a Data Access Object for {@code Orchard} type entities.
 * 
 * @author Gergely Szabó
 *
 */
public class OrchardDao implements GenericDaoInterface<Orchard, Integer> {
	
	/**
	 * EntityManager for database access.
	 */
	private EntityManager entityManager;
	
	/**
	 * Constructs a newly allocated {@code OrchardDao} object, and initializes its connection to the database.
	 * 
	 * @param entityManager  EntityManager for database access
	 */
	public OrchardDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Override
	public Orchard persist(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public Orchard update(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public Orchard findById(Integer id) {
		Orchard orchard = entityManager.find(Orchard.class, id);
		return orchard;
	}

	@Override
	public Orchard delete(Orchard entity) {	
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	@Override
	public List<Orchard> findAll() {
		TypedQuery<Orchard> query = entityManager.createQuery("SELECT o FROM com.baxi.agrohelper.model.Orchard o", Orchard.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Orchard> orchardList = findAll();
		for(Orchard orchard : orchardList){
			delete(orchard);
		}
	}

}
