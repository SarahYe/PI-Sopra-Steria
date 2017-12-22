package quiz;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import objets.Question;
import objets.Quiz;
import objets.Reponse;

public class ViewQuestionController implements Initializable{
	
	//a revoir comment transmettre l'objet quiz dans cette classe + le remplir pour test
	private Quiz quiz=new Quiz();
	private Question question;
	
	
	
	//@FXML
	//private Button 
	@FXML
	private Label justif1,justif2,justif3,justif4,justif5;
	@FXML
	private CheckBox intiRep1,intiRep2,intiRep3,intiRep4,intiRep5;
	@FXML
	private Label intiQue;
	
	
	
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	remplissageQuizPourTest();
    	
		question=quiz.getListeQuestions().get(0);
		remplissageContentQuestion(question);
    	
	}
	
	private void remplissageContentQuestion(Question question){
		intiQue.setText(question.getIntituleQuestion());
		ArrayList<Reponse> reponses=question.getListeReponses();
		
		if(reponses.size()>0){
			intiRep1.setText(reponses.get(0).getIntitule());
			justif1.setText(reponses.get(0).getJustification());
		} else 
			intiRep1.setVisible(Boolean.FALSE);
		justif1.setVisible(Boolean.FALSE);
		
		if(reponses.size()>1){
			intiRep2.setText(reponses.get(1).getIntitule());
			justif2.setText(reponses.get(1).getJustification());
		} else
			intiRep2.setVisible(Boolean.FALSE);
		justif2.setVisible(Boolean.FALSE);
		
		if(reponses.size()>2){
			intiRep3.setText(reponses.get(2).getIntitule());
			justif3.setText(reponses.get(2).getJustification());
		} else
			intiRep3.setVisible(Boolean.FALSE);
		justif3.setVisible(Boolean.FALSE);
		
		if(reponses.size()>3){
			intiRep4.setText(reponses.get(3).getIntitule());
			justif4.setText(reponses.get(3).getJustification());
		} else
			intiRep4.setVisible(Boolean.FALSE);
		justif4.setVisible(Boolean.FALSE);
		
		if(reponses.size()>4){
			intiRep5.setText(reponses.get(4).getIntitule());
			justif5.setText(reponses.get(4).getJustification());
		} else
			intiRep5.setVisible(Boolean.FALSE);
		justif5.setVisible(Boolean.FALSE);
		
	}
	
	@FXML
	private void ClickButtonValider(ActionEvent event){
		int cmptTrue=0;
		boolean choice=Boolean.TRUE;
		for(Reponse reponse : question.getListeReponses()){
			if (reponse.getCorrect())
				cmptTrue++;
		}
		
		ArrayList<Reponse> reponses=question.getListeReponses();
		if(reponses.size()>0 && intiRep1.isSelected()){
			justif1.setVisible(Boolean.TRUE);
			if(!reponses.get(0).getCorrect())
				choice=Boolean.FALSE;
			else
				cmptTrue--;
		}
		
		if(reponses.size()>1 && intiRep2.isSelected()){
			justif2.setVisible(Boolean.TRUE);
			if(!reponses.get(1).getCorrect())
				choice=Boolean.FALSE;
			else
				cmptTrue--;
		}
		
		if(reponses.size()>2 && intiRep3.isSelected()){
			justif3.setVisible(Boolean.TRUE);
			if(!reponses.get(2).getCorrect())
				choice=Boolean.FALSE;
			else
				cmptTrue--;
		}
		
		if(reponses.size()>3 && intiRep4.isSelected()){
			justif4.setVisible(Boolean.TRUE);
			if(!reponses.get(3).getCorrect())
				choice=Boolean.FALSE;
			else
				cmptTrue--;
		}
		
		if(reponses.size()>4 && intiRep5.isSelected()){
			justif5.setVisible(Boolean.TRUE);
			if(!reponses.get(4).getCorrect())
				choice=Boolean.FALSE;
			else
				cmptTrue--;
		}
		
		
		
	}
	
	
	private void remplissageQuizPourTest(){
		Reponse rep1 = new Reponse("rep 1 !", Boolean.TRUE, "justif1");
		Reponse rep2 = new Reponse("rep 2 !", Boolean.FALSE, "justif3");
		Reponse rep3 = new Reponse("rep 3 !", Boolean.FALSE, "justif2");
		Reponse rep4 = new Reponse("rep 4 !", Boolean.FALSE, "justif4");
		Reponse rep5 = new Reponse("rep 5 !", Boolean.FALSE, "justif5");
		 
		ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		reponses.add(rep1);reponses.add(rep2);reponses.add(rep3);reponses.add(rep4);reponses.add(rep5);
	               
	   Question question1 =  new Question("Intitule question 1", reponses);
	   Question question2 =  new Question("Intitule question 1", reponses);
	   
	   ArrayList<Question> questions=new ArrayList<Question>();
	   questions.add(question1);questions.add(question1);
	   quiz.setListeQuestions(questions);
	   
	}
	

}
