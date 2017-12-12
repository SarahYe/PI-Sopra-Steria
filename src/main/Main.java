package main;

import java.util.ArrayList;

import objets.Question;
import objets.Quiz;
import objets.Reponse;

public class Main {
	
	 public static void main(String[] args) {
		 
		 Reponse reponse1 = new Reponse("rep 1 !", Boolean.FALSE, "justification 1");
		 Reponse reponse2 = new Reponse("rep 2 !", Boolean.FALSE, "justification 2");
		 Reponse reponse3 = new Reponse("rep 3 !", Boolean.TRUE, "justification 3");
		 
		 ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
		 ListeReponses.add(reponse1);
		 ListeReponses.add(reponse2);
		 ListeReponses.add(reponse3);
	                
	    Question question1 =  new Question("Question 1 ?", ListeReponses);
	    //System.out.println(question.toString());
	    
	    Question question2 =  new Question("Question 2 ?", ListeReponses);
	    
	    ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		 ListeQuestions.add(question1);
		 ListeQuestions.add(question2);
		 
		 Quiz quiz = new Quiz ("Test",ListeQuestions);
	    
		 quiz.convertirJavaToXML(quiz);
	  }
	     

}
