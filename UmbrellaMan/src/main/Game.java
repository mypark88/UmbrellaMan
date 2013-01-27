package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import characters.Boy;
import characters.Girl;

public class Game extends BasicGameState{
	
	Image girlImg;
	Image background;
	Girl girl;
	Boy boy;
	long currentFrame;
	SpriteSheet sheet;
	
	Animation left, right;
	
	
	public Game(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/img/background-sprite.png");
		sheet = new SpriteSheet("res/img/spritesheetboy.png", 32, 64);
		boy = new Boy();
		
		boy.initAnimation(sheet);

		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale((float)2.5, (float)2.5);
		g.drawImage(background, 0,0);
		g.drawAnimation(boy.getAnimation(), boy.getPosX(), boy.getPosY());
		
	
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		currentFrame++;
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_LEFT)){
			boy.move(-3);
			boy.getLeft().start();
			
		}else if(input.isKeyDown(Input.KEY_RIGHT)){
			boy.move(3);
			boy.getRight().start();
		}
		
	}

	@Override
	public int getID() {
		return 1;
	}


}
