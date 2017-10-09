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

	@FXML
	private void initialize(){
		cropService = new CropServiceImpl(new CropDao(EntityManagerProvider.provideEntityManager()));
	}
	
	public void setDialogStage(Stage stage){
		this.dialogStage = stage;
	}

	public void setOrchard(Orchard orchard){
		this.orchard = orchard;
		
		nameTextField.setText(orchard.getOrchardName());
		topographicNumberTextField.setText(orchard.getTopographicNumber());
		meparCodeTextField.setText(orchard.getMeparCode());
		plantationYearTextField.setText(DateUtil.format(orchard.getYearOfPlantation()));
		plantationYearTextField.setPromptText("éééé");
		numberOfTreesTextField.setText(Integer.toString(orchard.getNumberOfTrees()));
		cropsTextField.setText(ListUtil.formatOutput(orchard.getCrops()));
	}

	public boolean isOkClicked(){
		return OkClicked;
	}

	@FXML
	public void handleOk(){
		if(isInputValid()){
	        orchard.setOrchardName(nameTextField.getText());
	        orchard.setTopographicNumber(topographicNumberTextField.getText());
	        orchard.setMeparCode(meparCodeTextField.getText());
	        orchard.setYearOfPlantation(DateUtil.parse(plantationYearTextField.getText()));
	        orchard.setNumberOfTrees(Integer.parseInt(numberOfTreesTextField.getText()));
	        List<String> cropNameList = ListUtil.parseInput(cropsTextField.getText());
	        List<Crop> cropList = new ArrayList<Crop>();
	        orchard.setCrops(cropList);
	        cropService.deleteAllCropsForOrchard(orchard.getId());	
		    for(String name : cropNameList){
		    	if(name != null && name.length() != 0){
			       	Crop crop = new Crop(name);
			       	crop.setOrchard(orchard);
			       	cropService.createCrop(crop);
			       	orchard.getCrops().add(crop);
		        }
	        }
	        OkClicked = true;
	        dialogStage.close();
		}
	}
	
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameTextField.getText() == null || nameTextField.getText().length() == 0) {
            errorMessage += "Név mező nem lehet üres!\n"; 
        }
        if (topographicNumberTextField.getText() == null || topographicNumberTextField.getText().length() == 0) {
            errorMessage += "Helyrajzi szám mező nem lehet üres!\n"; 
        }
        if (meparCodeTextField.getText() == null || meparCodeTextField.getText().length() == 0) {
            errorMessage += "MEPAR kód mező nem lehet üres!\n"; 
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
