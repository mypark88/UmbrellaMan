package characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Boy {

		private Animation front, left, right;
		private int posX = 128, posY = 144;
		boolean isFacingLeft = false;
		
		public Boy(){
			
		}
		
		public int getPosX(){
			return this.posX;
		}
		
		public int getPosY(){
			return this.posY;
		}
		
		public void initAnimation(SpriteSheet sheet){
			left = new Animation();
			left.addFrame(sheet.getSprite(0, 1), 200);
			left.addFrame(sheet.getSprite(0, 0), 200);
			left.addFrame(sheet.getSprite(0, 1), 200);
			left.addFrame(sheet.getSprite(0, 2), 200);
			left.setAutoUpdate(false);
			
			right = new Animation();
			right.addFrame(sheet.getSprite(1, 1), 200);
			right.addFrame(sheet.getSprite(1, 0), 200);
			right.addFrame(sheet.getSprite(1, 1), 200);
			right.addFrame(sheet.getSprite(1, 2), 200);
			right.setAutoUpdate(false);
		}

		public Animation getAnimation() {
			if(isFacingLeft)
				return left;
			else
				return right;
		}

		public void move(int speed) {
			if(speed<0)
				isFacingLeft = true;
			else
				isFacingLeft = false;
			
			this.posX+=speed;
			
		}
}
