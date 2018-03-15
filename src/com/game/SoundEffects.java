package com.game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
			bgmenuClip.open(markResetSupport(bgmenu));
			bgsingleClip = AudioSystem.getClip();
			bgsingleClip.open(markResetSupport(bgsingle));
			bgmultiClip = AudioSystem.getClip();
			bgmultiClip.open(markResetSupport(bgmulti));
			bgscoresClip = AudioSystem.getClip();
			bgscoresClip.open(markResetSupport(bgscores));
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
	
	//--Helping Functions
	private AudioInputStream markResetSupport(String path) {
		//read audio data from whatever source (file/classloader/etc.)
		InputStream audioSrc = getClass().getResourceAsStream(path);
		//add buffer for mark/reset support
		InputStream bufferedIn = new BufferedInputStream(audioSrc);
		AudioInputStream audioStream = null;
		try {
			audioStream = AudioSystem.getAudioInputStream(bufferedIn);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audioStream;
	}
		
}

