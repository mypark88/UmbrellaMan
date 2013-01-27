package characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WaterDrop {
	Image raindrop;
	Image rainplop;
	Image rainsplatter;
	int posX, posY=0, state = 0;
	

	public WaterDrop(int x) throws SlickException{
		raindrop = new Image("res/img/raindrop.png");
		rainplop = new Image("res/img/rainplop.png");
		rainsplatter = new Image("res/img/rainsplatter.png");
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
		else if(state<30)
			return rainplop;
		else return rainsplatter;
	}
	
	public int getState() {
		return state;
	}

	public void fall() {
		if(state==0){
			if(posY<200)
				++posY;
			else
			{
				++state;
				posY-=4;
			}	
		}
		else if(state<30)
		{
			state++;
		}
			
		else
		{
			state++;
		}
		
	}

}
