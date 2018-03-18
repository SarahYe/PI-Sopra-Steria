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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modeles.Fouille;
import modeles.Instruction;

/**
 * Controleur de l'interface de paramétrage d'un jeu de fouille.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
public class ViewParametresFouilleController implements Initializable {

	@FXML
	private TextField imageFDE;

	@FXML
	private Button boutonTelechargerFDE;

	@FXML
	private Button boutonSave;

	@FXML
	private Label errorLabel;

	@FXML
	private Button boutonAjout;

	@FXML
	private Button boutonModifier;

	@FXML
	private Button boutonSupprimer;

	@FXML
	private TableView<Instruction> table;

	@FXML
	private TextField bronze;

	@FXML
	private TextField silver;

	@FXML
	private TextField gold;

	@FXML
	private Label errorScore;

	@FXML
	private Label titre;

	@FXML
	private AnchorPane previsualisation;

	@FXML
	private ImageView prevFDE;

	private String xml;

	private ArrayList<ImageView> listeObjets = new ArrayList<ImageView>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")),
					20);
			titre.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		errorLabel.setVisible(false);
		errorScore.setVisible(false);
		bronze.setText("25");
		silver.setText("50");
		gold.setText("100");
	}

	/**
	 * Fonction d'initialisation des données de paramétrage du jeu de fouille.
	 * Vérifie s'il existe un fichier xml pré-configuré . Si oui, recupère les
	 * informations paramétrées et dans le cas contraire, présente un formulaire de
	 * paramétrage vide.
	 */
	public void initData() {
		File f = new File(xml);

		if (f.exists()) {
			ArrayList<Instruction> list = new ArrayList<Instruction>();
			Fouille fouille = new Fouille();
			Fouille fParam = fouille.convertirXMLToJava(xml);

			ObservableList<Instruction> data = table.getItems();
			for (int i = 0; i < fParam.getListeInstructions().size(); i++) {
				data.add(fParam.getListeInstructions().get(i));
				ImageView imageView = new ImageView(ViewParametresPageExplicationController
						.chargerImage(fParam.getListeInstructions().get(i).getImageObjet()));
				imageView.setPreserveRatio(true);
				previsualisation.getChildren().add(imageView);
				imageView.setLayoutX(fParam.getListeInstructions().get(i).getPosX());
				imageView.setLayoutY(fParam.getListeInstructions().get(i).getPosY());
				listeObjets.add(i, imageView);
			}
			imageFDE.setText(fParam.getFondEcran());
			silver.setText("" + fParam.getSilver());
			bronze.setText("" + fParam.getBronze());
			gold.setText("" + fParam.getGold());
			prevFDE.setImage(ViewParametresPageExplicationController.chargerImage(fParam.getFondEcran()));
		} else
			System.out.println("\"" + xml + "\" doesn't exist");
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml de fouille.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * Fonction appelée par le bouton d'ajout d'une nouvelle instruction. Affiche le
	 * formulaire vide correspondant aux paramètres d'une instruction.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void ClickButtonAdd(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setTitle("Nouvelle instruction");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyInstruction.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyInstructionController controller = loader.<ViewAddOrModifyInstructionController>getController();
		controller.initData(false, null, this);
		stage.show();
	}

	/**
	 * Fonction appelée par le bouton de modification d'une instruction. Vérifie si
	 * une instruction est sélectionnée avant d'afficher le formulaire d'instruction
	 * avec les informations pré-remplies.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void ClickButtonModify(ActionEvent event) throws IOException {

		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Stage stage = new Stage();
		stage.setTitle("Nouvelle instruction");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyInstruction.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyInstructionController controller = loader.<ViewAddOrModifyInstructionController>getController();
		controller.initData(true, table.getSelectionModel(), this);
		stage.show();

	}

	/**
	 * Fonction appelée par le bouton de suppression d'une instruction. Vérifie si
	 * une instruction est sélectionnée avant de la supprimer.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonRemove(ActionEvent event) {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}
		Instruction selectedItem = table.getSelectionModel().getSelectedItem();
		System.out.println(table.getSelectionModel().getSelectedIndex());
		previsualisation.getChildren().remove(listeObjets.get(table.getSelectionModel().getSelectedIndex()));
		listeObjets.remove(table.getSelectionModel().getSelectedIndex());
		System.out.println(listeObjets.size());
		table.getItems().remove(selectedItem);
	}

	/**
	 * Fonction de sauvegarde du paramétrage du jeu. Récupère les listes des
	 * instructions, enregistre le fichier XML puis affiche la popup de confirmation d'enregistrement du fichier.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonSave(ActionEvent event) {
		ArrayList<Instruction> listeInstructions = new ArrayList<Instruction>();
		ObservableList<Instruction> data = table.getItems();

		for (int i = 0; i < data.size(); i++) {
			listeInstructions.add(data.get(i));
		}

		if (imageFDE.getText().isEmpty() || listeInstructions.size() == 0) {
			errorLabel.setVisible(true);
		} else {

			if (ViewAddOrModifyInstructionController.tryParseDouble(gold.getText())
					&& ViewAddOrModifyInstructionController.tryParseDouble(bronze.getText())
					&& ViewAddOrModifyInstructionController.tryParseDouble(silver.getText())) {
				Fouille f = new Fouille("Fouille", listeInstructions, imageFDE.getText(),
						Double.parseDouble(gold.getText()), Double.parseDouble(bronze.getText()),
						Double.parseDouble(silver.getText()));
				f.convertirJavaToXML(f, xml);

				// popup de confirmation
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Paramétrage  d'un quiz");
				alert.setContentText("Le paramétrage a bien été enregistré !");
				alert.showAndWait();
			} else {
				errorScore.setVisible(true);
			}
		}
	}

	/**
	 * Fonction de téléchargement de l'image de décor du jeu de fouille. Affiche
	 * l'image dans la prévisualisation.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void telechargerImageFDE(ActionEvent event) throws IOException {

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

		imageFDE.setText("././Ressources/Images/" + nomImage);
		prevFDE.setImage(ViewParametresPageExplicationController.chargerImage("././Ressources/Images/" + nomImage));
	}

	/**
	 * Modifie une instruction dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage. Repositionne également l'objet dans la zone de
	 * prévisualisation.
	 * 
	 * @param selectedIndex
	 *            Entier représentant le rang de l'instruction dans le tableau .
	 *            Première instruction correspondant au rang 0.
	 * @param intitule
	 *            Consigne relative à l'image objet.
	 * @param imageObjet
	 *            Chemin relatif vers l'image objet.
	 * @param posX
	 *            Abscisse de positionnement de l'image objet dans le décor.
	 * @param posY
	 *            Ordonnée de positionnement de l'image objet dans le décor.
	 * @param type
	 *            Type de l'image objet.
	 * @see Instruction
	 */
	public void setInstruction(int selectedIndex, String intitule, String imageObjet, Double posX, Double posY,
			String type) {

		table.getItems().set(selectedIndex, new Instruction(intitule, imageObjet, posX, posY, type));

		previsualisation.getChildren().remove(listeObjets.get(selectedIndex));
		listeObjets.remove(selectedIndex);
		ImageView imageView = new ImageView(ViewParametresPageExplicationController.chargerImage(imageObjet));
		imageView.setPreserveRatio(true);
		previsualisation.getChildren().add(imageView);
		imageView.setLayoutX(posX);
		imageView.setLayoutY(posY);

		listeObjets.add(selectedIndex, imageView);

	}

	/**
	 * Ajoute une nouvelle instruction dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage. Positionne l'objet dans la zone de
	 * prévisualisation.
	 * 
	 * @param intitule
	 *            Consigne relative à l'image objet.
	 * @param imageObjet
	 *            Chemin relatif vers l'image objet.
	 * @param posX
	 *            Abscisse de positionnement de l'image objet dans le décor.
	 * @param posY
	 *            Ordonnée de positionnement de l'image objet dans le décor.
	 * @param type
	 *            Type de l'image objet.
	 * @see Instruction
	 */
	public void addInstruction(String intitule, String imageObjet, Double posX, Double posY, String type) {
		table.getItems().add(new Instruction(intitule, imageObjet, posX, posY, type));

		ImageView imageView = new ImageView(ViewParametresPageExplicationController.chargerImage(imageObjet));
		imageView.setPreserveRatio(true);
		previsualisation.getChildren().add(imageView);
		imageView.setLayoutX(posX);
		imageView.setLayoutY(posY);

		listeObjets.add(listeObjets.size(), imageView);
	}

}
