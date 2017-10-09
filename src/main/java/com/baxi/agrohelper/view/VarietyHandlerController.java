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

import com.baxi.agrohelper.dao.VarietyNameDao;
import com.baxi.agrohelper.model.VarietyName;
import com.baxi.agrohelper.service.VarietyNameService;
import com.baxi.agrohelper.service.VarietyNameServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VarietyHandlerController {

	@FXML
	private TableView<VarietyName> varietyNameTable;
	
	@FXML
	private TableColumn<VarietyName, String> varietyNameColumn;
	
	@FXML
	private TextField newNameTextField;
	
	private Stage dialogStage;
	
	private ObservableList<VarietyName> varietyNameData;
	
	private VarietyNameService varietyNameService;
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private void initialize(){
		varietyNameService = new VarietyNameServiceImpl(new VarietyNameDao(EntityManagerProvider.provideEntityManager()));
		varietyNameData = FXCollections.observableArrayList(varietyNameService.findAllVarietyNames());
		varietyNameTable.setItems(varietyNameData);
		
		varietyNameColumn.setCellValueFactory(new PropertyValueFactory<VarietyName, String>("name"));

		
        varietyNameTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails());
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void showDetails(){
		varietyNameTable.setItems(varietyNameData);
	}
	
	@FXML
	public void handleAdd(){
		if(isInputValid()){
			VarietyName varietyName = new VarietyName();
			varietyName.setName(newNameTextField.getText());
			varietyNameService.createVarietyName(varietyName);
			varietyNameData.add(varietyName);
		}
	}
	
	@FXML
	public void handleDelete(){
		int selectedIndex = varietyNameTable.getSelectionModel().getSelectedIndex();
        VarietyName selectedVarietyName = varietyNameTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
               varietyNameService.deleteVarietyName(selectedVarietyName.getId());
               varietyNameTable.getItems().remove(selectedIndex);
               showDetails();
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva fajta");
            alert.setContentText("Válasszon fajtát a táblázatból.");
            alert.showAndWait();
        }
	}
	
	@FXML
	private void handleCancelButton() {
		dialogStage.close();
	}
	
	
	public boolean isInputValid(){
		String errorMessage = "";
		
		if(newNameTextField.getText() == null || newNameTextField.getText().length() == 0){
			errorMessage += "Hibás fajtanév!";
		}
		
		if(errorMessage.length() == 0){
			return true;
		}else{
			Alert alert = new Alert(AlertType.ERROR);
	        alert.initOwner(dialogStage);
	        alert.setTitle("Hiba");
	        alert.setHeaderText("Adjon meg valós adatot");
	        alert.setContentText(errorMessage);

	        alert.showAndWait();
			return false;
		}
	}
	
}
