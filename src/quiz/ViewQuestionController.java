package quiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import objets.Question;
import objets.Quiz;
import objets.Reponse;

public class ViewQuestionController implements Initializable{
	
	//a revoir comment transmettre l'objet quiz dans cette classe + le remplir pour test
	private Quiz quiz=new Quiz();
	
	
	
	
	@FXML
	private Label Justif1,Justif2,Justif3,Justif4,Justif5;
	
	
	
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	remplissageQuizPourTest();
		
		
		
	}
	
	private void remplissageQuizPourTest(){
		Reponse rep1 = new Reponse("rep 1 !", Boolean.TRUE, "Justif1");
		Reponse rep2 = new Reponse("rep 2 !", Boolean.FALSE, "Justif3");
		Reponse rep3 = new Reponse("rep 3 !", Boolean.FALSE, "Justif2");
		Reponse rep4 = new Reponse("rep 4 !", Boolean.FALSE, "Justif4");
		Reponse rep5 = new Reponse("rep 5 !", Boolean.FALSE, "Justif5");
		 
		ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		reponses.add(rep1);reponses.add(rep2);reponses.add(rep3);reponses.add(rep4);reponses.add(rep5);
	               
	   Question question1 =  new Question("Intitule question 1", reponses);
	   Question question2 =  new Question("Intitule question 1", reponses);
	   
	   ArrayList<Question> questions=new ArrayList<Question>();
	   
	}
	
	
	
	

}
