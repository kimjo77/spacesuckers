package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blackhole extends GameObject {
		private double pull=5, dx, dy, angle, dis;
		private Handler handler;
		private GameObject tempObject;
		private Player playerObject;
		private Asteroid asteroidObject;
		private Bullet bulletObject;
		
		
		public Blackhole(int x, int y, int originX, int originY, ID id, Handler handler) {
			super(x, y, originX, originY, id);
			this.handler = handler;
		}
		public void tick() {
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
			for(int i=0; i < handler.object.size(); i++) {
				tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Asteroid) {
					asteroidObject = (Asteroid)tempObject;
					if(this.getBounds().intersects(asteroidObject.getBounds())) {
						handler.removeObject(asteroidObject);
					}
				} else if(tempObject.getId() == ID.Player) {
					playerObject = (Player)tempObject;
					if(this.getBounds().intersects(playerObject.getBounds())) {
						//handler.removeObject(playerObject);
					}
				} else if(tempObject.getId() == ID.Bullet) {
					bulletObject = (Bullet)tempObject;
					if(this.getBounds().intersects(bulletObject.getBounds())) {
						handler.removeObject(bulletObject);
					}
				}
			}
			
		}
		
		public void render(Graphics g) {
			g.setColor(Color.WHITE);
			g.drawRect(x-originX, y-originY, 32, 32);
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x-originX,y-originY,32,32);
		}
		
		public void destroy() {
			
		}
}
