package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Test extends GameObject {
	private double rot = 0;

	public Test(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
	}

	public void tick() {
		rot += 0.5;
		System.out.println("Rotation: " + rot);
		
		x += 1;
	}
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.drawRect(x-originX, y-originY, 64, 64);
		
		//-----------------TEST CODE HERE-------------------------//
		Rectangle rect2 = new Rectangle(x-originX, y-originY, 64, 64);
		Shape rect = new Rectangle(x-originX, y-originY, 64, 64);
	    Graphics2D g2d = (Graphics2D)g;
	    AffineTransform at = new AffineTransform();
	    at.rotate(Math.toRadians(rot),x, y);
	    rect = at.createTransformedShape(rect2);
	    g2d.draw(rect);
	    
	    if(rect.) {
	    	System.out.println("True");
	    }
	    //---------------------------------------------------------//

	}

	public Shape getBounds() {
		return (Shape)new Rectangle(x-originX,y-originY,64,64);
	}

	@Override
	public void destroy() {
	}
	

}
