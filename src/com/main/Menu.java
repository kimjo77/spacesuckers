package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.game.Asteroid;
import com.game.AsteroidController;
import com.game.Blackhole;
import com.game.Game;
import com.game.ID;
import com.game.Player;
import com.game.Texture;
import com.test.ClientTest;
import com.test.ServerTest;

public class Menu extends MouseAdapter{
	
	private Texture tex = Game.tex;
	private float teamAlpha = 0;
	private int mx,my;
	//Splash
	private boolean teamFadeout = false;
	private int screenTime = 0;
	private int stime = 0,
				sx = 0,
				sy = 0 - tex.splash.getHeight() + Game.HEIGHT,
				sspd = 1;
	//Main
	private boolean quit = false;
	//Connect and Create TextFields
	private CustomTextField name,
							port,
							pass;
	
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		switch(Game.gameState) {
		case Menu:
					if(!quit) {
						if(mouseOver(mx,my,345,350+(0*85),285,73)) {
							Game.gamestart();
							Game.gameState = Game.STATE.Game;
						}else if(mouseOver(mx,my,345,350+(1*85),285,73)) {
							Game.gameState = Game.STATE.Multi;
						} else if(mouseOver(mx,my,345,350+(2*85),285,73)) {
							quit = true;
						}	
					} else {
						if(mouseOver(mx,my,270,350,203,51)) {
							System.exit(0);
						} else if(mouseOver(mx,my,530,350,203,51)) {
							quit = false;
						}
					}
					break;
		case Multi:
					if(mouseOver(mx,my,345,350+(0*85),285,73)) {
						createCustomTextFields();
						Game.gameState = Game.STATE.Create;
					}else if(mouseOver(mx,my,345,350+(1*85),285,73)) {
						createCustomTextFields();
						Game.gameState = Game.STATE.Connect;
					}else if(mouseOver(mx,my,345,350+(2*85),285,73)) {
						Game.gameState = Game.STATE.Menu;
					}
					break;
		case Create:
					if(mouseOver(mx,my,295,520,203,50)) {
						Game.gameState = Game.STATE.Multi;
						Game.ui.clearObject();
						System.out.println("Multi!");
					} else if(mouseOver(mx,my,515,520,203,50)) {
						Game.gameState = Game.STATE.Lobby;
						Game.networkType = Game.NETWORK_TYPE.Server;
						try {
							Game.server = new ServerTest(name.getText(),Integer.parseUnsignedInt(port.getText()),pass.getText());
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						//Game.gameState = Game.STATE.Menu;
						Game.ui.clearObject();
						
					}
					break;
		case Connect:
					if(mouseOver(mx,my,295,520,203,50)) {
						Game.gameState = Game.STATE.Multi;
						Game.ui.clearObject();
						System.out.println("Multi!");
					} else if(mouseOver(mx,my,515,520,203,50)) {
						Game.gameState = Game.STATE.Lobby;
						Game.networkType = Game.NETWORK_TYPE.Client;
						try {
							Game.client = new ClientTest(name.getText(),Integer.parseUnsignedInt(port.getText()),pass.getText());
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						//Game.gameState = Game.STATE.Menu;
						Game.ui.clearObject();
					}
					break;
					
		case Lobby:
					if(mouseOver(mx,my,524, 474, 175, 44)) {
							//Disconnect / delete lobby
						Game.gameState = Game.STATE.Multi;
						Game.networkType = null;
					} else if(mouseOver(mx,my, 704, 474, 175, 44)) {
						Game.gamestart();
						Game.gameState = Game.STATE.Game;
					}
					break;
		case Game:
					break;
		default:
		}
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}
	
	public void tick() {
		switch(Game.gameState) {
			case Splash:
				// Splash bg goes down.
						if(sy < 0) {
							sy += sspd;
						} else {
							Game.gameState = Game.STATE.Menu;
						}
				// Fade-in >> wait >> Fade-out
					if(screenTime == 0) {
						if(!teamFadeout) {
							if((teamAlpha+=0.01) > 1) {
								teamAlpha = 1;
								teamFadeout = true;
								screenTime = 60*2;
							}
						} else {
							if((teamAlpha -= 0.01) < 0) {
								teamAlpha = 0;
							}
						}
					} else {
						screenTime--;
					}
						
						if(stime < 0) {
							
						} else {
							stime--;
						}
							break;
			default:
				
		}
	}
	
	public void render(Graphics g) {
		//FontMetrics fntm= g.getFontMetrics(fnt);
		//g.setFont(fnt);
		//g.drawString("Suckers", Game.WIDTH/2 - fntm.stringWidth("Suckers")/2, Game.HEIGHT/3 - fntm.getHeight()/2+fntm.getHeight()-10);
		g.setFont(Game.fnt);
		Graphics2D g2d = (Graphics2D) g;
		
		switch(Game.gameState) {
			case Splash:
						g2d.drawImage(tex.splash, sx, sy, null);
						g2d.setComposite(setAlpha(teamAlpha));
						g2d.drawImage(tex.team,290,170,null);
						break;
			case Menu:
						g2d.drawImage(tex.bg,0,0,null);
						g2d.drawImage(tex.logo,230,80,null);
						for(int i=0; i<3; i++) {
							g2d.drawImage(tex.menuBtn[i], 330, 340 + (i*85), null);
							//g.drawRect(345,350+(i*85),285,73);
						}
						
						if(quit) {
							g.setColor(Color.white);
							g.fillRect(200, 230, 600, 200);
							g.setColor(Color.black);
							g.fillRect(200+6, 230+6, 600-12, 200-12);
							g2d.drawImage(tex.quitwarning, 215, 250, null);
							g2d.drawImage(tex.quit, 270, 350, null);
							g2d.drawImage(tex.cancel, 530, 350, null);
							//g.setColor(Color.red);
							//g.drawRect(270,350,203,51);
							//g.drawRect(530,350,203,51);
						}
						
						break;
			case Multi:
						g2d.drawImage(tex.bg,0,0,null);
						g2d.drawImage(tex.logo,230,80,null);
						for(int i=0; i<3; i++) {
							g2d.drawImage(tex.multiBtn[i], 330, 340 + (i*85), null);
							//g.drawRect(345,350+(i*85),285,73);
						}
						break;
			case Create:
						g2d.drawImage(tex.bg, 0, 0, null);
						g2d.drawImage(tex.createHeader, 265, 110, null);
						g2d.drawImage(tex.back, 295, 520, null);
						g2d.drawImage(tex.create, 515, 520, null);
						g.setColor(Color.white);
						g.fillRect(280, 180, 450, 300);
						g.setColor(Color.black);
						g.fillRect(280+6, 180+6, 450-12, 300-12);
						g.setColor(Color.white);
						g.setFont(Game.fnt);
						Game.fnt = new Font("04B", Font.TRUETYPE_FONT, 32);
						g.drawString("Name", 310, 230);
						g.drawString("Port", 310, 320);
						g.drawString("Pass", 310, 410);
						g.setColor(Color.white);
						g.fillRect(310, 270, 390, 6);
						g.fillRect(310, 360, 390, 6);
						g.fillRect(310, 450, 390, 6);
						//g.setColor(Color.red);
						//g.drawRect(295,520,203,50);
						//g.drawRect(515,520,203,50);
						break;
			case Connect:
						g2d.drawImage(tex.bg, 0, 0, null);
						g2d.drawImage(tex.connectHeader, 380, 110, null);
						g2d.drawImage(tex.back, 295, 520, null);
						g2d.drawImage(tex.connect, 515, 520, null);
						g.setColor(Color.white);
						g.fillRect(280, 180, 450, 300);
						g.setColor(Color.black);
						g.fillRect(280+6, 180+6, 450-12, 300-12);
						g.setColor(Color.white);
						g.setFont(Game.fnt);
						Game.fnt = new Font("04B", Font.TRUETYPE_FONT, 32);
						g.drawString("Name", 310, 230);
						g.drawString("Port", 310, 320);
						g.drawString("Pass", 310, 410);
						g.setColor(Color.white);
						g.fillRect(310, 270, 390, 6);
						g.fillRect(310, 360, 390, 6);
						g.fillRect(310, 450, 390, 6);
						//g.setColor(Color.red);
						//g.drawRect(295,520,203,50);
						//g.drawRect(515,520,203,50);
						break;
			case Lobby:
						g2d.drawImage(tex.bg, 0, 0, null);
						if(Game.networkType == Game.NETWORK_TYPE.Server) {
							g2d.drawImage(tex.createHeader,250,125,null);
							g2d.drawImage(tex.create_s, 705, 475, null);
						} else if(Game.networkType == Game.NETWORK_TYPE.Client) {
							g2d.drawImage(tex.connectedPlayers,175,125,null);
							g2d.drawImage(tex.ready_s, 705, 475, null);
						}
						g2d.drawImage(tex.lobbyBox, 70, 200, null);
						g2d.drawImage(tex.lobbyBox, 70, 300, null);
						g2d.drawImage(tex.lobbyBox, 70, 400, null);
						g2d.drawImage(tex.lobbyBox, 70, 500, null);
						g2d.drawImage(tex.back_s, 525, 475, null);
						
						
						g.setColor(Color.white);
						g.fillRect(500, 200, 400, 250);
						g.setColor(Color.black);
						g.fillRect(500+4, 200+4, 400-8, 250-8);
						g.setColor(Color.white);
						g.setFont(Game.fnt);
						Game.fnt = new Font("04B", Font.TRUETYPE_FONT, 24);
						g.drawString("Name", 520, 240);
						g.drawString("Port", 520, 315);
						g.drawString("Pass", 520, 390);
						g.setColor(Color.white);
						g.fillRect(520, 270, 360, 4);
						g.fillRect(520, 345, 360, 4);
						g.fillRect(520, 420, 360, 4);
						g.setColor(Color.red);
						g.drawRect(524, 474, 175, 44);
						g.drawRect(704, 474, 175, 44);
						break;
			case Game:
			default:
		}
	}
	
	public boolean mouseOver(double mx, double my, int x,int y, int width, int height) {
		boolean ans = false;
		if(mx > x && mx < x+width)
			if(my > y && my < y+height)
				ans = true;
		
		return ans;
	}

	public AlphaComposite setAlpha(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public void createCustomTextFields() {
		name = new CustomTextField(310, 230, 390, 40,ID.CustomTextField);
		port = new CustomTextField(310, 320, 390, 40,ID.CustomTextField);
		pass = new CustomTextField(310, 410, 390, 40,ID.CustomTextField);
		
		Game.ui.addObject(name);
		Game.ui.addObject(pass);
		Game.ui.addObject(port);
	}
}
