package quiz;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

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
}