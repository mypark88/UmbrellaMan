package characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WaterDrop {
	Image raindrop;
	Image rainplop;
	int posX, posY=0;

	public WaterDrop(int x) throws SlickException{
		raindrop = new Image("res/img/raindrop.png");
		posX = x;
	}
	
	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public Image getImg() {
		return raindrop;
	}

	public void fall() {
		++posY;
	}

}
