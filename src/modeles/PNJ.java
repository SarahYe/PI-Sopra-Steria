package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "nom", propOrder = { "nom", "listeQuestions" })
public class PNJ {

	@XmlAttribute
	String nomQuiz;

	ArrayList<Dialogue> ListeDialogues;
	
	public PNJ() {}
	
	public PNJ(String nomQuiz, ArrayList<Dialogue> listeDialogues) {
		//super();
		this.nomQuiz = nomQuiz;
		ListeDialogues = listeDialogues;
	}

	public String getNomQuiz() {
		return nomQuiz;
	}

	public void setNomQuiz(String nomQuiz) {
		this.nomQuiz = nomQuiz;
	}

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
