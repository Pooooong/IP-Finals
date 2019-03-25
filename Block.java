import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Block extends GameObject {

	private Handler handler;
	private int xxx = 255, yyy = 255, zzz = 255;
	private int loop = 0;
	private GameObject tempObject;
	private Block block[];
	private Random rand = new Random();


	public Block(int x, int y, ObjectId id, int width, int height, Handler handler){
		super(x, y, width, height, id);

		
		this.handler = handler;
		block = new Block[handler.flooring.size()];

		xxx = rand.nextInt(255);
		yyy = rand.nextInt(255);
		zzz = rand.nextInt(255);

		}


	public void tick(LinkedList<GameObject> object){

		getFloor();

		if(y + height < block[0].y){

		if(falling || jumping){
			y += 7;

			if(velY > MAX_SPEED - 5)
				velY = MAX_SPEED - 5;
		}
		}

		if(loop == 4){
			loop = 0;
			collision();
		}else{
			loop++;
		}
	}

	private void collision(){

		for(int i = 0; i < handler.flooring.size(); i++){
			for(int x = 0; x < handler.object.size(); x++){
				if(Bounds().intersects(handler.flooring.get(i).Bounds())){
					setY(handler.flooring.get(i).getY() - height);
				}

				if(bottomBounds().intersects(handler.object.get(x).topBounds())){
					if(handler.object.get(x) != this && handler.object.get(x).getId() != ObjectId.Player){
						setY(handler.object.get(x).getY() - height);
						falling = false;
					}
				}
			}

		}

		
	}

	private Block getFloor(){

		for(int i = 0; i <= handler.flooring.size(); i++){
			
			block[i] = handler.flooring.get(i);	
			
			return block[i];

		}
		return null;
	}

	public void render(Graphics g){
		
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(new Color(xxx, yyy, zzz));
		g2d.fill(Bounds());

		g.setColor(new Color(yyy, zzz, xxx));
		g2d.draw(Bounds());

		//g.setColor(Color.BLUE);
		//g2d.draw(topBounds());

		//g.setColor(Color.GREEN);
		//g2d.draw(leftBounds());

		//g.setColor(Color.YELLOW);
		//g2d.draw(rightBounds());

		//g2d.draw(deathBounds());

	}

	public Ellipse2D getBounds() {
		return new Ellipse2D.Double((int)x, (int)y, width, height); 
	}

	public Rectangle Bounds(){
		return new Rectangle(x, y, width, height);
	}

	public Rectangle bottomBounds(){
		return new Rectangle(x, y + height - height/9, width, height/9);
	}

	public Rectangle topBounds(){
		return new Rectangle(x, y, width, height/6);
	}

	public Rectangle leftBounds(){
		return new Rectangle(x, y + height/6, width/2, height - height/3);
	}

	public Rectangle rightBounds(){
		return new Rectangle(x + width/2, y + height/6, width/2, height - height/3);

	}

	public Rectangle deathBounds(){
		return new Rectangle(x + 20, y + height - height/3, width - 40, height/4);

	}
}