package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.game.Asteroid;
import com.game.AsteroidController;
import com.game.Blackhole;
import com.game.Game;
import com.game.ID;
import com.game.Player;
import com.game.Texture;

public class Menu extends MouseAdapter{
	
	private Texture tex = Game.tex;
	private int mx,my;
	private int splashTime,
				sx = 0,
				sy = 0 - tex.splash.getHeight() + Game.HEIGHT,
				//sy = 0,
				sspd = 1;
	
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		switch(Game.gameState) {
		case Menu:
					if(mouseOver(mx,my,345,350+(0*85),285,73)) {
						Game.handler.addObject(new Player(Game.WIDTH/2,Game.HEIGHT/2,24,12,ID.Player));
						Game.handler.addObject(new Blackhole(Game.WIDTH/2,Game.HEIGHT/2,16,16,ID.Blackhole));
						Game.handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
						Game.handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
						Game.gameState = Game.STATE.Game;
						System.out.println("Singleplayer!");
					}
					if(mouseOver(mx,my,345,350+(1*85),285,73)) {
						Game.gameState = Game.STATE.Multi;
						System.out.println("Multiplayer!");
					}
					if(mouseOver(mx,my,345,350+(2*85),285,73)) {
						System.exit(0);
						System.out.println("Quit!");
					}	
					break;
		case Multi:
					if(mouseOver(mx,my,345,350+(0*85),285,73))
						System.out.println("Create!");
					if(mouseOver(mx,my,345,350+(1*85),285,73))
						System.out.println("Connect!");
					if(mouseOver(mx,my,345,350+(2*85),285,73))
						Game.gameState = Game.STATE.Menu;
						System.out.println("Back!");
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
						if(sy < 0) {
							sy += sspd;
						} else {
							Game.gameState = Game.STATE.Menu;
						}
						break;
			default:
				
		}
	}
	
	public void render(Graphics g) {
		//FontMetrics fntm= g.getFontMetrics(fnt);
		//g.setFont(fnt);
		//g.drawString("Suckers", Game.WIDTH/2 - fntm.stringWidth("Suckers")/2, Game.HEIGHT/3 - fntm.getHeight()/2+fntm.getHeight()-10);
		g.setColor(Color.red);
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
						g2d.drawImage((Image)tex.splash, sx, sy, null);
						break;
			case Menu:
						g2d.drawImage((Image)tex.bg,0,0,null);
						g2d.drawImage((Image)tex.logo,230,80,null);
						for(int i=0; i<3; i++) {
							g2d.drawImage((Image)tex.menuBtn[i], 330, 340 + (i*85), null);
							g.drawRect(345,350+(i*85),285,73);
						}
						break;
			case Multi:
						g2d.drawImage((Image)tex.bg,0,0,null);
						g2d.drawImage((Image)tex.logo,230,80,null);
						for(int i=0; i<3; i++) {
							g2d.drawImage((Image)tex.multiBtn[i], 330, 340 + (i*85), null);
							g.drawRect(345,350+(i*85),285,73);
						}
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

}
