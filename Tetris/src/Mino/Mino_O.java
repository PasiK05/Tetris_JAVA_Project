package Mino;

import java.awt.Color;

public class Mino_O extends mino{
	
	public Mino_O() {
		create(Color.PINK);
	}
	
    public void setXY(int x, int y) {
		
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y + Blocks.SIZE;
		b[2].x = b[0].x + Blocks.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + Blocks.SIZE;
		b[3].y = b[0].y + Blocks.SIZE;
		
	}
	public void getDirection1() {}
	public void getDirection2() {}
	public void getDirection3() {}
	public void getDirection4() {}
}
