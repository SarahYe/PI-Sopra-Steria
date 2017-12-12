import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Quiz")
public class Question {
	
	String intituléQuestion;
	//String fond;
	ArrayList<Réponse> ListeRéponses;
	
	public Question () {
		
	}
	
	public Question (String mQuestion, ArrayList<Réponse> mReponses) {
		this.intituléQuestion = mQuestion;
		this.ListeRéponses = mReponses;
	}
	
	@XmlElement
	public String getIntutiléQuestion() {
		return intituléQuestion;
	}

	//@XmlElement
	public void setIntutiléQuestion(String intutiléQuestion) {
		this.intituléQuestion = intutiléQuestion;
	}
	
	@XmlElementWrapper(name = "Réponses")
	@XmlElement (name = "Réponse")
	public ArrayList<Réponse> getListeRéponses() {
		return ListeRéponses;
	}
	
	//@XmlElementWrapper(name = "Réponses")
	//@XmlElement (name = "Réponse")
	public void setListeRéponses(ArrayList<Réponse> réponses) {
		this.ListeRéponses = réponses;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  "Question : \n intitulé = " + intituléQuestion + "\n Réponses = " + ListeRéponses ;
	}
	
	

}
