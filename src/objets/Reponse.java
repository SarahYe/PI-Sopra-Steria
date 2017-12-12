package objets;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Question")
public class Reponse {

	String intitule;
	Boolean correct;
	String justification;
	
	public Reponse() {
		
	}
	
	public Reponse (String mIntitule, Boolean mCorrect, String mJustification) {
		this.intitule = mIntitule;
		this.correct = mCorrect;
		this.justification = mJustification;
	}

	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	public Boolean getCorrect() {
		return correct;
	}
	
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	public String getJustification() {
		return justification;
	}
	
	public void setJustification(String justification) {
		this.justification = justification;
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
		return  "Reponse: \n intitule = " + intitule + "\n Correct = " + correct + "\n Justification = " + justification;
	}
}
