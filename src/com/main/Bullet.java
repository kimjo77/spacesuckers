package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

public class Bullet extends GameObject {

	private double angle = 0; 
	private final int spd = 15 ;
	private Handler handler;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform();
	
	public Bullet(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
		rect = this.getBounds();
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
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
	public double getAngle() {
		return angle;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.draw(path);
	}
	
		
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,6,6);
	}
	public Area getArea() {
		return area;
	}
}


