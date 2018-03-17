package modeles;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Modèle d'un bloc "Accueil".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement
@XmlType(propOrder = { "titre", "fond" })
public class Accueil {

	Titre titre;
	FondEcran fond;

	public Accueil() {
	}

	/**
	 * @return L'actuel nom du jeu ou du serious game en forme de chemin relatif
	 *         vers une image ou texte formatté (texte + couleur (format hexadécimal
	 *         : 0x00000000) + taille + police).
	 * @see Titre
	 */
	@XmlElement(name = "Titre")
	public Titre getTitre() {
		return titre;
	}

	/**
	 * Le titre est le nom d'un jeu élémentaire paramétré ou du serious game en
	 * lui-même. Il est soit un chemin vers une image ou un texte formatté avec une
	 * couleur, une taille et une police.
	 * 
	 * @param titre.
	 */
	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	/**
	 * @return L'actuel fond d'écran utilisé sur l'accueil sous forme de lien vers
	 *         une image (format png ou jpg) ou de couleur unie (format hexadécimal
	 *         : 0x00000000).
	 * @see FondEcran
	 */
	@XmlElement(name = "FondEcran")
	public FondEcran getFond() {
		return fond;
	}

	/**
	 * Le fond d'écran de l'accueil est soit une image (format png ou jpg) ou une
	 * couleur unie.
	 * 
	 * @param fond
	 */
	public void setFond(FondEcran fond) {
		this.fond = fond;
	}

	/**
	 * Transforme l'objet "Accueil" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * 
	 * @param accueil
	 *            Classe Accueil.
	 * @param nomFichier
	 *            Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 */
	public void convertirJavaToXML(Accueil accueil, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Accueil.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(accueil, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère un fichier xml et le transforme en objet Accueil.
	 * 
	 * @param nomFichier
	 *            Chemin vers le fichier xml.
	 * @return l'objet "Accueil"
	 * @see Accueil
	 */
	public Accueil convertirXMLToJava(String nomFichier) {

		Accueil ac = new Accueil();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Accueil.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (Accueil) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}
}
