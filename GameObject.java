import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;


public abstract class GameObject{


	protected int x, y;
	protected int width, height;
	protected ObjectId id;
	protected int velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected int gravity = 1;
	protected final int MAX_SPEED = 10;
	protected boolean died = false;


	public GameObject(int x, int y, int width, int height, ObjectId id){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;

	}

	public GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void tick(LinkedList<GameObject> Object);
	public abstract void render(Graphics g); 
	public abstract Ellipse2D getBounds();
	public abstract Rectangle Bounds();

	public Rectangle bottomBounds(){
		return new Rectangle(x, y + height/2, width, height/2);
	}

	public Rectangle topBounds(){
		return new Rectangle(x, y, width, height/2);
	}

	public Rectangle leftBounds(){
		return new Rectangle(x, y + height/6, width/2, height - height/3);
	}

	public Rectangle rightBounds(){
		return new Rectangle(x + width/2, y + height/6, width/2, height - height/3);

	}

	public Rectangle deathBounds(){
		return new Rectangle(x, y + height - height/3, width, height/6);

	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public int getVelX(){
		return velX;
	}

	public int getVelY(){

		return velY;
	}

	public void setVelX(int velX){
		this.velX = velX;
	}

	public void setVelY(int velY){
		this.velY = velY;
	}

	public boolean isFalling(){
		return falling;
	}

	public void setFalling (boolean falling){
		this.falling = falling;
	}

	public boolean isJumping(){
		return jumping;
	}

	public void setJumping (boolean jumping){
		this.jumping = jumping;
	}
	
	public  ObjectId getId(){
		return id;
	}
}