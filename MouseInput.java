import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	protected int x, y;
	protected int width, height;

	protected int click;
	protected boolean isClicked = false;


	public MouseInput(Handler handler){
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e){

		click = e.getButton();
		isClicked = true;

	}

	public void mouseEntered(MouseEvent e){


	}

	public void mouseExited(MouseEvent e){

	}

	public void mouseMoved(MouseEvent e){

		x = e.getX();
		y = e.getY();
	}

	public void mouseReleased(MouseEvent e){
		isClicked = false;

	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

}