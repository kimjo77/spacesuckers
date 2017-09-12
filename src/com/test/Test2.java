package com.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import com.main.Game;
import com.main.GameObject;
import com.main.ID;

public class Test2 extends GameObject{
	
	private double rot = 0;
	private Rectangle rect;
	private Area area;

	public Test2(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		rect = this.getBounds();
	}

	public void tick() {
		rect = this.getBounds();
		rot += 1;
		
		x += 1;
		
		if(x >= Game.WIDTH) {
			x = 0;
		}
	}
	
	public Area getArea() {
		return area;
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);

		//Rectangle rect2 = new Rectangle(x-originX, y-originY, 64, 64);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		GeneralPath path2;
		path2 = new GeneralPath();
        //--Rectangle (2)
        at.translate(0,0);
		at.rotate(Math.toRadians(rot), x,y);
        path2.append(rect.getPathIterator(at), true);
        area = new Area(path2);
        g2d.fill(path2);
       // g2d.draw(path2.getBounds());
	    //---------------------------------------------------------//		
	}

	public Rectangle getBounds() {
		return new Rectangle(x-originX, y-originY, 64, 64);
	}
}
