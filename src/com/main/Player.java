package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	public int 
					key_up = 0,
					key_down = 0,
					key_left = 0,
					key_right = 0,
					key_shoot = 0;
	private double 
					dir = 0,
					spd = 0,
					mag = 0,
					angle = 0,
					rotspd = 4,
					pullX,
					pullY;
	final static double MAX_SPEED = 7,MIN_SPEED = -3;
	
	private Handler handler;
	
	public Player(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
	}

	public void tick() {
		angle += (key_left + key_right) * rotspd;
		
		if(key_shoot == 1) {
			this.shoot(angle);
			key_shoot = 2;
		}
		
		if(key_up == 1) {
			motion_add(angle,0.2,velX,velY,spd);
		} else {
			spd -= 0.1;
		}
	
		velX = Math.cos(Math.toRadians(dir)) * spd;
		velY = Math.sin(Math.toRadians(dir)) * spd;
		
		if(spd > MAX_SPEED) {
			spd = MAX_SPEED;
		} else if(spd < MIN_SPEED) {
			spd = MIN_SPEED;
		}
		
		x += velX + pullX;
		y += velY + pullY;
		
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
	}

	public void motion_add(double angle, double accelspd ,double pvelX, double pvelY, double pspd) {
		double nvelX,nvelY;
		spd += accelspd;
		
		nvelX = Math.cos(Math.toRadians(angle)) * spd;
		nvelY = Math.sin(Math.toRadians(angle)) * spd;
		velX = pvelX + nvelX;
		velY = pvelY + nvelY;
		
		dir = angle;
	}

	public void shoot(double angle) {
		Bullet b = new Bullet(x, y, 0, 0, ID.Bullet,handler);
		b.setAngle(angle);
		handler.addObject(b);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x-originX, y-originY, 32, 32);
		g.drawRect((int)(x+velX)-8, (int)(y+velY)-8, 16, 16);
		//g.drawRect((int)(x+(Math.cos(Math.toRadians(angle))*MAX_SPEED))-8, (int)(y+(Math.sin(Math.toDegrees(angle))*MAX_SPEED))-8, 16, 16);
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
	
}
