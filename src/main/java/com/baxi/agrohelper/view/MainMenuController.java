package com.baxi.agrohelper.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

	private static Logger logger = LoggerFactory.getLogger(MainMenuController.class);
	
	@FXML
	private Button manageOrchardsButton;
	
	@FXML
	private Button makeStatementsButton;
	
	@FXML
	private Button exitButton;
	
	@FXML
	public void handleManageOrchardsButton() {
		
		Stage stage;
		Parent root;
		
		try{
			logger.debug("handling manageOrchardsButton");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OrchardOverview.fxml"));
			root = loader.load();
			stage = (Stage) manageOrchardsButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to Manage Orchards button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleMakeStatementsButton() {
		
		Stage stage;
		Parent root;
		
		try{
			logger.debug("handling makeStatementsButton");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StatementOverview.fxml"));
			root = loader.load();
			stage = (Stage) makeStatementsButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to Make Statements button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleExitButton() {
		logger.warn("exiting MainMenuController");
		Platform.exit();
	}
	
	
}
