package com.game;

import java.io.*;

import javax.sound.sampled.*;
import java.applet.AudioClip;
import sun.audio.*;

public class SoundEffects {
	
	private Clip clip;
	
	public SoundEffects() {
		
	}
	
	public SoundEffects(String sound) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(sound));
//			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(sound));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),16,baseFormat.getChannels(),baseFormat.getChannels()*2,baseFormat.getSampleRate(),false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(dais));
			clip.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void play() {
		if(clip == null) return;
		stop();
	}
	
	public void stop() {
		if(clip.isRunning())
			clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
}

/*
public class SoundEffects implements Runnable
{
private boolean running = false;
private Thread thread;

public SoundEffects()
{
    this.start();
}

public synchronized void start()
{
    if(running)
        return;
    this.thread = new Thread(this);
    this.running = true;
    this.thread.start();
}

//
private boolean playSong = false;
private AudioInputStream inputStream;
private String url = "Laser_Shoot.wav";
private Clip clip;

@Override
public synchronized void run()
{
    while(running)
    {
        if(inputStream == null && playSong)
        {
            this.playSong = false;
            try
            {
                this.inputStream = AudioSystem.getAudioInputStream(SoundEffects.class.getResource(url));
                this.clip.open(inputStream);
                this.clip.loop(10);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

	public void playBackGround(String string) // call to play .wav file
	{
	    if(this.clip != null)
	    {
	        this.clip.stop();
	        this.clip.close();
	    }
	    try
	    {
	        this.clip = AudioSystem.getClip();
	    }
	    catch(LineUnavailableException e)
	    {
	        e.printStackTrace();
	    }
	    url = string + ".wav";
	    this.playSong = true;
	    this.inputStream = null;
	}

	public void disposeSound()
	{
	    if(this.clip != null)
	    {
	        this.clip.stop();
	        this.clip.close();
	    }
	    this.clip = null;
	    this.playSong = false;
	    this.inputStream = null;
	}
}

/*public class SoundEffects {
	
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
			clip.stop();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}*/
