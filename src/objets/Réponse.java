package objets;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Question")
public class Réponse {

	String intitulé;
	Boolean correct;
	String justification;
	
	public Réponse() {
		
	}
	
	public Réponse (String mIntitulé, Boolean mCorrect, String mJustification) {
		this.intitulé = mIntitulé;
		this.correct = mCorrect;
		this.justification = mJustification;
	}

	public String getIntitulé() {
		return intitulé;
	}
	
	public void setIntitulé(String intitulé) {
		this.intitulé = intitulé;
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
		return  "Réponse: \n intitulé = " + intitulé + "\n Correct = " + correct + "\n Justification = " + justification;
	}
}
