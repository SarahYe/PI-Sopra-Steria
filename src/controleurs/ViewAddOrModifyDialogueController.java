package controleurs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	private String imageVsCouleurFDE = "Couleur";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		zoneDialogue.setWrapText(true);
		ViewAddOrModifyQuestionController.addTextALimiter(zoneDialogue, 300);
	}

	public void initData(boolean modifyDialogueInterface, TableViewSelectionModel<Dialogue> tableViewSelectionModel,ViewParametresPNJController controller) {
		this.modifyDialogueInterface = modifyDialogueInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		mainController = controller;
		
		if (this.modifyDialogueInterface){
			
			Dialogue selectedDialogue = tableViewSelectionModel.getSelectedItem();
			 System.out.println(selectedDialogue.getImageVsCouleur());
			 System.out.println(selectedDialogue.getImageFondEcran());
			 System.out.println(selectedDialogue.getCouleurFondEcran());
			if (!selectedDialogue.getCouleurFondEcran().isEmpty()) {
				CouleurFondEcran.setValue(Color.web(selectedDialogue.getCouleurFondEcran().replace("0x", "#")));
				couleurFDE = true;
				activerCouleur();
			} 
			
			if (!selectedDialogue.getImageFondEcran().isEmpty()){
				imageFondEcran.setText(selectedDialogue.getImageFondEcran());
				couleurFDE = false;
				activerCouleur();
			}
			
			//CouleurFondEcran.setValue(Color.web(selectedDialogue.getCouleurFondEcran().replace("0x", "#")));
			
			//imageFondEcran.setText(selectedDialogue.getImageFondEcran());
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
		
		if (modifyDialogueInterface) {	
			mainController.setDialogue(tableViewSelectionModel.getSelectedIndex(), zoneDialogue.getText(), imageVsCouleurFDE, imagePersonnage.getText(), imageFondEcran.getText(), CouleurFondEcran.getValue().toString());		
		} else {		
			mainController.addDialogue(zoneDialogue.getText(), imageVsCouleurFDE, imagePersonnage.getText(),  imageFondEcran.getText(), CouleurFondEcran.getValue().toString());	
		}
		
		/* Stage stage = (Stage) boutonSauvegarder.getScene().getWindow();
		 stage.close();*/
	}

	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
	}

	@FXML
	void telechargerFondEcran(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);
		
		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		BufferedImage image = ImageIO.read(new File(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString()));
		
		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else 
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}
		
		imageFondEcran.setText("././Ressources/Images/" + nomImage);
		//imageFondEcran.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
		
		couleurFDE = false;
		activerCouleur();
	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {	
		couleurFDE = true;
		activerCouleur();
	}
	
	@FXML
	void telechargerPersonnage(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());

		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		BufferedImage image = ImageIO.read(new File(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString()));
		
		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else 
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}
		
		imagePersonnage.setText("././Ressources/Images/" + nomImage);
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
