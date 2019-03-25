import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public LinkedList<Block> flooring = new LinkedList<Block>();

	private GameObject tempObject; 

	public void tick(){
		for(int i = 0; i < object.size(); i++){
			
			tempObject = object.get(i);

			tempObject.tick(object);
		}
	}

	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void renderFloor(Graphics g){
		for(int i = 0; i < flooring.size(); i++){
			tempObject = flooring.get(i);

			tempObject.render(g);
		}
	}

	public void add(GameObject object){
		this.object.add(object);
	}

	public void removeObject(GameObject object){
		this.object.remove(object);
	}

	public void floor(){

		for(int i = 2; i < 23; i++){
			flooring.add(new Block(i*32, 29950, ObjectId.Block, 32, 32, this));
		}
	}
}