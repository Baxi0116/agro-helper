package com.baxi.agrohelper.view;


import java.util.ArrayList;
import java.util.List;

import com.baxi.agrohelper.dao.CropDao;
import com.baxi.agrohelper.model.Crop;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.service.CropService;
import com.baxi.agrohelper.service.CropServiceImpl;
import com.baxi.agrohelper.util.DateUtil;
import com.baxi.agrohelper.util.EntityManagerProvider;
import com.baxi.agrohelper.util.ListUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 */
public class OrchardEditDialogController {

	@FXML
	private TextField nameTextField;
	
	@FXML
	private TextField topographicNumberTextField;
	
	@FXML
	private TextField meparCodeTextField;
	
	@FXML
	private TextField plantationYearTextField;
	
	@FXML
	private TextField numberOfTreesTextField;
	
	@FXML
	private TextField cropsTextField;
	
	private Stage dialogStage;
	private Orchard orchard;
	private boolean OkClicked = false;
	
	private CropService cropService;
	
    /**
     * Initializes the controller class.
     */
	@FXML
	private void initialize(){
		cropService = new CropServiceImpl(new CropDao(EntityManagerProvider.provideEntityManager()));
	}
	
    /**
     * Sets the stage of this dialog.
     * 
     * @param stage
     */
	public void setDialogStage(Stage stage){
		this.dialogStage = stage;
	}
	
    /**
     * Sets the orchard to be edited in the dialog.
     * 
     * @param orchard
     */
	public void setOrchard(Orchard orchard){
		this.orchard = orchard;
		
		nameTextField.setText(orchard.getOrchardName());
		topographicNumberTextField.setText(orchard.getTopographicNumber());
		meparCodeTextField.setText(orchard.getMeparCode());
		plantationYearTextField.setText(DateUtil.format(orchard.getYearOfPlantation()));
		plantationYearTextField.setPromptText("éééé.hh.nn.");
		numberOfTreesTextField.setText(Integer.toString(orchard.getNumberOfTrees()));
		cropsTextField.setText(ListUtil.formatOutput(orchard.getCrops()));
	}
	
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
	public boolean isOkClicked(){
		return OkClicked;
	}
	
	/**
     * Called when the user clicks ok.
     */
	@FXML
	public void handleOk(){
		if(isInputValid()){
	        orchard.setOrchardName(nameTextField.getText());
	        orchard.setTopographicNumber(topographicNumberTextField.getText());
	        orchard.setMeparCode(meparCodeTextField.getText());
	        orchard.setYearOfPlantation(DateUtil.parse(plantationYearTextField.getText()));
	        orchard.setNumberOfTrees(Integer.parseInt(numberOfTreesTextField.getText()));
	        List<String> cropNameList = ListUtil.parseInput(cropsTextField.getText());
	        cropService.deleteAllCropsForOrchard(orchard.getId());
	        List<Crop> cropList = new ArrayList<Crop>();
	        orchard.setCrops(cropList);
	        for(String name : cropNameList){
	        	Crop crop = new Crop(name);
	        	crop.setOrchard(orchard);
	        	cropService.createCrop(crop);
	        	orchard.getCrops().add(crop);
	        }
	        
	        OkClicked = true;
	        dialogStage.close();
		}
	}
	
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
            errorMessage += "Hibás név!\n"; 
        }
        if (topographicNumberTextField.getText() == null || topographicNumberTextField.getText().length() == 0) {
            errorMessage += "Hibás helyrajzi szám!\n"; 
        }
        if (meparCodeTextField.getText() == null || meparCodeTextField.getText().length() == 0) {
            errorMessage += "Hibás MEPAR kód!\n"; 
        }
        try {
        	Integer.parseInt(numberOfTreesTextField.getText());
        } catch (NumberFormatException e) {
        	errorMessage += "A mező tartalmának számnak kell lennie!\n"; 
        }

        if (!DateUtil.validDate(plantationYearTextField.getText())) {
        	errorMessage += "Hibás dátum (éééé.hh.nn.)!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
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
