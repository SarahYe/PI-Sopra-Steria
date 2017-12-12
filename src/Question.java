import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Quiz")
public class Question {
	
	String intitul�Question;
	//String fond;
	ArrayList<R�ponse> ListeR�ponses;
	
	public Question () {
		
	}
	
	public Question (String mQuestion, ArrayList<R�ponse> mReponses) {
		this.intitul�Question = mQuestion;
		this.ListeR�ponses = mReponses;
	}
	
	@XmlElement
	public String getIntutil�Question() {
		return intitul�Question;
	}

	//@XmlElement
	public void setIntutil�Question(String intutil�Question) {
		this.intitul�Question = intutil�Question;
	}
	
	@XmlElementWrapper(name = "R�ponses")
	@XmlElement (name = "R�ponse")
	public ArrayList<R�ponse> getListeR�ponses() {
		return ListeR�ponses;
	}
	
	//@XmlElementWrapper(name = "R�ponses")
	//@XmlElement (name = "R�ponse")
	public void setListeR�ponses(ArrayList<R�ponse> r�ponses) {
		this.ListeR�ponses = r�ponses;
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
		return  "Question : \n intitul� = " + intitul�Question + "\n R�ponses = " + ListeR�ponses ;
	}
	
	

}
