package modeles;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javafx.beans.property.SimpleStringProperty;

/**
 * Modèle d'un dialogue unitaire dans le bloc "Dialogue avec un personnage non
 * joueur".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.PNJ")
public class Dialogue {

	String imageVsCouleur = "";
	String imageFondEcran = "";
	String couleurFondEcran = "";
	String intitule;
	// SimpleStringProperty intituleProperty;
	String imagePersonnage;

	public Dialogue() {
	}
	/*
		*//**
			 * 
			 * @param string
			 * @param listeDialogues
			 *//*
				 * public Dialogue(String string, ArrayList<Dialogue> listeDialogues) { }
				 */

	/**
	 * Crée un nouvel objet Dialogue qui représente un dialogue unitaire dans le
	 * bloc "Dialogue avec un PNJ"
	 * 
	 * @param imageVsCouleur
	 *            L'indication d'une image ou d'une couleur pour le fond d'écran.
	 * @param imageFondEcran
	 *            Le chemin relatif vers l'image pour le fond d'écran (format png,
	 *            ou jpg).
	 * @param couleurFondEcran
	 *            La couleur du fond d'écran au format 0x00000000.
	 * @param intitule
	 *            Le texte du dialogue.
	 * @param imagePersonnage
	 *            Le chemin relatif vers l'image du personnage (format png, ou jpg).
	 */
	public Dialogue(String imageVsCouleur, String imageFondEcran, String couleurFondEcran, String intitule,
			String imagePersonnage) {
		// super()
		if (imageVsCouleur.contains("Couleur")) {
			this.couleurFondEcran = couleurFondEcran;
			this.imageFondEcran = "";
		} else {
			this.imageFondEcran = imageFondEcran;
			this.couleurFondEcran = "";
		}

		this.intitule = intitule;
		// this.intituleProperty = new SimpleStringProperty(intitule);
		this.imagePersonnage = imagePersonnage;
	}

	/*	*//**
			 * 
			 * @return
			 */
	/*
	 * public SimpleStringProperty getIntituleProperty() { return intituleProperty;
	 * }
	 * 
	 *//**
		 * 
		 * @param intituleProperty
		 *//*
			 * public void setIntituleProperty(SimpleStringProperty intituleProperty) {
			 * this.intituleProperty = intituleProperty; }
			 */

	/**
	 * Indique si le fond d'écran est une image ou une couleur unie.
	 * 
	 * @return les mots "image" ou "couleur".
	 */
	@XmlElement
	public String getImageVsCouleur() {
		return imageVsCouleur;
	}

	/**
	 * Permet d'indiquer si le fond d'écran de l'interface est une image ou un
	 * couleur.
	 * 
	 * @param imageVsCouleur
	 *            Valeurs possibles: "image" ou "couleur".
	 */
	public void setImageVsCouleur(String imageVsCouleur) {
		this.imageVsCouleur = imageVsCouleur;
	}

	/**
	 * Renvoie le chemin relatif vers l'image utilisée en fond d'écran
	 * 
	 * @return actuel chemin relatif vers l'image
	 */
	@XmlElement
	public String getImageFondEcran() {
		return imageFondEcran;
	}

	/**
	 * L'image de fond d'écran est utilisée en background de l'interface
	 * d'apparition du dialogue.
	 * 
	 * @param imageFondEcran
	 *            (format png ou jpg)
	 */
	public void setImageFondEcran(String imageFondEcran) {
		this.imageFondEcran = imageFondEcran;
	}

	/**
	 * Renvoie la couleur du fond d'écran.
	 * 
	 * @return actuel couleur du fond d'écran au format hexadécimal 0x00000000.
	 */
	@XmlElement
	public String getCouleurFondEcran() {
		return couleurFondEcran;
	}

	/**
	 * La couleur de l'image est choisie via un colorpicker.
	 * 
	 * @param couleurFondEcran
	 */
	public void setCouleurFondEcran(String couleurFondEcran) {
		this.couleurFondEcran = couleurFondEcran;
	}

	/**
	 * Renvoie le texte du dialogue.
	 * 
	 * @return le texte du dialogue.
	 */
	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	/**
	 * L'intitulé est le texte du dialogue.
	 * 
	 * @param intitule
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * Renvoie le chemin relatif vers l'image du personnage.
	 * 
	 * @return le chemin relatif de l'image (format png ou jpg)
	 */
	@XmlElement
	public String getImagePersonnage() {
		return imagePersonnage;
	}

	/**
	 * L'image du personnage est le chemin relatif vers le fichier png ou jpg du
	 * personnage.
	 * 
	 * @param imagePersonnage
	 */
	public void setImagePersonnage(String imagePersonnage) {
		this.imagePersonnage = imagePersonnage;
	}

}
