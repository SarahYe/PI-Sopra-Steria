package controleurs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import modeles.Dialogue;
import modeles.PNJ;
/**
 * Controlleur du bloc pnj
 *
 */
public class ViewParametresPNJController implements Initializable {

	@FXML
	private Button btn;

	@FXML
	private Label errorLabel;

	@FXML
	private Label titre;

	@FXML
	private Button questionUp;

	@FXML
	private Button questionDown;

	@FXML
	private Button boutonAjout;

	@FXML
	private Button boutonModifier;

	@FXML
	private Button boutonSupprimer;

	@FXML
	private TableView<Dialogue> table;

	@FXML
	private AnchorPane AP_ParamDialogue;

	private String xml;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")),
					15);
			titre.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Fonction d'initialisation des données de paramétrage du bloc de Dialogue avec
	 * un PNJ. Vérifie s'il existe un fichier xml pré-configuré . Si oui, recupère
	 * les informations paramétrées et dans le cas contraire, présente un formulaire
	 * de paramétrage vide.
	 */
	public void initData() {
		File f = new File(xml);
		if (f.exists()) {
			ArrayList<Dialogue> list = new ArrayList<Dialogue>();
			PNJ pnj = new PNJ("Nom du quiz", list);
			PNJ pnj2 = pnj.convertirXMLToJava(xml);
			ObservableList<Dialogue> data = table.getItems();
			for (int i = 0; i < pnj2.getListeDialogues().size(); i++) {
				data.add(pnj2.getListeDialogues().get(i));
			}
		} else {
			System.out.println("\"" + xml + "\" doesn't exist");
		}
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml de dialogue avec un PNJ.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * Fonction appelée par le bouton d'ajout d'un nouveau dialogue. Affiche le
	 * formulaire vide correspondant aux paramètres d'un dialogue.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	void ClickButtonAdd(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialoguePNJ.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamDialogue.getChildren().setAll(newPane);
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(false, null, this);

	}

	/**
	 * Fonction appelée par le bouton de modification d'un dialogue. Vérifie si un
	 * dialogue est sélectionné avant d'afficher le formulaire de dialogue avec les
	 * informations pré-remplies.
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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/dialoguePNJ.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamDialogue.getChildren().setAll(newPane);
		ViewAddOrModifyDialogueController controller = loader.<ViewAddOrModifyDialogueController>getController();
		controller.initData(true, table.getSelectionModel(), this);
	}

	/**
	 * Fonction appelée par le bouton de suppression d'un dialogue. Vérifie si un
	 * dialogue est sélectionné avant de le supprimer.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonRemove(ActionEvent event) {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Dialogue selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
	}

	/**
	 * Fonction de sauvegarde du paramétrage d'un bloc de dialogue avec PNJ.
	 * Récupère les listes des dialogues, enregistre le fichier XML puis affiche la
	 * popup de confirmation d'enregistrement du fichier.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonSave(ActionEvent event) {
		ArrayList<Dialogue> listeDialogues = new ArrayList<Dialogue>();
		ObservableList<Dialogue> data = table.getItems();

		for (int i = 0; i < data.size(); i++) {
			listeDialogues.add(data.get(i));
		}

		PNJ dialogue = new PNJ("Dialogue avec PNJ", listeDialogues);
		dialogue.convertirJavaToXML(dialogue, xml);

		// popup de confirmation
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Paramétrage  d'un quiz");
		alert.setContentText("Le paramétrage a bien été enregistré !");
		alert.showAndWait();
	}

	/**
	 * Fonction permettant de remonter la position d'un dialogue dans une table
	 * correspondant à l'ordre d'apparition.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonUp(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index > 0) {
			Dialogue buff = table.getItems().get(index - 1);
			table.getItems().set(index - 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index - 1);
		}
	}

	/**
	 * Fonction permettant de descendre la position d'un dialogue dans une table correspondant à l'ordre d'apparition.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	void ClickButtonDown(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index < table.getItems().size()) {
			Dialogue buff = table.getItems().get(index + 1);
			table.getItems().set(index + 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index + 1);
		}
	}

	/**
	 * Modifie un dialogue donnée dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage.
	 * 
	 * @param selectedIndex
	 *            Entier représentant le rang de l'instruction dans le tableau .
	 *            Première instruction correspondant au rang 0.
	 * @param text
	 *            Intitulé du dialogue.
	 * @param imageVsCouleurFDE
	 *            Indication d'un fond d'ecran en couleur unie ou d'une image.
	 * @param perso
	 *            Chemin relatif vers l'image (format png ou jpg) représentant le
	 *            personnage.
	 * @param imFDE
	 *            Chemin relatif vers l'image (format png ou jpg) représentant le
	 *            fond d'écran.
	 * @param coulFDE
	 *            Couleur de fond d'écran au format 0x00000000.
	 */
	public void setDialogue(int selectedIndex, String text, String imageVsCouleurFDE, String perso, String imFDE,
			String coulFDE) {
		table.getItems().set(selectedIndex, new Dialogue(imageVsCouleurFDE, imFDE, coulFDE, text, perso));
	}

	/**
	 * Ajoute un nouveau dialogue dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage.
	 * 
	 * @param text
	 *            Intitulé du dialogue.
	 * @param imageVsCouleurFDE
	 *            Indication d'un fond d'ecran en couleur unie ou d'une image.
	 * @param perso
	 *            Chemin relatif vers l'image (format png ou jpg) représentant le
	 *            personnage.
	 * @param imFDE
	 *            Chemin relatif vers l'image (format png ou jpg) représentant le
	 *            fond d'écran.
	 * @param coulFDE
	 *            Couleur de fond d'écran au format 0x00000000.
	 */
	public void addDialogue(String text, String imageVsCouleurFDE, String perso, String imFDE, String coulFDE) {
		table.getItems().add(new Dialogue(imageVsCouleurFDE, imFDE, coulFDE, text, perso));
	}

}
