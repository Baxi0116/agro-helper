package com.baxi.agrohelper;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
    
    private static Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    public MainApp(){}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AgroHelper");

        initRootLayout();
        showOrchardOverview();
    }
    
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
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            logger.debug(loader.getLocation().toString());
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the orchard overview inside the root layout.
     */
    public void showOrchardOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrchardOverview.fxml"));
            logger.debug(loader.getLocation().toString());
            AnchorPane orchardOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(orchardOverview);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
		launch(args);
	}

}
