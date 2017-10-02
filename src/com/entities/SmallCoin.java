package com.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import com.game.Game;
import com.game.ID;

public class SmallCoin extends GameObject{

	private double angle = 0;
	private int frame = 0,
				max_frames = 6,
				imagespeed = 60/12,
				frametime = 0;
	
	private Rectangle rect;
	private transient Area area;
	private transient GeneralPath path = new GeneralPath();
	private transient AffineTransform at = new AffineTransform(),
									gat = new AffineTransform();	
	
	public SmallCoin(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);

		rect = this.getBounds();
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
	}

	public void tick() {
		angle++;
		
		collision();
		
		if(frametime < 0) {
			frametime = imagespeed;
			if(frame+1 == max_frames) {
				frame = 0;
			}else {
				frame++;
			}
		}else {
			frametime--;
		}
	}

	public void collision() {
		GameObject tempObject;
		Player playerObject;
		Area tempArea;
		for(int i=0; i < Game.handler.object.size(); i++) {
			tempObject = Game.handler.object.get(i);
			tempArea = new Area(path);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(playerObject.getArea() != null) {
					tempArea.intersect(playerObject.getArea());
					if(!tempArea.isEmpty()) {
						Game.SCORE+=5;
						Game.soundfx.play(Game.soundfx.pickupCoin);
						Game.handler.removeObject(this);
					}
				}
			}
		}	
	}
		
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		at = new AffineTransform();
		gat = new AffineTransform();
		path = new GeneralPath();
		
		rect = this.getBounds();
		gat.rotate(Math.toRadians(angle), x, y);
		//gat.translate(x-(Game.tex.coin[frame].getWidth()/2),y-(Game.tex.coin[frame].getHeight()/2));
		gat.translate(x-(Game.tex.coin_small[0].getWidth()/2),y-(Game.tex.coin_small[0].getHeight()/2));
		
		at.rotate(Math.toRadians(angle), x, y);
		path.append(rect.getPathIterator(at), true);
		area = new Area(path);
		g2d.drawImage(Game.tex.coin_small[frame], gat, null);
		//g2d.draw(path);		
	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,15,15);
	}
	
	public Area getArea() {
		return area;
	}

}
