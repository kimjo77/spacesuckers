package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

import com.entities.GameObject;
import com.entities.Player;
import com.game.Game;
import com.game.ID;

public class ClientThread extends Thread {
		protected Socket s;
		protected int index;
		ClientThread(Socket s,int index){
			this.s = s;
			this.index = index;
		}
		
		@Override
		public void run() {
			ObjectInputStream ois = null;
			while(true) {
				if(s.isConnected()) {
					try {
						ois = new ObjectInputStream(s.getInputStream());
						Data d = (Data) ois.readObject();
						switch(d.areatype) {
							case lobby:
								Game.server.status[index] = d.bool;
								break;
							case game:
								for(int i=0; i<Game.handler.object.size(); i++) {
									GameObject tempObject = Game.handler.object.get(i);
									if(tempObject.getId() == ID.Player) {
										Player playerObject = (Player) tempObject;
										if(playerObject.playerID == index) {
											playerObject.key_up = d.pi.key_up;
											playerObject.key_down = d.pi.key_down;
											playerObject.key_left = d.pi.key_left;
											playerObject.key_right = d.pi.key_right;
											playerObject.key_shoot = d.pi.key_shoot;
											playerObject.mouse_shoot = d.pi.mouse_shoot;
											playerObject.turrentKey_left = d.pi.turrentKey_left;
											playerObject.turrentKey_right = d.pi.turrentKey_right;
											playerObject.mouseX = d.pi.mouseX;
											playerObject.mouseY = d.pi.mouseY;
										}
									}
								}
								break;
						}
					} catch (IOException e) {
						System.out.println(s + " disconnected");
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
}
