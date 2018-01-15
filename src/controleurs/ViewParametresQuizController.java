package controleurs;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.MainQuiz;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;

public class ViewParametresQuizController{

	private ViewParametresQuizController mainController;
	
	@FXML
	private Button btnSave;
	@FXML
	private Button boutonAjout;
	@FXML
	private Button boutonModifier;
	@FXML
	private Button boutonSupprimer;
	@FXML 
	private TableView<Question> table;

	
	public void initData(ViewParametresQuizController controller) {
		
		mainController = controller;
		
		ArrayList<Question> list = new ArrayList<Question>();
		Quiz quiz = new Quiz("Nom du quiz", list);
		Quiz quiz2 = quiz.convertirXMLToJava("FichiersDeConfig/quiz.xml");
		ObservableList<Question> data = table.getItems();
		for (int i = 0; i < quiz2.getListeQuestions().size(); i++) {
			data.add(quiz2.getListeQuestions().get(i));
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
		quiz.convertirJavaToXML(quiz, "FichiersDeConfig/quiz.xml");

		Stage stage = (Stage) btnSave.getScene().getWindow();
		stage.close();
		try {
			new MainQuiz().start(stage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@FXML
	protected void ClickButtonAdd(ActionEvent event) throws IOException {
		
		Stage stage = new Stage();
		stage.setTitle("Nouvelle question");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(false, null, mainController);
		stage.show();

	}
	
	@FXML
	protected void ClickButtonModify(ActionEvent event) throws IOException {
		
		if (table.getSelectionModel().isEmpty()) {
			return;
		}
		
		Stage stage = new Stage();
		stage.setTitle("Modifier une question");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewAddOrModifyQuestion.fxml"));
		stage.setScene(new Scene(loader.load()));
		ViewAddOrModifyQuestionController controller = loader.<ViewAddOrModifyQuestionController>getController();
		controller.initData(true, table.getSelectionModel(), mainController);
		stage.show();

	}
	
	@FXML
	protected void ClickButtonRemove(ActionEvent event) throws IOException {
		
		if (table.getSelectionModel().isEmpty()) {
			return;
		}

		Question selectedItem = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(selectedItem);
		
	}


}
