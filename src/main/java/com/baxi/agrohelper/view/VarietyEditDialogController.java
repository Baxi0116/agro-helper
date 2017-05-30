package com.baxi.agrohelper.view;

import com.baxi.agrohelper.dao.VarietyDao;
import com.baxi.agrohelper.dao.VarietyNameDao;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.VarietyNameService;
import com.baxi.agrohelper.service.VarietyNameServiceImpl;
import com.baxi.agrohelper.service.VarietyService;
import com.baxi.agrohelper.service.VarietyServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class VarietyEditDialogController {

	@FXML
	private ChoiceBox<String> nameBox;
	
	@FXML
	private TextField priceTextField;
	
	@FXML
	private TextField yieldTextField;
	
	@FXML
	private TextField areaTextField;
	
	private Stage dialogStage;
	private Variety variety;
	private boolean okClicked;
	
	private VarietyService varietyService;
	private VarietyNameService varietyNameService;
	
	@FXML
	private void initialize(){
		varietyService = new VarietyServiceImpl(new VarietyDao(EntityManagerProvider.provideEntityManager()));
		varietyNameService = new VarietyNameServiceImpl(new VarietyNameDao(EntityManagerProvider.provideEntityManager()));
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setVariety(Variety variety){
		this.variety = variety;
		
		nameBox.getItems().addAll(varietyNameService.getAllVarietyNames());
		nameBox.setValue(this.variety.getVarietyName());
		areaTextField.setText(Double.toString(this.variety.getVarietyArea()));
		priceTextField.setText(Integer.toString(this.variety.getVarietyPrice()));
		yieldTextField.setText(Double.toString(this.variety.getVarietyYield()));
	}
	
	public boolean isOkClicked(){
		return okClicked;
	}
	
	@FXML
	public void handleOk(){
		if(isInputValid()){
			variety.setVarietyName(nameBox.getSelectionModel().getSelectedItem());
			try{
				variety.setVarietyPrice(Integer.parseInt(priceTextField.getText()));
				variety.setVarietyYield(Double.parseDouble(yieldTextField.getText()));
				variety.setVarietyArea(Double.parseDouble(areaTextField.getText()));
			}catch(NumberFormatException e){
				
			}
			
			varietyService.updateVariety(variety);
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
	}
	
	private boolean isInputValid(){
		String errorMessage = "";
		if(priceTextField.getText().length() != 0){
			try{
				Integer.parseInt(priceTextField.getText());
			}catch(NumberFormatException e){
				errorMessage += "Hibás ár!\n";
			}
		}
		if(yieldTextField.getText().length() != 0){
			try{
				Double.parseDouble(yieldTextField.getText());
			}catch(NumberFormatException e){
				errorMessage += "Hibás hozam!\n";
			}
		}
		try{
			Double.parseDouble(areaTextField.getText());
		}catch(NumberFormatException e){
			errorMessage += "Hibás terület!\n";
		}

		if(errorMessage.length() == 0){
			return true;
		}else{
			
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText("Hibás adatok.");
            alert.setContentText(errorMessage);

            alert.showAndWait();
			return false;
		}
	}
	
}
