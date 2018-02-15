package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modeles.Quiz;

public class ViewQuizRecapController implements Initializable {
	
	@FXML
	private Label nbRepCorrect;
	
	@FXML
	private Label nbRepIncorrect;
	
	@FXML
	private Button buttonSuivant;
	
	@FXML
	private VBox vbox;
	
	private String xml = "";
	private boolean soloBloc = true;
	private int cmptChronologie = 0;
	private String xmlChronologie = "";
	HashMap<Integer,String> reponsesJoueur  = new HashMap<Integer,String>();
	private Quiz quiz = new Quiz();
	private int nbCorrect;
	private int nbIncorrect;
	private boolean son;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nbRepCorrect.setText("0");
		nbRepIncorrect.setText("0");
	}

	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}

	public void initData(HashMap<Integer,String> reponsesJoueur, boolean son) {
		this.reponsesJoueur = reponsesJoueur;
		//this.quiz = quiz;
		this.son=son;

		File f =  new File(xml);
		if (f.exists()){
			quiz = quiz.convertirXMLToJava(xml);
			//System.out.println(reponsesJoueur.values());
			for (int i = 0; i < quiz.getListeQuestions().size(); i++) {
				for (int j = 0; j < quiz.getListeQuestions().get(i).getListeReponses().size(); j++) {
					if (quiz.getListeQuestions().get(i).getListeReponses().get(j).getCorrect()) {
						String correctReponse = quiz.getListeQuestions().get(i).getListeReponses().get(j).getIntitule();
						if (quiz.getListeQuestions().get(i).getListeReponses().get(j).getIntitule().equals(reponsesJoueur.get(i))) {
							nbCorrect++;
							HBox hbox = new HBox();
							Label question = new Label();
							question.setText("" + (i+1) + ". " + quiz.getListeQuestions().get(i).getIntituleQuestion());
							question.setStyle("-fx-font-weight: bold;");
							question.setFont(Font.font ("Verdana", 12));
							question.setWrapText(true);
							hbox.getChildren().add(question);
							vbox.getChildren().add(hbox);
							
							HBox hbox2 = new HBox();
							hbox2.setSpacing(50);
							Label corRep = new Label();
							corRep.setText("\tVotre réponse :\n\t " + reponsesJoueur.get(i));
							corRep.setWrapText(true);
							hbox2.getChildren().add(corRep);
							ImageView imageView = new ImageView(ViewParametresPageExplicationController.chargerImage("././Ressources/Images/checkVert_mini.png"));
							imageView.setPreserveRatio(true);
							hbox2.getChildren().add(imageView);
							vbox.getChildren().add(hbox2);
						} else {
							nbIncorrect++;
							HBox hbox = new HBox();
							Label question = new Label();
							question.setText("" + (i+1) + ". " + quiz.getListeQuestions().get(i).getIntituleQuestion());
							question.setStyle("-fx-font-weight: bold;");
							question.setFont(Font.font ("Verdana", 12));
							question.setWrapText(true);
							hbox.getChildren().add(question);
							vbox.getChildren().add(hbox);
							
							HBox hbox2 = new HBox();
							hbox2.setSpacing(50);
							Label rep = new Label();
							rep.setText("\tVotre réponse :\n\t " + reponsesJoueur.get(i));
							rep.setWrapText(true);
							hbox2.getChildren().add(rep);
							ImageView imageView = new ImageView(ViewParametresPageExplicationController.chargerImage("././Ressources/Images/croixRouge_mini.png"));
							imageView.setPreserveRatio(true);
							imageView.setRotate(45);
							hbox2.getChildren().add(imageView);
							vbox.getChildren().add(hbox2);
							
							HBox hbox3 = new HBox();
							Label corRep = new Label();
							corRep.setText("\tLa bonne réponse était :\n\t " + correctReponse);
							corRep.setWrapText(true);
							hbox3.getChildren().add(corRep);
							vbox.getChildren().add(hbox3);
						}
						//System.out.println("Correct :" + nbCorrect + "\nIncorrect:" + nbIncorrect);
					}
						
				}
			}
			nbRepCorrect.setText("" + nbCorrect);
			nbRepIncorrect.setText("" + nbIncorrect);
		} else {
			System.out.println("xml : "+xml);
		}
		
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	@FXML
	private void ClickButtonNext(ActionEvent event) throws IOException {
		if(soloBloc){
			Stage stage = (Stage) buttonSuivant.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) buttonSuivant.getScene().getWindow();
			Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}
	}

}
