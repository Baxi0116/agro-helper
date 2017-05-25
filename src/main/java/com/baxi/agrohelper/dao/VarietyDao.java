package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.Variety;

public class VarietyDao implements GenericDaoInterface<Variety, Integer>{

	private EntityManager entityManager;
	
	public VarietyDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(Variety entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Variety entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public Variety findById(Integer id) {
		return entityManager.find(Variety.class, id);
	}

	@Override
	public void delete(Variety entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Variety> findAll() {
		TypedQuery<Variety> query = entityManager.createQuery("SELECT v FROM com.baxi.agrohelper.model.Variety v", Variety.class);
		return query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Variety> varietyList = findAll();
		for(Variety variety : varietyList){
			delete(variety);
		}
	}
}
