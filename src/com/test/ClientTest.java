package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.game.Game;
import com.game.Handler;

public class ClientTest {
	
	public Socket socket = null;
	
	private String playerID;
	private String name;
	private String ip;
	private int port;
	
	private boolean isReady = false;
	
	public ClientTest() {
		
	}
	
	public ClientTest(String playerID, String name, String ip, int port) {
		this.playerID = playerID;
		this.name = name;
		this.ip = ip;
		this.port = port;
		
		try {
			socket = new Socket("localhost",port);
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(this.name + ":" + isReady);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setSocket(Socket s) {
		this.socket = s;
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	
	public String getIP() {
		return this.ip;
	}
	
}
/*
public class ClientTest {
	private static Socket s = null;

	private String name;
	private String ip;
	private int port;
	private boolean ready; 
	
	public ClientTest(String name,int  port, String ip) throws IOException {
		s = new Socket("localhost",port);
		this.name = name;
		this.port = port;
		this.ip = ip;
		this.s = s;
		
		System.out.println("Connected.........");
		new Thread(receive).start();
		
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeUTF(this.name);
		dos.close();
	}
	
	public void setSocket(Socket s) {
		this.s= s;
	}
	
	public boolean isReady() {
		ready = true;
		return ready;
	}
	
	private Runnable send = new Runnable(){
		public void run(){
			ObjectOutputStream oos = null;
			FileOutputStream fout = null;

			DataOutputStream dos;
			try {
				dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(name);
//				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while (true){
				
			}
		}
	};

	private static Runnable receive = new Runnable(){
		public void run(){
			ObjectInputStream ois = null;
			while (true){
				try{
					ois = new ObjectInputStream(s.getInputStream());
					Game.handler = (Handler) ois.readObject();
					System.out.println("Receiving!");
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	
	public static void send() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(Game.handler);			
			System.out.println("Handler sent");			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void receive() {
		try {
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Handler temp = (Handler) ois.readObject();
			System.out.println("Handler received");			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		

	}
}
*/