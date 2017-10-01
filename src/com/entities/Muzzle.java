package com.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import com.game.Game;
import com.game.ID;

public class Muzzle extends GameObject{

	private double angle = 0;
	private int frame = 0,
				max_frames = 6,
				imagespeed = 60/24,
				frametime = 0;
	private Rectangle rect;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform 	at = new AffineTransform(),
										gat = new AffineTransform();
	public Muzzle(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
	}

	public void tick() {
		if(frametime < 0) {
			frametime = imagespeed;
			if(frame+1 == max_frames) {
				Game.handler.removeObject(this);
			}else {
				frame++;
			}
		}else {
			frametime--;
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		gat = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		double offsetX = Math.cos(Math.toRadians(angle)) * 25;
		double offsetY = Math.sin(Math.toRadians(angle)) * 25;
		
		gat.rotate(Math.toRadians(angle+90), (x-offsetX), (y-offsetY));
		gat.translate((x-offsetX)-(Game.tex.muzzle[frame].getWidth()/2),(y-offsetY)-(Game.tex.muzzle[frame].getHeight()/2));
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		g2d.drawImage(Game.tex.muzzle[frame], gat, null);
		//g2d.draw(path);
	}
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,8,8);
	}
	public void setAngle(double angle) {
		this.angle=angle;
	}
	public double getAngle() {
		return angle;
	}
}
