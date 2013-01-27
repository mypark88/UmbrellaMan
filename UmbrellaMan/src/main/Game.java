package main;

import java.util.ArrayList;
import java.util.List;

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
import characters.WaterDrop;

public class Game extends BasicGameState{
	//Images
	Image background;
	Image rainDrop;
	SpriteSheet sheetBoy;
	Animation leftBoy, rightBoy;

	//Game pieces
	Girl girl;
	Boy boy;
	List<WaterDrop> drops;
	
	//Other game stuff
	long currentFrame;

	
	
	public Game(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/img/background-sprite.png");
		sheetBoy = new SpriteSheet("res/img/spritesheetboy.png", 32, 64);
		
		boy = new Boy();
		girl = new Girl();
		drops = new ArrayList<WaterDrop>();
		
		boy.initAnimation(sheetBoy);

		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale((float)2.5, (float)2.5);
		g.drawImage(background, 0,0);
		g.drawAnimation(boy.getAnimation(), boy.getPosX(), boy.getPosY());
		
		for(WaterDrop drop: drops)
		{
			g.drawImage(drop.getImg(), drop.getPosX(), drop.getPosY());
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		currentFrame++;
		updateInput(gc);
		
		
	}

	private void updateInput(GameContainer gc) {
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
