package objets;
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
@XmlType(name = "nom", propOrder = {"nom","listeQuestions"})
public class Quiz {

	@XmlAttribute
	String nomQuiz;
	
	ArrayList<Question> ListeQuestions;
	
	public Quiz () {
		
	}
	
	public Quiz(String mNom, ArrayList<Question> listeQuestions) {
		this.nomQuiz = mNom;
		this.ListeQuestions = listeQuestions;
	}
	
	public String getNom() {
		return nomQuiz;
	}

	public void setNom(String mNom) {
		this.nomQuiz = mNom;
	}
	
	@XmlElement (name = "Question")
	public ArrayList<Question> getListeQuestions() {
		return ListeQuestions;
	}

	public void setListeQuestions(ArrayList<Question> listeQuestions) {
		ListeQuestions = listeQuestions;
	}
	
	public void convertirJavaToXML(Quiz quiz) {
		
		try {
    		// create JAXB context and initializing Marshaller
       JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
       Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
       
       // for getting nice formatted output
       jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
       
       File XMLfile = new File("Fichiers XML/quiz.xml");
       // Writing to XML file
       jaxbMarshaller.marshal(quiz, XMLfile);
       
       // Writing to console
       System.out.println("Fichier XML cree :\n");
       jaxbMarshaller.marshal(quiz, System.out);
       
      } catch (JAXBException e) {
    	  		e.printStackTrace();
      }
		
	}
	
public void convertirXMLToJava(Quiz quiz) {
	
	try {
		   JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
		   Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		   
		   File XMLfile = new File("Fichiers XML/quiz.xml");
		   // this will create Java object - quizz from the XML file
		   Quiz quizz = (Quiz) jaxbUnmarshaller.unmarshal(XMLfile);
		   
		   String nomQuiz = quizz.getNom();
		   System.out.println("Nom du quiz : " + nomQuiz);
		   
		   ArrayList<Question> listeQuestions = quizz.getListeQuestions();
		   int i = 0;
		   for(Question q : listeQuestions) {
			   i++;
			   System.out.println("Question " + i + " : " + q.getIntituleQuestion() + "\n");
			   
			   ArrayList<Reponse> listeReponses = q.getListeReponses();
			   int j = 0;
			   for(Reponse r : listeReponses) {
				   j++;
				   System.out.println("Reponse " + j + " : " + r.getIntitule() + " || "  + r.getCorrect() + " || " + r.getJustification() + "\n");
			   }
		   }
	  } catch (JAXBException e) {
		  e.printStackTrace();
	  }
	
	}
	
}
