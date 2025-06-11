package Default;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	static final int UNIT_SIZE = 5;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	final int FPS = 60;
	Thread gameThread;
	TetrisGame tg = new TetrisGame();	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension (SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
		
		tg = new TetrisGame();
	}
    public void launchGame() {
    	gameThread = new Thread(this);
    	gameThread.start();
    }

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
		    lastTime = currentTime;
		    
		    if (delta >= 1) {
		    	update();
		    	repaint();
		    	delta--;
		    }
		}
	}
	private void update() {
		if(KeyHandler.PAUSE == false && tg.gameOver == false) {
			tg.update();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tg.draw(g2);
	}
}
