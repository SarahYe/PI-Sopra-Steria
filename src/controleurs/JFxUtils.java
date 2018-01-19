package controleurs;

import java.io.IOException;

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
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public static Node loadQuizFxml(String fxml,String xml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			QuizAccueilController controller = loader.<QuizAccueilController>getController();
			controller.setXML(xml);
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}
	
	public Node loadFirstQuestion(String fxml, String xml) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JFxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(main.MainQuiz.class.getResource(fxml).openStream());
			ViewQuestionController controller = loader.<ViewQuestionController>getController();
			controller.setXML(xml);
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

	public Stage loadExplication(String fichierXML, Stage stage) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../vues/PageExplicationParametrage.fxml"));
		stage.setScene(new Scene((Pane) loader.load()));

		ParametresPageExplicationController controller = loader.<ParametresPageExplicationController>getController();
		controller.initData(fichierXML);

		stage.show();
		return stage;
	}

}