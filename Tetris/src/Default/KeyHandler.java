package Default;

import java.awt.event.*;

public class KeyHandler implements KeyListener {
	
	public static boolean UP, DOWN, LEFT, RIGHT, PAUSE;

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) {
			UP = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			DOWN = true;
		}
		if(code == KeyEvent.VK_LEFT) {
			LEFT = true;
		}
		if(code == KeyEvent.VK_RIGHT) {
			RIGHT = true;			
		}
		if(code == KeyEvent.VK_SPACE) {
			if(PAUSE) {
				PAUSE = false;
			}
			else {
				PAUSE = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
		

}
