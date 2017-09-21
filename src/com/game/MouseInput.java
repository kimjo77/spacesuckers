package com.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.main.CustomTextField;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private GameObject tempObject;
	private Player playerObject;
	private CustomTextField tempTF;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
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
			if(tempObject.getId() == ID.CustomTextField) {
				tempTF = (CustomTextField)tempObject;
				if(mouseOver(mx, my, tempTF.getX(), tempTF.getY(), tempTF.getWidth(), tempTF.getHeight())){
					tempTF.setFocus(true);
					System.out.println("INSIDE!!!");
				} else {
					tempTF.setFocus(false);
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
	
	public boolean mouseOver(double mx, double my, int x,int y, int width, int height) {
		boolean ans = false;
		if(mx > x && mx < x+width)
			if(my > y && my < y+height)
				ans = true;
		
		return ans;
	}
}
