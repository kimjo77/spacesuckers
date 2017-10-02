package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.game.Game;
import com.game.ID;
import com.game.SoundEffects;
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
						if(mouseOver(mx,my,500,475,280,20)) {
							Game.gamestart("single");
							Game.gameState = Game.STATE.Game;
						}else if(mouseOver(mx,my,520,520,240,20)) {
							Game.gameState = Game.STATE.Multi;
						} else if(mouseOver(mx,my,540,565,192,20)) {
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
					if(mouseOver(mx,my,440,290,140,20)) {
						createCreateTextFields();
						Game.gameState = Game.STATE.Create;
					}else if(mouseOver(mx,my,430,335,160,20)) {
						createConnectTextFields();
						Game.gameState = Game.STATE.Connect;
					}else if(mouseOver(mx,my,465,380,90,20)) {
						Game.gameState = Game.STATE.Menu;
					}
					break;
		case Create:
					if(mouseOver(mx,my,295,520,203,50)) {
						Game.ui.clearObject();
						System.out.println("Multi!");
						Game.gameState = Game.STATE.Multi;
					} else if(mouseOver(mx,my,515,520,203,50)) {
						Game.networkType = Game.NETWORK_TYPE.Server;
						try {
							Game.server = new ServerTest(name.getText(),Integer.parseUnsignedInt(port.getText()),null);
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Game.ui.clearObject();
						Game.gameState = Game.STATE.Lobby;
					}
					break;
		case Connect:
					if(mouseOver(mx,my,295,520,203,50)) {
						Game.ui.clearObject();
						System.out.println("Multi!");
						Game.gameState = Game.STATE.Multi;
					} else if(mouseOver(mx,my,515,520,203,50)) {
						Game.networkType = Game.NETWORK_TYPE.Client;
						try {
							Game.client = new ClientTest(name.getText(),Integer.parseUnsignedInt(port.getText()),pass.getText());
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Game.ui.clearObject();
						Game.gameState = Game.STATE.Lobby;
					}
					break;
					
		case Lobby:
					if(mouseOver(mx,my,524, 474, 175, 44)) {
							//Disconnect / delete lobby
						Game.networkType = null;
						Game.gameState = Game.STATE.Multi;
					} else if(mouseOver(mx,my, 704, 474, 175, 44)) {
						switch(Game.networkType) {
							case Server:
										Game.gamestart("multi");
										Game.server.send(true);
										Game.gameState = Game.STATE.Game;
										break;
							case Client:
										Game.client.send(Game.isReady = !Game.isReady);
										break;
						}
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
		Game.fnt = new Font("04B", Font.PLAIN, 24);
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
						g2d.drawImage(tex.logo,200,130,null);
						g2d.drawString("SINGLE PLAYER", 500, 460 + (35));
						g2d.drawString("MULTIPLAYER", 520, 470 + (2*35));
						g2d.drawString("QUIT GAME", 540, 480 + (3*35));
						g.drawRect(500,475,280,20);
						g.drawRect(520, 520, 240,20);
						g.drawRect(540, 565,192,20);
							//g2d.drawString(string,x,y);
							//Game.fnt = new Font("Connection", Font.PLAIN, int size);
							//g.setColor(Color color);
							//g.setFont(Font font);
							//g.fillRect(x,y,width,height)
							//g.drawRect(x,y,width,height)
						
						if(quit) {
							g2d.drawImage(tex.bgpane,100,30,null);
							g2d.drawImage(tex.quitwarning, 255, 260, null);
							g2d.drawImage(tex.quit, 370, 340, null);
							g2d.drawImage(tex.cancel, 530, 340, null);
						}
						
						break;
			case Multi:
						g2d.drawImage(tex.bg,0,0,null);
						g2d.drawString("CREATE",440,310);
						g2d.drawString("CONNECT",430,355);
						g2d.drawString("BACK",465,400);
						g.drawRect(440,290,140,20);
						g.drawRect(430,335,160,20);
						g.drawRect(465,380,90,20);

						break;
			case Create:
						g2d.drawImage(tex.bg, 0, 0, null);
						g2d.drawImage(tex.createHeader, 265, 160, null);
						g2d.drawString("NAME",330, 280);
						g2d.drawString("PORT",330, 370);
						g2d.drawImage(tex.back, 355, 450, null);
						g2d.drawImage(tex.create, 515, 450, null);
/*						g.setColor(Color.white);
						g.fillRect(280, 180, 450, 300);
						g.setColor(Color.black);
						g.fillRect(280+6, 180+6, 450-12, 300-12);
						g.setColor(Color.white);
*/						g.setFont(Game.fnt);
						Game.fnt = new Font("Connection", Font.TRUETYPE_FONT, 32);
						g.setColor(Color.white);
						g.fillRect(330, 320, 350, 4);
						g.fillRect(330, 410, 350, 4);
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
						Game.fnt = new Font("04B", Font.TRUETYPE_FONT, 32);
						if(Game.networkType == Game.NETWORK_TYPE.Server) {
							if(Game.server != null) {
								for(int i=0; i < ServerTest.MAX_PLAYERS; i++) {
									if(Game.server.names[i] != null) {
										g.drawString(Game.server.names[i],100,250+(100*i));
										if(Game.server.status[i])
											g2d.drawImage(tex.ready, 300, 225+(100*i), null);
									}
								}
							}
						} else if(Game.networkType == Game.NETWORK_TYPE.Client) {
							if(Game.client != null) {
								if(Game.data.names != null && Game.data.status != null)
								for(int i=0; i < ServerTest.MAX_PLAYERS; i++) {
									if(Game.data.names[i] != null) {
										g.drawString(Game.data.names[i],100,250+(100*i));
										if(Game.data.status[i])
											g2d.drawImage(tex.ready, 300, 225+(100*i), null);
									}
								}
							}
						}
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
			if(my > y && my < y+height) {
				ans = true;
				Game.soundfx.play(Game.soundfx.blip);
			}
		
		return ans;
	}

	public AlphaComposite setAlpha(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
	
	public void createConnectTextFields() {
		name = new CustomTextField(310, 230, 390, 40,ID.CustomTextField);
		port = new CustomTextField(310, 320, 390, 40,ID.CustomTextField);
		pass = new CustomTextField(310, 410, 390, 40,ID.CustomTextField);
		
		Game.ui.addObject(name);
		Game.ui.addObject(port);
		Game.ui.addObject(pass);
	}
	
	public void createCreateTextFields() {
		name = new CustomTextField(330, 280, 350, 40,ID.CustomTextField);
		port = new CustomTextField(330, 370, 350, 40,ID.CustomTextField);
		
		Game.ui.addObject(name);
		Game.ui.addObject(port);
	}
}
