package com.test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.game.Game;
import com.game.Handler;

public class ClientTest {
	private static Socket s = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	public ClientTest(String name,int  port, String Password) throws IOException {
		//ObjectOutputStream oos = null;
		
		s = new Socket("localhost",port);
		System.out.println("Connected.........");
		
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(receive).start();
	}
	
	private Runnable receive = new Runnable() {
		public void run() {
			//ObjectInputStream ois = null;
			while(true) {
				try {
					ois = new ObjectInputStream(s.getInputStream());
					Game.data = (Data) ois.readObject();
				} catch (IOException e) {
				} catch (ClassNotFoundException e) {}
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
		//ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(data);
		} catch (Exception e) {
			System.out.println("Data Not Sent");
		}
	}
/*	
	private static Runnable send = new Runnable(){
		public void run(){
			ObjectOutputStream oos = null;
			FileOutputStream fout = null;
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
	};*/
}
