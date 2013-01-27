package characters;

public class Girl {
	private int x = 128;
	private int y = 160;
	
	public Girl(){
		
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void move(int speed){
		x+=speed;
	}
	

}
