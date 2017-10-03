package com.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.Game;
import com.game.ID;

public class Scoreboard extends GameObject{

	public Scoreboard(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		g.fillRect(250, 200, 500, 300);
		g.setColor(Color.black);
		g.fillRect(250+2,200+2, 500-4, 300-4);
		
		//g.setColor(new Color(255,30,24));
		/*Game.fnt = new Font("Connection", Font.PLAIN, 64);
		g.setFont(Game.fnt);
		g.setColor(new Color(255,30,24));
		g.drawString("Game Over!", 310, 270);
		
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		g.setColor(new Color(255,30,24));
		g.drawString("000", 650, 320);
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		g.setColor(new Color(255,30,24));
		g.drawString("000", 650, 320);
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		g.setColor(new Color(255,30,24));
		g.drawString("000", 650, 320);
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		g.setColor(new Color(255,30,24));
		g.drawString("000", 650, 320);*/
		
	}
	public Rectangle getBounds() {
		return null;
	}

}
