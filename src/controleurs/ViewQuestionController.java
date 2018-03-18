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

/**
 * Controleur de l'interface joueur d'une question pendant le quiz.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
public class ViewQuestionController implements Initializable {

	private Quiz quiz = new Quiz();
	private int cmpt;
	private Question question;

	@FXML
	private Button buttonNextQue;
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
	@FXML
	private CheckBox intiRep1, intiRep2, intiRep3, intiRep4, intiRep5;
	@FXML
	private Label intiQue;
	@FXML
	private ProgressBar progQuiz;
	@FXML
	private ImageView vrai;
	@FXML
	private ImageView faux;
	@FXML
	private ImageView bulle;

	private String xml = "";
	private boolean soloBloc = true;
	private int cmptChronologie = 0;
	private String xmlChronologie = "";
	private float niveauSon = -30.0f;
	private boolean son;
	private int score;

	HashMap<Integer, String> reponsesJoueur = new HashMap<Integer, String>();

	/**
	 * Fonction d'initialisation des données paramétrées d'une question du jeu de
	 * quiz. Vérifie l'existence d'un fichier xml correspondant puis recupère les
	 * informations paramétrées.
	 * 
	 * Affiche la question et atualise la barre de progression.
	 * 
	 * @param quiz
	 *            Objet quiz.
	 * @param reponsesJoueur
	 *            Liste contenant les différentes réponses choisies par le joueur au
	 *            coursd'un quiz.
	 * @param cmpt
	 *            Compteur définissant le rang de la question présentée.
	 * @param son
	 *            Paramètre d'activation ou non des éléments sonores.
	 * @param score
	 *            Score cumulé par le joueur lors des événements antérieurs.
	 * 
	 * @see Quiz.
	 */
	public void initData(Quiz quiz, HashMap<Integer, String> reponsesJoueur, int cmpt, boolean son, int score) {
		this.cmpt = cmpt;
		this.quiz = quiz;
		this.son = son;
		this.score = score;
		this.reponsesJoueur = reponsesJoueur;
		if (son) {
			niveauSon = -30.0f;
			volumeOn.setVisible(true);
			volumeOff.setVisible(false);
		} else {
			niveauSon = -100000.0f;
			volumeOff.setVisible(true);
			volumeOn.setVisible(false);
		}

		File f = new File(xml);
		if (f.exists()) {
			this.quiz = quiz.convertirXMLToJava(xml);

			buttonNextQue.setVisible(Boolean.FALSE);
			question = this.quiz.getListeQuestions().get(cmpt);
			remplissageContentQuestion(question);
			progQuiz.setProgress((double) cmpt / this.quiz.getListeQuestions().size());

		} else {
			System.out.println("xml : " + xml);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bulle.setVisible(false);
	}

	/**
	 * Modifie le chemin relatif vers le fichier xml de quiz.
	 * 
	 * @param xml
	 */
	public void setXML(String xml) {
		this.xml = xml;
	}

	/**
	 * 
	 * @param soloBloc
	 * @param cmptChronologie
	 * @param xmlChronologie
	 */
	public void setChronologie(boolean soloBloc, int cmptChronologie, String xmlChronologie) {
		this.soloBloc = soloBloc;
		this.cmptChronologie = cmptChronologie;
		this.xmlChronologie = xmlChronologie;
	}

	/**
	 * Affiche les propositions de réponses pour une question.
	 * 
	 * @param question
	 *            Objet question
	 * @see Question.
	 */
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
		/*
		 * if (soloBloc) { Stage stage = (Stage) buttonNextQue.getScene().getWindow();
		 * stage.close(); } else { Stage stage = (Stage)
		 * buttonNextQue.getScene().getWindow();
		 *//**** Previous bloc ****//*
									 * }
									 */
	}

	/**
	 * Fonction gérant la validation d'une réponse. Affiche la justification,
	 * l'indication d'une réponse correcte ou pas et l'affichage du bouton
	 * permettant de passer à la question suivante. Gère l'effet sonore et le score
	 * du joueur.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	private void ClickButtonValider(ActionEvent event) {
		int cmptTrue = 0;
		boolean choice = Boolean.TRUE;

		bulle.setVisible(true);
		justification.setVisible(Boolean.TRUE);
		justification.setText("");

		if (!intiRep1.isSelected() && !intiRep2.isSelected() && !intiRep3.isSelected() && !intiRep4.isSelected()
				&& !intiRep5.isSelected()) {
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
				ViewAccueilController.jouerAudio("././Ressources/Sons/succes.wav", niveauSon);
				faux.setVisible(false);
				vrai.setVisible(true);
				score += 500;
			} else {
				ViewAccueilController.jouerAudio("././Ressources/Sons/echec.wav", niveauSon);
			}

			buttonNextQue.setVisible(Boolean.TRUE);
		}
	}

	/**
	 * Fonction permettant de passer à la prochaine question ou au récapitulatif des réponses
	 * du joueur.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 * @throws IOException
	 */
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

		if (quiz.getListeQuestions().size() == cmpt + 1) {
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			stage.setScene(new Scene((Parent) JFxUtils.loadQuizReviewFxml(reponsesJoueur, "/vues/ViewRecapQuiz.fxml",
					xml, soloBloc, cmptChronologie, xmlChronologie, son, score), 850, 650));
		} else {
			Stage stage = (Stage) buttonNextQue.getScene().getWindow();
			Node node = JFxUtils.loadQuestion(quiz, reponsesJoueur, cmpt + 1, "/vues/ViewQuestion.fxml", xml, soloBloc,
					cmptChronologie, xmlChronologie, son, score);
			if (node != null) {
				stage.setScene(new Scene((Parent) node, 850, 650));
			} else {
				stage.close();
			}
		}

	}

	/**
	 * Active le son.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	private void SonOn(ActionEvent event) {
		niveauSon = -30.0f;
		son = true;
		volumeOn.setVisible(true);
		volumeOff.setVisible(false);
	}

	/**
	 * Désactive le son.
	 * 
	 * @param event
	 *            Listener d'action sur un bouton.
	 */
	@FXML
	private void SonOff(ActionEvent event) {
		son = false;
		niveauSon = -100000.0f;
		volumeOff.setVisible(true);
		volumeOn.setVisible(false);
	}
}
