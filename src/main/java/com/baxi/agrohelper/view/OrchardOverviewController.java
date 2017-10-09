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

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.dao.VarietyDao;
import com.baxi.agrohelper.dao.VarietyNameDao;
import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.service.VarietyNameService;
import com.baxi.agrohelper.service.VarietyNameServiceImpl;
import com.baxi.agrohelper.service.VarietyService;
import com.baxi.agrohelper.service.VarietyServiceImpl;
import com.baxi.agrohelper.service.WorkService;
import com.baxi.agrohelper.service.WorkServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;
import com.baxi.agrohelper.util.ListUtil;
import com.baxi.agrohelper.util.StatementUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrchardOverviewController {

	private static Logger logger = LoggerFactory.getLogger(OrchardOverviewController.class);

	@FXML
	private Button backToMainButton;
	
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
	private TableColumn<AgWork, Integer> materialPriceColumn;
	
	@FXML
	private TableColumn<AgWork, Integer> totalPriceColumn;

	@FXML
	private TableColumn<AgWork, String> workNoteColumn;

	@FXML
	private TableColumn<AgWork, LocalDate> workDateColumn;

	private ObservableList<AgWork> workData;
	private WorkService workService;

	@FXML
	private TableView<Variety> varietyTable;

	@FXML
	private TableColumn<Variety, String> varietyNameColumn;

	@FXML
	private TableColumn<Variety, Double> varietyYieldColumn;

	@FXML
	private TableColumn<Variety, Integer> varietyPriceColumn;

	@FXML
	private TableColumn<Variety, Double> varietyAreaColumn;
	
	@FXML
	private TableColumn<Variety, Double> totalHarvestColumn;

	@FXML
	private ChoiceBox<String> varietyNameBox;

	@FXML
	private TextField varietyYieldTextField;

	@FXML
	private TextField varietyPriceTextfield;

	@FXML
	private TextField varietyAreaTextfield;

	private ObservableList<Variety> varietyData;
	private VarietyService varietyService;
	private VarietyNameService varietyNameService;

	 public OrchardOverviewController(){}

	 @FXML
	 public void initialize(){

		 logger.info("Initializing controller...");
		 orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));
		 workService = new WorkServiceImpl(new WorkDao(EntityManagerProvider.provideEntityManager()));
		 varietyService = new VarietyServiceImpl(new VarietyDao(EntityManagerProvider.provideEntityManager()));
		 varietyNameService = new VarietyNameServiceImpl(new VarietyNameDao(EntityManagerProvider.provideEntityManager()));

		 orchardData = FXCollections.observableArrayList(orchardService.findAllOrchards());
		 orchardTable.setItems(orchardData);
		 workData = FXCollections.observableArrayList();
		 varietyData = FXCollections.observableArrayList();
		 workTable.setItems(workData);
		 varietyTable.setItems(varietyData);


		 orchardNameColumn.setCellValueFactory(new PropertyValueFactory<Orchard, String>("orchardName"));

		 workNameColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workDesignation"));
		 workPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("workPrice"));
		 materialPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("materialPrice"));
		 totalPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("totalPrice"));
		 workNoteColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workNote"));
		 workDateColumn.setCellValueFactory(new PropertyValueFactory<AgWork, LocalDate>("workDate"));

		 varietyNameColumn.setCellValueFactory(new PropertyValueFactory<Variety, String>("varietyName"));
		 varietyYieldColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyYield"));
		 varietyPriceColumn.setCellValueFactory(new PropertyValueFactory<Variety, Integer>("varietyPrice"));
		 varietyAreaColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyArea"));
		 totalHarvestColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("totalHarvest"));

		 varietyNameBox.getItems().addAll(varietyNameService.getAllVarietyNames());

		 showOrchardDetails(null);

		 orchardTable.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) -> showOrchardDetails(newValue));
	 }

	 public void refreshVarieties(){
		 List<String> names = varietyNameBox.getItems();
		 varietyNameBox.getItems().removeAll(names);
		 varietyNameBox.getItems().addAll(varietyNameService.getAllVarietyNames());
	 }

	 public void refreshVarietyTable(Orchard orchard){
		 varietyData.clear();
		 varietyData = FXCollections.observableArrayList(orchard.getVarieties());
		 varietyTable.setItems(varietyData);
	 }
	 
	 public void refreshWorks(Orchard orchard) {
		 workData.clear();
		 workData = FXCollections.observableArrayList(orchard.getWorks());
		 workTable.setItems(workData);
	 }

	 public void showOrchardDetails(Orchard orchard){
		 if(orchard != null){
			 logger.info("Showing details for Orchard: {}", orchard.getOrchardName());
			 nameLabel.setText(orchard.getOrchardName());
			 topographicNumberLabel.setText(orchard.getTopographicNumber());
			 meparCodeLabel.setText(orchard.getMeparCode());
			 plantationDateLabel.setText(Integer.toString(orchard.getYearOfPlantation().getYear()));
			 numberOfTreesLabel.setText(Integer.toString(orchard.getNumberOfTrees()));
			 cropLabel.setText(ListUtil.formatOutput(orchard.getCrops()));
			 workData = FXCollections.observableArrayList(orchard.getWorks());
			 workTable.setItems(workData);
			 refreshVarietyTable(orchard);

		 }else{
			 nameLabel.setText("");
			 topographicNumberLabel.setText("");
			 meparCodeLabel.setText("");
			 plantationDateLabel.setText("");
			 numberOfTreesLabel.setText("");
			 cropLabel.setText("");
			 workData = FXCollections.observableArrayList();
			 workTable.setItems(workData);
		 }
	 }

	 public boolean showOrchardEditDialog(Orchard orchard) {
		 try {
			 logger.info("Showing Orchard Edit Dialog");
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(OrchardOverviewController.class.getResource("/view/OrchardEditDialog.fxml"));
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
			 logger.info("Showing Work Edit Dialog");
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(OrchardOverviewController.class.getResource("/view/WorkEditDialog.fxml"));
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

	 public boolean showVarietyEditDialog(Variety variety){
		 try {
			 logger.info("Showing Variety Edit Dialog");
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(OrchardOverviewController.class.getResource("/view/VarietyEditDialog.fxml"));
			 AnchorPane page = (AnchorPane) loader.load();

			 Stage dialogStage = new Stage();
			 dialogStage.setTitle("Szerkesztés");
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 Scene scene = new Scene(page);
			 dialogStage.setScene(scene);

			 VarietyEditDialogController controller = loader.getController();
			 controller.setDialogStage(dialogStage);
			 controller.setVariety(variety);

			 dialogStage.showAndWait();
			 
			 refreshVarietyTable(orchardTable.getSelectionModel().getSelectedItem());


			 return controller.isOkClicked();
		 } catch (IOException e) {
			 e.printStackTrace();
			 return false;
		 }
	 }
	 
	 @FXML
	 private void handleBackToMainButton() {
			Stage stage;
			Parent root;
			
			try{
				logger.debug("going back to Main");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
				root = loader.load();
				stage = (Stage) backToMainButton.getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();			
				
			}catch(IOException e){
				logger.error("Can't get back to Main...");
				e.printStackTrace();
			}
	 }

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
			 showOrchardDetails(null);
		 } else {
			 logger.warn("No orchard selected");
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Hiba");
			 alert.setHeaderText("Nincs kiválasztva kert");
			 alert.setContentText("Válasszon kertet a táblázatból.");

			 alert.showAndWait();
		 }
	 }

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
			 showOrchardDetails(null);
		 }
	 }

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
				 showOrchardDetails(selectedOrchard);
			 }

		 } else {
			 logger.warn("No orchard selected");
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
				 showOrchardDetails(selectedOrchard);
			 }

		 } else {
			 logger.warn("No orchard selected");
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
					 showOrchardDetails(null);
				 }
			 });

		 } else {
			 logger.warn("No work selected");
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Hiba");
			 alert.setHeaderText("Nincs kiválasztva munka");
			 alert.setContentText("Válasszon munkálatot a táblázatból.");

			 alert.showAndWait();
		 }
	 }

	 @FXML
	 private void handleWorks(){
		 try {
			 logger.info("Showing Work Handling Dialog");
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(OrchardOverviewController.class.getResource("/view/WorkHandlerDialog.fxml"));
			 AnchorPane page = (AnchorPane) loader.load();

			 Stage dialogStage = new Stage();
			 dialogStage.setTitle("Munkák kezelése");
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 Scene scene = new Scene(page);
			 dialogStage.setScene(scene);

			 WorkHandlerController controller = loader.getController();
			 controller.setDialogStage(dialogStage);

			 dialogStage.showAndWait();

			 showOrchardDetails(orchardTable.getSelectionModel().getSelectedItem());

		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }

	 @FXML
	 private void handleVarieties(){
		 try {
			 logger.info("Showing Variety Handling Dialog");
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(OrchardOverviewController.class.getResource("/view/VarietyHandlerDialog.fxml"));
			 AnchorPane page = (AnchorPane) loader.load();

			 Stage dialogStage = new Stage();
			 dialogStage.setTitle("Fajták kezelése");
			 dialogStage.initModality(Modality.WINDOW_MODAL);
			 Scene scene = new Scene(page);
			 dialogStage.setScene(scene);

			 VarietyHandlerController controller = loader.getController();
			 controller.setDialogStage(dialogStage);

			 dialogStage.showAndWait();

			 refreshVarieties();

		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }

	 @FXML
	 private void handleAddVariety(){
		 Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
		 if(selectedOrchard != null){
			 Variety variety = new Variety();
			 if(varietyNameBox.getSelectionModel().getSelectedItem() != null){
				 variety.setVarietyName(varietyNameBox.getSelectionModel().getSelectedItem());
				 try{
					 variety.setVarietyArea(Double.parseDouble(varietyAreaTextfield.getText()));
					 try{
						 variety.setVarietyPrice(Integer.parseInt(varietyPriceTextfield.getText()));
						 variety.setVarietyYield(Double.parseDouble(varietyYieldTextField.getText()));
						 variety.setTotalHarvest(StatementUtil.countVarietyHarvest(variety));
						 variety.setTotalIncome(StatementUtil.countVarietyIncome(variety));
					 }catch(NumberFormatException e){}
					 variety.setOrchard(selectedOrchard);
					 varietyService.createVariety(variety);
					 selectedOrchard.getVarieties().add(variety);
					 varietyData.add(variety);
					 showOrchardDetails(selectedOrchard);
					
				 }catch(NumberFormatException e){
					 logger.error("Invalid area value");
					 Alert alert = new Alert(AlertType.ERROR);
					 alert.setTitle("Hiba");
					 alert.setHeaderText("Hibás terület érték.");
					 alert.setContentText("Csak szám írható ide. (Ügyeljen a tizedespontra! (.)"); 
					 alert.showAndWait();
				 }
			 }else{
				 logger.error("Variety name invalid");
				 Alert alert = new Alert(AlertType.ERROR);
				 alert.setTitle("Hiba");
				 alert.setHeaderText("Nincs kiválasztva fajta.");
				 alert.setContentText("Válasszon fajtát a listából.");

				 alert.showAndWait();
			 }
		 }else{
			 logger.warn("No orchard selected");
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Hiba");
			 alert.setHeaderText("Nincs kiválasztva kert.");
			 alert.setContentText("Válasszon kertet a táblázatból.");

			 alert.showAndWait();
		 }

	 }

	 @FXML
	 private void handleEditVariety(){
		 Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
		 Variety selectedVariety = varietyTable.getSelectionModel().getSelectedItem();
		 if(selectedOrchard != null){

			 if(selectedVariety != null){
				 boolean okClicked = showVarietyEditDialog(selectedVariety);
				 if (okClicked) {
					 orchardService.updateOrchard(selectedOrchard);
					 showOrchardDetails(selectedOrchard);
				 }else{
					 orchardService.updateOrchard(selectedOrchard);
				 }
			 }else{
				 logger.warn("No variety selected");
				 Alert alert = new Alert(AlertType.ERROR);
				 alert.setTitle("Hiba");
				 alert.setHeaderText("Nincs kiválasztva fajta szerkesztésre.");
				 alert.setContentText("Válasszon fajtát a táblázatból.");

				 alert.showAndWait();
			 }

		 }else{
			 logger.warn("No orchard selected");
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Hiba");
			 alert.setHeaderText("Nincs kiválasztva kert.");
			 alert.setContentText("Válasszon kertet a táblázatból.");

			 alert.showAndWait();
		 }
	 }

	 @FXML
	 private void handleDeleteVariety(){
		 Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
		 int selectedIndex = varietyTable.getSelectionModel().getSelectedIndex();
		 Variety selectedVariety = varietyTable.getSelectionModel().getSelectedItem();
		 if (selectedIndex >= 0) {
			 Alert alert = new Alert(AlertType.CONFIRMATION);
			 alert.setTitle("");
			 alert.setHeaderText("Biztosan törli?");
			 alert.setContentText("Törlés után az adatok végleg elvesznek.");
			 alert.showAndWait().ifPresent(response -> {
				 if (response == ButtonType.OK) {
					 selectedOrchard.getVarieties().remove(selectedVariety);
					 varietyService.removeVariety(selectedVariety.getId());
					 varietyTable.getItems().remove(selectedIndex);  
				 }
			 });
		 } else {
			 logger.warn("No variety selected");
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("Hiba");
			 alert.setHeaderText("Nincs kiválasztva fajta");
			 alert.setContentText("Válasszon fajtát a táblázatból.");

			 alert.showAndWait();
		 }
	 }
}
