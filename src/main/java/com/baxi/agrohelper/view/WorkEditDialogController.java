package com.baxi.agrohelper.view;

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

import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.dao.WorkNameDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.service.WorkNameService;
import com.baxi.agrohelper.service.WorkNameServiceImpl;
import com.baxi.agrohelper.service.WorkService;
import com.baxi.agrohelper.service.WorkServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class WorkEditDialogController {

	@FXML
	private TextField priceTextField;
	
	@FXML
	private TextField noteTextField;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private TextField materialPriceTextField;
	
	@FXML
	private ChoiceBox<String> nameBox;
	
	private Stage dialogStage;
	private Orchard orchard;
	private boolean okClicked = false;
	
	private WorkService workService;
	private WorkNameService workNameService;
	
	@FXML
	private void initialize(){
		workService = new WorkServiceImpl(new WorkDao(EntityManagerProvider.provideEntityManager()));
		workNameService = new WorkNameServiceImpl(new WorkNameDao(EntityManagerProvider.provideEntityManager()));
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void setOrchard(Orchard orchard){
		this.orchard = orchard;
		priceTextField.setText("0");
		materialPriceTextField.setText("0");
		nameBox.getItems().addAll(workNameService.getAllWorkNames());
		nameBox.setTooltip(new Tooltip("Válasszon a listából"));
	}
	
	public boolean isOkClicked(){
		return this.okClicked;
	}
	
	@FXML
	public void handleOk(){
		if(isInputValid()){
			AgWork work = new AgWork();
			work.setWorkDesignation(nameBox.getSelectionModel().getSelectedItem());
			datePicker.setOnAction(event -> {});
			System.out.println(datePicker.getValue());
			work.setWorkDate(datePicker.getValue());
			work.setWorkNote(noteTextField.getText());
			work.setWorkPrice(Integer.parseInt(priceTextField.getText()));
			work.setMaterialPrice(Integer.parseInt(materialPriceTextField.getText()));
			work.setTotalPrice(Integer.parseInt(priceTextField.getText()) + Integer.parseInt(materialPriceTextField.getText()));
			work.setOrchard(orchard);
			workService.createWork(work);
			orchard.getWorks().add(work);
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	public void handleCancel(){
		dialogStage.close();
	}
	
	public boolean isInputValid(){
		
		String errorMessage = "";
		
		if(datePicker.getValue() == null){
			errorMessage += "Dátum nem lehet üres";
		}
		if(nameBox.getSelectionModel().getSelectedItem() == null){
			errorMessage += "Munka nem lehet üres";
		}
		try{
			Integer.parseInt(priceTextField.getText());
		}catch(NumberFormatException e){
			errorMessage += "Hibás érték az ár mezőben!";
		}
		try{
			Integer.parseInt(materialPriceTextField.getText());
		}catch(NumberFormatException e){
			errorMessage += "Hibás érték az anyagköltség mezőben!";
		}
		
		if(errorMessage.length() == 0){
			return true;
		}else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Hibás mezők");
            alert.setHeaderText("Javítsa ki a hibás mezőket");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
		}
	}
	
	
}
