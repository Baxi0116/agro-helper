package com.baxi.agrohelper;

import java.io.IOException;

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

	/**
	 * Primary stage of this application.
	 */
	private Stage primaryStage;
	
	/**
	 * Root layout of this application.
	 */
    private BorderPane rootLayout;
 
    /**
     * Constructor
     */
    public MainApp(){}

    /**
     * Start method required for application initialization.
     * 
     * @param primaryStage giving stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrchardOverview.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
		launch(args);
	}

}
