package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modeles.Accueil;
import modeles.FondEcran;
import modeles.Titre;

public class ViewParametresAccueilController implements Initializable {

	@FXML
	private TextField imageFondEcran;

	@FXML
	private Button boutonsupprimerImageFDE;

	@FXML
	private Button previsualiser;

	@FXML
	private ColorPicker couleurFondEcran;

	@FXML
	private Button boutonsupprimerCouleurFDE;

	@FXML
	private Button boutonTelechargerImageFDE;

	@FXML
	private Button boutonApercu;

	@FXML
	private Button boutonEffacerNomSG;

	@FXML
	private Label ZoneApercuNom;

	@FXML
	private TextField NomSG;

	@FXML
	private ColorPicker couleurNomSG;

	@FXML
	private Button boutonTelechargerImageNomSG;

	@FXML
	private Button boutonSupprimerImageNomSG;

	@FXML
	private TextField imageNomSG;

	@FXML
	private ComboBox<String> policeNomSG;

	@FXML
	private Button boutonEnregistrer;

	@FXML
	private Label erreur;
	
	@FXML
	private Label titre;

	private int modif = 0;
	private String xml;
	private Boolean texteNomSG = false;
	private Boolean couleurFDE = false;
	private ArrayList<String> listeNomFonts = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Font font = Font.loadFont(
					new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")), 20);
			titre.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//titre.setFont(Font.loadFont(getClass().getResourceAsStream("././Ressources/Polices/Comfortaa_Bold.ttf"), 24));
		final File dossierPolices = new File("././Ressources/Polices");
		listerPolices(dossierPolices);
		Collections.sort(listeNomFonts);
		policeNomSG.getItems().addAll(listeNomFonts);
	}

	public void initData() {
		Accueil accueil = new Accueil();
		File f = new File(xml);

		if (f.exists()) {
			Accueil accXML = accueil.convertirXMLToJava(xml);
			Titre titre = accXML.getTitre();

			if (titre.getImageVsTexte().contains("Texte")) {
				NomSG.setText(titre.getTexte());
				policeNomSG.setValue(titre.getPoliceTexte());
				couleurNomSG.setValue(Color.web(titre.getCouleurTexte().replace("0x", "#")));

				texteNomSG = true;
				activerTelechargementNomSG();
			} else {
				imageNomSG.setText(titre.getLienImage());

				texteNomSG = false;
				activerTelechargementNomSG();
			}

			FondEcran fondEcran = accXML.getFond();

			if (fondEcran.getImageVsCouleur().contains("Image")) {
				imageFondEcran.setText(fondEcran.getLienImage());

				couleurFDE = false;
				activerCouleur();
			} else {
				couleurFondEcran.setValue(Color.web(fondEcran.getCouleur().replace("0x", "#")));

				couleurFDE = true;
				activerCouleur();
			}

		} else {
			System.out.println("xml \"" + xml + "\" doesn't exist");
		}

	}

	public void setXML(String xml) {
		this.xml = xml;
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

		modif++;
		couleurFDE = false;
		activerCouleur();
		// couleurFondEcran.setDisable(true);
		// activerUnChoixFondEcran();
	}

	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		couleurFondEcran.setDisable(false);
		modif--;
	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {
		couleurFDE = true;
		activerCouleur();
		modif++;
		erreur.setVisible(false);
		/*
		 * imageFondEcran.setDisable(true); boutonsupprimerImageFDE.setDisable(true);
		 * boutonTelechargerImageFDE.setDisable(true);
		 */
	}

	@FXML
	void supprimerCouleurFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		couleurFondEcran.setDisable(false);
		imageFondEcran.setDisable(false);
		boutonsupprimerImageFDE.setDisable(false);
		boutonTelechargerImageFDE.setDisable(false);

		couleurFDE = false;
		modif--;
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

