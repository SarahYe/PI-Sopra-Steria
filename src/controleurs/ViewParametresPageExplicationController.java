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
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import modeles.Explication;

/**
 * Controleur de l'interface de paramétrage d'une page explicative.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

public class ViewParametresPageExplicationController implements Initializable {

	private boolean modifierExplication;

	@FXML
	private ImageView fondEcran;
	@FXML
	private TextField champTitre;
	@FXML
	private TextField champSource;
	@FXML
	private TextArea champExplication;
	@FXML
	private TextArea champHyperlien;
	@FXML
	private Label theme;
	@FXML
	private Label erreur;
	@FXML
	private Label messageImage;
	@FXML
	private Label explication;
	@FXML
	private Label source;
	@FXML
	private Label label1;
	@FXML
	private Hyperlink hyperlien;
	@FXML
	private ImageView image1;
	@FXML
	private ImageView image2;
	@FXML
	private Button enregistrer;
	@FXML
	private Button boutonImage1;
	@FXML
	private Button boutonImage2;

	private String xml;

	String cheminImage1 = "Ressources/Images/icone_image.png";
	String cheminImage2 = "Ressources/Images/icone_image.png";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		champTitre.setVisible(false);
		champExplication.setVisible(false);
		champHyperlien.setVisible(false);
		champSource.setVisible(false);
		enregistrer.setVisible(false);
		erreur.setVisible(false);
		boutonImage1.setDisable(true);
		boutonImage2.setDisable(true);
		messageImage.setVisible(false);

		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")),
					25);
			theme.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Fonction d'initialisation des données de paramétrage d'une page explicative.
	 * Vérifie s'il existe un fichier xml pré-configuré . Si oui, recupère les
	 * informations paramétrées et dans le cas contraire, présente un formulaire de
	 * paramétrage vide.
	 */
	public void initData() {
		File f = new File(xml);
		if (f.exists()) {
			Explication expl = new Explication();
			Explication explXml = expl.convertirXMLToJava(xml);

			theme.setText(explXml.getTitre());
			explication.setText(explXml.getContenu());
			source.setText(explXml.getSource());

			if (explXml.getListeImages().size() == 0) {
				image1.setImage(chargerImage(cheminImage1));
				image2.setImage(chargerImage(cheminImage2));
			} else if (explXml.getListeImages().size() == 1) {
				image1.setImage(chargerImage(explXml.getListeImages().get(0)));
				image2.setImage(chargerImage(cheminImage2));
			} else {
				image1.setImage(chargerImage(explXml.getListeImages().get(0)));
				image2.setImage(chargerImage(explXml.getListeImages().get(1)));
			}

			hyperlien.setText(explXml.getListeLiens().toString().replace("[", "").replace("]", "").replace(", ", "\n"));
		} else
			System.out.println("xml :" + xml);

	}

	/**
	 * Fonction de téléchargement et d'affichage d'une image décorative ou
	 * illustrative . Affiche
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void changerImage1(ActionEvent event) throws IOException {

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
		image1.setImage(ViewParametresPageExplicationController.chargerImage("././Ressources/Images/" + nomImage));
		cheminImage1 = "././Ressources/Images/" + nomImage;

	}

	/**
	 * Fonction de téléchargement et d'affichage d'une image décorative ou
	 * illustrative . Affiche
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void changerImage2(ActionEvent event) throws IOException {

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
		image2.setImage(ViewParametresPageExplicationController.chargerImage("././Ressources/Images/" + nomImage));
		cheminImage2 = "././Ressources/Images/" + nomImage;

	}

	/**
	 * Fonction d'apparition du formulaire d'enregistrement d'une explication.
	 * Désactive le mode prévisualisation puis active le mode d'édition.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void changerTitre(MouseEvent event) {

		// éléments invisibles
		theme.setVisible(false);
		explication.setVisible(false);
		source.setVisible(false);
		hyperlien.setVisible(false);
		label1.setVisible(false);
		erreur.setVisible(false);

		// éléments visibles
		champTitre.setVisible(true);
		champExplication.setVisible(true);
		champSource.setVisible(true);
		champHyperlien.setVisible(true);
		enregistrer.setVisible(true);
		messageImage.setVisible(true);
		boutonImage1.setDisable(false);
		boutonImage2.setDisable(false);

		// titre
		champTitre.setText(theme.getText());

		// explication
		champExplication.setText(explication.getText());

		// source
		champSource.setText(source.getText());

		// lien(s)
		champHyperlien.setText(hyperlien.getText());

	}

	/**
	 * Fonction de sauvegarde du paramétrage de la page explicative. Récupère les
	 * paramètres de ladite interface, enregistre le fichier xml puis affiche la
	 * popup de confirmation d'enregistrement du fichier xml. Active le mode de
	 * prévisualisation.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void enregistrerParametrage(ActionEvent event) throws IOException {

		if (champExplication.getText().length() == 0)
			erreur.setVisible(true);
		else {

			// éléments invisibles
			champTitre.setVisible(false);
			champExplication.setVisible(false);
			champHyperlien.setVisible(false);
			champSource.setVisible(false);
			enregistrer.setVisible(false);
			erreur.setVisible(false);
			messageImage.setVisible(false);
			boutonImage1.setDisable(true);
			boutonImage2.setDisable(true);

			// titre
			theme.setText(champTitre.getText());

			// explication
			explication.setText(champExplication.getText());

			// source
			source.setText(champSource.getText());

			// lien(s)
			if (champHyperlien.getText().length() == 0
					|| champHyperlien.getText().equals("Entrez un ou plusieurs lien(s)")
					|| champHyperlien.getText().equals("Aucun lien"))
				hyperlien.setText("Aucun lien");
			else
				hyperlien.setText(champHyperlien.getText());

			// éléments visibles
			theme.setVisible(true);
			explication.setVisible(true);
			source.setVisible(true);
			hyperlien.setVisible(true);
			label1.setVisible(true);

			// ecriture xml
			ArrayList<String> ListeLiens = new ArrayList<String>();
			if (hyperlien.getText().contains("\n") || !hyperlien.getText().isEmpty()) {
				String[] difLiens = hyperlien.getText().split("\n");
				for (String s : difLiens)
					ListeLiens.add(s);
			}

			ArrayList<String> ListeImages = new ArrayList<String>();
			ListeImages.add(cheminImage1);
			ListeImages.add(cheminImage2);

			Explication objetExplication = new Explication(theme.getText(), explication.getText(), source.getText(),
					ListeLiens, ListeImages);
			objetExplication.convertirJavaToXML(objetExplication, xml);

			// popup de confirmation
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Paramétrage  d'une page explicative");
			alert.setContentText("Le paramétrage a bien été enregistré !");
			alert.showAndWait();
		}

	}

	/**
	 * Fonction de conversion d'un chemin au format Image.
	 * 
	 * @param chemin
	 *            Chemin vers une image.
	 * @return Un objet Image.
	 * @see Image.
	 */
	public static Image chargerImage(String chemin) {
		File file = new File(chemin);
		Image image = new Image(file.toURI().toString());
		return image;
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml de page explicative.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/*
	 * public void setTitre(String mTitre) { theme.setText(mTitre); }
	 * 
	 * public void setExplication(String mExplication) {
	 * explication.setText(mExplication); }
	 * 
	 * public void setSource(String mSource) { source.setText(mSource); }
	 * 
	 * public void setLien(String mlien) { hyperlien.setText(mlien); }
	 * 
	 * public void setImage1(String chemin) { image1.setImage(chargerImage(chemin));
	 * }
	 * 
	 * public void setImage2(String chemin) { image2.setImage(chargerImage(chemin));
	 * }
	 */

}
