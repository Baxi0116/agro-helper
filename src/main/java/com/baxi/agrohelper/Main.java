package com.baxi.agrohelper;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.baxi.agrohelper.model.Orchard;

public class Main {
	
	public static void main(String[] args){
		
		MainApp uiService = new MainApp();
		
		uiService.launch(args);
		
	}
	
}
