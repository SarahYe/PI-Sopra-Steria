package modeles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;

/**
 * Modèle d'un bloc "Jeu de fouille".
 * 
 * @author YESUFU Sarah
 * @version 1.0
 */

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Fouille")
public class Instruction {

	String imageObjet = "";
	Double posX;
	Double posY;
	String intitule;
	String type;

	public Instruction() {
	}

	/**
	 * Crée un nouvel objet Instruction représentant une instruction pour un objet à trouver dans
	 * le jeu de fouille. Une instruction comporte l'objet à trouver, la consigne correspondante, ses coordonnées 
	 * pour sa position (abscisse et ordonnée en décimal), son type (compris entre "BRONZE",
	 *  "SILVER", "GOLD").
	 *  
	 * @param intitule La consigne associée à l'objet.
	 * @param imageObjet Le chemin relatif vers l'image de l'objet. Format d'image : png ou jpg.
	 * @param posX L'abscisse de l'objet dans le décor.
	 * @param posY L'ordonnée de l'objet dans le décor.
	 * @param type Le type de l'objet ie GOLD, BRONZE ou SILVER.
	 */
	public Instruction(String intitule, String imageObjet, Double posX, Double posY, String type) {
		super();
		this.imageObjet = imageObjet;
		this.posX = posX;
		this.posY = posY;
		this.intitule = intitule;
		this.type = type;
	}

	/**
	 * Renvoie le chemin relatif vers l'image de l'objet.
	 * @return Le chemin relatif vers l'image.
	 */
	@XmlElement
	public String getImageObjet() {
		return imageObjet;
	}

	/**
	 * Permet d'indiquer le chemin relatif vers l'image d'un objet.
	 * @param imageObjet Format d'image : png ou jpg.
	 */
	public void setImageObjet(String imageObjet) {
		this.imageObjet = imageObjet;
	}

	/**
	 * Renvoie l'abscisse de l'objet placé.
	 * @return L'actuel abscisse de l'objet.
	 */
	@XmlElement
	public Double getPosX() {
		return posX;
	}

	/**
	 * Permet de modifier la position de l'objet selon l'axe X.
	 * @param posX Nouvel abscisse de l'objet.
	 */
	public void setPosX(Double posX) {
		this.posX = posX;
	}

	/**
	 * Renvoie l'ordonnée de l'objet placé.
	 * @return L'actuelle ordonnée de l'objet.
	 */
	@XmlElement
	public Double getPosY() {
		return posY;
	}

	/**
	 * Permet de modifier la position de l'objet selon l'axe Y.
	 * @param posY Nouvelle ordonnée de l'objet.
	 */
	public void setPosY(Double posY) {
		this.posY = posY;
	}

	/**
	 * Renvoie la consigne correspondante à une image.
	 * @return La consigne affiliée une image.
	 */
	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	/**
	 * Permet d'entrer ou de modifier une consigne.
	 * @param intitule 
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	/**
	 * Renvoie le type de points que rapporte de l'objet.
	 * @return GOLD, BRONZE ou SILVER.
	 */
	@XmlElement
	public String getType() {
		return type;
	}

	/**
	 * Permet d'entrer ou de modifier le type de points d'un image.
	 * @param type Valeurs possibles : GOLD, BRONZE, SILVER.
	 */
	public void setType(String type) {
		this.type = type;
	}

}
