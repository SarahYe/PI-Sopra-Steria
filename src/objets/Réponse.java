package objets;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "org.arpit.javapostsforlearning.jaxb.Question")
public class R�ponse {

	String intitul�;
	Boolean correct;
	String justification;
	
	public R�ponse() {
		
	}
	
	public R�ponse (String mIntitul�, Boolean mCorrect, String mJustification) {
		this.intitul� = mIntitul�;
		this.correct = mCorrect;
		this.justification = mJustification;
	}

	public String getIntitul�() {
		return intitul�;
	}
	
	public void setIntitul�(String intitul�) {
		this.intitul� = intitul�;
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
		return  "R�ponse: \n intitul� = " + intitul� + "\n Correct = " + correct + "\n Justification = " + justification;
	}
}
