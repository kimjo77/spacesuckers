package com.game;

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
	
	private Texture tex = Game.tex;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform(),
							gat = new AffineTransform();

	public SmallAsteroid(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
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
			x += velX + (int)pullX;
			y += velY + (int)pullY;
		} else {
			pullTime--;
			
			x += velX;
			y += velY;
		}
		collision();
	}
	
	public void collision(){
		GameObject tempObject;
		Player playerObject;
		Bullet bulletObject;
		Area tempArea;
		for(int i=0; i < Game.handler.object.size(); i++) {
			tempObject = Game.handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				tempArea.intersect(playerObject.getArea());
				if(!tempArea.isEmpty()) {
					playerObject.stunTime=30;
					Game.handler.removeObject(this);
				}
			} else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				tempArea.intersect(bulletObject.getArea());
				if(!tempArea.isEmpty()) {
					Game.handler.removeObject(bulletObject);
					Game.handler.removeObject(this);
					System.out.println("Tiny_Deadz");
				}
			}
		}
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		Graphics2D g2d = (Graphics2D) g;
		gat = new AffineTransform();
		at = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		gat.rotate(Math.toRadians(rot), x,y);
		gat.translate(x-(tex.asteroid[2].getWidth()/2),y-(tex.asteroid[2].getHeight()/2));
		
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.drawImage(tex.asteroid[2], gat, null);
		//g2d.draw(path);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,24,24);
	}

	public Area getArea() {
		return area;
	}
}
