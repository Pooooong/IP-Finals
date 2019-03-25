import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.imageio.ImageIO;


public class Game extends Canvas implements Runnable {


	private Thread thread; 
 	private boolean running = false; 
 	private Player player;
 	private StartScreen startScreen;
 	protected MouseInput mouse;

 	public static int WIDTH, HEIGHT;

 	private BufferedImage background = null;
 	private Random rand = new Random();

 	private int loop = 0;
 	private int deadloop = 0;
 	private int pos;
 	protected boolean startGame = false;
 	protected SoundLoader sound;

 	Handler handler;
 	Camera cam;

 	private void init(){
 		
 		WIDTH = getWidth();
 		HEIGHT = getHeight();

		BufferedImageLoader loader = new BufferedImageLoader();
 		background = loader.loadImage("/background.JPG");
 		sound = new SoundLoader();
 		sound.loadSound("Game-Menu.wav");
 		sound.playBackGroundMusic();

 		handler = new Handler();

 		cam = new Camera(0, 29400, handler);

 		mouse = new MouseInput(handler);

 		this.addKeyListener(new KeyInput(handler));
 		this.addMouseListener(mouse);
 		this.addMouseMotionListener(mouse);
 		startScreen = new StartScreen(this, mouse);


 		handler.floor();
 	}

	public synchronized void start(){
		if(running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void run(){

		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
		
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick(){
	
		if(startGame == true){
			if(player== null){
				player = new Player(300, 29900, 48, 48, handler, ObjectId.Player);
				handler.add(player);
			}
			handler.tick();
			pos = player.getY();

			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ObjectId.Player){
					GameObject tempObject = handler.object.get(i);

					cam.tick(tempObject);
				
					if(loop == 45){
						loop = 0;
						int wid = rand.nextInt(250);
						int hei = rand.nextInt(250);
						int xPos = rand.nextInt(600);

						if(xPos > 59 && xPos < 686){

							if(wid >= 50 && hei >= 50){

								handler.add(new Block(xPos, tempObject.getY() - 1400, ObjectId.Block, wid, hei, handler));
							}
						}

					}else{
						loop++;
					}
				}

				if(player.getY() + player.height > cam.getY() + 630 ){
					player.died = true;
				}

				if(player.died == true){

						player = null;
						handler.object.clear();
						startGame = false;
						cam.setX(0);
						cam.setY(29400);
						cam.loop = 0;
						deadloop = 0;


			}

			}
			
		}else{
			startScreen.tick();
		}

		
	}

	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return; 
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		if(startGame == true){
			g2d.translate(-cam.getX(), -cam.getY());

			
			for(int i = 0; i < background.getHeight()*50; i+=background.getHeight()){
				g.drawImage(background, 0, i, 800, 600, this);
			}

			handler.render(g);
			handler.renderFloor(g);

			g2d.translate(cam.getX(), cam.getY());

		}else if(startGame == false){
			startScreen.render(g);

		}else if(player.died == true){

			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.setColor(Color.RED);
			g.drawString("YOU HAVE DIED", Game.WIDTH/2, pos);
		}

		g.dispose();
		bs.show();
	}

	public static void main(String args[]){
		new Window(800, 600, "2D Game", new Game());
	}
}