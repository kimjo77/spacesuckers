package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import com.entities.GameObject;

public class Gameover extends GameObject{

	private final int width = 300;
	private final int height = 150;
	
	public Gameover(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.drawImage(, x, y, null);
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.black);
		g.fillRect(x + 6, y + 6, width-12, height-12);
		g.setFont(Game.fnt);
		Game.fnt = new Font("04B", Font.PLAIN, 32);
		g.drawString("GAMEOVER!", 0, 0);
		Game.fnt = new Font("04B", Font.PLAIN, 16);
		g.drawString("Play Again", 0, 100);
		g.drawString("Back to Main", 0, 200);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
