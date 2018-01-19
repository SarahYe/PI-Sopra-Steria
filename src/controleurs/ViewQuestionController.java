package controleurs;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	@FXML
	private Label justification;
	@FXML
	private CheckBox intiRep1, intiRep2, intiRep3, intiRep4, intiRep5;
	@FXML
	private Label intiQue;
	@FXML
	private Label progression;
	@FXML
	private ProgressBar progQuiz;
	@FXML
	private ImageView vrai;
	@FXML
	private ImageView faux;
	@FXML
	private ImageView bulle;
	private String xml="";

	public void initData(Quiz quiz, int cmpt) {
		this.cmpt = cmpt;
		this.quiz = quiz;
		
		File f =  new File(xml);
		if (f.exists()){
			quiz = quiz.convertirXMLToJava(xml);

			if (quiz.getListeQuestions().size() == cmpt) {
				Stage stage = (Stage) buttonNextQue.getScene().getWindow();
				stage.getScene().setRoot((Parent) JFxUtils.loadFxml("../vues/QuizAccueil.fxml"));
			} else {
				buttonNextQue.setVisible(Boolean.FALSE);
				question = quiz.getListeQuestions().get(cmpt);
				remplissageContentQuestion(question);
				progression.setText(cmpt + 1 + "/" + quiz.getListeQuestions().size());
				progQuiz.setProgress((double) cmpt / quiz.getListeQuestions().size());
			}
		} else {
			System.out.println("xml : "+xml);
		}

		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bulle.setVisible(false);
	}

	private void remplissageContentQuestion(Question question) {

		intiQue.setText(question.getIntituleQuestion());
		justification.setVisible(Boolean.FALSE);

		ArrayList<Reponse> reponses = question.getListeReponses();

		if (reponses.size() > 0) {
			intiRep1.setText(reponses.get(0).getIntitule());
			// justif1.setText(reponses.get(0).getJustification());
			// justification.setText("");
			// justification.setText(reponses.get(0).getJustification());
		} else
			intiRep1.setVisible(Boolean.FALSE);
		// justif1.setVisible(Boolean.FALSE);
		// justification.setVisible(Boolean.FALSE);

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
		
		
		
		
		Stage stage = (Stage) buttonNextQue.getScene().getWindow();
		stage.setScene(new Scene((Parent) JFxUtils.loadQuizFxml("../vues/QuizAccueil.fxml",xml), 850, 650));
		//stage.getScene().setRoot((Parent) JFxUtils.loadFxml("../vues/QuizAccueil.fxml"));
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
				QuizAccueilController.jouerAudio("././Ressources/Sons/succes.wav", -25.0f);
				buttonNextQue.setVisible(Boolean.TRUE);
				faux.setVisible(false);
				vrai.setVisible(true);
			} else {
				QuizAccueilController.jouerAudio("././Ressources/Sons/echec.wav", -25.0f);
			}
		}
	}

	@FXML
	private void ClickButtonNextQue(ActionEvent event) throws IOException {
		Stage stage = (Stage) buttonNextQue.getScene().getWindow();
		new JFxUtils().loadQuestion(quiz, cmpt + 1, stage,xml);
	}

	public void setXML(String xml) {
		this.xml=xml;
	}

}
