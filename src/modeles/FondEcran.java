package modeles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modèle du fond d'écran pour le bloc "Accueil".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Accueil")
public class FondEcran {

	String ImageVsCouleur;
	String couleur;
	String lienImage;

	public FondEcran() {
	}

	/**
	 * Indique si le fond d'écran est une image ou une couleur unie.
	 * 
	 * @return les mots "image" ou "couleur".
	 */
	@XmlElement
	public String getImageVsCouleur() {
		return ImageVsCouleur;
	}

	/**
	 * Permet d'indiquer si le fond d'écran de l'interface est une image ou un
	 * couleur.
	 * 
	 * @param imageVsCouleur
	 *            Valeurs possibles: "image" ou "couleur".
	 */
	public void setImageVsCouleur(String imageVsCouleur) {
		ImageVsCouleur = imageVsCouleur;
	}

	/**
	 *  Renvoie la couleur du fond d'écran.
	 * 
	 * @return actuel couleur du fond d'écran au format hexadécimal 0x00000000.
	 */
	@XmlElement
	public String getCouleur() {
		return couleur;
	}

	/**
	 * Permet d'indiquer la couleur de fond choisie . La couleur est sous la forme 0x00000000.
	 * 
	 * @param couleurFondEcran
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/**
	 * Renvoie le chemin relatif vers l'image pour le fond d'écran.
	 * @return Le chemin relatif de l'image.
	 */
	@XmlElement
	public String getLienImage() {
		return lienImage;
	}

	/**
	 * Permet de donner un image au fond d'écran. Les images acceptées sont des png ou des jpg;
	 * @param lienImage
	 */
	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

}
