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
@XmlType(name = "nom", propOrder = { "nomPuzzle", "score_init", "decr_pts", "decr_sec", "score_min", "intitule",  "fragmentType", "listeFragments", "listeIndices" })
public class Puzzle2 {
	
	@XmlAttribute
	String nomPuzzle;
	Double score_init;
	Double decr_pts;
	Double decr_sec;
	Double score_min;
	String intitule;
	String fragmentType;
	ArrayList<String> listeIndices;
	ArrayList<String> listeFragments;
	
	public Puzzle2() {}
	
	public Puzzle2(String nomPuzzle, Double score_init, Double decr_pts, Double decr_sec, Double score_min,
			String intitule, String fragmentType, ArrayList<String> listeIndices, ArrayList<String> listeFragments) {
		this.nomPuzzle = nomPuzzle;
		this.score_init = score_init;
		this.decr_pts = decr_pts;
		this.decr_sec = decr_sec;
		this.score_min = score_min;
		this.intitule = intitule;
		this.fragmentType = fragmentType;
		this.listeIndices = listeIndices;
		this.listeFragments = listeFragments;
	}
	
	public String getNom() {
		return nomPuzzle;
	}
	
	public void setNomPuzzle(String nomPuzzle) {
		this.nomPuzzle = nomPuzzle;
	}
	
	@XmlElement
	public Double getScore_init() {
		return score_init;
	}
	
	public void setScore_init(Double score_init) {
		this.score_init = score_init;
	}
	
	@XmlElement
	public Double getDecr_pts() {
		return decr_pts;
	}
	
	public void setDecr_pts(Double decr_pts) {
		this.decr_pts = decr_pts;
	}
	
	@XmlElement
	public Double getDecr_sec() {
		return decr_sec;
	}
	
	public void setDecr_sec(Double decr_sec) {
		this.decr_sec = decr_sec;
	}
	
	@XmlElement
	public Double getScore_min() {
		return score_min;
	}
	
	public void setScore_min(Double score_min) {
		this.score_min = score_min;
	}
	
	@XmlElement
	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	@XmlElement
	public String getFragmentType() {
		return fragmentType;
	}
	
	public void setFragmentType(String fragmentType) {
		this.fragmentType = fragmentType;
	}
	
	@XmlElementWrapper(name = "Indices")
	@XmlElement(name = "Indice")
	public ArrayList<String> getListeIndices() {
		return listeIndices;
	}
	
	public void setListeIndices(ArrayList<String> listeIndices) {
		this.listeIndices = listeIndices;
	}
	
	@XmlElementWrapper(name = "Fragments")
	@XmlElement(name = "Fragment")
	public ArrayList<String> getListeFragments() {
		return listeFragments;
	}
	
	public void setListeFragments(ArrayList<String> listeFragments) {
		this.listeFragments = listeFragments;
	}
	
	public void convertirJavaToXML(Puzzle2 dialogue, String nomFichier) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Puzzle2.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File XMLfile = new File(nomFichier);
			jaxbMarshaller.marshal(dialogue, XMLfile);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	

	public Puzzle2 convertirXMLToJava(String nomFichier) {

		Puzzle2 ac = new Puzzle2();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PNJ.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			File XMLfile = new File(nomFichier);
			ac = (Puzzle2) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return ac;
	}
}
