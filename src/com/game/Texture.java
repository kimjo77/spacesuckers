package com.game;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage[] 	player = new BufferedImage[2],
							cursor = new BufferedImage[2],
							asteroid = new BufferedImage[3];
	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			player[0] = loader.loadImage("/sprites/spr_PlayerBlue.png",1.5,1.5);
			player[1] = loader.loadImage("/sprites/spr_playerOrange.png",1.5,1.5);
			//player[0] = loader.loadImage("/spr_PlayerBlue.png");
			//player[1] = loader.loadImage("/spr_playerOrange.png");
			
			cursor[0] = loader.loadImage("/sprites/spr_CursorBlue.png",1.5,1.5);
			
			asteroid[0] = loader.loadImage("/sprites/spr_Asteroid1.png",1.5,1.5);
			asteroid[1] = loader.loadImage("/sprites/spr_Asteroid2.png",1.5,1.5);
			asteroid[2] = loader.loadImage("/sprites/spr_Asteroid3.png",1.5,1.5);
			/*asteroid[0] = loader.loadImage("/spr_Asteroid1.png");
			asteroid[1] = loader.loadImage("/spr_Asteroid2.png");
			asteroid[2] = loader.loadImage("/spr_Asteroid3.png");*/
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
