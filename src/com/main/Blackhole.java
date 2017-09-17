package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

public class Blackhole extends GameObject {
	private double pull=4, dx, dy, angle, dis, rot=0;
	private Handler handler;
	private GameObject tempObject;
	private Player playerObject;
	private Asteroid asteroidObject;
	private Bullet bulletObject;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform();
	
	
	public Blackhole(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	public void tick() {
		rot += 0.5;
		
		for(int i=0; i<handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Asteroid) {
				asteroidObject = (Asteroid)tempObject;
				dx = x-asteroidObject.getX();
				dy = y-asteroidObject.getY();
				//dis = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
				angle = Math.toDegrees(Math.atan2(dy,dx)); 
				velX = Math.cos(Math.toRadians(angle)) * pull;
				velY = Math.sin(Math.toRadians(angle)) * pull;
				asteroidObject.setPullX(velX);
				asteroidObject.setPullY(velY);
			} else if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				dx = x-playerObject.getX();
				dy = y-playerObject.getY();
				//dis = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
				angle = Math.toDegrees(Math.atan2(dy,dx)); 
				velX = Math.cos(Math.toRadians(angle)) * pull;
				velY = Math.sin(Math.toRadians(angle)) * pull;
				playerObject.setPullX(velX);
				playerObject.setPullY(velY);
			}
		}
		
		collision();
	}
	
	public void collision() {
		Area tempArea;
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Asteroid) {
				asteroidObject = (Asteroid)tempObject;
				tempArea.intersect(asteroidObject.getArea());
				if(!tempArea.isEmpty()) {
					handler.removeObject(asteroidObject);
				}
			} else if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				tempArea.intersect(playerObject.getArea());
				if(!tempArea.isEmpty()) {
					handler.removeObject(playerObject);
				}
			} /*else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				if(area.intersects(bulletObject.getBounds())) {
					handler.removeObject(bulletObject);
				}
			}*/
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		//g.fillRect(x-originX, y-originY, 32, 32);
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
		return new Rectangle(x-originX,y-originY,32,32);
	}
	public Area getArea() {
		return area;
	}
}
