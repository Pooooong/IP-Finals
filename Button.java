import java.awt.Rectangle;

public class Button{
	
	private int x, y;
	private int width, height;

	public Button(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;


	}

	public Rectangle getButtonBounds(){
		return (new Rectangle(x, y, width, height));
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
}