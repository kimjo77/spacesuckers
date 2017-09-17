package com.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import com.game.GameObject;
import com.game.Handler;
import com.game.ID;

public class Test extends GameObject {
	private double rot = 0;
	private Rectangle rect;
	private Area area;
	private Handler handler;
	private GameObject tempObject;
	private Test2 test2Object;

	public Test(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
		this.handler = handler;
	}

	public void tick() {
		rect = this.getBounds();
		rot += 0.5;
		
		//collision();
	}
	
	public void collision() {
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Test2) {
				test2Object = (Test2)tempObject;
				area.intersect(test2Object.getArea());
				if(!area.isEmpty()) {
					handler.removeObject(test2Object);
					System.out.println("Removed!");
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		/*
		//-----------------TEST CODE (1) HERE-------------------------//
		Rectangle rect2 = new Rectangle(x-originX, y-originY, 64, 64);
		Shape rect = new Rectangle(x-originX, y-originY, 64, 64);
	    Graphics2D g2d = (Graphics2D)g;
	    AffineTransform at = new AffineTransform();
	    at.rotate(Math.toRadians(rot),x, y);
	    rect = at.createTransformedShape(rect2);
	    g2d.draw(rect);
	    */
		//-----------------TEST CODE (2) HERE-------------------------//
		//Rectangle rect1 = new Rectangle(Game.WIDTH/2-originX, Game.HEIGHT/2-originY, 64, 64);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		GeneralPath path1,path2;
		path1 = new GeneralPath();
		path2 = new GeneralPath();
		//--Rectangle (1)
		at.translate(0,0);
		at.rotate(Math.toRadians(rot), x,y);
        path1.append(rect.getPathIterator(at), true);
        //area = new Area(path1);
        g2d.fill(path1);
        //g2d.draw(path1.getBounds());
        
	    //---------------------------------------------------------//

	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX,y-originY,64,64);
	}
}