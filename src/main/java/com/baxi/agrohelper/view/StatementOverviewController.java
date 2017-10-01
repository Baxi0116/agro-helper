package com.baxi.agrohelper.view;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StatementOverviewController {
	
	private static Logger logger = LoggerFactory.getLogger(StatementOverviewController.class);
	
	@FXML
	private Button statementForOrchardButton;
	
	@FXML
	private Button statementForAllButton;
	
	@FXML
	private Button backButton;
	
	
	@FXML
	private void handleStatementForOrchardButton() {
		logger.debug("Statement for Orchard Button pressed...");
		Stage stage;
		Parent root;
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StatementOrchard.fxml"));
			root = loader.load();
			stage = (Stage) statementForOrchardButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to Statement for Orchard button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleStatementForAllButton() {
		logger.debug("Statement for all Button pressed...");
		Stage stage;
		Parent root;
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StatementAll.fxml"));
			root = loader.load();
			stage = (Stage) statementForAllButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to Statement for all button interaction");
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleBackButton() {
		logger.debug("Back Button pressed...");
		Stage stage;
		Parent root;
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
			root = loader.load();
			stage = (Stage) backButton.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();			
			
		}catch(IOException e){
			logger.error("Can't respond to back button interaction");
			e.printStackTrace();
		}
	}
	
}
