import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class StartScreen{
	
	private Game game;
	private MouseInput mouseInput;
	private BufferedImage image;

	private String start = "Start";
	private String exit = "Exit";
	private String score = "HighScores";

	private Button startButton;
	private Button exitButton;
	private Button highScoresButton;


	public StartScreen(Game game, MouseInput mouse){

		this.game = game;
		this.mouseInput = mouse;

		BufferedImageLoader loader = new BufferedImageLoader();
 		image = loader.loadImage("/backgroundtitle.JPG");

		startButton = new Button(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 100);
		exitButton = new Button(Game.WIDTH/2 - 100, Game.HEIGHT/2 + 50, 200, 100);
		highScoresButton = new Button(Game.WIDTH/2 - 100, Game.HEIGHT/2  + 100, 200, 75);
		

	}

	public void tick(){


	}

	public void render(Graphics g){
		

		Graphics2D g2d = (Graphics2D)g;

		g.drawImage(image, 0, 0 ,800, 600, null);

		g.setColor(Color.ORANGE);
		g.setFont(new Font("Arial", Font.ITALIC, 45));
		g.drawString("Brick Dodge", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 180);

		//Buttons
		g.setColor(Color.PINK);
		g2d.fill(startButton.getButtonBounds());
		g2d.fill(exitButton.getButtonBounds());
		g.setColor(Color.RED);

		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);
		g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);

		if(startButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){

			g.setColor(Color.RED);
			g2d.fill(startButton.getButtonBounds());
			g.setColor(Color.PINK);
			g.drawString(start, startButton.getX() + 45, startButton.getY() + 50);

			if(mouseInput.isClicked == true){
				game.startGame = true;

			}
		}

		if(exitButton.getButtonBounds().contains(mouseInput.getX(), mouseInput.getY())){
			
			g.setColor(Color.RED);
			g2d.fill(exitButton.getButtonBounds());
			g.setColor(Color.PINK);
			g.drawString(exit, exitButton.getX() + 45, exitButton.getY() + 50);
			
			if(mouseInput.isClicked == true){
				System.exit(1);
			}
		}
	}

	}
