package com.game;

import java.awt.image.BufferedImage;

public class Texture{

	public BufferedImage[] 	player = new BufferedImage[4],
							cursor = new BufferedImage[4],
							asteroid = new BufferedImage[3],
							muzzle = new BufferedImage[6],
							explode_med = new BufferedImage[6],
							explode_large = new BufferedImage[6],
							coin = new BufferedImage[6];
	
	public BufferedImage 	logo,
							team,
						 	bg,
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
						 	multiBtn[] = new BufferedImage[3];
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			player[0] = loader.loadImage("/sprites/spr_PlayerBlue.png",1.5,1.5,0);
			player[1] = loader.loadImage("/sprites/spr_playerOrange.png",1.5,1.5,0);
			player[2] = loader.loadImage("/sprites/spr_playerRed.png",1.5,1.5,0);
			player[3] = loader.loadImage("/sprites/spr_playerGreen.png",1.5,1.5,0);
			cursor[0] = loader.loadImage("/sprites/spr_CursorBlue.png",1.5,1.5,0);
			cursor[1] = loader.loadImage("/sprites/spr_CursorOrange.png",1.5,1.5,0);
			cursor[2] = loader.loadImage("/sprites/spr_CursorRed.png",1.5,1.5,0);
			cursor[3] = loader.loadImage("/sprites/spr_CursorGreen.png",1.5,1.5,0);
			asteroid[0] = loader.loadImage("/sprites/spr_Asteroid1.png",2,2,0);
			asteroid[1] = loader.loadImage("/sprites/spr_Asteroid2.png",2,2,0);
			asteroid[2] = loader.loadImage("/sprites/spr_Asteroid3.png",2,2,0);
			muzzle[0] = loader.loadImage("/sprites/muzzle1.png",2,2,0);
			muzzle[1] = loader.loadImage("/sprites/muzzle2.png",2,2,0);
			muzzle[2] = loader.loadImage("/sprites/muzzle3.png",2,2,0);
			muzzle[3] = loader.loadImage("/sprites/muzzle4.png",2,2,0);
			muzzle[4] = loader.loadImage("/sprites/muzzle5.png",2,2,0);
			muzzle[5] = loader.loadImage("/sprites/muzzle6.png",2,2,0);
			explode_med[0] = loader.loadImage("/sprites/spr_explosion_medium1.png",1,1,0);
			explode_med[1] = loader.loadImage("/sprites/spr_explosion_medium2.png",1,1,0);
			explode_med[2] = loader.loadImage("/sprites/spr_explosion_medium3.png",1,1,0);
			explode_med[3] = loader.loadImage("/sprites/spr_explosion_medium4.png",1,1,0);
			explode_med[4] = loader.loadImage("/sprites/spr_explosion_medium5.png",1,1,0);
			explode_med[5] = loader.loadImage("/sprites/spr_explosion_medium6.png",1,1,0);
			explode_large[0] = loader.loadImage("/sprites/spr_explosion_large1.png",1,1,0);
			explode_large[1] = loader.loadImage("/sprites/spr_explosion_large2.png",1,1,0);
			explode_large[2] = loader.loadImage("/sprites/spr_explosion_large3.png",1,1,0);
			explode_large[3] = loader.loadImage("/sprites/spr_explosion_large4.png",1,1,0);
			explode_large[4] = loader.loadImage("/sprites/spr_explosion_large5.png",1,1,0);
			explode_large[5] = loader.loadImage("/sprites/spr_explosion_large6.png",1,1,0);
			coin[0] = loader.loadImage("/sprites/spr_coin1.png",2,2,0);
			coin[1] = loader.loadImage("/sprites/spr_coin2.png",2,2,0);
			coin[2] = loader.loadImage("/sprites/spr_coin3.png",2,2,0);
			coin[3] = loader.loadImage("/sprites/spr_coin4.png",2,2,0);
			coin[4] = loader.loadImage("/sprites/spr_coin5.png",2,2,0);
			coin[5] = loader.loadImage("/sprites/spr_coin6.png",2,2,0);

			logo = loader.loadImage("/images/logo.png",.7,.7,1);
			team = loader.loadImage("/images/ws-logo.png",1,1,1);	
			bg = loader.loadImage("/images/menu-bg.png",.7,.7,1);
			splash = loader.loadImage("/images/splash.png",.7,.7,1);
			quit = loader.loadImage("/images/cp-quit.png",.7,.7,1);
			cancel = loader.loadImage("/images/cancel.png",.7,.7,1);
			back = loader.loadImage("/images/c1-back.png",.7,.7,1);
			create = loader.loadImage("/images/cs-create.png",.7,.7,1);
			back_s = loader.loadImage("/images/c1-back.png",.6,.6,1);
			create_s = loader.loadImage("/images/cs-create.png",.6,.6,1);
			ready_s  = loader.loadImage("/images/cp-ready.png",.6,.6,1);
			connect = loader.loadImage("/images/c1-connect.png",.7,.7,1);
			createHeader = loader.loadImage("/images/createserver-header.png",.7,.7,1);
			connectHeader = loader.loadImage("/images/c1-connect-header.png",.7,.7,1);
			connectedPlayers = loader.loadImage("/images/cp-header.png",.7,.7,1);
			lobbyBox = loader.loadImage("/images/cp-des.png",.7,.7,1);
			quitwarning = loader.loadImage("/images/quitquestion.png",.7,.7,1);
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
