package com.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.game.Game;

public class ServerTest {
	private static ServerSocket ss = null;
	private static Socket s = null;
	public ServerTest(String name,int  port, String Password) throws IOException {
		ss= new ServerSocket(port);
		System.out.println("Server Started.........");
		//waiting();	
		new Thread(accept).start();
	}
	
	public void waiting() throws IOException {
		s=ss.accept();
		System.out.println("Client Connected.......");
	}
	
	private static Runnable accept = new Runnable(){
		@Override
		public void run(){
			new Thread(send).start();
			//new Thread(receive).start();
			while (true){
				try{
					s = ss.accept();
					System.out.println("Client Connected.......");
				}
				catch (Exception ex) {}
			}
		}
	};
	
	private static Runnable send = new Runnable(){
		public void run(){
			ObjectOutputStream oos = null;
			while (true){
				try {
					oos = new ObjectOutputStream(s.getOutputStream());
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
					/*ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					int receive_state = (Integer) ois.readObject();

					ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					DataPackage dp = (DataPackage) ois.readObject();

					//list_data.set(i, dp); */
				}
				catch (Exception e) // Client Disconnected (Client Didn't Notify Server About Disconnecting)
				{
					e.printStackTrace();
				}
			}
		}
	};
}
