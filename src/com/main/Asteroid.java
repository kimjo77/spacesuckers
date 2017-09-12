package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class Asteroid extends GameObject {
	
	public double dir, spd=0, pullX, pullY, rot;
	private Random rand = new Random();
	private Handler handler;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform();
	
	public Asteroid(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler=handler;
		rot = rand.nextInt(360);
		
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	public void tick() {
		//rot += 1;
		
		velX = Math.cos(Math.toRadians(dir)) * spd;
		velY = Math.sin(Math.toRadians(dir)) * spd;
		
		x += velX + (int)pullX;
		y += velY + (int)pullY;
		
		
		if(spd > Player.MAX_SPEED) {
			spd = Player.MAX_SPEED;
		} else if(spd < Player.MIN_SPEED) {
			spd = Player.MIN_SPEED;
		}
		
		collision();
	}

	public void collision() {
		GameObject tempObject;
		Player playerObject;
		Bullet bulletObject;
		Area tempArea;
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				tempArea.intersect(playerObject.getArea());
				if(!tempArea.isEmpty()) {
//					handler.removeObject(playerObject);
					playerObject.stunTime=60;
					handler.removeObject(this);
				}
			} else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				tempArea.intersect(bulletObject.getArea());
				if(!tempArea.isEmpty()) {
					handler.removeObject(bulletObject);
					generateAsteroidSmall(bulletObject);
					handler.removeObject(this);
					System.out.println("Deadz");
				}
			}
		}
		
	}
	
	public void generateAsteroidSmall(Bullet bullet) {
		SmallAsteroid sAsteroid;
		double bulletAngle = bullet.getAngle();
		int rotGap = 60;
		
		for(int i=0;i<2;i++) {
			sAsteroid = new SmallAsteroid(x,y,8,8,id,handler);
			rotGap *= -1;
			bulletAngle = bulletAngle + rotGap;
			sAsteroid.dir = bulletAngle;
			handler.addObject(sAsteroid);
		}
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
	
	public void setPullX(double pullX) {
		this.pullX=pullX;
	}
	public void setPullY(double pullY) {
		this.pullY=pullY;
	}
	
	public double getPullX() {
		return this.pullX;
	}
	public double getPullY() {
		return this.pullY;
	}
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,32,32);
	}
	public Area getArea() {
		return area;
	}
}
