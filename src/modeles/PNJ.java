package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nomPNJ", "listeDialogues" })
public class PNJ {

	@XmlAttribute
	String nomPNJ;

	ArrayList<Dialogue> ListeDialogues;
	
	public PNJ() {}
	
	public PNJ(String nomPNJ, ArrayList<Dialogue> listeDialogues) {
		//super();
		this.nomPNJ = nomPNJ;
		ListeDialogues = listeDialogues;
	}

	public String getPNJ() {
		return nomPNJ;
	}

	public void setNomPNJ(String nomPNJ) {
		this.nomPNJ = nomPNJ;
	}

	@XmlElement(name = "Dialogue")
	public ArrayList<Dialogue> getListeDialogues() {
		return ListeDialogues;
	}

	public void setListeDialogues(ArrayList<Dialogue> listeDialogues) {
		ListeDialogues = listeDialogues;
	}

	public void convertirJavaToXML(PNJ dialogue, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(PNJ.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(dialogue, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	

	public PNJ convertirXMLToJava(String nomFichier) {

		PNJ ac = new PNJ();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Accueil.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (PNJ) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}

}
