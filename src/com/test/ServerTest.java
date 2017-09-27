package com.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.game.Game;
import com.game.Handler;

public class ServerTest {
	
	private ServerSocket server = null;
	private Socket[] clientSocket = null;
	private int clientCount = 0;
	
//	private HashMap<String,Socket> clients = new HashMap<String,Socket>();

	private int currentClient = 0;
	private String playerID;
	private int port;
	private String ip;
	
	public ServerTest() {
		
	}
	
	public ServerTest(String playerID,int port, String ip) {
		this.playerID = playerID;
		this.port = port;
		this.ip = ip;
		
		try {
			server = new ServerSocket(port);
			System.out.println("Server created");
			new Thread(accept).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Runnable accept = new Runnable()
	{
		@Override
		public void run()
		{
			try{
//			new Thread(send).start();
//			new Thread(receive).start();
			while (true)
			{
				try
				{
					currentClient = clientCount;
					clientSocket[currentClient] = server.accept();
					System.out.println("Client Connected: " + clientSocket[currentClient].getInetAddress());
					if(clientSocket[currentClient].isConnected()) {
						System.out.println("Client Connected: " + clientSocket[currentClient].getInetAddress());
					} else {
						System.out.println("Not connected");
					}
					
					ObjectInputStream ois = new ObjectInputStream(clientSocket[currentClient].getInputStream());
					System.out.println(ois.readUTF());
					clientCount++;

//					for (int i = 0; i < list_data.size(); i++)
//					{
	//					if (list_data.get(i).username.toLowerCase().equals(username.toLowerCase()))
//						{
//							accepted = false;
//							break;
//						}
//					}

//					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

//					if (accepted)
//					{
//						oos.writeObject("Welcome To This Server...");

//						list_clients_model.addElement(username + " - " + socket.getInetAddress().getHostAddress() + " - " + socket.getInetAddress().getHostName());
//						list_client_states.add(0);

//						list_data.add(new DataPackage());
//						list_sockets.add(socket);
	//				}
	//				else
	//				{
	//					oos.writeObject("Your name is already taken!");
	//				}
				} catch (Exception e) {}
			}
		}catch (Exception ex) {}
	
	};
};

}
	
/*
public class ServerTest {
	private static ServerSocket serverSocket = null;
	private static ClientTest[] clients = new ClientTest[4];
	private static Socket[] clientSockets = new Socket[4];
	private static int clientCount = 0;
	private static int currentCount = 0;

	private String name;
	private String ip;
	private int port;
	
	public ServerTest(String name,int  port, String ip) throws IOException {
		serverSocket= new ServerSocket(port);
		System.out.println("Server Started.........");
		//waiting();	
		
		this.name = name;
		this.ip = ip;
		this.port = port;
		
		new Thread(accept).start();
	}
	
	private static Runnable accept = new Runnable(){
		@Override
		public void run(){
			new Thread(send).start();
			new Thread(receive).start();
			while (true){
				try{
					clientSockets[clientCount] = serverSocket.accept();
					clients[clientCount].setSocket(clientSockets[clientCount]);
					
					System.out.println("Client Connected: " + clientSockets[clientCount].getInetAddress());
					
					DataInputStream dis = new DataInputStream(clientSockets[clientCount].getInputStream());
					System.out.println(dis.readUTF());
//					dis.close();
					
					currentCount = clientCount;
					clientCount++;
//					send();

				}
				catch (Exception e) {
				}
			}
		}
	};
	
	private static Runnable send = new Runnable(){
		public void run(){
			ObjectOutputStream oos = null;
			while (true){
				try {
					oos = new ObjectOutputStream(clientSockets[currentCount].getOutputStream());
					oos.writeObject(Game.handler);
				} catch (Exception e) {
					System.out.println("No client connected yet");
//					e.printStackTrace();
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
					System.out.println("Receiving..");
					DataInputStream dis = new DataInputStream(clientSockets[currentCount].getInputStream());
					System.out.println(dis.readUTF());
					/*ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					int receive_state = (Integer) ois.readObject();

					ois = new ObjectInputStream(list_sockets.get(i).getInputStream());
					DataPackage dp = (DataPackage) ois.readObject();

					//list_data.set(i, dp); */
/*				}
				catch (Exception e) // Client Disconnected (Client Didn't Notify Server About Disconnecting)
				{
					e.printStackTrace();
				}
			}
		}
	};
	
	public static void receive() {
		try {
			ObjectInputStream ois = new ObjectInputStream(clientSockets[currentCount].getInputStream());
			DataInputStream temp = (DataInputStream) ois.readObject();
			System.out.println("Handler received");			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}				
	}
	
	public static boolean checkReady() {
		boolean flag = true;
		for(int i = 0;i<4;i++) {
			if(clients[i].isReady() != true) {
				flag = false;
				
			}
		}
		
		return flag;
	}
	
	public static void send() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(clientSockets[currentCount].getOutputStream());
			oos.writeObject(Game.handler);			
			System.out.println("Handler sent");			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}

*/
