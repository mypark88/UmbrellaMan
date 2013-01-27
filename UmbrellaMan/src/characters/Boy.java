package characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Boy {

		protected Animation gameover, left, right;
		protected int posX = 128, posY = 144;
		boolean isFacingLeft = false, isGameover = false;
		
		int speed = 1;
		
		public Boy(){
			
		}
		
		public int getPosX(){
			return this.posX;
		}
		
		public int getPosY(){
			return this.posY;
		}
		
		public Animation getLeft(){
			return this.left;
		}
		
		public Animation getRight(){
			return this.right;
		}
		
		public void initAnimation(SpriteSheet sheet){
			left = new Animation();
			left.addFrame(sheet.getSprite(0, 1), 200);
			left.addFrame(sheet.getSprite(0, 0), 200);
			left.addFrame(sheet.getSprite(0, 1), 200);
			left.addFrame(sheet.getSprite(0, 2), 200);
			
			
			right = new Animation();
			right.addFrame(sheet.getSprite(1, 1), 200);
			right.addFrame(sheet.getSprite(1, 0), 200);
			right.addFrame(sheet.getSprite(1, 1), 200);
			right.addFrame(sheet.getSprite(1, 2), 200);
			
			gameover = new Animation();
			gameover.addFrame(sheet.getSprite(1, 3), 200);

		}

		public Animation getAnimation() {
			if(isGameover)
				return gameover;
			else if(isFacingLeft)
				return left;
			else
				return right;
		}

		public void move(int speed) {
			if(speed<0)
				isFacingLeft = true;
			else if (speed>0)
				isFacingLeft = false;
			
			this.posX+=speed;
		}
		
		
		public int getSpeed(){
			return speed;
		}
		
		public void setSpeed(int s){
			speed = s;
		}

		public void setGameover(boolean b) {
			this.isGameover = b;
		}
}
