package slickGames.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class OddWordOutExplanation extends BasicGameState {
	
	public static int ID = 1;
	
	protected StateBasedGame game;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		
		this.game = game;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(int key, char c) {

		if(key == Input.KEY_ENTER)
			game.enterState(OddWordOutGame.ID, new FadeOutTransition(),new FadeInTransition());
	}


	@Override
	public int getID() {
		return ID;
	}

}
