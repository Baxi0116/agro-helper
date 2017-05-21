package com.baxi.agrohelper.view;

import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	private Stage dialogStage;
	private Orchard orchard;
	private boolean OkClicked = false;
	
    /**
     * Initializes the controller class.
     */
	@FXML
	private void initialize(){}
	
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
		
		nameTextField.setText(orchard.getNev());
		topographicNumberTextField.setText(orchard.getHelyrajziSzam());
		meparCodeTextField.setText(orchard.getMeparKod());
		plantationYearTextField.setText(DateUtil.format(orchard.getTelepitesEve()));
		plantationYearTextField.setPromptText("éééé.hh.nn.");
		numberOfTreesTextField.setText(Integer.toString(orchard.getFakSzama()));
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
	        orchard.setNev(nameTextField.getText());
	        orchard.setHelyrajziSzam(topographicNumberTextField.getText());
	        orchard.setMeparKod(meparCodeTextField.getText());
	        orchard.setTelepitesEve(DateUtil.parse(plantationYearTextField.getText()));
	        orchard.setFakSzama(Integer.parseInt(numberOfTreesTextField.getText()));

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
