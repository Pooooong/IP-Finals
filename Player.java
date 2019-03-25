import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.Font;

public class Player extends GameObject{
		

	private Handler handler;
	protected boolean died = false;
	private int loop = 0;
	private int pos;


	public Player(int x, int y,int width, int height, Handler handler, ObjectId id){
		super(x, y, width, height, id);
		this.handler = handler;

	}

	public Rectangle Bounds(){
		return new Rectangle(x, y, width, height);
	}

	public void tick(LinkedList<GameObject> object){
		x += velX;
		y += velY;

		if(falling || jumping){
			velY += gravity;

			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}

		Collision(object);
	}

	private void Collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.flooring.size(); i++){
			GameObject tempObject = handler.flooring.get(i);

			if(tempObject.getId()== ObjectId.Block){
				if(tempObject.Bounds().intersects(x,y, width, height)){
					velX = 0;
					velY = -5;
				}	
			}
		}

		for(int x = 0; x < handler.object.size(); x++){
			GameObject block = handler.object.get(x);
			
			if(block.getId()== ObjectId.Block){
				if(getBounds().intersects(block.topBounds())){
					velY = -5;
					velX = 0;
				}

				if(getBounds().intersects(block.leftBounds())){
					velY = 0;
					velX = -1;
				}

				if(getBounds().intersects(block.rightBounds())){
					velY = 0;
					velX = +1;
				}

				if(getBounds().intersects(block.bottomBounds())){
					velY = 5;
					velX= 0;
				}

				if(getBounds().intersects(block.deathBounds())){
						died = true;
				}

			}
			
		}

		if(getX() <=59){
			setX(59);
			setVelY(+1);
		}

		if(getX() >=686){
			setX(686);
			setVelY(+1);

		}
	}

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D)g;

			g.setColor(Color.pink);
			g2d.fill(getBounds());
			pos = getY();

	}

	public Ellipse2D getBounds(){
		return new Ellipse2D.Double (x, y, width, height);
	}
}

