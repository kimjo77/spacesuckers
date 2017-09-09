package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends GameObject {
	
	private double dir, spd=0, pullX, pullY;
	final static double MAX_SPEED = 8;	
	private Random rand = new Random();
	
	public Asteroid(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
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
	}
	
	public void render(Graphics g) {	
		g.setColor(Color.blue);
		g.drawRect(x-originX, y-originY, 16, 16);
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
		return new Rectangle(x-originX,y-originY,16,16);
	}
	
	public void destroy() {
		
	}
	
	
}
