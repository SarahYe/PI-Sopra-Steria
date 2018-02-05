package controleurs;

import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.MainQuiz;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;

public class ViewParametresQuizController implements Initializable{

	

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
	private String xml;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*// TODO Auto-generated method stub
		ArrayList<Question> list = new ArrayList<Question>();
		Quiz quiz = new Quiz("Nom du quiz", list);
		Quiz quiz2 = quiz.convertirXMLToJava("FichiersDeConfig/quiz.xml");
		ObservableList<Question> data = table.getItems();
		for (int i = 0; i < quiz2.getListeQuestions().size(); i++) {
			data.add(quiz2.getListeQuestions().get(i));
		}*/
	}

	public void initData() {
		File f =  new File(xml);
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

	public void setQuestion(int index, String intitule, ArrayList<Reponse> listeReponses) {
		table.getItems().set(index, new Question(intitule, listeReponses));
	}

	public void addQuestion(String intitule, ArrayList<Reponse> listeReponses) {
		table.getItems().add(new Question(intitule, listeReponses));
	}

	@FXML
	protected void ClickButtonSave(ActionEvent event) throws IOException {
		ArrayList<Question> listeQuestions = new ArrayList<Question>();
		ObservableList<Question> data = table.getItems();
		for (int i = 0; i < data.size(); i++) {
			listeQuestions.add(data.get(i));
		}
		
		Quiz quiz = new Quiz("Nom du quiz", listeQuestions);
		quiz.convertirJavaToXML(quiz, xml);

		//Stage stage = (Stage) btnSave.getScene().getWindow();
		//stage.close();
		
		/*try {
			new MainQuiz().start(stage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		//popup de confirmation
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Paramétrage  d'un quiz");
		alert.setContentText("Le paramétrage a bien été enregistré !");
		alert.showAndWait();
	}

	@FXML
	protected void ClickButtonAdd(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamQuestion.getChildren().setAll(newPane);
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(false, null, this);
		
		//Stage stage = new Stage();
		//stage.setTitle("Nouvelle question");
		// FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		// stage.setScene(new Scene(loader.load()));
		// ViewAddOrModifyQuestionController controller =
		// newPane.<ViewAddOrModifyQuestionController>getController();
		// controller.initData(false, null, mainController);
		// stage.show();
	}

	@FXML
	protected void ClickButtonModify(ActionEvent event) throws IOException {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		ScrollPane newPane = loader.load();
		AP_ParamQuestion.getChildren().setAll(newPane);
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(true, table.getSelectionModel(),this);
		
		/*
		 * Stage stage = new Stage();
		stage.setTitle("Modifier une question");
		 * FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		 * stage.setScene(new Scene(loader.load())); ViewAddOrModifyQuestionController
		 * controller = loader.<ViewAddOrModifyQuestionController>getController();
		 * controller.initData(true, table.getSelectionModel(), mainController);
		 * stage.show();
		 */
	}

	@FXML
	protected void ClickButtonRemove(ActionEvent event) throws IOException {
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Question selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
	}

	@FXML
	private void ClickButtonUp (ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index > 0){
			Question buff = table.getItems().get(index-1);
			table.getItems().set(index-1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index-1);
		}
	}
	
	@FXML
	private void ClickButtonDown (ActionEvent event) {
		int index = table.getSelectionModel().getSelectedIndex();
		if (index < table.getItems().size()){
			Question buff = table.getItems().get(index+1);
			table.getItems().set(index + 1, table.getItems().get(index));
			table.getItems().set(index, buff);
			table.getSelectionModel().select(index + 1);
		}
	}

	public void setXML(String xml) {
		this.xml=xml;
		
	}
	

}
