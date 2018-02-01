package modeles;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "titre", "fond"})
public class Accueil {
	
	Titre titre;
	FondEcran fond;
	
	public Accueil() {}
	
	@XmlElement(name = "Titre")
	public Titre getTitre() {
		return titre;
	}
	
	public void setTitre(Titre titre) {
		this.titre = titre;
	}
	
	@XmlElement(name = "FondEcran")
	public FondEcran getFond() {
		return fond;
	}
	
	public void setFond(FondEcran fond) {
		this.fond = fond;
	}

	public void convertirJavaToXML(Accueil ac, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Accueil.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(ac, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public Accueil convertirXMLToJava(String nomFichier) {

		Accueil ac = new Accueil();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Accueil.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (Accueil) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}
}
