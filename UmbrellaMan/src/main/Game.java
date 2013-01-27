package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
	SpriteSheet sheetGirl;
	Animation leftBoy, rightBoy;

	//Game pieces
	Girl girl;
	Boy boy;
	List<WaterDrop> drops;
	int dropCount = 0;
	int oldestDrop = 0;
	
	//Other game stuff
	long currentFrame;
	Random randomGenerator;
	boolean gameOver = false;
	
	
	public Game(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/img/background-sprite.png");
		sheetBoy = new SpriteSheet("res/img/spritesheetboy.png", 32, 64);
		sheetGirl = new SpriteSheet("res/img/spritesheetgirl.png", 16, 48);
		
		boy = new Boy();
		girl = new Girl();
		drops = new ArrayList<WaterDrop>();
		
		boy.initAnimation(sheetBoy);
		girl.initAnimation(sheetGirl);
		randomGenerator = new Random();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale((float)2.5, (float)2.5);
		g.drawImage(background, 0,0);
		g.drawAnimation(boy.getAnimation(), boy.getPosX(), boy.getPosY());
		g.drawAnimation(girl.getAnimation(), girl.getPosX(), girl.getPosY());
		
		for(WaterDrop drop: drops)
		{
			g.drawImage(drop.getImg(), drop.getPosX(), drop.getPosY());
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		currentFrame++;
		updateInput(gc);
		updateDrops();

		if(currentFrame%30==0)
			generateDrop();
		
	}

	private void updateDrops() {
		for(int i = 0;i<drops.size();i++)
		{
			WaterDrop drop = drops.get(i);
			if(drop.getState()>=60||hitUmbrella(drop.getPosX(),drop.getPosY()))
				drops.remove(drop);
		}
		for(WaterDrop drop : drops)
			drop.fall();
	}

	private boolean hitUmbrella(float x, float y) {
		return false;
	}

	private void generateDrop() throws SlickException {
		int xPosition = randomGenerator.nextInt(206);
		xPosition+=32;
		drops.add(new WaterDrop(xPosition));
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
