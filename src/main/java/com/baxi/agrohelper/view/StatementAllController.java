package com.baxi.agrohelper.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.dao.VarietyDao;
import com.baxi.agrohelper.dao.WorkDao;
import com.baxi.agrohelper.model.AgWork;
import com.baxi.agrohelper.model.Variety;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.service.VarietyService;
import com.baxi.agrohelper.service.VarietyServiceImpl;
import com.baxi.agrohelper.service.WorkService;
import com.baxi.agrohelper.service.WorkServiceImpl;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StatementAllController {
	
	private static Logger logger = LoggerFactory.getLogger(StatementAllController.class);
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button makeStatementButton;
	
	@FXML
	private Button importToPdfButton;
	
	private OrchardService orchardService;
	
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
	private TableColumn<AgWork, String> wOrchardColumn;

	@FXML
	private TableColumn<AgWork, LocalDate> workDateColumn;
	
	private ObservableList<AgWork> workData;
	private WorkService workService;
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
	private TableColumn<Variety, String> vOrchardColumn;
	
	@FXML
	private TableColumn<Variety, Double> totalHarvestColumn;
	
	@FXML
	private TableColumn<Variety, Double> totalIncomeColumn;
	
	private ObservableList<Variety> varietyData;
	private VarietyService varietyService;
	
	@FXML
	 public void initialize(){

		 logger.info("Initializing controller...");
		 
		 orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));
		 workService = new WorkServiceImpl(new WorkDao(EntityManagerProvider.provideEntityManager()));
		 varietyService = new VarietyServiceImpl(new VarietyDao(EntityManagerProvider.provideEntityManager()));

		 workData = FXCollections.observableArrayList();
		 varietyData = FXCollections.observableArrayList();
		 expensesTable.setItems(workData);
		 varietyTable.setItems(varietyData);

		 workNameColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("workDesignation"));
		 workPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("workPrice"));
		 materialPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("materialPrice"));
		 totalPriceColumn.setCellValueFactory(new PropertyValueFactory<AgWork, Integer>("totalPrice"));
		 workDateColumn.setCellValueFactory(new PropertyValueFactory<AgWork, LocalDate>("workDate"));
		 wOrchardColumn.setCellValueFactory(new PropertyValueFactory<AgWork, String>("orchard"));

		 varietyNameColumn.setCellValueFactory(new PropertyValueFactory<Variety, String>("varietyName"));
		 varietyYieldColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("varietyYield"));
		 varietyPriceColumn.setCellValueFactory(new PropertyValueFactory<Variety, Integer>("varietyPrice"));
		 totalHarvestColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("totalHarvest"));
		 totalIncomeColumn.setCellValueFactory(new PropertyValueFactory<Variety, Double>("totalIncome"));
		 vOrchardColumn.setCellValueFactory(new PropertyValueFactory<Variety, String>("orchard"));
		 
		 totalIncomeLabel.setText("");
		 totalExpensesLabel.setText("");
		 totalProfitLabel.setText("");
		 totalWorkPriceLabel.setText("");
		 totalMaterialPriceLabel.setText("");
	 }
	
	@FXML
	private void handleMakeStatementsButton() {
		logger.debug("making Statements");
		
		if(importToPdfButton.isDisabled()) {
			importToPdfButton.setDisable(false);
		}
		
		totalIncomeLabel.setText(Double.toString(StatementUtil.countIncomeForAllOrchard(orchardService.findAllOrchards())) + " Ft");
		totalExpensesLabel.setText(Double.toString(StatementUtil.countExpensesForAllOrchard(orchardService.findAllOrchards())) + " Ft");
		totalProfitLabel.setText(Double.toString(StatementUtil.countProfitForAllOrchard(orchardService.findAllOrchards())) + " Ft");
		totalWorkPriceLabel.setText(Double.toString(StatementUtil.countWorkExpensesForAllOrchard(orchardService.findAllOrchards())) + " Ft");
		totalMaterialPriceLabel.setText(Double.toString(StatementUtil.countMaterialExpensesForAllOrchard(orchardService.findAllOrchards())) + " Ft");
		
		workData = FXCollections.observableArrayList(workService.findAllWorks());
		varietyData = FXCollections.observableArrayList(varietyService.findAllVarieties());
		expensesTable.setItems(workData);
		varietyTable.setItems(varietyData);
		
		
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
		String dirpath = "kimutatasok";
		 String filename = dirpath + "/kimutatas_uzemi_" + LocalDate.now() + ".pdf";			 
		 try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			document.open();
			PDFUtil.addMetaData(document, "Kimutatás üzemi szinten");
			PDFUtil.addTitle(document);
			PDFUtil.addContent(document, orchardService.findAllOrchards());
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
	
	
}
