package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modeles.Question;
import modeles.Quiz;
import modeles.Reponse;

import controleurs.QuizAccueilController;

public class ViewQuestionController implements Initializable {

	private Quiz quiz = new Quiz();
	private int cmpt;
	private Question question;

	@FXML
	private Button buttonNextQue;
/*	@FXML
	private Button reponse1;
	@FXML
	private Button reponse1P;
	@FXML
	private Button reponse2;
	@FXML
	private Button reponse2P;
	@FXML
	private Button reponse3;
	@FXML
	private Button reponse3P;
	@FXML
	private Button reponse4;
	@FXML
	private Button reponse4P;
	@FXML
	private Button reponse5;
	@FXML
	private Button reponse5P;*/
	@FXML
	private Button valider;
	@FXML
	private Button validerP;
	@FXML
	private Button volumeOn;
	@FXML
	private Button volumeOff;
	@FXML
	private Label justification;
/*	@FXML
	private Label reponse1L;
	@FXML
	private Label reponse2L;
	@FXML
	private Label reponse3L;
	@FXML
	private Label reponse4L;
	@FXML
	private Label reponse5L;*/
	@FXML
	private CheckBox intiRep1, intiRep2, intiRep3, intiRep4, intiRep5;
	@FXML
	private Label intiQue;
	//@FXML
	//private Label progression;
	@FXML
	private ProgressBar progQuiz;
	@FXML
	private ImageView vrai;
	@FXML
	private ImageView faux;
	@FXML
	private ImageView bulle;
	
	private String xml="";
	private boolean soloBloc=true;
	private int cmptChronologie=0;
	private String xmlChronologie="";
	private float niveauSon = -30.0f;
	private boolean son;
	private int score;
	
	HashMap<Integer,String> reponsesJoueur = new HashMap<Integer,String>();
	//private ArrayList<String> reponsesJoueur = new ArrayList<String>();

	public void initData(Quiz quiz, HashMap<Integer,String> reponsesJoueur, int cmpt, boolean son, int score) {
		this.cmpt = cmpt;
		this.quiz = quiz;
		this.son=son;
		this.score=score;
		this.reponsesJoueur = reponsesJoueur;
		if (son) {
			niveauSon =  -30.0f;
			volumeOn.setVisible(true);
			volumeOff.setVisible(false);
		} else {
			niveauSon =  -100000.0f;
			volumeOff.setVisible(true);
			volumeOn.setVisible(false);
		}
			
		
		File f =  new File(xml);
		if (f.exists()){
			this.quiz = quiz.convertirXMLToJava(xml);

			buttonNextQue.setVisible(Boolean.FALSE);
			question = this.quiz.getListeQuestions().get(cmpt);
			remplissageContentQuestion(question);
			//progression.setText(cmpt + 1 + "/" + quiz.getListeQuestions().size());
			progQuiz.setProgress((double) cmpt / this.quiz.getListeQuestions().size());
			
		} else {
			System.out.println("xml : "+xml);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bulle.setVisible(false);
	}
	
	public void setXML(String xml) {
		this.xml=xml;
	}
	
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie){
		this.soloBloc=soloBloc;
		this.cmptChronologie=cmptChronologie;
		this.xmlChronologie=xmlChronologie;
	}

	private void remplissageContentQuestion(Question question) {

		intiQue.setText(question.getIntituleQuestion());
		justification.setVisible(Boolean.FALSE);

		ArrayList<Reponse> reponses = question.getListeReponses();

		if (reponses.size() > 0) {
			intiRep1.setText(reponses.get(0).getIntitule());
		} else
			intiRep1.setVisible(Boolean.FALSE);

		if (reponses.size() > 1) {
			intiRep2.setText(reponses.get(1).getIntitule());
		} else
			intiRep2.setVisible(Boolean.FALSE);

		if (reponses.size() > 2) {
			intiRep3.setText(reponses.get(2).getIntitule());
		} else
			intiRep3.setVisible(Boolean.FALSE);

		if (reponses.size() > 3) {
			intiRep4.setText(reponses.get(3).getIntitule());
		} else
			intiRep4.setVisible(Boolean.FALSE);

		if (reponses.size() > 4) {
			intiRep5.setText(reponses.get(4).getIntitule());
		} else
			intiRep5.setVisible(Boolean.FALSE);

	}

	@FXML
	private void ClickBackHome(ActionEvent event) {
		//Stage stage = (Stage) buttonNextQue.getScene().getWindow();
		//stage.setScene(new Scene((Parent) JFxUtils.loadQuizFxml("../vues/QuizAccueil.fxml",xml, soloBloc, cmptChronologie, xmlChronologie), 850, 650));
		if(soloBloc){
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			/****Previous bloc****/
			/*Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}*/
		}
	}

