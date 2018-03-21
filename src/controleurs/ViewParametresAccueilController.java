package controleurs;

import java.awt.image.BufferedImage;
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

import javax.imageio.ImageIO;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modeles.Accueil;
import modeles.FondEcran;
import modeles.Titre;

/**
 * Controleur de l'interface de paramétrage d'un accueil.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

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
	private ComboBox<Integer> tailleNomSG;

	@FXML
	private Button boutonEnregistrer;

	@FXML
	private Label erreur;

	@FXML
	private Label titre;

	@FXML
	Accordion AD_Title;
	@FXML
	TitledPane FDE;

	private int modif = 0;
	private String xml;
	private Boolean texteNomSG = false;
	private Boolean couleurFDE = false;
	private ArrayList<String> listeNomFonts = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AD_Title.setExpandedPane(FDE);
		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")),
					20);
			titre.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ViewAddOrModifyQuestionController.addTextFLimiter(NomSG, 50);

		final File dossierPolices = new File("././Ressources/Polices");
		listerPolices(dossierPolices);
		Collections.sort(listeNomFonts);
		policeNomSG.getItems().addAll(listeNomFonts);

		tailleNomSG.getItems().addAll(8, 9, 10, 11, 12, 14, 18, 24, 30, 36, 48, 60, 72, 96);
		tailleNomSG.setValue(60);
		tailleNomSG.getEditor().textProperty().addListener(new ChangeListener<String>() {
			String restriction = "[0-9]";
			int maxLength = 3;
			private boolean ignore;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (ignore || newValue == null) {
					return;
				}

				if (newValue.length() > maxLength) {
					ignore = true;
					tailleNomSG.getEditor().setText(newValue.substring(0, maxLength));
					ignore = false;
				}

				if (!newValue.matches(restriction + "*")) {
					ignore = true;
					tailleNomSG.getEditor().setText(oldValue);
					ignore = false;
				}
			}
		});
	}

	/**
	 * Fonction d'initialisation des données de paramétrage d'un accueil. Vérifie
	 * s'il existe un fichier xml pré-configuré . Si oui, recupère les informations
	 * paramétrées et dans le cas contraire, présente un formulaire de paramétrage
	 * vide.
	 */
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
				tailleNomSG.setValue(Integer.parseInt(titre.getTaille()));
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

	/**
	 * Modifie le chemin relatif vers le fichier xml d'accueil.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * Fonction de téléchargement de l'image en fond d'écran de l'accueil.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void chargerImageFondEcran(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		nomImage = nomImage.substring(nomImage.lastIndexOf("\\") + 1);

		BufferedImage image = ImageIO.read(new File(cheminAbsoluImage.toString()));

		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}
		imageFondEcran.setText("././Ressources/Images/" + nomImage);

		modif++;
		couleurFDE = false;
		activerCouleur();
	}

	/**
	 * Fonction de suppression de l'image de fond d'écran.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		couleurFondEcran.setDisable(false);
		modif--;
	}

	/**
	 * Fonction de modification d'une couleur unie pour le fond d'écran.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {
		couleurFDE = true;
		activerCouleur();
		modif++;
		erreur.setVisible(false);
	}

	/**
	 * Fonction de suppression d'une couleur unie pour le fond d'écran.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
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

	/**
	 * Fonction de téléchargement d'une image pour le titre de l'accueil.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @see Titre
	 */
	@FXML
	void chargerImageNomSG(ActionEvent event) throws IOException {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);

		Path cheminAbsoluActuel = Paths.get("").toAbsolutePath();
		Path cheminAbsoluImage = Paths.get(file.getAbsolutePath());
		String[] s = cheminAbsoluImage.toString().split("/");
		String nomImage = s[s.length - 1];
		nomImage = nomImage.substring(nomImage.lastIndexOf("\\") + 1);
		BufferedImage image = ImageIO.read(new File(cheminAbsoluImage.toString()));

		if (nomImage.contains(".png") || nomImage.contains(".PNG")) {
			ImageIO.write(image, "png", new File("././Ressources/Images/" + nomImage));
		} else {
			if (nomImage.contains(".jpg") || nomImage.contains(".JPG"))
				ImageIO.write(image, "jpg", new File("././Ressources/Images/" + nomImage));
			else
				ImageIO.write(image, "jpeg", new File("././Ressources/Images/" + nomImage));
		}

		imageNomSG.setText("././Ressources/Images/" + nomImage);

		modif++;
		erreur.setVisible(false);
		texteNomSG = false;
		activerTelechargementNomSG();

	}

	/**
	 * Fonction de suppression de l'image pour le titre de l'accueil.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
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

	/**
	 * Fonction de prévisualisation du texte formatté pour le titre de l'accueil.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @see Titre.
	 */
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

	/**
	 * Fonction de suppression du texte formatté.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
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

	/**
	 * Fonction permettant de switcher entre le mode "Image" et le mode "Texte
	 * formatté" pour le titre.
	 */
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
			tailleNomSG.setDisable(true);
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

	/**
	 * Fonction de sauvegarde du paramétrage de l'accueil. Récupère les paramètres
	 * pour le titre et le fond d'écran. Ecrit le fichier xml puis affiche la popup
	 * de confirmation de son enregistrement.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
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
				titreObj.setTaille("" + tailleNomSG.getValue());
			} else {
				titreObj.setImageVsTexte("Image");
				titreObj.setLienImage(imageNomSG.getText());
			}

			// ecriture xml
			accueil.setFond(fe);
			accueil.setTitre(titreObj);
			accueil.convertirJavaToXML(accueil, xml);

			// popup de confirmation
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Paramétrage  d'un accueil");
			alert.setContentText("Le paramétrage a bien été enregistré !");
			alert.showAndWait();
		}
	}

	/**
	 * Fonction de prévisualisation de l'accueil paramétré.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void previsualisation(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Prévisualisation");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Accueil.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAccueilController controller = loader.<ViewAccueilController>getController();
		controller.initData(xml, true, 0);
		stage.show();
	}

	/**
	 * Permet de lister toutes les polices de caractères au format TTF issues d'un
	 * dosssier.
	 * 
	 * @param dossier
	 *            chemin vers le dossier.
	 */
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
