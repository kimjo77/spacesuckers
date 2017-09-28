package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.game.Game;
import com.game.Handler;

public class ServerTest {
	public static int MAX_PLAYERS = 4;
	private ServerSocket ss = null;
	public Socket[] s = new Socket[MAX_PLAYERS];
	public boolean[] status = new boolean[MAX_PLAYERS];
	public String[] names = new String[MAX_PLAYERS];
	public int currentcount = 0;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public ServerTest(String name,int  port, String Password) throws IOException {
		ss = new ServerSocket(port);
		this.names[currentcount] = name;
		this.status[currentcount] = true;
		currentcount++;
		new Thread(accept).start();
		//new Thread(receive).start();
	}
	
	private Runnable accept = new Runnable(){
		@Override
		public void run(){
			new Thread(receive).start();
			//ObjectInputStream ois = null;
			boolean accepting = true;
			while (accepting){
				if(currentcount <= MAX_PLAYERS) {
					try{
						Socket socket = ss.accept();
						s[currentcount] =  socket;
						ois = new ObjectInputStream(s[currentcount].getInputStream());
						names[currentcount] = (String) ois.readObject();
						status[currentcount] = false;
						System.out.println(names[currentcount]+ " has connected.");
						currentcount++;
					}
					catch (Exception ex) {}
				} else {
					accepting = false;
					System.out.println("Lobby is full");
				}
			}
		}
	};
	
	private Runnable receive =  new Runnable() {
		@Override
		public void run() {
			//ObjectInputStream ois = null;
			while(true) {
				for(int i=1; i<currentcount; i++) {
					try {
						ois = new ObjectInputStream(s[i].getInputStream());
						Data d = (Data) ois.readObject();
						System.out.println(d.isReady);					
						switch(d.areatype) {
							case lobby:
								Game.server.status[i] = d.isReady;
								break;
							case game:
								Game.data.handler = d.handler;
								break;
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
				} 
			}
		}
	};
	
	public void send(boolean[] status, String[] names) {
		Data data = new Data(status,names);
		writeData(data);
	}
	public void send(boolean isReady) {
		Data data = new Data(isReady);
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
	
	public void writeData(Data data) {
		ObjectOutputStream oos = null;
		for(int i=0; i<currentcount; i++) {
			try {
				oos = new ObjectOutputStream(s[i].getOutputStream());
				oos.writeObject(data);
			} catch (Exception e) {
			}
		}
	}
	
/*	private static Runnable send = new Runnable(){
		public void run(){
			ObjectOutputStream oos = null;
			while (true){
				try {
					oos = new ObjectOutputStream(s[currentIndex].getOutputStream());
					oos.writeObject(Game.handler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
	private static Runnable receive = new Runnable(){
		public void run(){
			ObjectInputStream ois;
			while (true){
				try
				{
					//ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					//int receive_state = (Integer) ois.readObject();

					//ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					//DataPackage dp = (DataPackage) ois.readObject();

					//list_data.set(i, dp);
					
					ois = new ObjectInputStream(s[currentIndex].getInputStream());
					names[currentIndex] = (String) ois.readObject();
					System.out.println(names[currentIndex] + "has connected.");
				}
				catch (Exception e) // Client Disconnected (Client Didn't Notify Server About Disconnecting)
				{
					//e.printStackTrace();
				}
			}
		}
	};*/
}