package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import com.game.Game;
import com.game.ID;

public class Bullet extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bulletID = 0;
	private double angle = 0; 
	private final int spd = 15 ;
	
	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform 	at = new AffineTransform(),
										gat = new AffineTransform();
	
	
	public Bullet(int x, int y, int originX, int originY, ID id, int bulletID) {
		super(x, y, originX, originY, id);
		this.bulletID = bulletID;
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
			Game.handler.removeObject(this);
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
		gat = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		gat.rotate(Math.toRadians(angle), x, y);
		gat.translate(x-(Game.tex.bullet[bulletID].getWidth()/2),y-(Game.tex.bullet[bulletID].getHeight()/2));
		g2d.drawImage(Game.tex.bullet[bulletID], gat, null);
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		//g2d.draw(path);
	}
	
		
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,6,6);
	}
	public Area getArea() {
		return area;
	}
}


