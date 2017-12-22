package quiz;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objets.Quiz;

public class JFxUtils {

	/**
	 * Fonction recuperant le noeud d'une vue provenant d'un fichier fxml pour son affichage
	 * @param fxml
	 * @return
	 */
    public static Node loadFxml(String fxml) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(JFxUtils.class.getResource(fxml));
            Node root = (Node) loader.load(quiz.MainQuiz.class.getResource(fxml).openStream());
            return root;
        } catch (IOException e) {
            throw new IllegalStateException("cannot load FXML screen", e);
        }
    }
    
    public Stage loadQuestion(Quiz quiz, int cmpt, Stage stage) throws IOException {
    	  FXMLLoader loader = new FXMLLoader(
    	    getClass().getResource(
    	      "fxml/ViewQuestion.fxml"
    	    )
    	  );

    	  stage.setScene(
    	    new Scene(
    	      (Pane) loader.load()
    	    )
    	  );

    	  ViewQuestionController controller = 
    	    loader.<ViewQuestionController>getController();
    	  controller.initData(quiz, cmpt);

    	  stage.show();

    	  return stage;
    	}
    
}