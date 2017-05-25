package com.baxi.agrohelper.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.baxi.agrohelper.model.Crop;

public class CropDao implements GenericDaoInterface<Crop, Integer>{

	private EntityManager entityManager;
	
	public CropDao(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public Crop findById(Integer id) {
		Crop crop = entityManager.find(Crop.class, id);
		return crop;
	}

	@Override
	public void delete(Crop entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Crop> findAll() {
		TypedQuery<Crop> query = entityManager.createQuery("SELECT c FROM com.baxi.agrohelper.model.Crop c", Crop.class);
		return query.getResultList();
	}

	public void deleteAll() {
		List<Crop> cropList = findAll();
		for(Crop crop : cropList){
			delete(crop);
		}
	}

}
