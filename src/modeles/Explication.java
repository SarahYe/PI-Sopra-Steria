package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Modèle d'un bloc "Page explicative".
 * @author YESUFU Sarah
 * @version 1.0
 */
@XmlRootElement
@XmlType(propOrder = { "titre", "contenu", "source", "listeLiens", "listeImages" })
public class Explication {

	String titre;
	String contenu;
	String source;
	ArrayList<String> listeLiens;
	ArrayList<String> listeImages;

	public Explication() {
	}

	/**
	 * Crée un nouvel objet "Explication" qui représente les données paramétrables du bloc "Page explicative".
	 *  
	 * @param mTitre Le titre/thème de l'explication.
	 * @param mContenu L'explication.
	 * @param mSource L'auteur.
	 * @param mLiens La liste d'hyperliens permettant d'illustrer l'explication.
	 * @param mImages La liste de 2 chemins relatifs vers des images décoratives de l'interface.
	 */
	public Explication(String mTitre, String mContenu, String mSource, ArrayList<String> mLiens,
			ArrayList<String> mImages) {
		this.titre = mTitre;
		this.contenu = mContenu;
		this.source = mSource;
		this.listeLiens = mLiens;
		this.listeImages = mImages;
	}

	/**
	 * Renvoie le titre.
	 * 
	 * @return actuel titre de l'explication.
	 */
	@XmlElement
	public String getTitre() {
		return titre;
	}

	/**
	 * Le titre est le thème de l'explication, le nom d'un jeu élémentaire paramétré ou du serious game en
	 * lui-même.
	 * @param titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Renvoie le texte d'explication.
	 * @return le texte explicatif.
	 */
	@XmlElement
	public String getContenu() {
		return contenu;
	}

	/**
	 * Le contenu est le texte explicatif lui-même.
	 * @param contenu
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * Renvoie l'auteur de l'explication.
	 * @return l'auteur
	 */
	@XmlElement
	public String getSource() {
		return source;
	}

	/**
	 * Permet d'indiquer l'auteur de l'explication.
	 * @param source
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Renvoie la liste des hyperliens.
	 * @return Une liste d'hyperliens.
	 */
	@XmlElementWrapper(name = "Liens")
	@XmlElement(name = "Lien")
	public ArrayList<String> getListeLiens() {
		return listeLiens;
	}
	
	/**
	 * La liste comprend des hyperliens consultables. 
	 * @param listeLiens
	 */
	public void setListeLiens(ArrayList<String> listeLiens) {
		this.listeLiens = listeLiens;
	}

	/**
	 * Renvoie la liste des chemins relatifs vers des images (format png ou jpg).
	 * @return liste de chemins relatifs vers des images.
	 */
	@XmlElementWrapper(name = "Images")
	@XmlElement(name = "Image")
	public ArrayList<String> getListeImages() {
		return listeImages;
	}

	/**
	 * La liste contient deux chemins relatifs correspondant aux chemins relatifs vers
	 * des images décoratives sur l'interface d'une page explicative. 
	 * @param listeImages
	 */
	public void setListeImages(ArrayList<String> listeImages) {
		this.listeImages = listeImages;
	}

	/**
	 * Transforme l'objet "Explication" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * @param explication
	 * 				Classe Explication.
	 * @param nomFichier
	 * 				Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 */
	public void convertirJavaToXML(Explication explication, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Explication.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(explication, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère un fichier xml et le transforme en objet Accueil.
	 * @param nomFichier Chemin vers le fichier xml.
	 * @return l'objet "Explication".
	 * @see Explication
	 */
	public Explication convertirXMLToJava(String nomFichier) {

		Explication explication = new Explication();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Explication.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			explication = (Explication) jaxbUnmarshaller.unmarshal(XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return explication;
	}

}
