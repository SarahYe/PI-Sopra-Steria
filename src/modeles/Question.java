package modeles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;

/**
 * Modèle d'une question dans le bloc "Quiz".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Quiz")
public class Question {

	String intituleQuestion;
	ArrayList<Reponse> ListeReponses;
	SimpleStringProperty intituleQuestionProperty;

	public Question() {

	}

	/**
	 * Crée un nouvel objet Question qui correspond à une question du jeu de quiz.
	 * L'objet est constitué de l'intitulé de la question ainsi que de ses
	 * propositions de réponses.
	 * 
	 * @param mName
	 *            L'intitulé de la question.
	 * @param mReponses
	 *            La liste de proposition des réponses.
	 * @see Quiz.
	 */
	public Question(String mName, ArrayList<Reponse> mReponses) {
		this.intituleQuestion = mName;
		this.ListeReponses = mReponses;
		this.intituleQuestionProperty = new SimpleStringProperty(mName);
	}

	/**
	 * 
	 * @return L'intitulé de la question.
	 */
	@XmlElement
	public String getIntituleQuestion() {
		return intituleQuestion;
	}

	/**
	 * 
	 * @return L'intitulé de la question.
	 */
	public SimpleStringProperty getIntituleQuestionProperty() {
		return intituleQuestionProperty;
	}

	/**
	 * Modifie l'intitulé d'une question.
	 * 
	 * @param intituleQuestion
	 */
	public void setIntituleQuestion(String intituleQuestion) {
		this.intituleQuestion = intituleQuestion;
	}

	/**
	 * Modifie l'intitulé d'une question.
	 * 
	 * @param intituleQuestion
	 */
	public void setIntituleQuestionProperty(String intituleQuestion) {
		this.intituleQuestionProperty.set(intituleQuestion);
	}

	/**
	 * 
	 * @return La liste des réponses proposées pour une question.
	 * @see Reponse.
	 */
	@XmlElementWrapper(name = "Reponses")
	@XmlElement(name = "Reponse")
	public ArrayList<Reponse> getListeReponses() {
		return ListeReponses;
	}

	/**
	 * Modifie la liste des réponses proposées pour une question.
	 * 
	 * @param reponses
	 */
	public void setListeReponses(ArrayList<Reponse> reponses) {
		this.ListeReponses = reponses;
	}

}
