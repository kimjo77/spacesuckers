package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Bullet extends GameObject {

	private double angle = 0; 
	private final int spd = 15 ;
	
	private Rectangle rect;
	private Area area;
	private GeneralPath path = new GeneralPath();
	private AffineTransform at = new AffineTransform();
	
	private File audio;
	
	public Bullet(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		audio = new File("C://Users/Matugas LKJ/Documents/spacesuckers/res/audio/Laser_Shoot.wav");

	}

	public void tick() {
		
		velX = Math.cos(Math.toRadians(angle)) * spd;
		velY = Math.sin(Math.toRadians(angle)) * spd;
				
		x += (int)velX;
		y += (int)velY;	
		
		if( x<0 || x>Game.WIDTH || y<0 || y>Game.HEIGHT) {
			Game.handler.removeObject(this);
		}
		
	}

	public void setAngle(double angle) {
		this.angle=angle;
	}
	public double getAngle() {
		return angle;
	}
	
	public File getAudioClip() {
		return audio;
	}
	
	public void playSound(File sound) { //still need to fix this so that no more interruptions
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/1000);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.draw(path);
	}
	
		
	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,6,6);
	}
	public Area getArea() {
		return area;
	}
}


