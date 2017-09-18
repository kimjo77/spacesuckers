package com.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

class Client
{
	public static void main(String args[]) throws UnknownHostException,IOException
	{
		Socket s=new Socket("localhost",4455);
		
		InputStream is=s.getInputStream();
		OutputStream os=s.getOutputStream();

		DataInputStream dis=new DataInputStream(is);
		DataOutputStream dos=new DataOutputStream(os);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str="";
		String str1="";
		while(true)
		{
			str=br.readLine();
			dos.writeUTF(str);
			
			str1=dis.readUTF();
			System.out.println(str1);
			
			if(str.equals("END")||str.equals("end"))
			{
				System.out.println("Bye!...Server quit");
				System.exit(0);
			
			}

		}
	}
}
