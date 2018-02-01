package modeles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Accueil")
public class FondEcran {

	String ImageVsCouleur;
	String couleur;
	String lienImage;
	
	public FondEcran() {}

	@XmlElement
	public String getImageVsCouleur() {
		return ImageVsCouleur;
	}

	public void setImageVsCouleur(String imageVsCouleur) {
		ImageVsCouleur = imageVsCouleur;
	}

	@XmlElement
	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	@XmlElement
	public String getLienImage() {
		return lienImage;
	}

	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}
	
	
}
