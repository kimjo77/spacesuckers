package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.Random;

import com.game.Game;
import com.game.ID;

public class Asteroid extends GameObject {
	
	public double dir, spd=0, pullX, pullY, rot;
	private Random rand = new Random();
	
	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform at = new AffineTransform(),
									gat = new AffineTransform();
	
	public Asteroid(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rot = rand.nextInt(360);
		
		rect = this.getBounds();
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
	}
	public void tick() {
		rot += 1;
		
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
		for(int i=0; i < Game.handler.object.size(); i++) {
			tempObject = Game.handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(playerObject.getArea() != null) {
					tempArea.intersect(playerObject.getArea());
					if(!tempArea.isEmpty()) {
	//					handler.removeObject(playerObject);
						playerObject.stunTime=60;
						Game.handler.removeObject(this);
					}
				}
			} else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				if(bulletObject.getArea() != null) {
					tempArea.intersect(bulletObject.getArea());
					if(!tempArea.isEmpty()) {
						//Game.soundfx.get("shoot").play(Game.soundfx.get("shoot").getShoot());
						Game.SCORE += 20;
						Game.handler.addObject(new Explosion(x,y,32,32,ID.Explosion));
						generateAsteroidSmall(bulletObject);
						Game.handler.removeObject(bulletObject);
						Game.soundfx.play(Game.soundfx.explosion);
						Game.handler.removeObject(this);
						//Game.soundfx.play(Game.soundfx.explosion);
					}
				}
			}
		}
		
	}
	
	public void generateAsteroidSmall(Bullet bullet) {
		SmallAsteroid sAsteroid;
		double bulletAngle = bullet.getAngle();
		int rotGap = 60;
		
		for(int i=0;i<2;i++) {
			sAsteroid = new SmallAsteroid(x,y,12,12,id);
			rotGap *= -1;
			bulletAngle = bulletAngle + rotGap;
			sAsteroid.dir = bulletAngle;
			Game.handler.addObject(sAsteroid);
		}
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		gat = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		gat.rotate(Math.toRadians(rot), x,y);
		gat.translate(x-(Game.tex.asteroid[0].getWidth()/2),y-(Game.tex.asteroid[0].getHeight()/2));
		
		at.rotate(Math.toRadians(rot), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.drawImage(Game.tex.asteroid[0], gat, null);
		//g2d.draw(path);
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
		return new Rectangle(x-originX,y-originY,48,48);
	}
	public Area getArea() {
		return area;
	}
}
