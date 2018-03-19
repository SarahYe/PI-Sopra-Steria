package controleurs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import modeles.Dialogue;

/**
 * Controleur de l'interface d'ajout et de modification d'un dialogue dans le
 * bloc "Dialogue avec un PNJ".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
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

	/**
	 * Fonction d'initialisation des données de paramétrage d'un dialogue dans le
	 * bloc correspondant. Vérifie s'il s'agit d'un nouvel ajout ou d'une
	 * modification et selon le cas, affiche le formulaire avec les informations
	 * paramétrées.
	 * 
	 * @param modifyDialogueInterface
	 *            boolean permettant d'indiquer s'il s'agit d'un ajout (FALSE) ou
	 *            d'une modification (TRUE).
	 * @param tableViewSelectionModel
	 *            TableViewSelectionModel.
	 * @param controller
	 *            ViewParametresPNJController
	 * @see ViewParametresPNJController
	 */
	public void initData(boolean modifyDialogueInterface, TableViewSelectionModel<Dialogue> tableViewSelectionModel,
			ViewParametresPNJController controller) {
		this.modifyDialogueInterface = modifyDialogueInterface;
		this.tableViewSelectionModel = tableViewSelectionModel;
		mainController = controller;

		if (this.modifyDialogueInterface) {

			Dialogue selectedDialogue = tableViewSelectionModel.getSelectedItem();
			System.out.println(selectedDialogue.getImageVsCouleur());
			System.out.println(selectedDialogue.getImageFondEcran());
			System.out.println(selectedDialogue.getCouleurFondEcran());
			if (!selectedDialogue.getCouleurFondEcran().isEmpty()) {
				CouleurFondEcran.setValue(Color.web(selectedDialogue.getCouleurFondEcran().replace("0x", "#")));
				couleurFDE = true;
				activerCouleur();
			}

			if (!selectedDialogue.getImageFondEcran().isEmpty()) {
				imageFondEcran.setText(selectedDialogue.getImageFondEcran());
				couleurFDE = false;
				activerCouleur();
			}

			zoneDialogue.setText(selectedDialogue.getIntitule());
			imagePersonnage.setText(selectedDialogue.getImagePersonnage());
		}
	}

	/**
	 * Fonction permettant d'annuler l'ajout d'une couleur pour le fond d'écran de
	 * l'interface.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void annulerCouleurFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
		imageFondEcran.setDisable(false);
		boutonSupprimerFDE.setDisable(false);
		boutonTelechargerFondEcran.setDisable(false);

		couleurFDE = false;
	}

	/**
	 * Fonction d'ajout ou de modification d'un dialogue. Vérifie la cohérence des
	 * champs : présence d'un personnage.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @see Dialogue.
	 */
	@FXML
	void sauvegarder(ActionEvent event) {
		if (zoneDialogue.getText().isEmpty()) {
			return;
		}

		if (imagePersonnage.getText().isEmpty()) {
			// popup d'alerte
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
			mainController.setDialogue(tableViewSelectionModel.getSelectedIndex(), zoneDialogue.getText(),
					imageVsCouleurFDE, imagePersonnage.getText(), imageFondEcran.getText(),
					CouleurFondEcran.getValue().toString());
		} else {
			mainController.addDialogue(zoneDialogue.getText(), imageVsCouleurFDE, imagePersonnage.getText(),
					imageFondEcran.getText(), CouleurFondEcran.getValue().toString());
		}
	}

	/**
	 * Fonction permettant de réinitialiser une image de fond d'écran.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void supprimerImageFondEcran(ActionEvent event) {
		imageFondEcran.setText("");
		CouleurFondEcran.setDisable(false);
	}

	/**
	 * Fonction de téléchargement d'une image pour le paramètre "fond d'écran" du
	 * blod de PNJ.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
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

		couleurFDE = false;
		activerCouleur();
	}

	@FXML
	void modifierCouleurFondEcran(ActionEvent event) {
		couleurFDE = true;
		activerCouleur();
	}

	/**
	 * Fonction de téléchargement d'une image pour le paramètre "personnage" du blod
	 * de PNJ.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
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

		imagePersonnage.setText("././Ressources/Images/" + nomImage);
	}

	/**
	 * Fonction permettant d'intervertir les modes "Couleur unie" ou "Image" pour le
	 * fond d'écran.
	 */
	public void activerCouleur() {
		if (couleurFDE) {
			imageFondEcran.setDisable(true);
			boutonSupprimerFDE.setDisable(true);
			boutonTelechargerFondEcran.setDisable(true);
		} else {
			CouleurFondEcran.setDisable(true);
		}
	}

}
