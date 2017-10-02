package com.game;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffects {
	
	public String shoot = "/audio/Laser_Shoot.wav",
				  blackholeBG = "/audio/Blackhole.wav",
				  spaceshift = "/audio/Space_Shift.wav",
				  explosion = "/audio/Explosion.wav",
				  pickupCoin = "/audio/Pickup_Coin.wav",
				  blip = "/audio/Blip_Select.wav",
				  type = "/audio/Keypress.wav",
				  bgmenu = "/audio/BG_Menu.wav",
				  bgsingle = "/audio/BG_Singleplayer.wav",
				  bgmulti = "/audio/BG_Multiplayer.wav",
				  bgscores = "/audio/BG_Scoreboard.wav";
	
	public static Clip 	bgmenuClip,
						bgsingleClip,
						bgmultiClip,
						bgscoresClip;
	public SoundEffects() {
		try {
			bgmenuClip = AudioSystem.getClip();
			bgmenuClip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(bgmenu)));
			bgsingleClip = AudioSystem.getClip();
			bgsingleClip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(bgsingle)));
			bgmultiClip = AudioSystem.getClip();
			bgmultiClip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(bgmulti)));
			bgscoresClip = AudioSystem.getClip();
			bgscoresClip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(bgscores)));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Clip clip;
	
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
	
	public void stop(Clip clip) {
		if(clip != null) {
			clip.stop();	
		}
	}
	
	public void loop(Clip clip){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.setFramePosition(0);
    }
	
	public void loop(String sound){
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sound)));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.setFramePosition(0);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
}

