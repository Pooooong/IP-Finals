import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {



	public Window(int w, int h, String title, Game game){
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		

		game.start();

	}
}