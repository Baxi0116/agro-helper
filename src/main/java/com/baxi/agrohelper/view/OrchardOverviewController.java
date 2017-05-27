package com.baxi.agrohelper.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.dao.StatementDao;
import com.baxi.agrohelper.dao.VarietyDao;
import com.baxi.agrohelper.dao.VarietyNameDao;
import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.FStatement;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.service.StatementService;
import com.baxi.agrohelper.service.StatementServiceImpl;
import com.baxi.agrohelper.service.VarietyNameService;
import com.baxi.agrohelper.service.VarietyNameServiceImpl;
import com.baxi.agrohelper.service.VarietyService;
import com.baxi.agrohelper.service.VarietyServiceImpl;
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
	
	@FXML
	private TableView<FStatement> statementTable;
	
	@FXML
	private TableColumn<FStatement, LocalDate> statementDateColumn;
	
	@FXML
	private TableColumn<FStatement, Double> expensesColumn;
	
	@FXML
	private TableColumn<FStatement, Double> incomeColumn;
	
	@FXML
	private TableColumn<FStatement, Double> profitColumn;
	
	@FXML
	private Label expensesLabel;
	
	@FXML
	private Label incomeLabel;
	
	@FXML
	private Label profitLabel;
	
	private StatementService statementService;
	private ObservableList<FStatement> statementData;
	
	/**
	 * Constructor
	 */
	public OrchardOverviewController(){}
	
	@FXML
	public void initialize(){
		
		orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));
		workService = new WorkServiceImpl(new WorkDao(EntityManagerProvider.provideEntityManager()));
		varietyService = new VarietyServiceImpl(new VarietyDao(EntityManagerProvider.provideEntityManager()));
		varietyNameService = new VarietyNameServiceImpl(new VarietyNameDao(EntityManagerProvider.provideEntityManager()));
		statementService = new StatementServiceImpl(new StatementDao(EntityManagerProvider.provideEntityManager()));
		
		orchardData = FXCollections.observableArrayList(orchardService.findAllOrchards());
		orchardTable.setItems(orchardData);
		workData = FXCollections.observableArrayList();
		varietyData = FXCollections.observableArrayList();
		workTable.setItems(workData);
		varietyTable.setItems(varietyData);
		statementData = FXCollections.observableArrayList();
		statementTable.setItems(statementData);
		
		orchardNameColumn.setCellValueFactory(new PropertyValueFactory<Orchard, String>("orchardName"));
		
		workNameColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workDesignation"));
		workPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("workPrice"));
		workNoteColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workNote"));
		workDateColumn.setCellValueFactory(new PropertyValueFactory<AgWork, LocalDate>("workDate"));
		
		varietyNameColumn.setCellValueFactory(new PropertyValueFactory<Variety, String>("varietyName"));
		varietyYieldColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyYield"));
		varietyPriceColumn.setCellValueFactory(new PropertyValueFactory<Variety, Integer>("varietyPrice"));
		varietyAreaColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyArea"));
		
		statementDateColumn.setCellValueFactory(new PropertyValueFactory<FStatement, LocalDate>("statementDate"));
		expensesColumn.setCellValueFactory(new PropertyValueFactory<FStatement, Double>("expenses"));
		incomeColumn.setCellValueFactory(new PropertyValueFactory<FStatement, Double>("income"));
		profitColumn.setCellValueFactory(new PropertyValueFactory<FStatement, Double>("profit"));
		
		varietyNameBox.getItems().addAll(varietyNameService.getAllVarietyNames());
		
		showOrchardDetails(null);
		
        orchardTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrchardDetails(newValue));
        
        statementTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showStatements(newValue));
	}
	
	public void refreshVarieties(){
		List<String> names = varietyNameBox.getItems();
		varietyNameBox.getItems().removeAll(names);
		varietyNameBox.getItems().addAll(varietyNameService.getAllVarietyNames());
	}

	public void refreshVarietyTable(Orchard orchard){
		varietyData = FXCollections.observableArrayList(orchard.getVarieties());
		varietyTable.setItems(varietyData);
	}
	
	public void refreshStatementTable(Orchard orchard){
		statementData = FXCollections.observableArrayList(orchard.getStatements());
		statementTable.setItems(statementData);
	}
	
	public void showStatements(FStatement statement){
		if(statement != null){
			expensesLabel.setText(Double.toString(statement.getExpenses()));
			incomeLabel.setText(Double.toString(statement.getIncome()));
			profitLabel.setText(Double.toString(statement.getProfit()));
		}else{
			expensesLabel.setText("");
			incomeLabel.setText("");
			profitLabel.setText("");
		}
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
			refreshVarietyTable(orchard);
			refreshStatementTable(orchard);
			showStatements(statementTable.getSelectionModel().getSelectedItem());
			
		}else{
			nameLabel.setText("");
			topographicNumberLabel.setText("");
			meparCodeLabel.setText("");
			plantationDateLabel.setText("");
			numberOfTreesLabel.setText("");
			cropLabel.setText("");
			showStatements(statementTable.getSelectionModel().getSelectedItem());
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
	
	public boolean showVarietyEditDialog(Variety variety){
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OrchardOverviewController.class.getResource("VarietyEditDialog.fxml"));
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
        	showOrchardDetails(null);
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
                	showOrchardDetails(orchardTable.getSelectionModel().getSelectedItem());
                }
            });
            
        } else {
        	logger.warn("No orchard selected");
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OrchardOverviewController.class.getResource("WorkHandlerDialog.fxml"));
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OrchardOverviewController.class.getResource("VarietyHandlerDialog.fxml"));
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
                	}catch(NumberFormatException e){}
                	variety.setOrchard(selectedOrchard);
                	varietyService.createVariety(variety);
                	selectedOrchard.getVarieties().add(variety);
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
    
    @FXML
    public void createStatement(){
    	Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
    	if(selectedOrchard != null){
        	FStatement statement = new FStatement();
        	statement.setExpenses(statementService.countExpensesForOrchard(selectedOrchard));
        	statement.setIncome(statementService.countIncomeForOrchard(selectedOrchard));
        	statement.setProfit(statementService.countProfitForOrchard(selectedOrchard));
        	statement.setStatementDate(LocalDate.now());
        	statement.setOrchard(selectedOrchard);
        	statementService.createStatement(statement);
        	selectedOrchard.getStatements().add(statement);
        	showOrchardDetails(selectedOrchard);
    	}else{
        	logger.warn("No orchard selected");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kert");
            alert.setContentText("Válasszon kertet a táblázatból.");

            alert.showAndWait();
    	}
    }
    
    @FXML
    private void deleteStatement(){
    	Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
    	FStatement selectedStatement = statementTable.getSelectionModel().getSelectedItem();
    	int selectedIndex = statementTable.getSelectionModel().getSelectedIndex();
    	if (selectedIndex >= 0) {
        	Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Biztosan törli?");
            alert.setContentText("Törlés után az adatok végleg elvesznek.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    selectedOrchard.getStatements().remove(selectedStatement);
                    statementService.removeStatement(selectedStatement.getId());
                    statementTable.getItems().remove(selectedIndex);  
                }
            });
        } else {
        	logger.warn("No statement selected");
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Hiba");
            alert.setHeaderText("Nincs kiválasztva kimutatás");
            alert.setContentText("Válasszon egyet a táblázatból.");

            alert.showAndWait();
        }
    	
    }
	
}
