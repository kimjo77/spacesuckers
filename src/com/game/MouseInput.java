package com.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private GameObject tempObject;
	private Player playerObject;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(playerObject.mouse_shoot == 0) {
						playerObject.mouse_shoot = 1;
					}
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				if(e.getButton() == MouseEvent.BUTTON1) playerObject.mouse_shoot = 0;
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for(int i=0; i < handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				playerObject = (Player)tempObject;
				playerObject.mouseX = e.getX();
				playerObject.mouseY = e.getY();
			}
		}
	}
}
