package com.networking.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.main.Game;

public class GameServer {

	static ServerSocket server;
	static Socket client;
	static Game spacesuckers;
	
	static DataOutputStream out;
	static DataInputStream in;
	
	public GameServer() throws IOException {
		
	}
	
	public static void main(String[] args) throws IOException {
		server=new ServerSocket(8880);
		server.setReuseAddress(true);
		System.out.println("Server started.");
		client=server.accept();
		System.out.println("Connection from: " + client.getInetAddress());
		out=new DataOutputStream(client.getOutputStream());
		out.writeUTF("Connection to server.");
		System.out.println("Connected to client.");
		
	}
	
	
}

