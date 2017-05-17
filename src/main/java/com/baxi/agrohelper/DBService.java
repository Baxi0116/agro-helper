package com.baxi.agrohelper;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import com.baxi.agrohelper.model.Crop;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.AgWork;

public class DBService {

	private EntityManager entityManager;
	
	public DBService(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public Orchard createOrchard(String nev, LocalDate telepitesEve, String helyrajziSzam, String meparKod, int fakSzama){
		Orchard orchard = new Orchard();
		orchard.setNev(nev);
		orchard.setTelepitesEve(telepitesEve);
		orchard.setHelyrajziSzam(helyrajziSzam);
		orchard.setMeparKod(meparKod);
		orchard.setFakSzama(fakSzama);
		entityManager.persist(orchard);
		return orchard;
	}
	
	public AgWork createWork(String munkavegzesNeve, int munkavegzesAra){
		AgWork work = new AgWork();
		work.setMunkavegzesNeve(munkavegzesNeve);
		work.setMunkavegzesAra(munkavegzesAra);
		entityManager.persist(work);
		return work;
	}
	
	public Crop createCrop(String nev){
		Crop crop = new Crop();
		crop.setNev(nev);
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
}
