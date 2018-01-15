package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class TestParseur {

	public static void main(String[] args) {

		InputStream in;
		try {
			in = new FileInputStream("/Users/SarahYe/git/PI-Sopra-Steria/Ressources/Sons/Jouer.wav");
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Reponse reponse1 = new Reponse("rep 1 !", Boolean.FALSE, "justification 1");
		 * Reponse reponse2 = new Reponse("rep 2 !", Boolean.FALSE, "justification 2");
		 * Reponse reponse3 = new Reponse("rep 3 !", Boolean.TRUE, "justification 3");
		 * 
		 * ArrayList<Reponse> ListeReponses = new ArrayList<Reponse>();
		 * ListeReponses.add(reponse1); ListeReponses.add(reponse2);
		 * ListeReponses.add(reponse3);
		 * 
		 * Question question1 = new Question("Question 1 ?", ListeReponses);
		 * //System.out.println(question.toString());
		 * 
		 * Question question2 = new Question("Question 2 ?", ListeReponses);
		 * 
		 * ArrayList<Question> ListeQuestions = new ArrayList<Question>();
		 * ListeQuestions.add(question1); ListeQuestions.add(question2);
		 * 
		 * Quiz quiz = new Quiz ("Test",ListeQuestions);
		 * 
		 * //quiz.convertirJavaToXML(quiz, "FichiersDeConfig/quiz.xml");
		 * quiz.convertirXMLToJava("FichiersDeConfig/quiz.xml");
		 */
	}

}
