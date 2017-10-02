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
	private String[] caption = {
			"In case you didn't notice.. You just died",
			"Is that really all you can do?!",
			"Don't ever dream of being a pilot anymore",
			"Give up and go to bed already.."
	};
	public Gameover(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
		// TODO Auto-generated constructor stub
	}

	public void tick() { 
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(x, y, 400, 200);
		g.setColor(Color.black);
		g.fillRect(x + 2, y + 2, 400-4, 200-4);
		g.setFont(Game.fnt);
		g.setColor(Color.white);
		Game.fnt = new Font("04B", Font.PLAIN, 64);
		g.drawString("GAMEOVER!", x, y);
		Game.fnt = new Font("04B", Font.PLAIN, 16);
		g.drawString("Play Again", x, 100);
		g.drawString("Back to Main", y, 200);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
