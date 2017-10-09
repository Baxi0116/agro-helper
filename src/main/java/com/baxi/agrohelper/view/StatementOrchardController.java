package com.baxi.agrohelper.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;
import com.baxi.agrohelper.util.PDFUtil;
import com.baxi.agrohelper.util.StatementUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StatementOrchardController {
	private static Logger logger = LoggerFactory.getLogger(StatementOrchardController.class);
	
	public static int counter = 1;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button makeStatementButton;
	
	@FXML
	private Button importToPdfButton;
	
	private OrchardService orchardService;
	
	@FXML
	private TableView<Orchard> orchardTable;

	@FXML
	private TableColumn<Orchard, String> orchardNameColumn;
	
	private ObservableList<Orchard> orchardData;
	
	//First tab
	
	@FXML
	private Label totalIncomeLabel;
	
	@FXML
	private Label totalExpensesLabel;
	
	@FXML
	private Label totalProfitLabel;
	
	@FXML
	private Label totalWorkPriceLabel;
	
	@FXML
	private Label totalMaterialPriceLabel;
	
	//Second tab
	
	@FXML
	private TableView<AgWork> expensesTable;
	
	@FXML
	private TableColumn<AgWork, String> workNameColumn;

	@FXML
	private TableColumn<AgWork, Integer> workPriceColumn;
	
	@FXML
	private TableColumn<AgWork, Integer> materialPriceColumn;
	
	@FXML
	private TableColumn<AgWork, Integer> totalPriceColumn;

	@FXML
	private TableColumn<AgWork, LocalDate> workDateColumn;
	
	private ObservableList<AgWork> workData;
	//Third tab
	
	@FXML
	private TableView<Variety> varietyTable;

	@FXML
	private TableColumn<Variety, String> varietyNameColumn;

	@FXML
	private TableColumn<Variety, Double> varietyYieldColumn;

	@FXML
	private TableColumn<Variety, Integer> varietyPriceColumn;
	
	@FXML
	private TableColumn<Variety, Double> totalHarvestColumn;
	
	@FXML
	private TableColumn<Variety, Double> totalIncomeColumn;
	
	private ObservableList<Variety> varietyData;
	
	@FXML
	 public void initialize(){

		 logger.info("Initializing controller...");
		 
		 
		 orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));

		 orchardData = FXCollections.observableArrayList(orchardService.findAllOrchards());
		 orchardTable.setItems(orchardData);
		 workData = FXCollections.observableArrayList();
		 varietyData = FXCollections.observableArrayList();
		 expensesTable.setItems(workData);
		 varietyTable.setItems(varietyData);
		 
		 orchardNameColumn.setCellValueFactory(new PropertyValueFactory<Orchard, String>("orchardName"));

		 workNameColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workDesignation"));
		 workPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("workPrice"));
		 materialPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("materialPrice"));
		 totalPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("totalPrice"));
		 workDateColumn.setCellValueFactory(new PropertyValueFactory<AgWork, LocalDate>("workDate"));

		 varietyNameColumn.setCellValueFactory(new PropertyValueFactory<Variety, String>("varietyName"));
		 varietyYieldColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyYield"));
		 varietyPriceColumn.setCellValueFactory(new PropertyValueFactory<Variety, Integer>("varietyPrice"));
		 totalHarvestColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("totalHarvest"));
		 totalIncomeColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("totalIncome"));
		 
		 totalIncomeLabel.setText("");
		 totalExpensesLabel.setText("");
		 totalProfitLabel.setText("");
		 totalWorkPriceLabel.setText("");
		 totalMaterialPriceLabel.setText("");
		 
	 }
	
	
	@FXML
	private void handleMakeStatementsButton() {
		logger.debug("making Statements");
		
		Orchard orchard = orchardTable.getSelectionModel().getSelectedItem();
		
		 if (orchard != null) {
			
				if(importToPdfButton.isDisabled()) {
					importToPdfButton.setDisable(false);
				}
			 
				totalIncomeLabel.setText(Double.toString(StatementUtil.countIncomeForOrchard(orchard)) + " Ft");
				totalExpensesLabel.setText(Double.toString(StatementUtil.countExpensesForOrchard(orchard)) + " Ft");
				totalProfitLabel.setText(Double.toString(StatementUtil.countProfitForOrchard(orchard)) + " Ft");
				totalWorkPriceLabel.setText(Double.toString(StatementUtil.countWorkExpensesForOrchard(orchard)) + " Ft");
				totalMaterialPriceLabel.setText(Double.toString(StatementUtil.countMaterialExpensesForOrchard(orchard)) + " Ft");
				
				workData = FXCollections.observableArrayList(orchard.getWorks());
				varietyData = FXCollections.observableArrayList(orchard.getVarieties());
				expensesTable.setItems(workData);
				varietyTable.setItems(varietyData);
		 }
		 else {
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("");
			 alert.setHeaderText("Nincs kiválasztva kert");
			 alert.setContentText("Válasszon kertet a listából");

			 alert.showAndWait();
		 }

		
	}
	
	@FXML
	private void handleBackButton() {
		logger.debug("Back Button pressed...");
		Stage stage;
		Parent root;
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StatementOverview.fxml"));
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
	
	@FXML
	private void handleImportToPdfButton() {
		logger.debug("importing to pdf");
		
		Orchard selectedOrchard = orchardTable.getSelectionModel().getSelectedItem();
		
		 if (selectedOrchard != null) {
			 String dirpath = "kimutatasok";
			 String filename = dirpath + "/" + selectedOrchard.getOrchardName() + "_" + LocalDate.now() + ".pdf";			 
			 try {
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(filename));
				document.open();
				PDFUtil.addMetaData(document, "Kimutatás a(z) " + selectedOrchard.getOrchardName() + " kertre");
				PDFUtil.addTitle(document, selectedOrchard);
				PDFUtil.addContent(document, selectedOrchard);
				document.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 importToPdfButton.setDisable(true);
			 
		 }
		 else {
			 Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("");
			 alert.setHeaderText("Nincs kiválasztva kert");
			 alert.setContentText("Válasszon kertet a listából");

			 alert.showAndWait();
		 }
		
		
	}
	
	
}
