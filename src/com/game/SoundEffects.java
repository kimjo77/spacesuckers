package com.game;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	
	public String shoot = "/audio/Laser_Shoot.wav",
				  blackholeBG = "/audio/Blackhole.wav",
				  spaceshift = "/audio/Space_Shift.wav",
				  explosion = "/audio/Explosion.wav";
	
	public Clip clip;
	
	public SoundEffects() {
	}
	
	public void play(String sound) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sound)));
			clip.start();
			clip.setFramePosition(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loop(String sound){
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sound)));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
}

