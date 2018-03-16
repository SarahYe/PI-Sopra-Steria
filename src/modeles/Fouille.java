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
 * Modèle d'un bloc "Jeu de fouille".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nomFouille", "fondEcran", "silver", "bronze", "gold", "listeInstructions" })
public class Fouille {

	@XmlAttribute
	String nomFouille;
	Double silver;
	Double bronze;
	Double gold;
	String fondEcran;

	ArrayList<Instruction> ListeInstructions;

	public Fouille() {
	}

	/**
	 * Crée un nouvel objet "Fouille" qui représente l'ensemble des données paramétrables nécessaires
	 * à la création d'un jeu de fouille.
	 * 
	 * @param nomJeu Le nom du jeu . La valeur par défaut est "Nom du bloc".
	 * @param listeInstructions La liste des consignes par rapport aux différentes images à trouver. 
	 * @param fde Le chemin relatif vers l'image représentant le décor sur lequel les images seronts disposées.
	 * @param gold Le nombre de points rapportés par une image de type GOLD. Il s'agit d'un décimal.
	 * @param bronze Le nombre de points rapportés par une image de type SILVER. Il s'agit d'un décimal.
	 * @param silver Le nombre de points rapportés par une image de type BRONZE. Il s'agit d'un décimal.
	 */
	public Fouille(String nomJeu,ArrayList<Instruction> listeInstructions, String fde, Double gold, Double bronze,
			Double silver) {
		this.nomFouille = nomJeu;
		this.bronze = bronze;
		this.gold = gold;
		this.silver = silver;
		this.fondEcran = fde;
		ListeInstructions = listeInstructions;
	}

	/**
	 * Renvoie le nom du jeu de fouille si indiqué. La valeur par défaut est "Nom du bloc".
	 * @return nom du jeu de fouille
	 */
	public String getNom() {
		return nomFouille;
	}

	/**
	 * Permet de nommer le jeu de fouille pour une personnalisation.
	 * @param nomFouille
	 */
	public void setNomFouille(String nomFouille) {
		this.nomFouille = nomFouille;
	}

	/**
	 * Renvoie le nombre de points rapportés par une image de type SILVER. Il s'agit d'un décimal.
	 * @return Le nombre de points pour le type SILVER.
	 */
	@XmlElement
	public Double getSilver() {
		return silver;
	}

	/**
	 * Permet de modifier le nombre de points que rapporte une image de type SILVER. 
	 * @param silver Il s'agit d'un décimal.
	 */
	public void setSilver(Double silver) {
		this.silver = silver;
	}

	/**
	 * Renvoie le nombre de points rapportés par une image de type BRONZE. Il s'agit d'un décimal.
	 * @return Le nombre de points pour le type BRONZE.
	 */
	@XmlElement
	public Double getBronze() {
		return bronze;
	}

	/**
	 *  Permet de modifier le nombre de points que rapporte une image de type BRONZE. 
	 * @param bronze Il s'agit d'un décimal.
	 */
	public void setBronze(Double bronze) {
		this.bronze = bronze;
	}

	/**
	 *  Renvoie le nombre de points rapportés par une image de type GOLD. Il s'agit d'un décimal.
	 * @return L'actuel nombre de points pour le type GOLD.
	 */
	@XmlElement
	public Double getGold() {
		return gold;
	}

	/**
	 *  Permet de modifier le nombre de points que rapporte une image de type GOLD. 
	 * @param gold Il s'agit d'un décimal.
	 */
	public void setGold(Double gold) {
		this.gold = gold;
	}

	/**
	 * Renvoie le chemin relatif vers l'image choisi pour le décor du jeu.
	 * @return L'actuel chemin relatif vers l'image de décor.
	 */
	@XmlElement
	public String getFondEcran() {
		return fondEcran;
	}

	/**
	 * Permet de modifier l'image de décor du jeu.
	 * @param fondEcran Le chemin relatif vers l'image de décor.
	 */
	public void setFondEcran(String fondEcran) {
		this.fondEcran = fondEcran;
	}

	/**
	 * Renvoie les listes des instructions par rapport aux objets à trouver dans un décor.
	 * Une instruction comporte l'objet à trouver, la consigne correspondante, ses coordonnées 
	 * pour sa position (abscisse et ordonnée en décimal), son type (compris entre "BRONZE",
	 *  "SILVER", "GOLD").
	 * @return la liste de toutes les instructions d'une partie de jeu.
	 * @see Instruction
	 */
	@XmlElementWrapper(name = "Instructions")
	@XmlElement(name = "Instruction")
	public ArrayList<Instruction> getListeInstructions() {
		return ListeInstructions;
	}

	/**
	 * Permet d'entrer et de modifier une liste d'instructions pour une partie de jeu.
	 * @param listeInstructions la liste de toutes les instructions d'une partie de jeu.
	 * @see Instruction
	 */
	public void setListeInstructions(ArrayList<Instruction> listeInstructions) {
		ListeInstructions = listeInstructions;
	}

	/**
	 * Transforme l'objet "Fouille" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * 
	 * @param fouille Classe "Fouille"
	 * @param nomFichier Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 */
	public void convertirJavaToXML(Fouille fouille, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Fouille.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(fouille, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Récupère un fichier xml et le transforme en objet Accueil.
	 * @param nomFichier Chemin vers le fichier xml.
	 * @return un objet Fouille
	 * @see Fouille
	 */
	public Fouille convertirXMLToJava(String nomFichier) {

		Fouille ac = new Fouille();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Fouille.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (Fouille) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}

}
