package modeles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Accueil")
public class Titre {
	
	String imageVsTexte;
	String lienImage;
	String texte;
	String couleurTexte;
	String policeTexte;

	public Titre() {}

	@XmlElement
	public String getImageVsTexte() {
		return imageVsTexte;
	}

	public void setImageVsTexte(String imageVsTexte) {
		this.imageVsTexte = imageVsTexte;
	}
	
	@XmlElement
	public String getLienImage() {
		return lienImage;
	}

	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}
	
	@XmlElement
	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	@XmlElement
	public String getCouleurTexte() {
		return couleurTexte;
	}

	public void setCouleurTexte(String couleurTexte) {
		this.couleurTexte = couleurTexte;
	}

	@XmlElement
	public String getPoliceTexte() {
		return policeTexte;
	}

	public void setPoliceTexte(String policeTexte) {
		this.policeTexte = policeTexte;
	}
	
	
}
