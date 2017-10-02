package com.game;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.LinkedList;

import com.entities.GameObject;



public class Handler implements Serializable{

	private static final long serialVersionUID = 1L;

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() {
		for(int i=0; i<object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i < object.size(); i++) {
			tempObject =  object.get(i);
			tempObject.render(g);
		}
	}
	
	public GameObject addObject(GameObject object) {
		this.object.add(object);
		return object;
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void clearObject() {
		System.out.println("Cleared!");
		object.clear();
	}
	
}
