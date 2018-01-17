package slickGames.entite;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import controleurs.QuizAccueilController;
import slickGames.MainOddWordOutGame;
import slickGames.states.OddWordOutGame;

public class EntiteReponse {

	float x,y;
	int width,height,direction;
	double speedX,speedY,deployDelay;
	boolean correct,deployed;
	long pastTime = 0;
	Image sprite;
	String textReponse;
	
	public EntiteReponse(String textReponse, Boolean correct, float x) throws SlickException{
		this.textReponse = textReponse;
		this.x = x;
		this.direction = 0;
		this.sprite = new Image("./Ressources/Images/rectangleReponse2.png");
		this.speedY = 0.035;
		this.correct = correct;
		this.deployed = false;
		Random rand = new Random();
		deployDelay = rand.nextInt(3)+2.5;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public double getSpeedX(){
		return speedX;
	}
	
	public double  getSpeedY(){
		return speedY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public String getTextReponse(){
		return textReponse;
	}
	
	public boolean isDeployed(){
		return deployed;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setDirection(int dirX) {
		this.direction = dirX;
	}

	public void setSpeedX(double d) {
		this.speedX = d;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setTextReponse(String textReponse){
		this.textReponse = textReponse;
	}
	
	public void setDeployed(boolean deployed){
		this.deployed = deployed;
	}

	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;	
	}
		
	public void die(){	
		OddWordOutGame.reponses.remove(this);		
	}
	
	public void checkForCollision(){	
		if(x+width > MainOddWordOutGame.longueur){
			if(this.correct){
				OddWordOutGame.jouerAudio("./Ressources/Sons/succes1.wav", -18.0f);
			} else {
				OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f);
			}
			die();
		}
		if(x < 0){
			if(!this.correct){
				OddWordOutGame.jouerAudio("./Ressources/Sons/succes1.wav", -18.0f);
			} else {
				OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f);
			}
			die();
		}
		if(y+height > MainOddWordOutGame.hauteur){
			OddWordOutGame.jouerAudio("./Ressources/Sons/echec1.wav", -18.0f);
			die();
		}		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
	}
	
	public boolean isReadyToDeploy(long delta) {
	    if(pastTime < deployDelay * 1000) {
	        pastTime += delta;
	        return false;
	    }else{
	        pastTime = 0;
	        return true;
	    }
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		width = g.getFont().getWidth(textReponse)+22;
		height = g.getFont().getHeight(textReponse)+15;
		sprite.draw(x-11, y-5, width, height);
		g.setColor(Color.white);
		g.drawString(textReponse,(float) x,(float) y);
		
	}
	
}
