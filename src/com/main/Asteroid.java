package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends GameObject {
	
	private double dir, spd=0, pullX, pullY;
	final static double MAX_SPEED = 8;	
	private Random rand = new Random();
	private Handler handler;
	
	public Asteroid(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler=handler;
		//dir = rand.nextInt(360);
		//spd = rand.nextInt(3)+2;
	}
	public void tick() {
		velX = Math.cos(Math.toRadians(dir)) * spd;
		velY = Math.sin(Math.toRadians(dir)) * spd;
		
		x += velX + (int)pullX;
		y += velY + (int)pullY;
		
		if(x <= 0) {
			x = Game.WIDTH;
		}else if(x >= Game.WIDTH) {
			x = 0;
		}
		
		if(y <= 0) {
			y = Game.HEIGHT;
		}else if(y >= Game.HEIGHT) {
			y = 0;
		}
		
		collision();
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		g.drawRect(x-originX, y-originY, 24, 24);
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
		return new Rectangle(x-originX,y-originY,24,24);
	}

	public void collision() {
		GameObject tempObject;
		Player playerObject;
		Bullet bulletObject;		
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(this.getBounds().intersects(playerObject.getBounds())) {
//					handler.removeObject(playerObject);
					playerObject.stunTime=50;
					handler.removeObject(this);
				}
			} else if(tempObject.getId() == ID.Bullet) {
				bulletObject = (Bullet)tempObject;
				if(bulletObject.getBounds().intersects(this.getBounds())) {
					this.destroy();
					handler.removeObject(this);
					System.out.println("Deadz");
				}
			} else { }
		}
		
	}
	
	public void destroy() {
		int num=rand.nextInt(5)+1;
		for(int i=0;i<num;i++) {
			handler.addObject(new SmallAsteroid(x, y, originX, originY, id, handler));
		}
	}
	
	
}
