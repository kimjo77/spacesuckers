package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class SmallAsteroid extends Asteroid{

	private double rot=0;
	public int pullTime = 0;
	
	final static double MAX_SPEED = 8;	
	private Random rand = new Random();
	private Handler handler;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform();

	public SmallAsteroid(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id, handler);
		rot = rand.nextInt(360);
		super.spd = 6;
		pullTime = 20;
		
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	
	public void tick() {
		rot += 1;
		
		velX = Math.cos(Math.toRadians(dir)) * spd;
		velY = Math.sin(Math.toRadians(dir)) * spd;
		
		if(pullTime < 0) {
			spd -= 0.1;
			if(spd < 0) spd = 0;
			//dir = 0;
			x += velX + (int)pullX;
			y += velY + (int)pullY;
		} else {
			pullTime--;
			
			x += velX;
			y += velY;
		}
		collision();
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.draw(path);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,16,16);
	}

	public void collision() {
		
	}
	public Area getArea() {
		return area;
	}
}
