package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class AsteroidController extends GameObject{
	
	private Handler handler;
	private int spawnTime;
	private Random rand = new Random();
	private int offset = 32 * 2;

	public AsteroidController(int x, int y, int originX, int originY, ID id, Handler handler) {
		super(x, y, originX, originY, id);
		this.handler = handler;
	}

	public void tick() {
		if(spawnTime <0) {
			spawnTime = 20 + rand.nextInt(60);
			for(int i=(rand.nextInt(3)+1); i > 0; i--) {
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
				handler.addObject(new Asteroid(xx,yy,16,16,ID.Asteroid,handler));
			}
		}else{
			spawnTime--;
		}
	}
	public void render(Graphics g) {
	
	}
	public Rectangle getBounds() {
		return null;
	}
		
	public void destroy() {
		
	}
	

}
