package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.HashMap;

import com.game.Game;
import com.game.ID;

public class Player extends GameObject {
	
	public int playerID = 0;
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
					mouseY,
					trailtime;
	private double 
					dir = 0,
					spd = 0,
					angle = 0,
					rotspd = 4,
					turrentAngle = 0,
					pullX,
					pullY,
					turrentX,
					turrentY;
	final static double MAX_SPEED = 6,MIN_SPEED = -3;
	
	//private HashMap<String, SoundEffects> sfx;

	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform 	at = new AffineTransform(),
										gat = new AffineTransform();
	
	public Player(int x, int y, int originX, int originY, ID id, int playerID) {
		super(200, 200, originX, originY, id);
		this.playerID = playerID;
		rect = this.getBounds();
		at.rotate(Math.toRadians(dir), x, y);
		path.append(rect.getPathIterator(at), true);
		path.moveTo(x, y);
		
		/*sfx = new HashMap<String, SoundEffects>();
		sfx.put("shoot",new SoundEffects("/res/audio/Laser_Shoot.mp3"));
		sfx.put("destroy",new SoundEffects("/res/audio/Explosion.mp3"));*/
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
				if(trailtime < 0) {
					trailtime = 60/12;
					Muzzle m = new Muzzle(x,y,4,4,ID.Muzzle);
					Game.handler.addObject(m);
					m.setAngle((double)angle);
				} else {
					trailtime--;
				}
			} else {
				spd -= 0.1;
				trailtime = 0;
			}
		} else {
			spd -= 0.1;

			stunTime--;
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

	public void shoot(int choice) {
		Bullet b = new Bullet((int)(x+turrentX), (int)(y+turrentY), 3, 3, ID.Bullet);
		Game.handler.addObject(b);
//		b.playSound(b.getAudioClip()); 
//		sfx.get("shoot").play();
		
		switch(choice) {
			case 0:
				int dx = mouseX - x;
				int dy = mouseY - y;
				turrentAngle = Math.toDegrees(Math.atan2(dy,dx)); 
				break;
		}
		b.setAngle(turrentAngle);
		Game.soundfx.play(Game.soundfx.shoot);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		gat = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		gat.rotate(Math.toRadians(angle), x,y);
		gat.translate(x-(Game.tex.player[playerID].getWidth()/2),y-(Game.tex.player[playerID].getHeight()/2));
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		path.moveTo(x, y);
		double lineX = Math.cos(Math.toRadians(dir)) * 16;
		double lineY = Math.sin(Math.toRadians(dir)) * 16;
		path.lineTo(x + lineX,y + lineY);
		g2d.drawImage(Game.tex.player[playerID], gat, null);
		//g2d.draw(path);
		
		turrentX = Math.cos(Math.toRadians(turrentAngle)) * 50;
		turrentY = Math.sin(Math.toRadians(turrentAngle)) * 50;
		gat = new AffineTransform();
		gat.rotate(Math.toRadians(turrentAngle), (x+turrentX),(y+turrentY));
		gat.translate((x+turrentX)-(Game.tex.cursor[playerID].getWidth()/2),(y+turrentY)-(Game.tex.cursor[playerID].getHeight()/2));
		g.setColor(Color.red);
		g2d.drawImage(Game.tex.cursor[playerID], gat, null);
		//g.drawRect((int)(x+turrentX) ,(int)(y+turrentY)-2,4,4);
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
		return new Rectangle(x-originX,y-originY,48,24);
	}
	
	public Area getArea() {
		return area;
	}
	
}
