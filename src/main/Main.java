package main;

import java.util.ArrayList;

import objets.Question;
import objets.Quiz;
import objets.R�ponse;

public class Main {
	
	 public static void main(String[] args) {
		 
		 R�ponse reponse1 = new R�ponse("rep 1 !", Boolean.FALSE, "justification 1");
		 R�ponse reponse2 = new R�ponse("rep 2 !", Boolean.FALSE, "justification 2");
		 R�ponse reponse3 = new R�ponse("rep 3 !", Boolean.TRUE, "justification 3");
		 
		 ArrayList<R�ponse> ListeR�ponses = new ArrayList<R�ponse>();
		 ListeR�ponses.add(reponse1);
		 ListeR�ponses.add(reponse2);
		 ListeR�ponses.add(reponse3);
	                
	    Question question1 =  new Question("Question 1 ?", ListeR�ponses);
	    //System.out.println(question.toString());
	    
	    Question question2 =  new Question("Question 2 ?", ListeR�ponses);
	    
	    ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		 ListeQuestions.add(question1);
		 ListeQuestions.add(question2);
		 
		 Quiz quiz = new Quiz ("Test",ListeQuestions);
	    
		 quiz.convertirXMLToJava(quiz);
	  }
	     

}