		modif++;
		erreur.setVisible(false);
		texteNomSG = false;
		activerTelechargementNomSG();
		/*
		 * NomSG.setDisable(true); policeNomSG.setDisable(true);
		 * couleurNomSG.setDisable(true); boutonApercu.setDisable(true);
		 */

	}

	@FXML
	void supprimerImageNomSG(ActionEvent event) {
		imageNomSG.setText("");
		boutonTelechargerImageNomSG.setDisable(false);
		boutonSupprimerImageNomSG.setDisable(false);
		imageNomSG.setDisable(false);

		NomSG.setDisable(false);
		policeNomSG.setDisable(false);
		couleurNomSG.setDisable(false);
		boutonApercu.setDisable(false);
		boutonEffacerNomSG.setDisable(false);

		modif--;
		texteNomSG = false;
	}

	@FXML
	void showNomSG(ActionEvent event) {
		if (NomSG.getText().length() > 0) {
			ZoneApercuNom.setText(NomSG.getText());
			try {
				Font font = Font.loadFont(
						new FileInputStream(new File("././Ressources/Polices/" + policeNomSG.getValue() + ".ttf")), 20);
				ZoneApercuNom.setFont(font);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ZoneApercuNom.setTextFill(couleurNomSG.getValue());

			modif++;
			erreur.setVisible(false);
			texteNomSG = true;
			activerTelechargementNomSG();
		} else {
			ZoneApercuNom.setText("");
			boutonTelechargerImageNomSG.setDisable(false);
			boutonSupprimerImageNomSG.setDisable(false);
			imageNomSG.setDisable(false);

			NomSG.setDisable(false);
			policeNomSG.setDisable(false);
			couleurNomSG.setDisable(false);
			boutonApercu.setDisable(false);
		}

	}

	@FXML
	void effacerNomSG(ActionEvent event) {
		NomSG.setText("");
		ZoneApercuNom.setText("");
		boutonTelechargerImageNomSG.setDisable(false);
		boutonSupprimerImageNomSG.setDisable(false);
		imageNomSG.setDisable(false);

		NomSG.setDisable(false);
		policeNomSG.setDisable(false);
		couleurNomSG.setDisable(false);
		boutonApercu.setDisable(false);

		modif--;
	}

	public void activerTelechargementNomSG() {
		if (texteNomSG) {
			boutonEffacerNomSG.setDisable(false);
			boutonTelechargerImageNomSG.setDisable(true);
			boutonSupprimerImageNomSG.setDisable(true);
			imageNomSG.setDisable(true);
		} else {
			NomSG.setDisable(true);
			policeNomSG.setDisable(true);
			couleurNomSG.setDisable(true);
			boutonApercu.setDisable(true);
			boutonEffacerNomSG.setDisable(true);
		}
	}

	public void activerCouleur() {
		if (couleurFDE) {
			imageFondEcran.setDisable(true);
			boutonsupprimerImageFDE.setDisable(true);
			boutonTelechargerImageFDE.setDisable(true);
		} else {
			couleurFondEcran.setDisable(true);
		}
	}

	@FXML
	void enregistrer(ActionEvent event) {

		Titre titreObj = new Titre();
		FondEcran fe = new FondEcran();
		Accueil accueil = new Accueil();

		if (modif <= 0) {
			erreur.setVisible(true);
		} else {
			if (couleurFDE) {
				fe.setImageVsCouleur("Couleur");
				fe.setCouleur(couleurFondEcran.getValue().toString());
			} else {
				fe.setImageVsCouleur("Image");
				fe.setLienImage(imageFondEcran.getText());
			}

			if (texteNomSG) {
				titreObj.setImageVsTexte("Texte");
				titreObj.setTexte(NomSG.getText());
				titreObj.setCouleurTexte(couleurNomSG.getValue().toString());
				titreObj.setPoliceTexte(policeNomSG.getValue().toString());
			} else {
				titreObj.setImageVsTexte("Image");
				titreObj.setLienImage(imageNomSG.getText());
			}

			// ecriture xml
			accueil.setFond(fe);
			accueil.setTitre(titreObj);
			accueil.convertirJavaToXML(accueil, "FichiersDeConfig/accueil.xml");

			// popup de confirmation
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Paramétrage  d'un accueil");
			alert.setContentText("Le paramétrage a bien été enregistré !");
			alert.showAndWait();
		}
	}

	@FXML
	void previsualisation(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Prévisualisation");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/Accueil.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAccueilController controller = loader.<ViewAccueilController>getController();
		controller.initData("FichiersDeConfig/accueil.xml", true);
		stage.show();

	}
	
	public void listerPolices(final File dossier) {
		for (final File police : dossier.listFiles()) {
			if (police.isDirectory()) {
				listerPolices(police);
			} else {
				if (police.getName().contains(".ttf")) {
					listeNomFonts.add(police.getName().substring(0, police.getName().indexOf(".")));
				}
			}
		}
	}

}
