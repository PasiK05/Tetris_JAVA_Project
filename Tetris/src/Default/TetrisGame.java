package Default;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import Mino.Blocks;
import Mino.Mino_I;
import Mino.Mino_J;
import Mino.Mino_L;
import Mino.Mino_O;
import Mino.Mino_S;
import Mino.Mino_T;
import Mino.Mino_Z;
import Mino.mino;

public class TetrisGame {
	final int WIDTH = 360;
	final int HEIGHT = 600;
	public static int left_x;
	public static int right_x;
	public static int top_y;
	public static int bottom_y;
	
	mino currentmino;
	final int MINO_START_X;
	final int MINO_START_Y;
	mino nextMino;
	final int NEXTMINO_X;
	final int NEXTMINO_Y;
	public static ArrayList<Blocks> staticBlocks = new ArrayList<>();
	
	public static int dropInterval = 60;
	boolean gameOver;
	
	int level = 1;
	int score;

	public TetrisGame() {
		
		//play area
		left_x = (GamePanel.SCREEN_WIDTH/2) - (WIDTH/2);
		right_x = left_x + WIDTH;
		top_y = 50;
		bottom_y = top_y + HEIGHT;
		
		MINO_START_X = left_x + (WIDTH/2) - Blocks.SIZE;
		MINO_START_Y = top_y + Blocks.SIZE;
		
		NEXTMINO_X = right_x + 175;
		NEXTMINO_Y = top_y + 500;
		
		currentmino = pickMino();
		currentmino.setXY(MINO_START_X, MINO_START_Y);
		nextMino = pickMino();
		nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
		
	}
	private mino pickMino() {
		
		//for random
		mino Mino = null;
		int i = new Random().nextInt(7);
		
		switch(i) {
		case 0: Mino = new Mino_L();break;
		case 1: Mino = new Mino_J();break;
		case 2: Mino = new Mino_O();break;
		case 3: Mino = new Mino_I();break;
		case 4: Mino = new Mino_Z();break;
		case 5: Mino = new Mino_S();break;
		case 6: Mino = new Mino_T();break;
		}
		return Mino;
	}

	public void update() {
		if(currentmino.active == false) {
			//if mino is inactive
			staticBlocks.add(currentmino.b[0]);
			staticBlocks.add(currentmino.b[1]);
			staticBlocks.add(currentmino.b[2]);
			staticBlocks.add(currentmino.b[3]);
			
			if(currentmino.b[0].x == MINO_START_X && currentmino.b[0].y == MINO_START_Y) {				
				gameOver = true;
			}
			
			currentmino.deactivating = false;
			
			//replacing
			currentmino = nextMino;
			currentmino.setXY(MINO_START_X, MINO_START_Y);
			nextMino = pickMino();
			nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
			
			checkRemove();
		}
		else {
			currentmino.update();
		}		
	}
	private void checkRemove() {
		
		int x = left_x;
		int y = top_y;
		int blockCount = 0;
		int lineCount = 0;
		int lines = 0;
		
		while(x < right_x && y < bottom_y) {
			
			for(int i = 0; i < staticBlocks.size(); i++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					blockCount++;
				}
			}
			x += Blocks.SIZE;
			
			if(x == right_x) {
				if(blockCount == 12) {
					for(int i = staticBlocks.size()-1; i > -1; i--) {
						if(staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
					}
					
					lineCount++;
					lines++;
					
					if(lines % 10 == 0 && dropInterval > 1) {
						level++;
						if(dropInterval > 10) {
							dropInterval -= 10;
						}
						else {
							dropInterval -= 1;
						}
					}
					
					for(int i = 0; i < staticBlocks.size(); i++) {
						if(staticBlocks.get(i).y < y) {
							staticBlocks.get(i).y += Blocks.SIZE;
						}
					}
				}
				
				blockCount = 0;
				x = left_x;
				y += Blocks.SIZE;
			}
		}
		if(lineCount > 0) {
			int singleLineScore = 10 * level;
			score += singleLineScore * lineCount;
		}
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4f));
		g2.drawRect(left_x-4, top_y-4, WIDTH+8, HEIGHT+8);
		
		int x = right_x + 100;
		int y = bottom_y - 200;
		g2.drawRect(x, y, 200, 200);
		g2.setFont(new Font("Arial", Font.PLAIN, 30));
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString("NEXT", x+60, y+40);
		
		g2.drawRect(x, top_y, 210, 200);
		x += 15;
		y = top_y + 80;
		g2.drawString("LEVEL : " + level, x, y); y += 70;
		g2.drawString("SCORE : " + score, x, y); y += 70;
		
		
		if(currentmino != null) {
			currentmino.draw(g2);
		}
		nextMino.draw(g2);
		
		for(int i = 0; i < staticBlocks.size(); i++) {
			staticBlocks.get(i).draw(g2);
		}
		
		g2.setColor(Color.magenta);
		g2.setFont(new Font("CANDELA", Font.PLAIN, 40));
		if(gameOver) {
			x = left_x + 60;
			y = top_y + 320;
			g2.drawString("GAME OVER", x, y);
		}
		if(KeyHandler.PAUSE) {
			x = left_x + 115;
			y = top_y + 320;
			g2.drawString("PAUSED", x, y);
		}
		x = 35;
		y = top_y + 320;
		g2.setColor(Color.cyan);
		g2.setFont(new Font("Times New Roman", Font.BOLD, 45));
		g2.drawString("CLASSIC TETRIS", x, y);
	}

}
