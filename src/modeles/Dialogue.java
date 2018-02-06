package modeles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;


@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.PNJ")
public class Dialogue {
	
	String imageVsCouleur;	
	String imageFondEcran;
	String couleurFondEcran;
	String intitule;
	SimpleStringProperty intituleProperty;
	String imagePersonnage;

	public Dialogue() {
	}

	public Dialogue(String string, ArrayList<Dialogue> listeDialogues) {
	}
	
	public Dialogue(String imageVsCouleur, String imageFondEcran, String couleurFondEcran, String intitule,
			String imagePersonnage) {
		//super()
		if (imageVsCouleur.contains("Couleur")) {
			this.couleurFondEcran = couleurFondEcran;
		} else 
			this.imageFondEcran = imageFondEcran;
		
		this.intitule = intitule;
		this.intituleProperty = new SimpleStringProperty(intitule);
		this.imagePersonnage = imagePersonnage;
	}

	public SimpleStringProperty getIntituleProperty() {
		return intituleProperty;
	}

	public void setIntituleProperty(SimpleStringProperty intituleProperty) {
		this.intituleProperty = intituleProperty;
	}

	@XmlElement
	public String getImageVsCouleur() {
		return imageVsCouleur;
	}

	public void setImageVsCouleur(String imageVsCouleur) {
		this.imageVsCouleur = imageVsCouleur;
	}

	@XmlElement
	public String getImageFondEcran() {
		return imageFondEcran;
	}

	public void setImageFondEcran(String imageFondEcran) {
		this.imageFondEcran = imageFondEcran;
	}

	@XmlElement
	public String getCouleurFondEcran() {
		return couleurFondEcran;
	}

	public void setCouleurFondEcran(String couleurFondEcran) {
		this.couleurFondEcran = couleurFondEcran;
	}
	
	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitulé) {
		this.intitule = intitulé;
	}

	@XmlElement
	public String getImagePersonnage() {
		return imagePersonnage;
	}

	public void setImagePersonnage(String imagePersonnage) {
		this.imagePersonnage = imagePersonnage;
	}

}
