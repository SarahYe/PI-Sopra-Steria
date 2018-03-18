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
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;

/**
 * Controleur de l'interface de paramétrage d'un quiz.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

public class ViewParametresQuizController implements Initializable {

	@FXML
	private Button btnSave;
	@FXML
	private Button boutonAjout;
	@FXML
	private Button boutonModifier;
	@FXML
	private Button boutonSupprimer;
	@FXML
	private Button questionUp;
	@FXML
	private Button questionDown;
	@FXML
	private TableView<Question> table;
	@FXML
	private AnchorPane AP_ParamQuestion;
	@FXML
	private Label titre;
	private String xml;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Font font = Font.loadFont(new FileInputStream(new File("././Ressources/Polices/PoetsenOne-Regular.ttf")),
					20);
			titre.setFont(font);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fonction d'initialisation des données de paramétrage du quiz. Vérifie s'il
	 * existe un fichier xml pré-configuré . Si oui, recupère les informations
	 * paramétrées et dans le cas contraire, présente un formulaire de paramétrage
	 * vide.
	 */
	public void initData() {
		File f = new File(xml);
		if (f.exists()) {
			ArrayList<Question> list = new ArrayList<Question>();
			Quiz quiz = new Quiz("Nom du quiz", list);
			Quiz quiz2 = quiz.convertirXMLToJava(xml);
			ObservableList<Question> data = table.getItems();
			for (int i = 0; i < quiz2.getListeQuestions().size(); i++) {
				data.add(quiz2.getListeQuestions().get(i));
			}
		} else {
			System.out.println("xml \"" + xml + "\" doesn't exist");
		}

	}

	/**
	 * Modifie une question dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage.
	 * 
	 * @param index
	 *            Entier représentant le rang de l'instruction dans le tableau .
	 *            Première instruction correspondant au rang 0.
	 * @param intitule
	 *            Intitulé de la question.
	 * @param listeReponses
	 *            Liste de propositions de réponses pour la question.
	 */
	public void setQuestion(int index, String intitule, ArrayList<Reponse> listeReponses) {
		table.getItems().set(index, new Question(intitule, listeReponses));
	}

	/**
	 * Ajoute une nouvellequestion dans la liste apparaissant dans le tableau de
	 * l'interface de parametrage.
	 * 
	 * @param intitule
	 *            Intitulé de la question.
	 * @param listeReponses
	 *            Liste de propositions de réponses pour la question.
	 */
	public void addQuestion(String intitule, ArrayList<Reponse> listeReponses) {
		table.getItems().add(new Question(intitule, listeReponses));
	}

	/**
	 * Fonction de sauvegarde du paramétrage du quiz. Récupère les listes des
	 * questions, enregistre le fichier XML puis affiche la popup de confirmation
	 * d'enregistrement du fichier.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	protected void ClickButtonSave(ActionEvent event) throws IOException {
		ArrayList<Question> listeQuestions = new ArrayList<Question>();
		ObservableList<Question> data = table.getItems();
		for (int i = 0; i < data.size(); i++) {
			listeQuestions.add(data.get(i));
		}

		Quiz quiz = new Quiz("Quiz", listeQuestions);
		quiz.convertirJavaToXML(quiz, xml);

		// popup de confirmation
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Paramétrage  d'un quiz");
		alert.setContentText("Le paramétrage a bien été enregistré !");
		alert.showAndWait();
	}

	/**
	 * Fonction appelée par le bouton d'ajout d'une nouvelle question. Affiche le
	 * formulaire vide correspondant aux paramètres d'une question.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	protected void ClickButtonAdd(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamQuestion.getChildren().setAll(newPane);
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(false, null, this);

	}

	/**
	 * Fonction appelée par le bouton de modification d'une question. Vérifie si une
	 * question est sélectionnée avant d'afficher le formulaire de question avec les
	 * informations pré-remplies.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	protected void ClickButtonModify(ActionEvent event) throws IOException {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamQuestion.getChildren().setAll(newPane);
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(true, table.getSelectionModel(), this);
	}

	/**
	 * Fonction appelée par le bouton de suppression d'une question. Vérifie si une
	 * question est sélectionnée avant de la supprimer.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
	@FXML
	protected void ClickButtonRemove(ActionEvent event) throws IOException {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Question selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
	}

	/**
	 * Fonction permettant de remonter la position d'une question dans une table
	 * correspondant à l'ordre d'apparition.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	private void ClickButtonUp(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index > 0) {
			Question buff = table.getItems().get(index - 1);
			table.getItems().set(index - 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index - 1);
		}
	}

	/**
	 * Fonction permettant de descendre la position d'un dialogue dans une table
	 * correspondant à l'ordre d'apparition.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	private void ClickButtonDown(ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index < table.getItems().size()) {
			Question buff = table.getItems().get(index + 1);
			table.getItems().set(index + 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index + 1);
		}
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml de quiz.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;

	}

}
