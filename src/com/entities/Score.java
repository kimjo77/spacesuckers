package com.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import com.game.Game;
import com.game.ID;

public class Score extends GameObject{

	public int scoreID = 0,
				score = 0;
	
	public Score(int x, int y, int originX, int originY, ID id, int scoreID) {
		super(x, y, originX, originY, id);
		this.scoreID = scoreID;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		Game.fnt = new Font("Connection", Font.PLAIN, 24);
		g.setFont(Game.fnt);
		switch(scoreID) {
		case 0:
			//Blue, orange, red ,green
				g.setColor(new Color(20,115,207));//Blue
				g.drawString("Score: " + score, 35, 40);
				break;
		case 1:
				g.setColor(new Color(241,119,44));//Orange
				g.drawString("Score: " + score, 245, 40);
				break;
		case 2:
				g.setColor(new Color(255,30,24));//Red
				g.drawString("Score: " + score, 465, 40);
				break;
		case 3:
				g.setColor(new Color(21,142,91));//Green
				g.drawString("Score: " + score, 685, 40);
				break;
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	

}
