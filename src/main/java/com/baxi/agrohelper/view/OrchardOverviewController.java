package com.baxi.agrohelper.view;

import com.baxi.agrohelper.MainApp;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.util.DateUtil;
import com.baxi.agrohelper.util.ListUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrchardOverviewController {
	
	@FXML
	private TableView<Orchard> orchardTable;
	
	@FXML
	private TableColumn<Orchard, String> orchardNameColumn;
	
	@FXML
	private Label nameLabel;
	
	@FXML
	private Label topographicNumberLabel;
	
	@FXML 
	private Label meparCodeLabel;
	
	@FXML
	private Label plantationDateLabel;
	
	@FXML
	private Label numberOfTreesLabel;
	
	@FXML
	private Label cropLabel;
	
	private MainApp mainApp;
	
	/**
	 * Constructor
	 */
	public OrchardOverviewController(){}
	
	@FXML
	public void initialize(){
		
		orchardNameColumn.setCellValueFactory(new PropertyValueFactory<Orchard, String>("orchardName"));
		
		showOrchardDetails(null);
		
        // Listen for selection changes and show the orchard details when changed.
        orchardTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrchardDetails(newValue));
	}
	
	
	public void showOrchardDetails(Orchard orchard){
		if(orchard != null){
			nameLabel.setText(orchard.getOrchardName());
			topographicNumberLabel.setText(orchard.getTopographicNumber());
			meparCodeLabel.setText(orchard.getMeparCode());
			plantationDateLabel.setText(DateUtil.format(orchard.getYearOfPlantation()));
			numberOfTreesLabel.setText(Integer.toString(orchard.getNumberOfTrees()));
			cropLabel.setText(ListUtil.formatOutput(orchard.getCrops()));
			
		}else{
			nameLabel.setText("");
			topographicNumberLabel.setText("");
			meparCodeLabel.setText("");
			plantationDateLabel.setText("");
			numberOfTreesLabel.setText("");
			cropLabel.setText("");
		}
	}

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
		orchardTable.setItems(mainApp.getOrchardData());
	}
	
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteOrchard() {
        int selectedIndex = orchardTable.getSelectionModel().getSelectedIndex();
        Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("");
            alert.setHeaderText("Biztosan törli?.");
            alert.setContentText("Törlés után az adatok végleg elvesznek.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                	mainApp.provideOrchardService().removeOrchard(selectedOrchard.getId());
                	orchardTable.getItems().remove(selectedIndex);
                }
            });
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kert");
            alert.setContentText("Válasszon kertet a táblázatból.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new orchard.
     */
    @FXML
    private void handleNewOrchard() {
        Orchard orchard = new Orchard();
    	mainApp.provideOrchardService().createOrchard(orchard);
        boolean okClicked = mainApp.showOrchardEditDialog(orchard);
        if (okClicked) {
        	mainApp.provideOrchardService().updateOrchard(orchard);
            mainApp.getOrchardData().add(orchard);
        }else{
        	mainApp.provideOrchardService().removeOrchard(orchard.getId());
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected orchard.
     */
    @FXML
    private void handleEditOrchard() {
        Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
        if (selectedOrchard != null) {
            boolean okClicked = mainApp.showOrchardEditDialog(selectedOrchard);
            if (okClicked) {
            	mainApp.provideOrchardService().updateOrchard(selectedOrchard);
            	showOrchardDetails(selectedOrchard);
            }else{
            	mainApp.provideOrchardService().updateOrchard(selectedOrchard);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kert.");
            alert.setContentText("Válasszon kertet a táblázatból.");

            alert.showAndWait();
        }
    }
	
}
