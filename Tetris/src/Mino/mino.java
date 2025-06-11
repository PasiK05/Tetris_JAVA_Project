package Mino;

import java.awt.*;

import Default.KeyHandler;
import Default.TetrisGame;

public class mino {
	
	public Blocks b[] = new Blocks[4];
	public Blocks tempB[] = new Blocks[4];
	int autoDropCounter = 0;
	public int direction = 1;
	boolean LeftCollision, RightCollision, BottomCollision;
	public boolean active = true;
	public boolean deactivating;
	int deactivateCounter = 0;
	
	public void create(Color c) {
		b[0] = new Blocks(c);
		b[1] = new Blocks(c);
		b[2] = new Blocks(c);
		b[3] = new Blocks(c);
		tempB[0] = new Blocks(c);
		tempB[1] = new Blocks(c);
		tempB[2] = new Blocks(c);
		tempB[3] = new Blocks(c);
		
	}
	public void setXY(int x, int y) {}
	public void updateXY(int direction) {
		
		checkRotationCollision();
		if(LeftCollision == false && RightCollision == false && BottomCollision == false) {
			this.direction = direction;
			b[0].x = tempB[0].x;
			b[0].y = tempB[0].y;
			b[1].x = tempB[1].x;
			b[1].y = tempB[1].y;
			b[2].x = tempB[2].x;
			b[2].y = tempB[2].y;
			b[3].x = tempB[3].x;
			b[3].y = tempB[3].y;
		}
	}
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
	public void checkMovementCollision() {
		
		LeftCollision = false;
		RightCollision = false;
		BottomCollision = false;
		
		checkBlockCollision();
		
		for(int i = 0; i < b.length; i++) {
			if(b[i].x == TetrisGame.left_x) {
				LeftCollision = true;
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(b[i].x + Blocks.SIZE == TetrisGame.right_x) {
				RightCollision = true;
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(b[i].y + Blocks.SIZE == TetrisGame.bottom_y) {
				BottomCollision = true;
			}
		}
	}
	public void checkRotationCollision() {
		LeftCollision = false;
		RightCollision = false;
		BottomCollision = false;
		
		checkBlockCollision();
		
		for(int i = 0; i < b.length; i++) {
			if(tempB[i].x < TetrisGame.left_x) {
				LeftCollision = true;
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(tempB[i].x + Blocks.SIZE > TetrisGame.right_x) {
				RightCollision = true;
			}
		}
		for(int i = 0; i < b.length; i++) {
			if(tempB[i].y + Blocks.SIZE > TetrisGame.bottom_y) {
				BottomCollision = true;
			}
		}
	}
	
	private void checkBlockCollision() {
		
		for(int i = 0; i < TetrisGame.staticBlocks.size(); i++) {
			
			int targetX = TetrisGame.staticBlocks.get(i).x;
			int targetY = TetrisGame.staticBlocks.get(i).y;
			
			for(int j = 0; j < b.length; j++) {
				if(b[j].y + Blocks.SIZE == targetY && b[j].x == targetX) {
					BottomCollision = true;
				}
			}
			for(int j = 0; j < b.length; j++) {
				if(b[j].x - Blocks.SIZE == targetX && b[j].y == targetY) {
					LeftCollision = true;
				}
			}
			for(int j = 0; j < b.length; j++) {
				if(b[j].x + Blocks.SIZE == targetX && b[j].y == targetY) {
					RightCollision = true;
				}
			}
		}
	}
	
	public void update() {
		
		if(deactivating) {
			deactivating();
		}
		
		if(KeyHandler.UP) {
			switch(direction) {
			case 1: getDirection2();break;
			case 2: getDirection3();break;
			case 3: getDirection4();break;
			case 4: getDirection1();break;
			}
			KeyHandler.UP = false;
		
		}
		
		checkMovementCollision();
		
        if(KeyHandler.DOWN) {
			if(BottomCollision == false) {
				b[0].y += Blocks.SIZE;
	        	b[1].y += Blocks.SIZE;
	        	b[2].y += Blocks.SIZE;
	        	b[3].y += Blocks.SIZE;
	        		        	
	        	autoDropCounter = 0;
			}
			KeyHandler.DOWN = false;        	
        }
		if(KeyHandler.LEFT) {
			if(LeftCollision == false) {
				b[0].x -= Blocks.SIZE;
	        	b[1].x -= Blocks.SIZE;
	        	b[2].x -= Blocks.SIZE;
	        	b[3].x -= Blocks.SIZE;
			}        	
        	KeyHandler.LEFT = false;
		}
		if(KeyHandler.RIGHT) {
			if(RightCollision == false) {
				b[0].x += Blocks.SIZE;
	        	b[1].x += Blocks.SIZE;
	        	b[2].x += Blocks.SIZE;
	        	b[3].x += Blocks.SIZE;
			}        	
        	KeyHandler.RIGHT = false;
		}
		
		if(BottomCollision) {
			deactivating = true;
		}
		else {
			autoDropCounter++;
			if(autoDropCounter == TetrisGame.dropInterval) {
				
				b[0].y += Blocks.SIZE;
				b[1].y += Blocks.SIZE;
				b[2].y += Blocks.SIZE;
				b[3].y += Blocks.SIZE;
				autoDropCounter = 0;				
			}
		}
	}
	private void deactivating() {
		deactivateCounter++;
		
		if(deactivateCounter == 45) {
			deactivateCounter = 0;
			checkMovementCollision();
			
			if(BottomCollision) {
				active = false;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		int margin = 2;
		g2.setColor(b[0].c);
		g2.fillRect(b[0].x+margin, b[0].y+margin, Blocks.SIZE-(margin*2), Blocks.SIZE-(margin*2));
		g2.fillRect(b[1].x+margin, b[1].y+margin, Blocks.SIZE-(margin*2), Blocks.SIZE-(margin*2));
		g2.fillRect(b[2].x+margin, b[2].y+margin, Blocks.SIZE-(margin*2), Blocks.SIZE-(margin*2));
		g2.fillRect(b[3].x+margin, b[3].y+margin, Blocks.SIZE-(margin*2), Blocks.SIZE-(margin*2));
		
	}

}
