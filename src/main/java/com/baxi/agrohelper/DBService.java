package com.baxi.agrohelper;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Crop;
import com.baxi.agrohelper.model.Orchard;

public class DBService {
	
	private static Logger logger = LoggerFactory.getLogger(DBService.class);

	private EntityManager entityManager;
	
	public DBService(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	public Orchard createOrchard(String nev, LocalDate telepitesEve, String helyrajziSzam, String meparKod, int fakSzama){
		Orchard orchard = new Orchard();
		logger.debug("Creating Orchard");
		orchard.setOrchardName(nev);
		orchard.setYearOfPlantation(telepitesEve);
		orchard.setTopographicNumber(helyrajziSzam);
		orchard.setMeparCode(meparKod);
		orchard.setNumberOfTrees(fakSzama);
		entityManager.persist(orchard);
		return orchard;
	}
	
	public AgWork createWork(String munkavegzesNeve, int munkavegzesAra){
		AgWork work = new AgWork();
		work.setWorkDesignation(munkavegzesNeve);
		work.setWorkPrice(munkavegzesAra);
		entityManager.persist(work);
		return work;
	}
	
	public Crop createCrop(String nev){
		Crop crop = new Crop();
		crop.setCropName(nev);
		entityManager.persist(crop);
		return crop;
	}
	
	public void removeOrchard(int id){
		Orchard orchard = entityManager.find(Orchard.class, id);
		if(orchard != null){
			entityManager.remove(orchard);
		}
	}
	
	public void removeWork(int id){
		AgWork work = entityManager.find(AgWork.class, id);
		if(work != null){
			entityManager.remove(work);
		}
	}
	
	public void removeCrop(int id){
		Crop crop = entityManager.find(Crop.class, id);
		if(crop != null){
			entityManager.remove(crop);
		}
	}
	
	public Orchard findOrchard(int id){
		Orchard orchard = entityManager.find(Orchard.class, id);
		return orchard;
	}
	
	public AgWork findWork(int id){
		AgWork work = entityManager.find(AgWork.class, id);
		return work;
	}
	
	public Crop findCrop(int id){
		Crop crop = entityManager.find(Crop.class, id);
		return crop;
	}
	
	public List<Orchard> findAllOrchards(){
		TypedQuery<Orchard> query = entityManager.createQuery("SELECT o FROM com.baxi.agrohelper.model.Orchard o", Orchard.class);
		return query.getResultList();
	}
}
