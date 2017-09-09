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
					key_shoot = 0,
					mouse_shoot = 0,
					stunTime=0,
					turrentKey_left,
					turrentKey_right,
					mouseX,
					mouseY;
	private double 
					dir = 0,
					spd = 0,
					angle = 0,
					rotspd = 4,
					turrentAngle = 0,
					pullX,
					pullY;
	final static double MAX_SPEED = 7,MIN_SPEED = -3;
	
	private Handler handler;
	
	public Player(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
	}

	public void tick() {
		
		if(stunTime<0) {	
			angle += (key_left + key_right) * rotspd;
			turrentAngle += (turrentKey_left + turrentKey_right) * rotspd;
			
			if(key_shoot == 1) {
				this.shoot(1);
				key_shoot = 2;
			} else if( mouse_shoot == 1) {
				this.shoot(0);
				mouse_shoot = 2;
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
		else {
			spd=0;
			velX = 0;
			velY = 0;
			
			x += velX + pullX;
			y += velY + pullY;

			motion_add(angle,0.2,velX,velY,spd);
			stunTime--;
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

	public void shoot(int choice) {
		Bullet b = new Bullet(x, y, 3, 3, ID.Bullet,handler);
		handler.addObject(b);
		switch(choice) {
			case 0:
				int dx = mouseX - x;
				int dy = mouseY - y;
				turrentAngle = Math.toDegrees(Math.atan2(dy,dx)); 
				break;
		}
		b.setAngle(turrentAngle);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x-originX, y-originY, 32, 32);
		g.drawRect((int)(x+velX)-8, (int)(y+velY)-8, 16, 16);
		
		
		/*switch(mouse_shoot) {
			case 0:
				int dx = mouseX - x;
				int dy = mouseY - y;
				turrentAngle = Math.toDegrees(Math.atan2(dy,dx)); 
				break;
		}*/
		double turrentX = Math.cos(Math.toRadians(turrentAngle)) * 100;
		double turrentY = Math.sin(Math.toRadians(turrentAngle)) * 100;
		g.setColor(Color.white);
		g.drawRect((int)(x+turrentX) ,(int)(y+turrentY)-2,4,4);
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
	
	public void destroy() {
		
	}
	
}
