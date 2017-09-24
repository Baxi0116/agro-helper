package com.baxi.agrohelper;

/*-
 * #%L
 * agro-helper
 * %%
 * Copyright (C) 2017 University of Debrecen, Faculty of Informatics
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * #L%
 */

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * Main class of the Agro-Helper application.
 * 
 * @author Gergely Szabó
 *
 */

public class MainApp extends Application {
	
	private static Logger logger = LoggerFactory.getLogger(MainApp.class);

	/**
	 * Primary stage of this application.
	 */
	private Stage primaryStage;
	
	/**
	 * Root layout of this application.
	 */
   // private BorderPane rootLayout;
	private AnchorPane rootLayout;	
	
    /**
     * Constructor.
     */
    public MainApp(){}

    /**
     * Start method required for application initialization.
     * 
     * @param primaryStage {@link javafx.stage.Stage} for the application
     */
    
    @Override
    public void init() {
    	EntityManagerProvider.provideEntityManager();
    }
    
    @Override
    public void start(Stage primaryStage) {
    	logger.info("Starting application...");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AgroHelper");
        initRootLayout();
    }
    
    /**
     * Stop method invoked when the user quits from the application.
     * Shuts down the connection to the database.
     */
    @Override
    public void stop(){
    	EntityManagerProvider.closeConnection();
    }

    /**
     * Initializes the root layout.
     */
   /* public void initRootLayout() {
        try {
        	logger.info("Initializing layout...");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/OrchardOverview.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            logger.info("Showing scene");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    
    public void initRootLayout() {
        try {
        	logger.info("Initializing layout...");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MainMenu.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            logger.info("Showing scene");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * 
     * @return primaryStage of this class
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Main method of the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
		launch(args);
	}

}
