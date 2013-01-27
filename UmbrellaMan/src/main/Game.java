package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import characters.Boy;
import characters.Girl;
import characters.Hearts;
import characters.WaterDrop;

public class Game extends BasicGameState{
	//Images and others
	Image background;
	Image rainDrop;
	SpriteSheet sheetBoy;
	SpriteSheet sheetGirl, sheetHeart;
	Animation leftBoy, rightBoy;
	Music music;
	Sound drip;
	Sound gameOver;

	//Game pieces
	Girl girl;
	Boy boy;
	List<WaterDrop> drops;
	int dropCount = 0;
	int oldestDrop = 0;

	List<Hearts> listOfHearts;
	List<Hearts> renderHearts = new ArrayList<Hearts>();

	//Other game stuff
	long currentFrame;
	Random randomGenerator;
	boolean gameover = false;
	int heartCount = 0;
	int heartsCollected = 0;


	public Game(int state){

	}

	public List<Hearts> getCurrentHearts(){
		return renderHearts;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/img/background-sprite.png");
		sheetBoy = new SpriteSheet("res/img/spritesheetboy.png", 32, 64);
		sheetGirl = new SpriteSheet("res/img/spritesheetgirl.png", 16, 48);
		sheetHeart = new SpriteSheet("res/img/hearts.png", 8, 8);

		boy = new Boy();
		girl = new Girl();
		drops = new ArrayList<WaterDrop>();
		listOfHearts = new ArrayList<Hearts>();

		boy.initAnimation(sheetBoy);
		girl.initAnimation(sheetGirl);
		randomGenerator = new Random();

		for(int i=0;i<20;i++){
			Hearts h = new Hearts();
			h.setPosX();
			h.initAnimation(sheetHeart);
			listOfHearts.add(h);
		}
		
		music = new Music("res/sounds/gyromite.wav");
		drip = new Sound("res/sounds/waterdrop.wav");
		gameOver = new Sound("res/sounds/gameover.wav");



	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.scale((float)2.5, (float)2.5);
		g.drawImage(background, 0,0);
		g.drawString("CurrentFrame: "+girl.getSpeed(),10,10);
		for(WaterDrop drop: drops)
		{
			g.drawImage(drop.getImg(), drop.getPosX(), drop.getPosY());
		}
		for(Hearts hearts: renderHearts){
			g.drawAnimation(hearts.getAnimation(), hearts.getPosX(), hearts.getPosY());
		}
		g.drawAnimation(boy.getAnimation(), boy.getPosX(), boy.getPosY());
		g.drawAnimation(girl.getAnimation(), girl.getPosX(), girl.getPosY());



	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		currentFrame++;
		if(currentFrame==1)
			music.loop(1, (float) 0.25);
		if(gameover)
		{
			music.stop();
			gameOver.play();
			try{
				Thread.sleep(4000);
			}catch(Exception e){

			}
			sbg.pauseUpdate();
			//sbg.enterState(0);
		}


		updateInput(gc);
		updateDrops();
		girl.move();
		isHeartCollected();

		if(currentFrame%30==0)
		{
			generateDrop();
			changeGirlSpeed(9);
		}

		if(currentFrame%100==0){
			renderHearts.add(listOfHearts.get(heartCount));
			heartCount++;
		}
	}

	private void changeGirlSpeed(int difficulty) {
		int speed = randomGenerator.nextInt(difficulty);

		girl.setSpeed(speed-(difficulty/2));
	}



	private void updateDrops() {
		for(int i = 0;i<drops.size();i++)
		{
			WaterDrop drop = drops.get(i);

			if(isGirlHit(drop.getPosX(),drop.getPosY())&&drop.getState()==0)
			{
				gameover = true;
				boy.setGameover(true);
				girl.setGameover(true);
				break;
			}

			boolean hit = hitUmbrella(drop.getPosX(),drop.getPosY());
			if(hit)
				drip.play();

			if(drop.getState()>=60||hit)
			{
				drops.remove(drop);
			}
		}
		for(WaterDrop drop : drops)
		{
			drop.fall();
			if(drop.getPosY()==199)
				drip.play();
		}
	}

	private boolean isGirlHit(float x, float y) {

		return  girl.getPosX() < x+7 && 
				girl.getPosX()+16 > x && 
				girl.getPosY() < y; 
	}

	private boolean hitUmbrella(float x, float y) {

		return boy.getPosX() < x+8 && 
				boy.getPosX()+32 > x && 
				boy.getPosY() < y+8 &&
				boy.getPosY()+32 > y;
	}

	private void generateDrop() throws SlickException {
		int xPosition = randomGenerator.nextInt(206);
		xPosition+=32;
		drops.add(new WaterDrop(xPosition));
	}

	private void updateInput(GameContainer gc) {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_LEFT)){
			boy.move(-boy.getSpeed());
			boy.getLeft().start();

		}else if(input.isKeyDown(Input.KEY_RIGHT)){
			boy.move(boy.getSpeed());
			boy.getRight().start();
		}
		recycleHearts(gc);
	}

	@Override
	public int getID() {
		return 1;
	}

	public boolean isHeartCollected(){
		if(!renderHearts.isEmpty()){
			for(Hearts heart : renderHearts){
				int heartX = heart.getPosX();
				int boyX = boy.getPosX();
				System.out.println("heartX: "+ heartX);
				System.out.println("BoyX: "+ boyX );
				if((heartX< boyX && heartX > boyX) || (heartX+10 >boyX && heartX <boyX)){
					System.out.println("Heart is same pos as boy");
					renderHearts.remove(heart);
					heartsCollected++;
					return true;
				} 
			}
		}
		return false;
	}


	public void increaseSpeed(){
		int speed = boy.getSpeed();
		speed = speed * 2;
		boy.setSpeed(speed);	
	}

	public void recycleHearts(GameContainer gc){
		Input input = gc.getInput();
		if(heartsCollected > 3){
			if(boy.getPosX() < 16 && input.isKeyPressed(Input.KEY_SPACE)){
				heartsCollected = 0;
				increaseSpeed();
			}
		}
	}

}
