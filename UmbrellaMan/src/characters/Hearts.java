package characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import java.util.Random;

public class Hearts{
	protected Animation heartAnimation;
	int posY = 200;
	int posX;


	public void initAnimation(SpriteSheet sheet){
		heartAnimation = new Animation();
		heartAnimation.addFrame(sheet.getSprite(0, 0), 600);
		heartAnimation.addFrame(sheet.getSprite(0, 1), 600);
		heartAnimation.addFrame(sheet.getSprite(0, 2), 600);
		heartAnimation.setLooping(false);
	}


	public void setPosX(){
		int ranNum = new Random().nextInt(256);
		posX = ranNum;                  	
	}

	public int getPosY(){
		return posY;
	}

	public int getPosX(){
		return posX;
	}

	public Animation getAnimation(){
		return heartAnimation;
	}

}


