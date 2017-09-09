package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

private double angle; 
private final int spd = 15 ;
private Handler handler;
	
	
	public Bullet(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
	}

	public void tick() {
		
		velX = Math.cos(Math.toRadians(angle)) * spd;
		velY = Math.sin(Math.toRadians(angle)) * spd;
				
		x += (int)velX;
		y += (int)velY;	
		
		if( x<0 || x>Game.WIDTH || y<0 || y>Game.HEIGHT) {
			handler.removeObject(this);
		}
	}

	public void setAngle(double angle) {
		this.angle=angle;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect(x, y, 6, 6);
	}
		
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,6,6);
	}

	public void destroy() {
		
	}

}


