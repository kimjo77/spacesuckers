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
	private double pull=4, dx, dy, angle, dis, rot=0,spiral_angle=0;
	
	private GameObject tempObject;
	private Player playerObject;
	private Asteroid asteroidObject;
	private Bullet bulletObject;
	
	private int spiralFrame = 0,
				max_spiralFrame = 13,
				spiral_imagespeed = 60/8,
				spiral_frametime = 0,
				
				/*blackholeFrame = 0,
				max_blackholeFrame = 13,
				blackhole_imagespeed = 60/4,
				blackhole_frametime = 0,*/
				
				pullFrame = 0,
				max_pullFrame = 28,
				pull_imagespeed = 60/12,
				pull_frametime = 0;
	
	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform 	at = new AffineTransform(),
										gat = new AffineTransform();
	
	
	public Blackhole(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	public void tick() {
		rot -= 0.5;
		
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
		
		if(spiral_frametime < 0) {
			spiral_frametime = spiral_imagespeed;
			if(spiralFrame+1 == max_spiralFrame) {
				spiralFrame = 0;
			}else {
				spiralFrame++;
			}
		}else {
			spiral_frametime--;
		}
		
		/*if(blackhole_frametime < 0) {
			blackhole_frametime = blackhole_imagespeed;
			if(blackholeFrame+1 == max_blackholeFrame) {
				blackholeFrame = 0;
			}else {
				blackholeFrame++;
			}
		}else {
			blackhole_frametime--;
		}*/
		
		if(pull_frametime < 0) {
			pull_frametime = pull_imagespeed;
			if(pullFrame+1 == max_pullFrame) {
				pullFrame = 0;
			}else {
				pullFrame++;
			}
		}else {
			pull_frametime--;
		}
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
			} 
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		//g.fillRect(x-originX, y-originY, 32, 32);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		path = new GeneralPath();
		gat = new AffineTransform();
		
		rect = this.getBounds();
		
		/*gat.rotate(Math.toRadians(rot), x, y);
		gat.translate(x-(Game.tex.spiral[spiralFrame].getWidth()/2),y-(Game.tex.spiral[spiralFrame].getHeight()/2));
		g2d.drawImage(Game.tex.spiral[spiralFrame], gat, null);*/
		
		gat = new AffineTransform();
		gat.rotate(Math.toRadians(0), x, y);
		gat.translate(x-(Game.tex.blackhole[0].getWidth()/2),y-(Game.tex.blackhole[0].getHeight()/2));
		g2d.drawImage(Game.tex.blackhole[0], gat, null);
		
		gat = new AffineTransform();
		//gat.rotate(Math.toRadians(rot), x, y);
		gat.translate(x-(Game.tex.pull[pullFrame].getWidth()/2),y-(Game.tex.pull[pullFrame].getHeight()/2));
		g2d.drawImage(Game.tex.pull[pullFrame], gat, null);
		
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		//g2d.draw(path);
		
		
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,32,32);
	}
	public Area getArea() {
		return area;
	}
}
