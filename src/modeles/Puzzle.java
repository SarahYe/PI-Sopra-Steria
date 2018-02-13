package modeles;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Puzzle")

public class Puzzle {

	String intitulePuzzle;
	ArrayList<String> ListePiecesImages;
	ArrayList<String> ListePiecesTextes;
	ArrayList<String> ListeIndices;

	public Puzzle() {

	}

	public Puzzle(String mName, ArrayList<String> mReponsesTxt, ArrayList<String> mReponsesImg, ArrayList<String> mIndices) {
		this.intitulePuzzle = mName;
		this.ListePiecesImages = mReponsesImg;
		this.ListePiecesTextes = mReponsesTxt;
		this.ListeIndices = mIndices;
	}

	@XmlElement
	public String getIntitulePuzzle() {
		return intitulePuzzle;
	}
	
	public void setIntitulePuzzle(String intitulePuzzle) {
		this.intitulePuzzle = intitulePuzzle;
	}

	public ArrayList<String> getListePiecesImages() {
		return ListePiecesImages;
	}

	public void setListePiecesImages(ArrayList<String> ListePiecesImages) {
		this.ListePiecesImages = ListePiecesImages;
	}
	
	public ArrayList<String> getListePiecesTextes() {
		return ListePiecesTextes;
	}

	public void setListePiecesTextes(ArrayList<String> ListePiecesTextes) {
		this.ListePiecesTextes = ListePiecesTextes;
	}
	
	public ArrayList<String> getListeIndices() {
		return ListeIndices;
	}

	public void setListeIndices(ArrayList<String> indices) {
		this.ListeIndices = indices;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	public void convertirJavaToXML(Puzzle puzzle, String nomFichier) {

		try {

			// create JAXB context and initializing Marshaller
			JAXBContext jaxbContext = JAXBContext.newInstance(Puzzle.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// for getting nice formatted output
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			// Writing to XML file
			jaxbMarshaller.marshal(puzzle, XMLfile);

			// Writing to console
			System.out.println("Fichier XML cree :\n");
			jaxbMarshaller.marshal(puzzle, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	public Puzzle convertirXMLToJava(String nomFichier) {

		Puzzle puzzle = new Puzzle();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Puzzle.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			// this will create Java object - puzzle from the XML file
			puzzle = (Puzzle) jaxbUnmarshaller.unmarshal(XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return puzzle;
	}

}
