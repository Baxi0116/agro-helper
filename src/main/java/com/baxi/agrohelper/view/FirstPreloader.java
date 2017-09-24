package com.baxi.agrohelper.view;

import java.io.IOException;

import com.baxi.agrohelper.MainApp;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FirstPreloader extends Preloader {
	
	
    private Stage stage;
    private AnchorPane layout;
    
    public Scene initLoadingScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/LoadingScreen.fxml"));
            layout = (AnchorPane) loader.load();
            Scene scene = new Scene(layout);
            return scene;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(initLoadingScreen());        
        stage.show();
    }
    
 
    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }    
}