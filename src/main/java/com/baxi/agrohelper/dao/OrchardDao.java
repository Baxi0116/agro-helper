package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.Orchard;

public class OrchardDao implements GenericDaoInterface<Orchard, Integer> {
	
	private EntityManager entityManager;
	
	public OrchardDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Orchard entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public Orchard findById(Integer id) {
		Orchard orchard = entityManager.find(Orchard.class, id);
		return orchard;
	}

	@Override
	public void delete(Orchard entity) {	
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		
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
