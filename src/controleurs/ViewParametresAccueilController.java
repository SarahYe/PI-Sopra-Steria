package controleurs;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;


public class ViewParametresAccueilController implements Initializable{

	@FXML
	private TextField imageFondEcran;

	@FXML
	private Button boutonsupprimerImageFDE;

	@FXML
	private ColorPicker couleurFondEcran;

	@FXML
	private Button boutonTelechargerImageFDE;

	@FXML
	private Button boutonApercu;

	@FXML
	private Label ZoneApercuNom;

	@FXML
	private TextField NomSG;

	@FXML
	private ColorPicker couleurNomSG;

	@FXML
	private Button boutonTelechargerImageNomSG;

	@FXML
	private TextField imageNomSG;

	@FXML
	private ComboBox<String> policeNomSG;

	@FXML
	private Button boutonEnregistrer;

	private String xml;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		couleurFondEcran.setDisable(false);
		policeNomSG.getItems().addAll(Font.getFontNames());
	}
	
	public void initData() {
		File f =  new File(xml);
		if (f.exists()) {
			
		} else {
			System.out.println("xml :"+xml);
		}
		
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void activerUnChoixFondEcran() {
		if (imageFondEcran.getText().length() > 0) {
			couleurFondEcran.setDisable(true);
		}
		
	    //tester si choix d'une couleur
	}

	@FXML
	void chargerImageFondEcran(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageFondEcran.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());
		
		couleurFondEcran.setDisable(true);
		//activerUnChoixFondEcran();
	}

	@FXML
	void chargerImageNomSG(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		imageNomSG.setText(cheminAbsoluActuel.relativize(cheminAbsoluImage).toString());

	}

	@FXML
	void choisirPoliceNomSG(ActionEvent event) {

	}

	@FXML
	void imageNomSG(ActionEvent event) {

	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {		
		imageFondEcran.setDisable(true);
		boutonsupprimerImageFDE.setDisable(true);
		boutonTelechargerImageFDE.setDisable(true);
	}

	@FXML
	void showNomSG(ActionEvent event) {
		ZoneApercuNom.setText(NomSG.getText());
		ZoneApercuNom.setFont(new Font(policeNomSG.getValue(), 20));
		ZoneApercuNom.setTextFill(couleurNomSG.getValue());
	}

	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		couleurFondEcran.setDisable(false);
	}
}
