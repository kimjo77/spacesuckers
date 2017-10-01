package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.entities.GameObject;
import com.entities.Player;
import com.main.CustomTextField;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private int key;
	private GameObject tempObject;
	private Player tempPlayer;
	private CustomTextField tempTF;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyTyped(KeyEvent e) {
		for(int i=0; i<handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.CustomTextField) {
				tempTF = (CustomTextField)tempObject;
				if(tempTF.getFocus()) {
					if((int)e.getKeyChar() == 8) { // BackSpace
						if(tempTF.getText().length() != 0) {
							tempTF.setText(tempTF.getText().substring(0, tempTF.getText().length()-1));
						}
					}else {
						Game.soundfx.play(Game.soundfx.type);
						tempTF.setText(tempTF.getText() + e.getKeyChar());
					}
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		if(Game.networkType == Game.NETWORK_TYPE.Client) {
			if(key == KeyEvent.VK_A) Game.pi.key_left = -1;
			if(key == KeyEvent.VK_D) Game.pi.key_right = 1;
			if(key == KeyEvent.VK_W) Game.pi.key_up = 1;
			if(key == KeyEvent.VK_LEFT) Game.pi.turrentKey_left = -1;
			if(key == KeyEvent.VK_RIGHT) Game.pi.turrentKey_right = 1;
			if(key == KeyEvent.VK_SPACE) {
				if(Game.pi.key_shoot == 0) {
					Game.pi.key_shoot = 1;
				} 
			}
		} else {
			for(int i=0; i<handler.object.size(); i++) {
				tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					tempPlayer = (Player)tempObject;
					if(tempPlayer.playerID == 0) {
						if(key == KeyEvent.VK_A) tempPlayer.key_left = -1;
						if(key == KeyEvent.VK_D) tempPlayer.key_right = 1;
						if(key == KeyEvent.VK_W) tempPlayer.key_up = 1;
						//if(key == KeyEvent.VK_S) tempPlayer.key_down = -1;
						if(key == KeyEvent.VK_LEFT) tempPlayer.turrentKey_left = -1;
						if(key == KeyEvent.VK_RIGHT) tempPlayer.turrentKey_right = 1;
						if(key == KeyEvent.VK_SPACE) {
							if(tempPlayer.key_shoot == 0) {
								tempPlayer.key_shoot = 1;
							}
						}
					}
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		if(Game.networkType == Game.NETWORK_TYPE.Client) {
			if(key == KeyEvent.VK_A) Game.pi.key_left = 0;
			if(key == KeyEvent.VK_D) Game.pi.key_right = 0;
			if(key == KeyEvent.VK_W) Game.pi.key_up = 0;
			if(key == KeyEvent.VK_LEFT) Game.pi.turrentKey_left = 0;
			if(key == KeyEvent.VK_RIGHT) Game.pi.turrentKey_right = 0;
			if(key == KeyEvent.VK_SPACE) Game.pi.key_shoot = 0;
		} else {
			for(int i=0; i<handler.object.size(); i++) {
				tempObject  = handler.object.get(i);
				if(tempObject.getId() == ID.Player) {
					tempPlayer = (Player)tempObject;
					if(tempPlayer.playerID == 0) {
						if(key == KeyEvent.VK_A) tempPlayer.key_left = 0;
						if(key == KeyEvent.VK_D) tempPlayer.key_right = 0;
						if(key == KeyEvent.VK_W) tempPlayer.key_up = 0;
						if(key == KeyEvent.VK_LEFT) tempPlayer.turrentKey_left = 0;
						if(key == KeyEvent.VK_RIGHT) tempPlayer.turrentKey_right = 0;
						if(key == KeyEvent.VK_SPACE) tempPlayer.key_shoot = 0;
						//if(key == KeyEvent.VK_S) tempPlayer.key_down = 0;
					}
				}
			}
		}
	}
}
