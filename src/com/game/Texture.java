package com.game;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage[] 	player = new BufferedImage[2],
							cursor = new BufferedImage[2],
							asteroid = new BufferedImage[3];
	
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
