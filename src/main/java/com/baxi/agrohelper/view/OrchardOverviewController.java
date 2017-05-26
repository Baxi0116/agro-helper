package com.baxi.agrohelper.view;

import java.io.IOException;
import java.time.LocalDate;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.service.WorkService;
import com.baxi.agrohelper.service.WorkServiceImpl;
import com.baxi.agrohelper.util.DateUtil;
import com.baxi.agrohelper.util.EntityManagerProvider;
import com.baxi.agrohelper.util.ListUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	private ObservableList<Orchard> orchardData;
	
	private OrchardService orchardService;
	
	@FXML
	private TableView<AgWork> workTable;
	
	@FXML
	private TableColumn<AgWork, String> workNameColumn;
	
	@FXML
	private TableColumn<AgWork, Integer> workPriceColumn;
	
	@FXML
	private TableColumn<AgWork, String> workNoteColumn;
	
	@FXML
	private TableColumn<AgWork, LocalDate> workDateColumn;
	
	private ObservableList<AgWork> workData;
	private WorkService workService;
	
	/**
	 * Constructor
	 */
	public OrchardOverviewController(){}
	
	@FXML
	public void initialize(){
		
		orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));
		workService = new WorkServiceImpl(new WorkDao(EntityManagerProvider.provideEntityManager()));
		orchardData = FXCollections.observableArrayList(orchardService.findAllOrchards());
		orchardTable.setItems(orchardData);
		workData = FXCollections.observableArrayList();
		workTable.setItems(workData);
		
		orchardNameColumn.setCellValueFactory(new PropertyValueFactory<Orchard, String>("orchardName"));
		
		workNameColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workDesignation"));
		workPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("workPrice"));
		workNoteColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workNote"));
		workDateColumn.setCellValueFactory(new PropertyValueFactory<AgWork, LocalDate>("workDate"));
		
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
			workData = FXCollections.observableArrayList(orchard.getWorks());
			workTable.setItems(workData);
			
		}else{
			nameLabel.setText("");
			topographicNumberLabel.setText("");
			meparCodeLabel.setText("");
			plantationDateLabel.setText("");
			numberOfTreesLabel.setText("");
			cropLabel.setText("");
		}
	}
    /**
     * Opens a dialog to edit details for the specified orchard. If the user
     * clicks OK, the changes are saved into the provided orchard object and true
     * is returned.
     * 
     * @param orchard the orchard object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
	public boolean showOrchardEditDialog(Orchard orchard) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OrchardOverviewController.class.getResource("OrchardEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Szerkesztés");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            OrchardEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrchard(orchard);
            
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public boolean showWorkEditDialog(Orchard orchard){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OrchardOverviewController.class.getResource("WorkEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Új munka");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            WorkEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrchard(orchard);
            
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
            alert.setTitle("");
            alert.setHeaderText("Biztosan törli?");
            alert.setContentText("Törlés után az adatok végleg elvesznek.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                	orchardService.removeOrchard(selectedOrchard.getId());
                	orchardTable.getItems().remove(selectedIndex);
                }
            });
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
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
        orchardService.createOrchard(orchard);
        boolean okClicked = showOrchardEditDialog(orchard);
        if (okClicked) {
        	orchardService.updateOrchard(orchard);
        	orchardData.add(orchard);
        }else{
        	orchardService.removeOrchard(orchard.getId());
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
            boolean okClicked = showOrchardEditDialog(selectedOrchard);
            if (okClicked) {
            	orchardService.updateOrchard(selectedOrchard);
            	showOrchardDetails(selectedOrchard);
            }else{
            	orchardService.updateOrchard(selectedOrchard);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kert.");
            alert.setContentText("Válasszon kertet a táblázatból.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleAddWorkButton(){
    	Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
    	if (selectedOrchard != null) {
            boolean okClicked = showWorkEditDialog(selectedOrchard);
            if (okClicked) {
            	orchardService.updateOrchard(selectedOrchard);
            	showOrchardDetails(selectedOrchard);
            }else{
            	orchardService.updateOrchard(selectedOrchard);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kert.");
            alert.setContentText("Válasszon kertet a táblázatból.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteWorkButton(){
    	int selectedIndex = workTable.getSelectionModel().getSelectedIndex();
        AgWork selectedWork = workTable.getSelectionModel().getSelectedItem();
        Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Biztosan törli?");
            alert.setContentText("Törlés után az adatok végleg elvesznek.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                	selectedOrchard.getWorks().remove(selectedWork);
                	workService.deleteWork(selectedWork.getId());
                	workTable.getItems().remove(selectedIndex);
                }
            });
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva munka");
            alert.setContentText("Válasszon munkálatot a táblázatból.");

            alert.showAndWait();
        }
    }
	
}
