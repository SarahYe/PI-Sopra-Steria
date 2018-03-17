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
 * Modèle d'un bloc "Jeu de Puzzle".
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
	 * Crée un nouvel objet Puzzle représentant l'ensemble des éléments
	 * paramétrables du bloc de Puzzle. Le jeu de puzzle est linéaire.
	 * 
	 * @param nomPuzzle
	 *            Le nom du bloc si l'utilisateur souhaite le personnaliser.
	 * @param score_init
	 *            Le score en début de jeu . Il s'agit d'un décimal.
	 * @param decr_pts
	 *            Le nombre de points à retirer après un nombre de secondes passées.
	 * @param decr_sec
	 *            Le nombre de secondes avant une décrémentation du score.
	 * @param score_min
	 *            Le score minimal de la partie
	 * @param intitule
	 *            La consigne du jeu.
	 * @param fragmentType
	 *            La nature du jeu de puzzle : puzzle avec des bouts d'image ou
	 *            puzzle avec des bouts de texte.
	 * @param listeIndices
	 *            La liste des indices à présenter au joueur.
	 * @param listeFragments
	 *            La liste des éléments à reconstituer.
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
	 * Crée une objet Puzzle contenant le nom du jeu, la consigne du jeu, la liste
	 * des éléments à reconstituer, le type de puzzle (imagé ou textuel) et la liste
	 * des indices de la partie.
	 * 
	 * @param nomPuzzle
	 *            Le nom du bloc si l'utilisateur souhaite le personnaliser.
	 * @param intitule
	 *            La consigne du jeu.
	 * @param fragmentType
	 *            La nature du jeu de puzzle : puzzle avec des bouts d'image ou
	 *            puzzle avec des bouts de texte.
	 * @param listeString1
	 *            La liste des indices à présenter au joueur.
	 * @param listeString2
	 *            La liste des éléments à reconstituer.
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
	 * @return Le nom actuel du jeu de puzzle.
	 */
	public String getNom() {
		return nomPuzzle;
	}

	/**
	 * Permet de personnaliser le nom du jeu puzzle.
	 * 
	 * @param nomPuzzle
	 *            Nom personnalisé du jeu.
	 */
	public void setNomPuzzle(String nomPuzzle) {
		this.nomPuzzle = nomPuzzle;
	}

	/**
	 * Le jeu de puzzle est un jeu chronométré avec un système de points dégressifs.
	 * 
	 * @return Le nombre de points accordé au joueur en début de partie.
	 */
	@XmlElement
	public Double getScore_init() {
		return score_init;
	}

	/**
	 * Permet de modifier le nombre de points en début de partie.
	 * 
	 * @param score_init
	 *            C'est un décimal.
	 */
	public void setScore_init(Double score_init) {
		this.score_init = score_init;
	}

	/**
	 * 
	 * @return Le nombre de points que perd le joueur par intervalle de temps
	 *         régulier.
	 * @see setDecr_sec, Puzzle.
	 */
	@XmlElement
	public Double getDecr_pts() {
		return decr_pts;
	}

	/**
	 * Permet de gérer l'évolution du score joueur en personnalisant le nombre de
	 * points perdu sur un intervalle de temps indiqué.
	 * 
	 * @param decr_pts
	 *            C'est un décimal.
	 */
	public void setDecr_pts(Double decr_pts) {
		this.decr_pts = decr_pts;
	}

	/**
	 * 
	 * @return Le nombre de secondes nécessaire pour une décrémentation du score du
	 *         joueur.
	 */
	@XmlElement
	public Double getDecr_sec() {
		return decr_sec;
	}

	/**
	 * Modifie le nombre de secondes nécessaire pour une décrémentation du score du
	 * joueur.
	 * 
	 * @param decr_sec
	 *            C'est un décimal.
	 */
	public void setDecr_sec(Double decr_sec) {
		this.decr_sec = decr_sec;
	}

	/**
	 * Le jeu de puzzle est un jeu chronométré avec un système de points dégressifs.
	 * Cette fonction permet de récupérer le score minimum du joueur quel que soit
	 * le temps mis sur la partie.
	 * 
	 * @return Le nombre de points minimun accordé au joueur en fin de partie dans
	 *         le cas où son réel score est inférieur à cette barre.
	 */
	@XmlElement
	public Double getScore_min() {
		return score_min;
	}

	/**
	 * Modifie le nombre de points minimun accordé au joueur en fin de partie dans
	 * le cas où son réel score est inférieur à cette barre.
	 * 
	 * @param score_min
	 *            C'est un décimal.
	 */
	public void setScore_min(Double score_min) {
		this.score_min = score_min;
	}

	/**
	 * 
	 * @return La consigne d'une partie de jeu de puzzle.
	 */
	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Modifie la consigne du jeu.
	 * 
	 * @param intitule
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * Le jeu de puzzle fonctionne avec 2 modes : le mode IMAGE et le mode TEXTE.
	 * 
	 * @return Le mode actuel du puzzle.
	 */
	@XmlElement
	public String getFragmentType() {
		return fragmentType;
	}

	/**
	 * Le jeu de puzzle fonctionne avec 2 modes : le mode IMAGE et le mode TEXTE.
	 * Modifie le mode du puzzle.
	 * 
	 * @param fragmentType
	 *            Deux valeurs possibles : "Image" ou "Texte".
	 */
	public void setFragmentType(String fragmentType) {
		this.fragmentType = fragmentType;
	}

	/**
	 * 
	 * @return La liste des indices d'une partie de puzzle.
	 */
	@XmlElementWrapper(name = "Indices")
	@XmlElement(name = "Indice")
	public ArrayList<String> getListeIndices() {
		return listeIndices;
	}

	/**
	 * Modifie la liste des indices à présenter au joueur.
	 * 
	 * @param listeIndices
	 */
	public void setListeIndices(ArrayList<String> listeIndices) {
		this.listeIndices = listeIndices;
	}

	/**
	 * 
	 * @return La liste des pièces constituant le puzzle.
	 */
	@XmlElementWrapper(name = "Fragments")
	@XmlElement(name = "Fragment")
	public ArrayList<String> getListeFragments() {
		return listeFragments;
	}

	/**
	 * La liste de fragments correspond à la liste des pièces constituant le puzzle.
	 * L'ordre des pièces dans la liste correspond à leur ordre d'apparition dans le
	 * puzzle reconstitué.
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
	 * @param puzzle
	 *            Classe Puzzle
	 * @param nomFichier
	 *            Chemin vers le fichier xml d'écriture. Le fichier porte déjà
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
	 * Récupère un fichier xml et le transforme en objet Puzzle.
	 * 
	 * @param nomFichier
	 *            Chemin vers le fichier xml.
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
