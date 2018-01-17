package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "titre", "contenu", "source", "listeLiens", "listeImages" })
public class Explication {

	String titre;
	String contenu;
	String source;
	ArrayList<String> listeLiens;
	ArrayList<String> listeImages;

	public Explication() {}

	public Explication(String mTitre, String mContenu, String mSource, ArrayList<String> mLiens, ArrayList<String> mImages) {
		this.titre = mTitre;
		this.contenu = mContenu;
		this.source = mSource;
		this.listeLiens = mLiens;
		this.listeImages = mImages;
	}

	@XmlElement
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@XmlElement
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@XmlElement
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@XmlElementWrapper(name = "Liens")
	@XmlElement(name = "Lien")
	public ArrayList<String> getListeLiens() {
		return listeLiens;
	}

	public void setListeLiens(ArrayList<String> listeLiens) {
		this.listeLiens = listeLiens;
	}
	
	@XmlElementWrapper(name = "Images")
	@XmlElement(name = "Image")
	public ArrayList<String> getListeImages() {
		return listeImages;
	}

	public void setListeImages(ArrayList<String> listeImages) {
		this.listeImages = listeImages;
	}

	public void convertirJavaToXML(Explication explication, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Explication.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(explication, XMLfile);

			// Writing to console
			//System.out.println("Fichier XML cree :\n");
			//jaxbMarshaller.marshal(explication, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Explication convertirXMLToJava(String nomFichier) {

		Explication explication = new Explication();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Explication.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			explication = (Explication) jaxbUnmarshaller.unmarshal(XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return explication;
	}

}
