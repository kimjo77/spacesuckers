package com.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.game.Game;
import com.game.Handler;

public class ClientTest {
	private static Socket s = null;
	public ClientTest(String name,int  port, String Password) throws IOException {
		ObjectOutputStream oos = null;
		
		s = new Socket("localhost",port);
		System.out.println("Connected.........");
		
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(name);
			System.out.println("Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(receive).start();
	}
	
	private Runnable receive = new Runnable() {
		public void run() {
			ObjectInputStream ois = null;
			while(true) {
				try {
					ois = new ObjectInputStream(s.getInputStream());
					Game.data = (Data) ois.readObject();
				} catch (IOException e) {
				} catch (ClassNotFoundException e) {
				}
			}
		}
	};
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
