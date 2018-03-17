package modeles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modèle d'un titre dans le bloc "Accueil".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Accueil")
public class Titre {

	String imageVsTexte;
	String lienImage;
	String texte;
	String couleurTexte;
	String policeTexte;
	String tailleTexte;

	public Titre() {
	}

	/**
	 * 
	 * @return les valeurs "Image" ou "Texte" selon que le titre soit une image ou
	 *         un texte formatté.
	 */
	@XmlElement
	public String getImageVsTexte() {
		return imageVsTexte;
	}

	/**
	 * Modifie le type de l'image d'une image à un texte formatté ou inversement.
	 * 
	 * @param imageVsTexte
	 */
	public void setImageVsTexte(String imageVsTexte) {
		this.imageVsTexte = imageVsTexte;
	}

	/**
	 * @return Le chemin relatif vers l'image utilisée pour le titre.
	 */
	@XmlElement
	public String getLienImage() {
		return lienImage;
	}

	/**
	 * Modifie l'image utilisée pour le titre par indication du chemin relatif vers
	 * une nouvelle image.
	 * 
	 * @param lienImage
	 */
	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

	/**
	 * 
	 * @return L'intitulé du titre dans le cas d'un titre en texte formatté.
	 */
	@XmlElement
	public String getTexte() {
		return texte;
	}

	/**
	 * Modifie l'intitulé du titre dans le cas d'un titre en texte formatté.
	 * 
	 * @param texte
	 */
	public void setTexte(String texte) {
		this.texte = texte;
	}

	/**
	 *
	 * @return La couleur du texte du titre dans le cas d'un titre en texte formatté
	 *         (format hexadécimal : 0x00000000).
	 */
	@XmlElement
	public String getCouleurTexte() {
		return couleurTexte;
	}

	/**
	 * Modifie la couleur du texte du titre dans le cas d'un titre en texte
	 * formatté.
	 * 
	 * @param couleurTexte
	 *            Format hexadécimal : 0x00000000.
	 */
	public void setCouleurTexte(String couleurTexte) {
		this.couleurTexte = couleurTexte;
	}

	/**
	 * @return La police du texte du titre dans le cas d'un titre en texte formatté.
	 */
	@XmlElement
	public String getPoliceTexte() {
		return policeTexte;
	}

	/**
	 * Modifie la police du texte du titre dans le cas d'un titre en texte formatté.
	 * 
	 * @param policeTexte
	 *            : Police au format TTF
	 */
	public void setPoliceTexte(String policeTexte) {
		this.policeTexte = policeTexte;
	}

	/**
	 * 
	 * @return La taille du texte du titre dans le cas d'un titre en texte formatté.
	 */
	@XmlElement
	public String getTaille() {
		return tailleTexte;
	}

	/**
	 * Modifie la taille du texte du titre dans le cas d'un titre en texte formatté.
	 * 
	 * @param taille
	 *            Chaîne de caractère constituée de caractères numériques
	 *            uniquement.
	 */
	public void setTaille(String taille) {
		this.tailleTexte = taille;
	}

}
