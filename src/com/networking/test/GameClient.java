package com.networking.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameClient {

	static Socket server;
	static DataInputStream in;
	static DataOutputStream out;
	
	public static String data;
	
	public GameClient() {
		
	}

	public static void main(String[] args) throws IOException {
		server=new Socket("localhost",8880);
		in=new DataInputStream(server.getInputStream());
		data=in.readUTF();
		System.out.println("Message from server: " + data);	
	}
	
}
