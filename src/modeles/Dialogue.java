package modeles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.PNJ")
public class Dialogue {
	
	String imageVsCouleur;	
	String imageFondEcran;
	String couleurFondEcran;
	String intitulé;
	String imagePersonnage;

	public Dialogue() {
	}

	public Dialogue(String string, ArrayList<Dialogue> listeDialogues) {
	}
	
	public Dialogue(String imageVsCouleur, String imageFondEcran, String couleurFondEcran, String intitulé,
			String imagePersonnage) {
		//super();
		this.imageVsCouleur = imageVsCouleur;
		this.imageFondEcran = imageFondEcran;
		this.couleurFondEcran = couleurFondEcran;
		this.intitulé = intitulé;
		this.imagePersonnage = imagePersonnage;
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
	public String getIntitulé() {
		return intitulé;
	}

	public void setIntitulé(String intitulé) {
		this.intitulé = intitulé;
	}

	@XmlElement
	public String getImagePersonnage() {
		return imagePersonnage;
	}

	public void setImagePersonnage(String imagePersonnage) {
		this.imagePersonnage = imagePersonnage;
	}

}
