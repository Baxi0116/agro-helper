package com.baxi.agrohelper;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baxi.agrohelper.dao.OrchardDao;
import com.baxi.agrohelper.model.Orchard;
import com.baxi.agrohelper.service.OrchardService;
import com.baxi.agrohelper.service.OrchardServiceImpl;
import com.baxi.agrohelper.util.EntityManagerProvider;
import com.baxi.agrohelper.view.OrchardEditDialogController;
import com.baxi.agrohelper.view.OrchardOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
    private BorderPane rootLayout;
    
    private OrchardService orchardService;
    
    private static Logger logger = LoggerFactory.getLogger(MainApp.class);
    
    ObservableList<Orchard> orchardData;
    
    public ObservableList<Orchard> getOrchardData(){
    	return orchardData;
    }
    
    public void setOrchardData(List<Orchard> orchardList){
    	this.orchardData = FXCollections.observableArrayList(orchardList);
    }
    
    public MainApp(){
    	this.orchardService = new OrchardServiceImpl(new OrchardDao(EntityManagerProvider.provideEntityManager()));
    }
    
    public OrchardService provideOrchardService(){
    	return this.orchardService;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AgroHelper");

        initRootLayout();

        showOrchardOverview();
    }
    
    @Override
    public void stop(){
    	EntityManagerProvider.closeConnection();
    }
    
    @Override
    public void init(){
    	this.orchardData = FXCollections.observableArrayList(orchardService.findAllOrchards());
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            logger.debug(loader.getLocation().toString());
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showOrchardOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrchardOverview.fxml"));
            logger.debug(loader.getLocation().toString());
            AnchorPane orchardOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(orchardOverview);
            
            OrchardOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
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
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrchardEditDialog.fxml"));
            logger.debug(loader.getLocation().toString());
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Szerkesztés");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            OrchardEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOrchard(orchard);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
		launch(args);
	}

}
