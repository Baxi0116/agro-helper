package com.baxi.agrohelper;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    private BorderPane rootLayout;
 
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
    public void initRootLayout() {
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
