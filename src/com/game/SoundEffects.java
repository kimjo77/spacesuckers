package com.game;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.JButton;
import java.applet.*;
import sun.audio.*;

public class SoundEffects {
	
	public SoundEffects() {
		File sound = new File("C://Users/Matugas LKJ/Desktop/MTGS/Academics/Java Projects/NetworkingTest/audio/Laser_Shoot.wav");
		play(sound);		
	}
	
	public static void main(String[] args) {
		new SoundEffects();
	}
	
	public static void play(File sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
