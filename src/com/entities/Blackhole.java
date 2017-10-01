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

public class Blackhole extends GameObject {
	private double pull=4, dx, dy, angle, dis, rot=0;
	
	private GameObject tempObject;
	private Player playerObject;
	private Asteroid asteroidObject;
	private Bullet bulletObject;
	private Coin coinObject;
	
	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform at = new AffineTransform();
	
	
	public Blackhole(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	public void tick() {
		rot += 0.5;
		
		for(int i=0; i<Game.handler.object.size(); i++) {
			tempObject = Game.handler.object.get(i);
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
		for(int i=0; i < Game.handler.object.size(); i++) {
			tempObject = Game.handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Asteroid) {
				asteroidObject = (Asteroid)tempObject;
				if(asteroidObject.getArea() != null) {
					tempArea.intersect(asteroidObject.getArea());
					if(!tempArea.isEmpty()) {
						Game.handler.removeObject(asteroidObject);
					}
				}	
			} else if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(playerObject.getArea() != null) {
				tempArea.intersect(playerObject.getArea());
					if(!tempArea.isEmpty()) {
						Game.handler.removeObject(playerObject);
					}
				}
			} else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				if(bulletObject.getArea() != null) {
					tempArea.intersect(bulletObject.getArea());
					if(!tempArea.isEmpty()) {
						Game.handler.removeObject(bulletObject);
					}
				}
			} else if(tempObject.getId() == ID.Coin) {
				coinObject = (Coin)tempObject;
				if(coinObject.getArea() != null) {
					tempArea.intersect(coinObject.getArea());
					if(!tempArea.isEmpty()) {
						Game.handler.removeObject(coinObject);
					}
				}
			}
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
