package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import com.entities.GameObject;
import com.game.Game;
import com.game.Handler;

public class ServerTest extends Thread{
	public static int MAX_PLAYERS = 4;
	private ServerSocket ss = null;
	private InetAddress serverIP;
	//--Client Info
	public Socket[] s = new Socket[MAX_PLAYERS];
	public boolean[] status = new boolean[MAX_PLAYERS];
	public String[] names = new String[MAX_PLAYERS];
	public int currentcount = 0;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	public ServerTest(String name,int  port, String Password) throws IOException {
		serverIP = InetAddress.getLocalHost();
		System.out.println(serverIP);
		ss = new ServerSocket(port,0,serverIP);
		this.names[currentcount] = name;
		this.status[currentcount] = true;
		Game.data.names[currentcount] = name;
		Game.data.status[currentcount] = true;
		currentcount++;
		//new Thread(accept).start();
		this.start();
	}
	
	@Override
	public void run() {
		while (currentcount < MAX_PLAYERS){
			try{
				Socket socket = ss.accept();
				s[currentcount] =  socket;
				ois = new ObjectInputStream(s[currentcount].getInputStream());
				Data d = (Data)ois.readObject();
				names[currentcount] = d.name;
				status[currentcount] = false;
				Game.data.names[currentcount] = d.name;
				Game.data.status[currentcount] = false;
				System.out.println(names[currentcount]+ " has connected." + currentcount);
				new ClientThread(socket,currentcount).start();
				currentcount++;
			}
			catch (Exception ex) {}
		}
	}
	
	public void send(boolean[] status, String[] names) {
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
	public void send(LinkedList<GameObject> object)  {
		Data data = new Data(object);
		writeData(data);
	}
	
	public void writeData(Data data) {
		for(int i=1; i<MAX_PLAYERS; i++) {
			if(s[i] != null) {
				try {
					oos = new ObjectOutputStream(s[i].getOutputStream());
					oos.writeObject(data);
					s[i].getOutputStream().flush();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public String getIPAddress() {
		return serverIP.toString();
	}
}