package com.networking;

import java.io.Serializable;


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
