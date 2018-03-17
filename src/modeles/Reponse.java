package modeles;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modèle d'une réponse dans le jeu de quiz.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Question")
public class Reponse {

	String intitule;
	Boolean correct;
	String justification;

	public Reponse() {

	}

	/**
	 * Crée un nouvelle proposition de réponse relative à une question du jeu de
	 * Quiz. Une réponse est composée de son intitulé, de son indication comme
	 * réponse correcte d'une question et d'une justification.
	 * 
	 * @param mIntitule
	 *            L'intitulé de la réponse.
	 * @param mCorrect
	 *            Boolean : vrai si la reponse est correcte, faux sinon.
	 * @param mJustification
	 *            La justification à présenter au joueur.
	 * @see Question.
	 * @see Quiz.
	 */
	public Reponse(String mIntitule, Boolean mCorrect, String mJustification) {
		this.intitule = mIntitule;
		this.correct = mCorrect;
		this.justification = mJustification;
	}

	/**
	 * 
	 * @return L'intitulé de la réponse.
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Modifie l'intitulé d'une réponse.
	 * 
	 * @param intitule
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * 
	 * @return vrai si la reponse est correcte, faux sinon.
	 */
	public Boolean getCorrect() {
		return correct;
	}

	/**
	 * Modifie l'indication de la réponse comme réponse correcte d'une question.
	 * 
	 * @param correct
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * 
	 * @return La justification associée à une réponse.
	 */
	public String getJustification() {
		return justification;
	}

	/**
	 * Modifie la justification associée à une réponse.
	 * 
	 * @param justification
	 */
	public void setJustification(String justification) {
		this.justification = justification;
	}
}
