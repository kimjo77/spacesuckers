package com.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import com.game.Asteroid;
import com.game.AsteroidController;
import com.game.Blackhole;
import com.game.Game;
import com.game.ID;
import com.game.Player;
import com.game.Texture;

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
							Game.handler.addObject(new Blackhole(Game.WIDTH/2,Game.HEIGHT/2,16,16,ID.Blackhole));
							Game.handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
							Game.handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
							Game.handler.addObject(new Player(Game.WIDTH/2,Game.HEIGHT/2,24,12,ID.Player));
							Game.gameState = Game.STATE.Game;
							System.out.println("Singleplayer!");
						}
						if(mouseOver(mx,my,345,350+(1*85),285,73)) {
							Game.gameState = Game.STATE.Multi;
							System.out.println("Multiplayer!");
						}
						if(mouseOver(mx,my,345,350+(2*85),285,73)) {
							quit = true;
							System.out.println("Quit!");
						}	
					}else {
						if(mouseOver(mx,my,270,350,203,51)) {
							System.out.println("Quit!");
							System.exit(0);
						}
						if(mouseOver(mx,my,530,350,203,51)) {
							quit = false;
							System.out.println("CANCEL!");
						}
					}
					break;
		case Multi:
					if(mouseOver(mx,my,345,350+(0*85),285,73)) {
						name = new CustomTextField(310, 230, 390, 40,ID.CustomTextField);
						port = new CustomTextField(310, 320, 390, 40,ID.CustomTextField);
						pass = new CustomTextField(310, 410, 390, 40,ID.CustomTextField);
						
						Game.handler.addObject(name);
						Game.handler.addObject(pass);
						Game.handler.addObject(port);
						Game.gameState = Game.STATE.Create;
						System.out.println("Create!");
					}
					if(mouseOver(mx,my,345,350+(1*85),285,73)) {
						System.out.println("Connect!");
					}
					if(mouseOver(mx,my,345,350+(2*85),285,73)) {
						Game.gameState = Game.STATE.Menu;
						System.out.println("Back!");
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
		/*g2d.drawImage((Image)tex.bg,0,0,null);
		g2d.drawImage((Image)tex.logo,Game.WIDTH/2-(tex.logo.getWidth()/2),Game.HEIGHT/3 - (tex.logo.getHeight()),null);
		for(int i=0; i<3; i++) {
			g2d.drawImage((Image)tex.menuBtn[i],Game.WIDTH/2-(tex.menuBtn[i].getWidth()/2),Game.HEIGHT/2 - (tex.menuBtn[i].getHeight()/2)+i*tex.menuBtn[i].getHeight(),null);
			g.drawRect(Game.WIDTH/2-(tex.menuBtn[i].getWidth()/2)+14, Game.HEIGHT/2-(tex.menuBtn[i].getHeight()/2)+i*tex.menuBtn[i].getHeight()+10, (int)tex.menuBtn[i].getWidth()-29, (int)tex.menuBtn[i].getHeight()-20);
			//g.drawRect(Game.WIDTH/2, y, width, height);
		}*/
		
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
						break;
			case Connect:
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
}
