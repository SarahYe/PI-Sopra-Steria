package slickGames.entite;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import slickGames.MainOddWordOutGame;
import slickGames.states.OddWordOutGame;

public class EntiteReponse {

	float x,y;
	int width,height,direction;
	double speedX,speedY;
	boolean alreadyDead, movingByUser;
	Shape hitbox;
	Image sprite;
	String textReponse;
	
	public EntiteReponse(String textReponse, float x, float y) throws SlickException{
		this.textReponse = textReponse;
		this.x = x;
		this.y = y;
		this.direction = 0;
		this.sprite = new Image("./Ressources/Images/rectangleReponse2.png");
		this.alreadyDead = false;
		this.movingByUser = false;
		this.speedY=0.035;
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
	
	public boolean isAlreadyDead(){
		return alreadyDead;
	}
	
	public Shape getShape(){
		return hitbox;
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public String getTextReponse(){
		return textReponse;
	}
	
	public boolean isMovingByUser(){
		return movingByUser;
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

	public void setAlreadyDead(boolean alreadyDead) {
		this.alreadyDead = alreadyDead;
	}

	public void setShape(Shape hitbox){
		this.hitbox = hitbox;
	}
	
	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setTextReponse(String textReponse){
		this.textReponse = textReponse;
	}
	
	public void setMovingByUser(boolean movingByUser){
		this.movingByUser = movingByUser;
	}

	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;
		//hitbox.setLocation((float)x, (float)y);	
	}
		
	public void die(){	
		OddWordOutGame.reponses.remove(this);		
	}
	
	public void checkForCollision(){	
		if(x+width > MainOddWordOutGame.longueur || y+height > MainOddWordOutGame.hauteur || x < 0){
			die();
		}	
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		checkForCollision();
		move(delta);
		//if(alreadyDead) die();
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		width = g.getFont().getWidth(textReponse)+22;
		height = g.getFont().getHeight(textReponse)+15;
		sprite.draw(x-11, y-5, width, height);
		g.setColor(Color.white);
		g.drawString(textReponse,(float) x,(float) y);
		
	}
	
}
