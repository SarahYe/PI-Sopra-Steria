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
 * Modèle d'un bloc de dialogue avec un personnage non joueur.
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */
@XmlRootElement
@XmlType(name = "nom", propOrder = { "nomPNJ", "listeDialogues" })
public class PNJ {

	@XmlAttribute
	String nomPNJ;

	ArrayList<Dialogue> ListeDialogues;

	public PNJ() {
	}

	/**
	 * Crée un nouvel objet PNJ représentant un module de Dialogue avec un personnage non joueur.
	 * L'objet contient les éléments paramétrables du module à savoir le nom du module ainsi que la
	 * liste des dialogues. 
	 * @param nomPNJ Le nom du module si l'utilisateur souhaite le personnaliser.
	 * @param listeDialogues La liste des dialogues.
	 */
	public PNJ(String nomPNJ, ArrayList<Dialogue> listeDialogues) {
		this.nomPNJ = nomPNJ;
		ListeDialogues = listeDialogues;
	}

	/**
	 * @return le nom du module.
	 */
	public String getPNJ() {
		return nomPNJ;
	}

	/**
	 * Permet d'entrer ou de modifier le nom du module pour une personnalisation.
	 * @param nomPNJ
	 */
	public void setNomPNJ(String nomPNJ) {
		this.nomPNJ = nomPNJ;
	}

	/**
	 * @return la liste des dialogues paramétrés
	 * @see Dialogue
	 */
	@XmlElementWrapper(name = "Dialogues")
	@XmlElement(name = "Dialogue")
	public ArrayList<Dialogue> getListeDialogues() {
		return ListeDialogues;
	}

	/**
	 * La liste des dialogues est l'ensemble comprenant le texte, le personnage à afficher,
	 * le fond d'écran pour chacun des dialogues.
	 * @param listeDialogues
	 * @see Dialogue
	 */
	public void setListeDialogues(ArrayList<Dialogue> listeDialogues) {
		ListeDialogues = listeDialogues;
	}

	/**
	 * Transforme l'objet "PNJ" en XML puis l'écrit dans un fichier avec la même
	 * extension.
	 * 
	 * @param dialogue Classe Dialogue
	 * @param nomFichier  Chemin vers le fichier xml d'écriture. Le fichier porte déjà
	 *            l'extension.
	 */
	public void convertirJavaToXML(PNJ dialogue, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(PNJ.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(dialogue, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 *  Récupère un fichier xml et le transforme en objet Accueil.
	 * @param nomFichier Chemin vers le fichier xml.
	 * @return l'objet "PNJ".
	 * @see PNJ
	 */
	public PNJ convertirXMLToJava(String nomFichier) {

		PNJ ac = new PNJ();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PNJ.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (PNJ) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}

}
