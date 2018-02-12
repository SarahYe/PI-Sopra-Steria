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

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nomFouille", "fondEcran", "silver", "bronze", "gold", "listeInstructions" })
public class Fouille {

	@XmlAttribute
	String nomFouille;
	Double silver;
	Double bronze;
	Double gold;
	String fondEcran;
	
	ArrayList<Instruction> ListeInstructions;
	
	public Fouille() {}

	public Fouille(String string, ArrayList<Instruction> listeInstructions, String fde, Double gold, Double bronze, Double silver) {
		this.nomFouille = nomFouille;
		this.bronze = bronze;
		this.gold = gold;
		this.silver = silver;
		this.fondEcran = fde;
		ListeInstructions = listeInstructions;
	}

	public String getNom() {
		return nomFouille;
	}
	
	public void setNomFouille(String nomFouille) {
		this.nomFouille = nomFouille;
	}

	@XmlElement
	public Double getSilver() {
		return silver;
	}

	public void setSilver(Double silver) {
		this.silver = silver;
	}

	@XmlElement
	public Double getBronze() {
		return bronze;
	}

	public void setBronze(Double bronze) {
		this.bronze = bronze;
	}

	@XmlElement
	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	@XmlElement
	public String getFondEcran() {
		return fondEcran;
	}

	public void setFondEcran(String fondEcran) {
		this.fondEcran = fondEcran;
	}

	@XmlElementWrapper(name = "Instructions")
	@XmlElement(name = "Instruction")
	public ArrayList<Instruction> getListeInstructions() {
		return ListeInstructions;
	}

	public void setListeInstructions(ArrayList<Instruction> listeInstructions) {
		ListeInstructions = listeInstructions;
	}

	public void convertirJavaToXML(Fouille instruction, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Fouille.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(instruction, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	

	public Fouille convertirXMLToJava(String nomFichier) {

		Fouille ac = new Fouille();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Fouille.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (Fouille) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}

}

