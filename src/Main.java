
import java.util.ArrayList;

public class Main {
	
	 public static void main(String[] args) {
		 
		 Réponse reponse1 = new Réponse("rep 1 !", Boolean.FALSE, "justification 1");
		 Réponse reponse2 = new Réponse("rep 2 !", Boolean.FALSE, "justification 2");
		 Réponse reponse3 = new Réponse("rep 3 !", Boolean.TRUE, "justification 3");
		 
		 ArrayList<Réponse> ListeRéponses = new ArrayList<Réponse>();
		 ListeRéponses.add(reponse1);
		 ListeRéponses.add(reponse2);
		 ListeRéponses.add(reponse3);
	                
	    Question question1 =  new Question("Question 1 ?", ListeRéponses);
	    //System.out.println(question.toString());
	    
	    Question question2 =  new Question("Question 2 ?", ListeRéponses);
	    
	    ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		 ListeQuestions.add(question1);
		 ListeQuestions.add(question2);
		 
		 Quiz quiz = new Quiz ("Test",ListeQuestions);
	    
		 quiz.convertirXMLToJava(quiz);
	  }
	     

}
