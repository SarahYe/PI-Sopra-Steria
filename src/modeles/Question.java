package modeles;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import javafx.beans.property.SimpleStringProperty;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Quiz")
public class Question {
	
	String intituleQuestion;
    ArrayList<Reponse> ListeReponses;
    SimpleStringProperty intituleQuestionProperty;

    public Question () {
    	
    }
    
    public Question(String mName, ArrayList<Reponse> mReponses) {
    	this.intituleQuestion = mName;
        this.ListeReponses = mReponses;
        this.intituleQuestionProperty = new SimpleStringProperty(mName);
    }
	
    @XmlElement
    public String getIntituleQuestion() {
		return intituleQuestion;
	}
    
	public SimpleStringProperty getIntituleQuestionProperty() {
		return intituleQuestionProperty;
	}

	//@XmlElement
	public void setIntituleQuestion(String intituleQuestion) {
		this.intituleQuestion = intituleQuestion;
	}
	
	public void setIntituleQuestionProperty(String intituleQuestion) {
		this.intituleQuestionProperty.set(intituleQuestion);
	}
	
	@XmlElementWrapper(name = "Reponses")
	@XmlElement (name = "Reponse")
	public ArrayList<Reponse> getListeReponses() {
		return ListeReponses;
	}
	
	//@XmlElementWrapper(name = "Reponses")
	//@XmlElement (name = "Reponse")
	public void setListeReponses(ArrayList<Reponse> reponses) {
		this.ListeReponses = reponses;
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
		return  "Question : \n intitule = " + intituleQuestion + "\n Reponses = " + ListeReponses ;
	}
	
	

}
