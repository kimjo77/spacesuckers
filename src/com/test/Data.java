package com.test;

import java.io.Serializable;

import com.game.Handler;

public class Data implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum AREA_TYPE{
		lobby,
		game;
	}
	
	public AREA_TYPE areatype;
	//--Server Sends
	public Handler handler;
	public boolean[] status;
	public String[] names;
	//--Client Sends
	public boolean isReady;
	public String[] inputs;
	
	public Data() {
		areatype = null;
		handler = null;
		status = new boolean[ServerTest.MAX_PLAYERS];
		names = new String[ServerTest.MAX_PLAYERS];
		for(int i=0; i<ServerTest.MAX_PLAYERS; i++) {
			status[i] = false;
			names[i] = "";
		}
	}
	public Data(boolean[] status, String[] names) {
		areatype = AREA_TYPE.lobby;
		this.status = status;
		this.names = names;
	}
	public Data(boolean isReady) {
		areatype = AREA_TYPE.lobby;
		this.isReady = isReady;
	}
	public Data(String[] inputs) {
		areatype = AREA_TYPE.game;
		this.inputs = inputs;
	}
	public Data(Handler handler) {
		areatype = AREA_TYPE.game;
		this.handler = handler;
	}
}
