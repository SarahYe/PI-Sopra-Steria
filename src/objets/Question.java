package objets;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import javafx.beans.property.SimpleStringProperty;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Quiz")
public class Question {
	
	SimpleStringProperty intituleQuestion;
    ArrayList<Reponse> ListeReponses;

    public Question(String mName, ArrayList<Reponse> mReponses) {
        this.intituleQuestion = new SimpleStringProperty(mName);
        this.ListeReponses = mReponses;
    }
	
	@XmlElement
	public String getIntituleQuestion() {
		return intituleQuestion.get();
	}

	//@XmlElement
	public void setIntituleQuestion(String intutileQuestion) {
		this.intituleQuestion.set(intutileQuestion);
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
