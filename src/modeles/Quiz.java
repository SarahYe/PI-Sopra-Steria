package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Modèle d'un bloc "Quiz".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nom", "listeQuestions" })
public class Quiz {

	@XmlAttribute
	String nomQuiz;

	ArrayList<Question> ListeQuestions;

	public Quiz() {

	}

	/**
	 * Crée un nouvel objet Quiz correspondant à l'ensemble des éléments
	 * paramétrables du jeu de Quiz.
	 * 
	 * @param mNom
	 *            Le nom du bloc si l'utilisateur souhaite le personnaliser.
	 * @param listeQuestions
	 *            La liste des questions constituant le quiz.
	 * @see Question
	 */
	public Quiz(String mNom, ArrayList<Question> listeQuestions) {
		this.nomQuiz = mNom;
		this.ListeQuestions = listeQuestions;
	}

	/**
	 * 
	 * @return Le nom du jeu.
	 */
	public String getNom() {
		return nomQuiz;
	}

	/**
	 * Modifie le nom du jeu de quiz pour une personnalisation.
	 * 
	 * @param mNom
	 */
	public void setNom(String mNom) {
		this.nomQuiz = mNom;
	}

	/**
	 * 
	 * @return La liste des questions constituant le quiz.
	 * @see Question
	 */
	@XmlElement(name = "Question")
	public ArrayList<Question> getListeQuestions() {
		return ListeQuestions;
	}

	/**
	 * Modifie la liste des questions constituant le quiz.
	 * 
	 * @param listeQuestions
	 * @see Question
	 */
	public void setListeQuestions(ArrayList<Question> listeQuestions) {
		ListeQuestions = listeQuestions;
	}

	/**
	 * Transforme l'objet "Quiz" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * 
	 * @param quiz
	 *            Classe Quiz
	 * @param nomFichier
	 *            Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 * 
	 */
	public void convertirJavaToXML(Quiz quiz, String nomFichier) {

		try {

			// create JAXB context and initializing Marshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// for getting nice formatted output
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			// Writing to XML file
			jaxbMarshaller.marshal(quiz, XMLfile);

			// Writing to console
			System.out.println("Fichier XML cree :\n");
			jaxbMarshaller.marshal(quiz, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Récupère un fichier xml et le transforme en objet Quiz.
	 * 
	 * @param nomFichier
	 *            Chemin vers le fichier xml.
	 * @return l'objet "Quiz".
	 * @see Quiz
	 */
	public Quiz convertirXMLToJava(String nomFichier) {

		Quiz quizz = new Quiz();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			// this will create Java object - quiz from the XML file
			quizz = (Quiz) jaxbUnmarshaller.unmarshal(XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return quizz;
	}

}
