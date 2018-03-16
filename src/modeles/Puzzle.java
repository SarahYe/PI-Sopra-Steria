package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Modèle d'un bloc de jeu de Puzzle.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nomPuzzle", "score_init", "decr_pts", "decr_sec", "score_min", "intitule",
		"fragmentType", "listeFragments", "listeIndices" })
public class Puzzle {

	@XmlAttribute
	String nomPuzzle;
	Double score_init = 5000d;
	Double decr_pts = 75d;
	Double decr_sec = 15d;
	Double score_min = 500d;
	String intitule;
	String fragmentType;
	ArrayList<String> listeIndices;
	ArrayList<String> listeFragments;

	public Puzzle() {
	}

	/**
	 * Crée un nouvel objet Puzzle représentant l'ensemble des éléments paramétrables du bloc de Puzzle.
	 * @param nomPuzzle Le nom du bloc si l'utilisateur souhaite le personnaliser.
	 * @param score_init Le score en début de jeu . Il s'agit d'un décimal.
	 * @param decr_pts Le nombre de points à retirer après un nombre de secondes passées.
	 * @param decr_sec Le nombre de secondes avant une décrémentation du score.
	 * @param score_min Le score minimal de la partie
	 * @param intitule La consigne du jeu.
	 * @param fragmentType La nature du jeu de puzzle : puzzle avec des bouts d'image ou puzzle avec des bouts de texte.
	 * @param listeIndices La liste des indices à présenter au joueur.
	 * @param listeFragments La liste des éléments à reconstituer.
	 */
	public Puzzle(String nomPuzzle, Double score_init, Double decr_pts, Double decr_sec, Double score_min,
			String intitule, String fragmentType, ArrayList<String> listeIndices, ArrayList<String> listeFragments) {
		this.nomPuzzle = nomPuzzle;
		this.score_init = score_init;
		this.decr_pts = decr_pts;
		this.decr_sec = decr_sec;
		this.score_min = score_min;
		this.intitule = intitule;
		this.fragmentType = fragmentType;
		this.listeIndices = listeIndices;
		this.listeFragments = listeFragments;
	}

	/**
	 * Crée une objet Puzzle contenant le nom du jeu, la consigne du jeu, la liste des éléments à reconstituer,
	 * le type de puzzle (imagé ou textuel) et la liste des indices de la partie.
	 * @param nomPuzzle Le nom du bloc si l'utilisateur souhaite le personnaliser.
	 * @param intitule La consigne du jeu.
	 * @param fragmentType La nature du jeu de puzzle : puzzle avec des bouts d'image ou puzzle avec des bouts de texte.
	 * @param listeString1 La liste des indices à présenter au joueur.
	 * @param listeString2 La liste des éléments à reconstituer.
	 */
	public Puzzle(String nomPuzzle, String intitule, String fragmentType, ArrayList<String> listeString1,
			ArrayList<String> listeString2) {
		this.nomPuzzle = nomPuzzle;
		this.intitule = intitule;
		this.fragmentType = fragmentType;
		this.listeIndices = listeString1;
		this.listeFragments = listeString2;
	}

	/**
	 * 
	 * @return
	 */
	public String getNom() {
		return nomPuzzle;
	}

	/**
	 * 
	 * @param nomPuzzle
	 */
	public void setNomPuzzle(String nomPuzzle) {
		this.nomPuzzle = nomPuzzle;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public Double getScore_init() {
		return score_init;
	}

	/**
	 * 
	 * @param score_init
	 */
	public void setScore_init(Double score_init) {
		this.score_init = score_init;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public Double getDecr_pts() {
		return decr_pts;
	}

	/**
	 * 
	 * @param decr_pts
	 */
	public void setDecr_pts(Double decr_pts) {
		this.decr_pts = decr_pts;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public Double getDecr_sec() {
		return decr_sec;
	}

	/**
	 * 
	 * @param decr_sec
	 */
	public void setDecr_sec(Double decr_sec) {
		this.decr_sec = decr_sec;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public Double getScore_min() {
		return score_min;
	}

	/**
	 * 
	 * @param score_min
	 */
	public void setScore_min(Double score_min) {
		this.score_min = score_min;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	/**
	 * 
	 * @param intitule
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElement
	public String getFragmentType() {
		return fragmentType;
	}

	/**
	 * 
	 * @param fragmentType
	 */
	public void setFragmentType(String fragmentType) {
		this.fragmentType = fragmentType;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElementWrapper(name = "Indices")
	@XmlElement(name = "Indice")
	public ArrayList<String> getListeIndices() {
		return listeIndices;
	}

	/**
	 * 
	 * @param listeIndices
	 */
	public void setListeIndices(ArrayList<String> listeIndices) {
		this.listeIndices = listeIndices;
	}

	/**
	 * 
	 * @return
	 */
	@XmlElementWrapper(name = "Fragments")
	@XmlElement(name = "Fragment")
	public ArrayList<String> getListeFragments() {
		return listeFragments;
	}

	/**
	 * 
	 * @param listeFragments
	 */
	public void setListeFragments(ArrayList<String> listeFragments) {
		this.listeFragments = listeFragments;
	}


	/**
	 * Transforme l'objet "Puzzle" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * 
	 * @param puzzle Classe Puzzle
	 * @param nomFichier  Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 */
	public void convertirJavaToXML(Puzzle puzzle, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Puzzle.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(puzzle, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 *  Récupère un fichier xml et le transforme en objet Puzzle.
	 * @param nomFichier Chemin vers le fichier xml.
	 * @return l'objet "Puzzle".
	 * @see Puzzle
	 */
	public Puzzle convertirXMLToJava(String nomFichier) {

		Puzzle puzzle = new Puzzle();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Puzzle.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			puzzle = (Puzzle) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return puzzle;
	}
}
