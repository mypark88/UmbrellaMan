package characters;


public class Girl extends Boy{
	private int speed = 0;
	
	public Girl(){
		this.posY = 168;
		
	}

	public void move() {
		if(speed<0)
			isFacingLeft = true;
		else if (speed>0)
			isFacingLeft = false;

		this.posX+=speed;
		if(posX<32||posX>220)
			setSpeed(this.speed*-1);
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getSpeed(){
		return this.speed;
	}
}
