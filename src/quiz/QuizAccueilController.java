package quiz;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class QuizAccueilController implements Initializable{
	
	@FXML
	private Button buttonJouer;
	@FXML
	private Button ButtonParametrage;
	@FXML
	private Button buttonScore;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	
		
		
		
	}
	
	@FXML
	protected void ClickButtonJouer(ActionEvent event) {
		Stage stage = (Stage)buttonJouer.getScene().getWindow();
		stage.getScene().setRoot((Parent) JFxUtils.loadFxml("fxml/ViewQuestion.fxml"));
		
	}
	
	@FXML
	protected void ClickButtonScore(ActionEvent event) {
		Stage stage = (Stage)buttonScore.getScene().getWindow();
		stage.getScene().setRoot((Parent) JFxUtils.loadFxml("fxml/ViewScore.fxml"));
		//ouverture de l'interface de parametrage
		stage.sizeToScene();
		
	}
	
	@FXML
	protected void ClickButtonParametrage(ActionEvent event) {
		Stage stage = (Stage)ButtonParametrage.getScene().getWindow();
		//ouverture de l'interface de parametrage
		stage.sizeToScene();
		
	}

}
