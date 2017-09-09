package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmallAsteroid extends Asteroid{

	final static double MAX_SPEED = 8;	
	private Handler handler;

	public SmallAsteroid(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id, handler);
		// TODO Auto-generated constructor stub
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		g.drawRect(x-originX, y-originY, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,16,16);
	}

	public void collision() {
		
	}
	
	public void destroy() {
		
	}

}
