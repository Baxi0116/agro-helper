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

import com.baxi.agrohelper.dao.WorkNameDao;
import com.baxi.agrohelper.model.WorkName;
import com.baxi.agrohelper.service.WorkNameService;
import com.baxi.agrohelper.service.WorkNameServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class WorkHandlerController {
	
	@FXML
	private TableView<WorkName> workNameTable;
	
	@FXML
	private TableColumn<WorkName, String> workNameColumn;
	
	@FXML
	private TextField newNameTextField;
	
	private Stage dialogStage;
	
	private ObservableList<WorkName> workNameData;
	
	private WorkNameService workNameService;
	
	@FXML
	private void initialize(){
		workNameService = new WorkNameServiceImpl(new WorkNameDao(EntityManagerProvider.provideEntityManager()));
		workNameData = FXCollections.observableArrayList(workNameService.findAllWorkNames());
		workNameTable.setItems(workNameData);
		
		workNameColumn.setCellValueFactory(new PropertyValueFactory<WorkName, String>("name"));

		
        workNameTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails());
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	public void showDetails(){
		workNameTable.setItems(workNameData);
	}
	
	@FXML
	public void handleAdd(){
		if(isInputValid()){
			WorkName workName = new WorkName();
			workName.setName(newNameTextField.getText());
			workNameService.createWorkName(workName);
			workNameData.add(workName);
		}
	}
	
	@FXML
	public void handleDelete(){
		int selectedIndex = workNameTable.getSelectionModel().getSelectedIndex();
        WorkName selectedWorkName = workNameTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
               workNameService.deleteWorkName(selectedWorkName.getId());
               workNameTable.getItems().remove(selectedIndex);
               showDetails();
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva munka");
            alert.setContentText("Válasszon munkát a táblázatból.");
            alert.showAndWait();
        }
	}
	
	public boolean isInputValid(){
		String errorMessage = "";
		
		if(newNameTextField.getText() == null || newNameTextField.getText().length() == 0){
			errorMessage += "Hibás munkanév!";
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
