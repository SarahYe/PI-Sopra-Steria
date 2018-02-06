package controleurs;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import modeles.Dialogue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class ViewAddOrModifyDialogueController implements Initializable {

	@FXML
	private TextField imageFondEcran;

	@FXML
	private Button boutonTelechargerFondEcran;

	@FXML
	private ColorPicker CouleurFondEcran;

	@FXML
	private Button boutonSupprimerFDE;

	@FXML
	private Button annulerCouleurFDE;

	@FXML
	private TextArea zoneDialogue;

	@FXML
	private TextField imagePersonnage;

	@FXML
	private Button boutonTelechargerPersonnage;

	@FXML
	private Button boutonSauvegarder;

	private TableViewSelectionModel<Dialogue> tableViewSelectionModel;
	private ViewParametresPNJController mainController;
	private boolean modifyDialogueInterface;
	private Boolean couleurFDE = false;
	private String imageVsCouleurFDE = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void initData(boolean modifyDialogueInterface, TableViewSelectionModel<Dialogue> tableViewSelectionModel,ViewParametresPNJController controller) {
		this.modifyDialogueInterface = modifyDialogueInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		mainController = controller;
		
		if (this.modifyDialogueInterface){
			
			Dialogue selectedDialogue = tableViewSelectionModel.getSelectedItem();
			
			if (selectedDialogue.getImageVsCouleur().contains("Couleur")) {
				CouleurFondEcran.setValue(Color.web(selectedDialogue.getCouleurFondEcran().replace("0x", "#")));
				couleurFDE = true;
				activerCouleur();
			} else {
				imageFondEcran.setText(selectedDialogue.getImageFondEcran());
				couleurFDE = false;
				activerCouleur();
			}
			
			zoneDialogue.setText(selectedDialogue.getIntitule());
			imagePersonnage.setText(selectedDialogue.getImagePersonnage());
		}
	}

	@FXML
	void annulerCouleurFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
		imageFondEcran.setDisable(false);
		boutonSupprimerFDE.setDisable(false);
		boutonTelechargerFondEcran.setDisable(false);
		
		couleurFDE = false;
	}

	@FXML
	void sauvegarder(ActionEvent event) {
		if (zoneDialogue.getText().isEmpty()) {
			//errorLabel.setText("Dialogue vide !");
			//errorLabel.setVisible(true);			
			return;	
		}
		
		if (imagePersonnage.getText().isEmpty()) {
			//popup d'alerte
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Paramétrage  d'un dialogue");
			alert.setContentText("Vous n'avez pas entré de personnage");
			alert.showAndWait();
		}
		
		if (couleurFDE)
			imageVsCouleurFDE = "Couleur";
		else
			imageVsCouleurFDE = "Image";
		
		if(modifyDialogueInterface){	
			mainController.setDialogue(tableViewSelectionModel.getSelectedIndex(), zoneDialogue.getText(), imageVsCouleurFDE, imagePersonnage.getText(), imageFondEcran.getText(), CouleurFondEcran.getValue().toString());		
		} else {		
			mainController.addDialogue(zoneDialogue.getText(), imageVsCouleurFDE, imagePersonnage.getText(),  imageFondEcran.getText(), CouleurFondEcran.getValue().toString());	
		}
		
	}

	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
	}

	@FXML
	void telechargerFondEcran(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageFondEcran.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
		

		couleurFDE = false;
		activerCouleur();
	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {	
		couleurFDE = true;
		activerCouleur();
	}
	
	@FXML
	void telechargerPersonnage(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imagePersonnage.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
	}
	
	public void activerCouleur() {
		if (couleurFDE) {
			imageFondEcran.setDisable(true);
			boutonSupprimerFDE.setDisable(true);
			boutonTelechargerFondEcran.setDisable(true);;
			//couleurFDE = false;
		} else {
			CouleurFondEcran.setDisable(true);
		}
	}


}
