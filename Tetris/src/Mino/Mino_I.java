package Mino;

import java.awt.Color;

public class Mino_I extends mino{
	
	public Mino_I() {
		create(Color.RED);
	}
	
    public void setXY(int x, int y) {
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x - Blocks.SIZE;
		b[1].y = b[0].y;
		b[2].x = b[0].x + Blocks.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + Blocks.SIZE*2;
		b[3].y = b[0].y;
		
	}
	public void getDirection1() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x - Blocks.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x + Blocks.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + Blocks.SIZE*2;
		tempB[3].y = b[0].y;
		updateXY(1);
	}
	public void getDirection2() {
		
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Blocks.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y + Blocks.SIZE;
		tempB[3].x = b[0].x;
		tempB[3].y = b[0].y + Blocks.SIZE*2;
		updateXY(2);
	}
	public void getDirection3() {
		getDirection1();
	}
	public void getDirection4() {
		getDirection2();
	}
}
