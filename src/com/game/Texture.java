package com.game;

import java.awt.image.BufferedImage;

public class Texture{

	public BufferedImage[] 	player = new BufferedImage[4],
							cursor = new BufferedImage[4],
							bullet = new BufferedImage[4],
							asteroid = new BufferedImage[3],
							muzzle = new BufferedImage[6],
							explode_med = new BufferedImage[6],
							explode_large = new BufferedImage[6],
							coin = new BufferedImage[6],
							coin_small = new BufferedImage[6],
							blackhole = new BufferedImage[13],
							spiral = new BufferedImage[13],
							pull = new BufferedImage[28];
							
	public BufferedImage 	logo,
							team,
						 	bg,
						 	bgpane,
						 	splash,
						 	quitwarning,
						 	quit,
						 	cancel,
						 	back,
						 	create,
						 	back_s,
						 	create_s,
						 	ready_s,
						 	connect,
						 	createHeader,
						 	connectHeader,
						 	connectedPlayers,
						 	lobbyBox,
						 	ready,
						 	menuBtn[] = new BufferedImage[3],
						 	multiBtn[] = new BufferedImage[3],
				 			menu_blackhole[] = new BufferedImage[13],
							menu_pull[] = new BufferedImage[28];	
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();		
		int muzzle_length = muzzle.length,
			explodeMed_length = explode_med.length,
			explodeLarge_length = explode_large.length,
			coin_length = coin.length,
			smallCoin_length = coin_small.length,
			blackhole_length = blackhole.length,
			spiral_length = spiral.length,
			pull_length = pull.length,
			menuBlackhole_length = menu_blackhole.length,
			menuPull_length = menu_pull.length;
		
		try {
			player[0] = loader.loadImage("/sprites/spr_PlayerBlue.png",1.5,1.5,0);
			player[1] = loader.loadImage("/sprites/spr_playerOrange.png",1.5,1.5,0);
			player[2] = loader.loadImage("/sprites/spr_playerRed.png",1.5,1.5,0);
			player[3] = loader.loadImage("/sprites/spr_playerGreen.png",1.5,1.5,0);
			cursor[0] = loader.loadImage("/sprites/spr_CursorBlue.png",1.5,1.5,0);
			cursor[1] = loader.loadImage("/sprites/spr_CursorOrange.png",1.5,1.5,0);
			cursor[2] = loader.loadImage("/sprites/spr_CursorRed.png",1.5,1.5,0);
			cursor[3] = loader.loadImage("/sprites/spr_CursorGreen.png",1.5,1.5,0);
			bullet[0] = loader.loadImage("/sprites/spr_bulletBlue.png",1.5,1.5,0);
			bullet[1] = loader.loadImage("/sprites/spr_bulletOrange.png",1.5,1.5,0);
			bullet[2] = loader.loadImage("/sprites/spr_bulletRed.png",1.5,1.5,0);
			bullet[3] = loader.loadImage("/sprites/spr_bulletGreen.png",1.5,1.5,0);
			
			for(int i=0; i<asteroid.length; i++)
				asteroid[i] = loader.loadImage("/sprites/spr_Asteroid" + (i+1) + ".png",2,2,0);
			for(int i=0; i<muzzle_length; i++)
				muzzle[i] = loader.loadImage("/sprites/muzzle" + (i+1) + ".png",2,2,0);
			for(int i=0; i<explodeMed_length; i++)
				explode_med[i] = loader.loadImage("/sprites/spr_explosion_medium" + (i+1) + ".png",1,1,0);
			for(int i=0; i<explodeLarge_length; i++)
				explode_large[i] = loader.loadImage("/sprites/spr_explosion_large" + (i+1) + ".png",1,1,0);
			for(int i=0; i<coin_length; i++)
				coin[i] = loader.loadImage("/sprites/spr_coin" + (i+1) + ".png",2,2,0);
			for(int i=0; i<smallCoin_length; i++)
				coin_small[i] = loader.loadImage("/sprites/spr_coin" + (i+1) + ".png",1.5,1.5,0);
			for(int i=0; i<blackhole_length; i++)
				blackhole[i] = loader.loadImage("/sprites/spr_blackhole" + (i+1) + ".png",2,2,0);
			for(int i=0; i<spiral_length; i++)
				spiral[i] = loader.loadImage("/sprites/spr_spiral" + (i+1) + ".png",3,3,0);
			for(int i=0; i<pull_length; i++)
				pull[i] = loader.loadImage("/sprites/spr_pull" + (i+1) + ".png",2,2,0);
			
			for(int i =0; i<menuBlackhole_length; i++) 
				menu_blackhole[i] = loader.loadImage("/sprites/spr_blackhole" + (i+1) + ".png",13,13,0);				
			for(int i =0; i<menuPull_length; i++) 
				menu_pull[i] = loader.loadImage("/sprites/spr_pull" + (i+1) + ".png",5,5,0);

			logo = loader.loadImage("/images/logo.png",.6,.6,1);
			team = loader.loadImage("/images/ws-logo.png",1,1,1);	
			bg = loader.loadImage("/images/menu-bg.png",.7,.7,1);
			bgpane = loader.loadImage("/images/bgpane.png",1.4,1.4,0);
			splash = loader.loadImage("/images/splash.png",.7,.7,1);
			quit = loader.loadImage("/images/cp-quit.png",.5,.5,0);
			cancel = loader.loadImage("/images/cancel.png",.5,.5,0);
			back = loader.loadImage("/images/c1-back.png",.5,.5,1);
			create = loader.loadImage("/images/cs-create.png",.5,.5,1);
			back_s = loader.loadImage("/images/c1-back.png",.6,.6,1);
			create_s = loader.loadImage("/images/cs-create.png",.6,.6,1);
			ready_s  = loader.loadImage("/images/cp-ready.png",.6,.6,1);
			connect = loader.loadImage("/images/c1-connect.png",.5,.5,1);
			createHeader = loader.loadImage("/images/createserver-header.png",.6,.6,1);
			connectHeader = loader.loadImage("/images/c1-connect-header.png",.7,.7,1);
			connectedPlayers = loader.loadImage("/images/cp-header.png",.7,.7,1);
			lobbyBox = loader.loadImage("/images/cp-des.png",.7,.7,1);
			quitwarning = loader.loadImage("/images/quitquestion.png",.6,.6,1);
			ready = loader.loadImage("/images/ready1.png",2.5,2.5,0);
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
