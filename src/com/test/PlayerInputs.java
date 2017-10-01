package com.test;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.entities.GameObject;
import com.game.ID;

public class PlayerInputs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int 
				key_up = 0,
				key_down = 0,
				key_left = 0,
				key_right = 0,
				key_shoot = 0,
				mouse_shoot = 0,
				turrentKey_left = 0,
				turrentKey_right = 0,
				mouseX = 0,
				mouseY = 0;

	public PlayerInputs() {}
}
