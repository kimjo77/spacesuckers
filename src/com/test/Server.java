package com.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

class Server
{
	public static void main(String args[]) throws UnknownHostException,IOException
	{
		ServerSocket ss=new ServerSocket(4455);
		System.out.println("Server Started.........");

		Socket s=ss.accept();
		System.out.println("Client Connected.......");

		InputStream is=s.getInputStream();
		OutputStream os=s.getOutputStream();

		DataInputStream dis=new DataInputStream(is);
		DataOutputStream dos=new DataOutputStream(os);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str="";
		String str1="";
		while(true)
		{
			str=dis.readUTF();
			System.out.println(str);
			str1=br.readLine();
			dos.writeUTF(str1);
			if(str.equals("END")||str.equals("end"))
			{
				System.out.println("Bye!...Client requested To quit");
				System.exit(0);
			
			}
		}

	}
}
