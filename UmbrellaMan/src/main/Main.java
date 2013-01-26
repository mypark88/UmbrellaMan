package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{

	public static final String GAMENAME = "Umbrella Man";
	public static final int TITLE_STATE = 0;
	public static final int GAME_STATE = 1;
	
	public Main(String gamename) {
		super(gamename);
		this.addState(new Title(TITLE_STATE));
		this.addState(new Game(GAME_STATE));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(TITLE_STATE).init(gc, this);
		this.getState(GAME_STATE).init(gc, this);
		this.enterState(TITLE_STATE);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			AppGameContainer appgc = new AppGameContainer(new Main(GAMENAME));
			appgc.setDisplayMode(256, 244, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}



}
