package com.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.entities.GameObject;
import com.entities.Player;
import com.main.CustomTextField;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private GameObject tempObject;
	private Player playerObject;
	private CustomTextField tempTF;
	private Gameover gameoverObject;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(Game.networkType == Game.NETWORK_TYPE.Client) {
			if(e.getButton() == MouseEvent.BUTTON1) Game.pi.mouse_shoot = 1;
		} else {
			for(int i=0; i < handler.object.size(); i++) {
				tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					playerObject = (Player)tempObject;
					if(playerObject.playerID == 0) {
						if(e.getButton() == MouseEvent.BUTTON1) {
							if(playerObject.mouse_shoot == 0) {
								playerObject.mouse_shoot = 1;
							}
						}
					}
				}
				if(tempObject.getId() == ID.CustomTextField) {
					tempTF = (CustomTextField)tempObject;
					if(mouseOver(mx, my, tempTF.getX(), tempTF.getY(), tempTF.getWidth(), tempTF.getHeight())){
						tempTF.setFocus(true);
						System.out.println("INSIDE!!!");
					} else {
						tempTF.setFocus(false);
					}
				}
				if(tempObject.getId() == ID.Gameover) {
					//g.fillRect(x+40, y+120, 150, 40);
					//g.fillRect(x+210, y+120, 150, 40);
					gameoverObject = (Gameover)tempObject;
					if(mouseOver(mx, my, gameoverObject.getX()+40, gameoverObject.getY()+120, 150, 40)) {
						Game.gameRestart();
					} else if(mouseOver(mx, my, gameoverObject.getX()+210, gameoverObject.getY()+120, 150, 40)) {
						Game.handler.clearObject();
						Game.ui.clearObject();
						Game.gameState = Game.STATE.Menu;
					}
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		if(Game.networkType == Game.NETWORK_TYPE.Client) {
			if(e.getButton() == MouseEvent.BUTTON1) Game.pi.mouse_shoot = 0;
		} else {
			for(int i=0; i < handler.object.size(); i++) {
				tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					playerObject = (Player)tempObject;
					if(playerObject.playerID == 0) {
						if(e.getButton() == MouseEvent.BUTTON1) playerObject.mouse_shoot = 0;
					}
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if(Game.networkType == Game.NETWORK_TYPE.Client) {
			Game.pi.mouseX = e.getX();
			Game.pi.mouseY = e.getY();
		} else {
			for(int i=0; i < handler.object.size(); i++) {
				tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					playerObject = (Player)tempObject;
					if(playerObject.playerID == 0) {
						playerObject.mouseX = e.getX();
						playerObject.mouseY = e.getY();
					}
				}
			}
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
