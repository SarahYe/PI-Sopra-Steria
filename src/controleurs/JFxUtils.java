package controleurs;

import java.io.IOException;

import controleurs.bloc.PageExplicationController;
import controleurs.bloc.QuizAccueilController;
import controleurs.bloc.ViewQuestionController;
import controleurs.parametrage.ParametresPageExplicationController;
import controleurs.parametrage.ViewParametresQuizController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modeles.Quiz;

public class JFxUtils {

	/**
	 * Fonction recuperant le noeud d'une vue provenant d'un fichier fxml pour son
	 * affichage
	 * 
	 * @param fxml
	 * @return
	 */
	public static Node loadFxml(String fxml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.bloc.MainQuiz.class.getResource(fxml).openStream());
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadQuizFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.bloc.MainQuiz.class.getResource(fxml).openStream());
			QuizAccueilController controller = loader.<QuizAccueilController>getController();
			controller.setXML(xml);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadExplicationFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.bloc.MainQuiz.class.getResource(fxml).openStream());
			PageExplicationController controller = loader.<PageExplicationController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadExplicationParamFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.bloc.MainQuiz.class.getResource(fxml).openStream());
			ParametresPageExplicationController controller = loader.<ParametresPageExplicationController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadParamQuiz(String fxml,String xml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.bloc.MainQuiz.class.getResource(fxml).openStream());
			ViewParametresQuizController controller = loader.<ViewParametresQuizController>getController();
			controller.setXML(xml);
			controller.initData();
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	public Stage loadQuestion(Quiz quiz, int cmpt, Stage stage, String xml) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/ViewQuestion.fxml"));
		stage.setScene(new Scene((Pane) loader.load()));

		ViewQuestionController controller = loader.<ViewQuestionController>getController();
		controller.setXML(xml);
		controller.initData(quiz, cmpt);

		stage.show();
		return stage;
	}
	
	

}