	@FXML
	private void ClickButtonValider(ActionEvent event) {
		int cmptTrue = 0;
		boolean choice = Boolean.TRUE;

		bulle.setVisible(true);
		justification.setVisible(Boolean.TRUE);
		justification.setText("");
		
		if (!intiRep1.isSelected() && !intiRep2.isSelected() && !intiRep3.isSelected() && !intiRep4.isSelected() && !intiRep5.isSelected()) {
			justification.setText("Cochez au moins une réponse");
			vrai.setVisible(false);
			faux.setVisible(false);
		} else {

			for (Reponse reponse : question.getListeReponses()) {
				if (reponse.getCorrect())
					cmptTrue++;
			}
			
			ArrayList<Reponse> reponses = question.getListeReponses();

			if (reponses.size() > 0 && intiRep1.isSelected()) {
				justification.setText(reponses.get(0).getJustification());
				if (!reponses.get(0).getCorrect()) {
					choice = Boolean.FALSE;
				} else {
					cmptTrue--;
				}
			}

			if (reponses.size() > 1 && intiRep2.isSelected()) {
				justification.setText(reponses.get(1).getJustification());
				if (!reponses.get(1).getCorrect()) {
					choice = Boolean.FALSE;
				} else {
					cmptTrue--;
				}
			}

			if (reponses.size() > 2 && intiRep3.isSelected()) {
				justification.setText(reponses.get(2).getJustification());
				if (!reponses.get(2).getCorrect()) {
					choice = Boolean.FALSE;
				} else {
					cmptTrue--;
				}
			}

			if (reponses.size() > 3 && intiRep4.isSelected()) {
				justification.setText(reponses.get(3).getJustification());
				if (!reponses.get(3).getCorrect()) {
					choice = Boolean.FALSE;
				} else {
					cmptTrue--;
				}
			}

			if (reponses.size() > 4 && intiRep5.isSelected()) {
				justification.setText(reponses.get(4).getJustification());
				if (!reponses.get(4).getCorrect()) {
					choice = Boolean.FALSE;
				} else {
					cmptTrue--;
				}
			}

			vrai.setVisible(false);
			faux.setVisible(true);

			if (cmptTrue == 00 && choice) {
				QuizAccueilController.jouerAudio("././Ressources/Sons/succes.wav", niveauSon);
				faux.setVisible(false);
				vrai.setVisible(true);
				//score++;
				//System.out.print(score);
			} else {
				QuizAccueilController.jouerAudio("././Ressources/Sons/echec.wav", niveauSon);
			}
			
			//System.out.println(reponsesJoueur.);
			buttonNextQue.setVisible(Boolean.TRUE);
		}
	}

	@FXML
	private void ClickButtonNextQue(ActionEvent event) throws IOException {
		
		if (intiRep1.isSelected())
			reponsesJoueur.put(cmpt, intiRep1.getText().toString());
		if (intiRep2.isSelected())
			reponsesJoueur.put(cmpt, intiRep2.getText().toString());
		if (intiRep3.isSelected())
			reponsesJoueur.put(cmpt, intiRep3.getText().toString());
		if (intiRep4.isSelected())
			reponsesJoueur.put(cmpt, intiRep4.getText().toString());
		if (intiRep5.isSelected())
			reponsesJoueur.put(cmpt, intiRep5.getText().toString());
		
		if (quiz.getListeQuestions().size() == cmpt+1) {
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			stage.setScene(new Scene((Parent) JFxUtils.loadQuizReviewFxml(reponsesJoueur, "/vues/ViewRecapQuiz.fxml", xml, soloBloc, cmptChronologie, xmlChronologie,son,score), 850, 650));
			/*if(soloBloc){
				Stage stage = (Stage) buttonNextQue.getScene().getWindow();
				stage.setScene(new Scene((Parent) JFxUtils.loadQuizFxml("../vues/QuizAccueil.fxml",xml, soloBloc, cmptChronologie, xmlChronologie), 850, 650));
			} else {
				Stage stage = (Stage) buttonNextQue.getScene().getWindow();
				//System.out.print(score);
				Node node=JFxUtils.loadNextBloc(cmptChronologie, xmlChronologie, son);
				if (node!=null){
					stage.setScene(new Scene((Parent) node, 850, 650));
				} else {
					stage.close();
				}
				
			}*/
			
		} else {
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			//System.out.print(score);
			Node node=JFxUtils.loadQuestion(quiz, reponsesJoueur, cmpt + 1, "/vues/ViewQuestion.fxml",xml,soloBloc,cmptChronologie,xmlChronologie, son,score);
			if (node!=null){
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}
		
	}

	@FXML
	private void SonOn(ActionEvent event) {
		niveauSon =  -30.0f;
		son=true;
		volumeOn.setVisible(true);
		volumeOff.setVisible(false);
	}
	
	@FXML
	private void SonOff(ActionEvent event) {
		son=false;
		niveauSon =  -100000.0f;
		volumeOff.setVisible(true);
		volumeOn.setVisible(false);
	}
}
