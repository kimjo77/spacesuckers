package com.networking;

import java.io.Serializable;
import java.util.LinkedList;

import com.entities.GameObject;
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
	public LinkedList<GameObject> object;
	//--Client Sends
	public boolean bool;
	public String[] inputs;
	public PlayerInputs pi;
	public String name;
	
	
	public Data() {
		status = new boolean[ServerTest.MAX_PLAYERS];
		names = new String[ServerTest.MAX_PLAYERS];
	}
	
	public Data(boolean[] status, String[] names) {
		areatype = AREA_TYPE.lobby;
		this.status = status;
		this.names = names;
	}
	public Data(boolean bool) {
		areatype = AREA_TYPE.lobby;
		this.bool = bool;
	}
	public Data(String name) {
		areatype = AREA_TYPE.lobby;
		this.name = name;
	}	
	
	public Data(String[] inputs) {
		areatype = AREA_TYPE.game;
		this.inputs = inputs;
	}
	public Data(Handler handler) {
		areatype = AREA_TYPE.game;
		this.handler = handler;
	}
	public Data(LinkedList<GameObject> object) {
		areatype = AREA_TYPE.game;
		this.object = object;
	}
	public Data(PlayerInputs pi) {
		areatype = AREA_TYPE.game;
		this.pi = pi;
	}
}
