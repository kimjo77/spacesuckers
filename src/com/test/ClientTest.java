package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.game.Game;
import com.game.Handler;
import com.game.SoundEffects;

public class ClientTest {
	private Socket s = null;
	private String serverIP = "";
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	public ClientTest(String name,int  port, String ip) throws IOException {
		s = new Socket(ip,port);
		serverIP = ip;
		System.out.println("Connected.........");
		send(name);
		new Thread(receive).start();
	}
	
	private Runnable receive = new Runnable() {
		public void run() {
			while(true) {
				try {//System.out.println("WORKING");
					ois = new ObjectInputStream(s.getInputStream());
					Data d = (Data) ois.readObject();
					switch(d.areatype) {
					case lobby:
						Game.data.names = d.names;
						Game.data.status = d.status;
						Game.data.bool = d.bool;
						if(d.bool) {
							Game.START = true;
							Game.soundfx.loop(SoundEffects.bgmultiClip);
							Game.soundfx.loop(Game.soundfx.blackholeBG);
							Game.gameState = Game.STATE.Game;
						}
						break;
					case game:
						Game.data.handler = d.handler;
						Game.data.object = d.object;
						Game.handler.object = d.object;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 			}
		}
	};
	
	public void send(boolean[] status, String[] names, boolean start) {
		Data data = new Data(status,names);
		writeData(data);
	}
	public void send(boolean bool) {
		Data data = new Data(bool);
		writeData(data);
	}
	public void send(String name) {
		Data data = new Data(name);
		writeData(data);
	}
	public void send(String[] inputs) {
		Data data = new Data(inputs);
		writeData(data);
	}
	public void send(Handler handler) {
		Data data = new Data(handler);
		writeData(data);
	}
	public void send(PlayerInputs pi) {
		Data data = new Data(pi);
		writeData(data);
	}
	
	public void writeData(Data data) {
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(data);
			s.getOutputStream().flush();
		} catch (Exception e) {
			System.out.println("Data Not Sent");
		}
	}
}
