package characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WaterDrop {
	Image raindrop;
	Image rainplop;
	int posX, posY=0, state = 0;
	

	public WaterDrop(int x) throws SlickException{
		raindrop = new Image("res/img/raindrop.png");
		rainplop = new Image("res/img/rainplop.png");
		posX = x;
	}
	
	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public Image getImg() {
		if(state==0)
			return raindrop;
		return rainplop;
	}
	
	public int getState() {
		return state;
	}

	public void fall() {
		if(posY<200)
			++posY;
		else
			++state;
		
	}

}
