package modeles;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Fouille")
public class Instruction {
		
	String imageObjet = "";
	Double posX;
	Double posY;
	String intitule;
	String type;

	public Instruction() {}
	
	public Instruction( String intitule, String imageObjet, Double posX, Double posY, String type) {
		super();
		this.imageObjet = imageObjet;
		this.posX = posX;
		this.posY = posY;
		this.intitule = intitule;
		this.type = type;
	}
	
	@XmlElement
	public String getImageObjet() {
		return imageObjet;
	}

	public void setImageObjet(String imageObjet) {
		this.imageObjet = imageObjet;
	}

	@XmlElement
	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}

	@XmlElement
	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}

	@XmlElement
	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
