package Mino;

import java.awt.*;

public class Mino_J extends mino{
	
	public Mino_J() {
		create(Color.LIGHT_GRAY);
	}
	
public void setXY(int x, int y) {
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y - Blocks.SIZE;
		b[2].x = b[0].x;
		b[2].y = b[0].y + Blocks.SIZE;
		b[3].x = b[0].x - Blocks.SIZE;
		b[3].y = b[0].y + Blocks.SIZE;
		
	}
	public void getDirection1() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Blocks.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y + Blocks.SIZE;
		tempB[3].x = b[0].x - Blocks.SIZE;
		tempB[3].y = b[0].y + Blocks.SIZE;
		updateXY(1);
	}
	public void getDirection2() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x + Blocks.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x - Blocks.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x - Blocks.SIZE;
		tempB[3].y = b[0].y - Blocks.SIZE;
		updateXY(2);
	}
	public void getDirection3() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y + Blocks.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y - Blocks.SIZE;
		tempB[3].x = b[0].x + Blocks.SIZE;
		tempB[3].y = b[0].y - Blocks.SIZE;
		updateXY(3);
	}
	public void getDirection4() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x - Blocks.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x + Blocks.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + Blocks.SIZE;
		tempB[3].y = b[0].y + Blocks.SIZE;
		updateXY(4);
	}
	
}
