import java.awt.Rectangle;

public class Camera {
	
	private int x, y;
	
	private Handler handler;
	protected int loop = 0;

	public Camera(int x, int y, Handler handler){
		
		this.x = x;
		this.y = y;
		this.handler = handler;
	}	

	public void tick(GameObject player){
		
		if(loop >= 500){			
		 	y --; 
		}else{
			loop++;
		}
		System.out.println(y);
	}
	public Rectangle cameraBounds(){
		return (new Rectangle(x, y, Game.WIDTH, Game.HEIGHT));
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

}