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
	private boolean serverSuccess = true;
	private boolean connectSuccess = true;
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
						if(mouseOver(mx,my,370,340,145,36)) {
							System.exit(0);
						} else if(mouseOver(mx,my,530,340,145,36)) {
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
					if(!serverSuccess) {
						if(mouseOver(mx,my,425,343,145,36)) {
							serverSuccess = true;	
							createCreateTextFields();
						}
					} else {
						if(mouseOver(mx,my,355,410,145,36)) {
							Game.ui.clearObject();
							System.out.println("Multi!");
							Game.gameState = Game.STATE.Multi;
						} else if(mouseOver(mx,my,515,410,145,36)) {
								if(!name.getText().contains("") || name.getText() != null) {
									if(isNumeric(port.getText())){
										Game.networkType = Game.NETWORK_TYPE.Server;
										try {
											Game.server = new ServerTest(name.getText(),Integer.parseUnsignedInt(port.getText()),null);
											Game.gameState = Game.STATE.Lobby;
										} catch (NumberFormatException e1) {
											System.out.println("string churva");
											serverSuccess = false;
										} catch (IOException e1) {
											serverSuccess = false;
										} catch (NullPointerException e1) {
											System.out.println("null churva");
											serverSuccess = false;
										};
									} else {
										serverSuccess = false;
									}
								} else {
									serverSuccess = false;
								}
								Game.ui.clearObject();
						}
					}
					
					break;
		case Connect: 
					if(!connectSuccess) {
						if(mouseOver(mx,my,425,343,145,36)) {
							connectSuccess = true;	
							createConnectTextFields();
						}
					} else {
						if(mouseOver(mx,my,355,430,145,36)) {
							Game.ui.clearObject();
							System.out.println("Back!");
							Game.gameState = Game.STATE.Multi;
						} else if(mouseOver(mx,my,515,430,145,36)) {
							if(!name.getText().contains("") || name.getText() != null) {
								if(!pass.getText().contains("") || pass.getText() != null) {
									if(isNumeric(port.getText())){
										Game.networkType = Game.NETWORK_TYPE.Client;
										try {
											Game.client = new ClientTest(name.getText(),Integer.parseUnsignedInt(port.getText()),pass.getText());
											Game.gameState = Game.STATE.Lobby;
										} catch (NumberFormatException e1) {
											System.out.println("string churva");
											connectSuccess = false;
										} catch (IOException e1) {
											connectSuccess = false;
										} catch (NullPointerException e1) {
											System.out.println("null churva");
											connectSuccess = false;
										}
									} else {
										connectSuccess = false;
									}
								} else {
									connectSuccess = false;
								}
							} else {
								connectSuccess = false;
							}
							Game.ui.clearObject();
						}
					}
					
					break;
		case Lobby:
					if(mouseOver(mx,my,323, 465, 175, 44)) {
							//Disconnect / delete lobby
						Game.networkType = null;
						Game.gameState = Game.STATE.Multi;
					} else if(mouseOver(mx,my, 510, 465, 175, 44)) {
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
						g.setColor(Color.red);
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
							g.setColor(Color.white);
							g.fillRect(250, 220, 510, 220);
							g.setColor(Color.black);
							g.fillRect(250+4, 220+4, 510-8, 220-8);
							g2d.drawImage(tex.quitwarning, 255, 260, null);
							g2d.drawImage(tex.quit, 350, 340, null);
							g2d.drawImage(tex.cancel, 520, 340, null);
							g.setColor(Color.red);
							g.drawRect(350,340,145,36);
							g.drawRect(520,340,145,36);
						}
						
						break;
			case Multi:
						g2d.drawImage(tex.bg,0,0,null);
						g2d.drawImage(tex.menu_blackhole[0],90,-70,null);
						g2d.drawString("CREATE",440,310);
						g2d.drawString("CONNECT",430,355);
						g2d.drawString("BACK",465,400);
						g.setColor(Color.red);
						g.drawRect(440,290,140,20);
						g.drawRect(430,335,160,20);
						g.drawRect(465,380,90,20);

						break;
			case Create:
						g2d.drawImage(tex.bg, 0, 0, null);
//						g2d.drawImage(tex.menu_blackhole[0],90,-70,null);
						g2d.drawImage(tex.createHeader, 295, 170, null);
						g2d.drawString("NAME",330, 260);
						g2d.drawString("PORT",330, 340);
						g2d.drawImage(tex.back, 355, 410, null);
						g2d.drawImage(tex.create, 515, 410, null);
						
						g.setColor(Color.white);
						g.fillRect(330, 290, 350, 4);
						g.fillRect(330, 370, 350, 4);
						g.setColor(Color.red);
						g.drawRect(355,410,145,36);
						g.drawRect(515,410,145,36);

						if(!serverSuccess) {
							g.setColor(Color.white);
							g.fillRect(250, 220, 510, 220);
							g.setColor(Color.black);
							g.fillRect(250+4, 220+4, 510-8, 220-8);
							g.setColor(Color.white);
							g2d.drawString("Something's wrong!", 330, 290);
							g2d.drawString("Please try again", 340, 320);
							g2d.drawString("OKAY", 450, 371);
							g.setColor(Color.red);
							g.drawRect(425,343,145,36);
						}
						
						break;						
			case Connect:
						g2d.drawImage(tex.bg, 0, 0, null);
						g2d.drawImage(tex.connectHeader, 375, 120, null);
						g2d.drawString("NAME",330,210);
						g2d.drawString("PORT",330,290);
						g2d.drawString("IP",330,370);
						g2d.drawImage(tex.back, 355, 430, null);
						g2d.drawImage(tex.connect, 515, 430, null);

						g.setColor(Color.white);
						g.fillRect(330, 240, 350, 4);
						g.fillRect(330, 320, 350, 4);
						g.fillRect(330, 400, 350, 4);
						g.setColor(Color.red);
						g.drawRect(355,430,145,36);
						g.drawRect(515,430,145,36);
						
						if(!connectSuccess) {
							g.setColor(Color.white);
							g.fillRect(250, 220, 510, 220);
							g.setColor(Color.black);
							g.fillRect(250+4, 220+4, 510-8, 220-8);
							g.setColor(Color.white);
							g2d.drawString("Something's wrong!", 330, 290);
							g2d.drawString("Please try again", 340, 320);
							g2d.drawString("OKAY", 450, 371);
							g.setColor(Color.red);
							g.drawRect(425,343,145,36);
						}
						break;
			case Lobby:
						g2d.drawImage(tex.bg, 0, 0, null);
						g2d.drawString("NAME",330, 240);
						g2d.drawString("PORT",330, 320);
						g2d.drawString("IP", 330, 400);

						g.setColor(Color.white);
						g.fillRect(330, 270, 350, 4);
						g.fillRect(330, 350, 350, 4);
						g.fillRect(330, 430, 350, 4);

						if(Game.networkType == Game.NETWORK_TYPE.Server) {
							g2d.drawString("GAME LOBBY", 385, 150);
							g2d.drawString("START", 540, 497);
						} else if(Game.networkType == Game.NETWORK_TYPE.Client) {
							g2d.drawString("GAME LOBBY", 385, 150);
							g2d.drawImage(tex.ready_s, 510, 465, null);
						}
																	
						g.setColor(Color.white);
						Game.fnt = new Font("Connection",Font.PLAIN,24);
						g2d.setFont(Game.fnt);
						g2d.drawImage(tex.back_s, 323, 465, null);
						g2d.drawString(name.getText(), 330, 265);
						g2d.drawString(port.getText(), 330, 345);

						Game.fnt = new Font("Connection",Font.PLAIN,16);
						g2d.setFont(Game.fnt);

						if(Game.networkType == Game.NETWORK_TYPE.Server) {
							g2d.drawString(Game.server.getIPAddress(), 330, 425);
							for(int i=0; i < ServerTest.MAX_PLAYERS; i++) {
								if(Game.server.names[i] != null) {
									g.drawImage(tex.player[i],240 + (i*150),570,null);
									if(Game.server != null && Game.server.status[i] == true) {
										g.drawString(Game.server.names[i] + " ready", 220 + (i*150), 640);										
									} else {
										g.drawString("waiting for", 210 + (i*150), 640);
										g.drawString(Game.server.names[i], 240 + (i*150), 657);
									}
								}
							}		
						} else if(Game.networkType == Game.NETWORK_TYPE.Client) {
							g2d.drawString(pass.getText(), 330, 425);							
							if(Game.client != null) {
								for(int i=0; i < ServerTest.MAX_PLAYERS; i++) {
									if(Game.data.names[i] != null) {
										g.drawImage(tex.player[i],240 + (i*150),570,null);
										if(Game.client != null && Game.data.status[i] == true) {
											g.drawString(Game.data.names[i] + " ready", 220 + (i*150), 640);										
										} else {
											g.drawString("waiting for", 210 + (i*150), 640);
											g.drawString(Game.data.names[i], 240 + (i*150), 657);
										}
									}
								}/*if(Game.data.names != null && Game.data.status != null) {
									for(int i=0; i < ServerTest.MAX_PLAYERS; i++) {
										if(Game.data.names[i] != null) {
											g.drawImage(tex.player[i],240 + (i*150),570,null);
											g.drawString("waiting for", 210 + (i*150), 640);
											g.drawString(Game.data.names[i], 240 + (i*150), 657);										
										} else if(Game.data.status[i]) {
											g.drawString(Game.data.names[i] + " ready", 220 + (i*150), 640);										
										} else {
										
										}
									}
								}*/
							}				
						}
						g.setColor(Color.red);
						g.drawRect(323, 465, 175, 44);
						g.drawRect(510, 465, 175, 44);
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
		name = new CustomTextField(330, 220, 350, 15, ID.CustomTextField);
		port = new CustomTextField(330, 300, 350, 15, ID.CustomTextField);
		pass = new CustomTextField(330, 380, 350, 15, ID.CustomTextField);
		
		Game.ui.addObject(name);
		Game.ui.addObject(port);
		Game.ui.addObject(pass);
	}
	
	public void createCreateTextFields() {
		name = new CustomTextField(330, 270, 350, 15, ID.CustomTextField);
		port = new CustomTextField(330, 350, 350, 15, ID.CustomTextField);
		
		Game.ui.addObject(name);
		Game.ui.addObject(port);
	}
	
	public static boolean isNumeric(String str) {  
		try {  
			double d = Double.parseDouble(str);  
		} catch(NumberFormatException nfe) {  
			return false;  
		}  
			return true;  
	}
}
