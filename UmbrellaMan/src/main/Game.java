package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState{
	
	public Game(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image background = new Image("res/img/background-sprite.png");
		g.scale((float)2.5, (float)2.5);
		g.drawImage(background, 0,0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)){
			
		}else if(input.isKeyDown(Input.KEY_RIGHT)){
			
		} 
		
	}

	@Override
	public int getID() {
		return 1;
	}


}
