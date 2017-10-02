package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.entities.GameObject;

public class Gameover extends GameObject{
	
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
		g.fillRect(x+2, y+2, 400-4, 200-4);
		g.setColor(Color.white);
		g.fillRect(x+40, y+120, 150, 40);
		g.fillRect(x+210, y+120, 150, 40);
		g.setColor(Color.black);
		g.fillRect(x+40+2, y+120+2, 150-4, 40-4);
		g.fillRect(x+210+2, y+120+2, 150-4, 40-4);
		
		g.setColor(Color.white);
		Game.fnt = new Font("Connection", Font.PLAIN, 48);
		g.setFont(Game.fnt);
		g.drawString("GAMEOVER!", x+75, y+50);
		Game.fnt = new Font("Connection", Font.PLAIN, 18);
		g.setFont(Game.fnt);
		g.drawString("Still wanna play? Huh noob...", x+40, y+90);
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		g.drawString("Restart", x+70, y+150);
		g.drawString("Quit", x+260, y+150);
		
		/*g.setColor(Color.black);
		g.fillRect(x+2, y+2, 400-4, 200-4);
		g.setColor(Color.white);*/
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
