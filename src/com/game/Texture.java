package com.game;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage[] 	player = new BufferedImage[2],
							cursor = new BufferedImage[2],
							asteroid = new BufferedImage[3];
	
	public BufferedImage 	logo,
						 	bg,
						 	splash,
						 	menuBtn[] = new BufferedImage[3],
						 	multiBtn[] = new BufferedImage[3];
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			player[1] = loader.loadImage("/sprites/spr_PlayerBlue.png",1.5,1.5,0);
			player[0] = loader.loadImage("/sprites/spr_playerOrange.png",1.5,1.5,0);
			cursor[0] = loader.loadImage("/sprites/spr_CursorBlue.png",1.5,1.5,0);
			asteroid[0] = loader.loadImage("/sprites/spr_Asteroid1.png",2,2,0);
			asteroid[1] = loader.loadImage("/sprites/spr_Asteroid2.png",2,2,0);
			asteroid[2] = loader.loadImage("/sprites/spr_Asteroid3.png",2,2,0);
			
			logo = loader.loadImage("/images/logo.png",.7,.7,1);
			bg = loader.loadImage("/images/menu-bg.png",.7,.7,1);
			splash = loader.loadImage("/images/splash.png",.7,.7,1);
			menuBtn[0] = loader.loadImage("/images/rec6-splayer.png",.7,.7,1);
			menuBtn[1] = loader.loadImage("/images/rec6-mplayer.png",.7,.7,1);
			menuBtn[2] = loader.loadImage("/images/rec6-quit.png",.7,.7,1);
			multiBtn[0] = loader.loadImage("/images/rec6-Create.png",.7,.7,1);
			multiBtn[1] = loader.loadImage("/images/rec6-Connect.png",.7,.7,1);
			multiBtn[2] = loader.loadImage("/images/rec6-Back.png",.7,.7,1);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
