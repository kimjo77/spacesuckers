package com.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.Game;
import com.game.ID;

public class AsteroidController extends GameObject{
	
	private int spawnTime,incTime;
	private Random rand = new Random();
	private int offset = 32 * 2;	
	private int spawninc = 1;
	
	public AsteroidController(int x, int y, int originX, int originY, ID id) {
		super(x, y, originX, originY, id);
	}

	public void tick() {
		if(spawnTime <0) {
			spawnTime = 60 + rand.nextInt(60);
			for(int i=(rand.nextInt(2)+spawninc); i > 0; i--) {
				int xx=0,yy=0;
				switch(rand.nextInt(4)) {
					case 0: // right
						xx = Game.WIDTH + offset;
						yy = rand.nextInt(Game.WIDTH) - rand.nextInt(offset);
						break;
					case 1: // top
						xx = rand.nextInt(Game.HEIGHT) - rand.nextInt(offset);
						yy = -offset;
						break;
					case 2: // left
						xx = -offset;
						yy = rand.nextInt(Game.WIDTH) - rand.nextInt(offset);
						break;						
					case 3: // bottom
						xx = rand.nextInt(Game.HEIGHT) - rand.nextInt(offset);
						yy = Game.HEIGHT + offset;
						break;
				}
				Game.handler.addObject(new Asteroid(xx,yy,24,24,ID.Asteroid));
			}
		}else{
			spawnTime--;
		}
		
		if(incTime < 0) {
			incTime = 60*30;
			spawninc++;
		} else {
			incTime--;
		}
		
	}
	public void render(Graphics g) {
	
	}
	public Rectangle getBounds() {
		return null;
	}
}
