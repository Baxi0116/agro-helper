package com.baxi.agrohelper.view;

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
