package slickGames.entite;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PuzzlePieceImg {

	float x,y,originX,originY;
	int correctPos;
	int width = 100;
	int height = 100;
	double speedX,speedY;
	Image sprite;
	
	public PuzzlePieceImg(int correctPos, Image sprite, float x, float y) throws SlickException{
		
		this.correctPos = correctPos;
		this.sprite = sprite;
		//this.width = sprite.getWidth();
		//this.height = sprite.getHeight();
		this.x = x;
		this.y = y;
		this.originX = x;
		this.originY = y;
		
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
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
	
	public int getCorrectPos(){
		return correctPos;
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
	
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public void setCorrectPos(int correctPos) {
		this.correctPos = correctPos;
	}
	
	public void move(int dt){
		x += speedX*dt;
		y += speedY*dt;	
	}
	
	public void die(){	
		//TODO
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//checkForCollision();
		move(delta);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
		sprite.draw(x, y, width, height);
		
	}
	
	public void resetPosition(){
		x = originX;
		y = originY;
	}
	
}
