package slickGames;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class MainOddWordOutGame extends StateBasedGame{
	
	public static int longueur=800;
	public static int hauteur=580;
	
	public static void main(String[] args) throws SlickException {
		//Normalement c'est plus necessaire, c'est fait dans le setup du projet en theorie
		//System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new MainOddWordOutGame(),longueur, hauteur, false);
		app.setTargetFrameRate(60);
		app.setVSync(true);
		app.setShowFPS(true);
		app.start();
	}
	

	public MainOddWordOutGame() {
		super("Chassez les intrus !");
	}



	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		//addState(new OddWordOutExplanation());
		addState(new OddWordOutGame());
		
		this.enterState(OddWordOutGame.ID);
	}
}

