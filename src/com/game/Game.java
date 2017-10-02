package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Random;

import com.entities.Asteroid;
import com.entities.AsteroidController;
import com.entities.Blackhole;
import com.entities.Player;
import com.main.Menu;
import com.test.ClientTest;
import com.test.Data;
import com.test.PlayerInputs;
import com.test.ServerTest;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6244201119884380285L;
	
	public final static int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	public static double DELTA_TIME;	
	private Thread thread;
	private boolean running = false;
	public static Font fnt = new Font("04B", Font.PLAIN, 24);
	public static int SCORE = 0;
	
	private static Random rand = new Random();
	
	private MouseInput mouse,mouseUI;
	
	public static enum STATE{
		Splash,
		Menu,
		Multi,
		Create,
		Connect,
		Lobby,
		Game;
	}
	private Menu menu;
	
	public static STATE gameState = STATE.Menu;
	
	public static Handler handler; 
	public static Handler ui;
	public static Texture tex;
	
	public static SoundEffects soundfx;
	
	//--Networking--//
	public static Data data;
	public static PlayerInputs pi;
	//Lobby
	public static boolean isReady = false;
	public static boolean START = false;
	//
	public static ServerTest server;
	public static ClientTest client;
	public static int NETWORK_ID = 0;
	public static enum NETWORK_TYPE{
		Client,
		Server;
	}
	public static NETWORK_TYPE networkType = null;
	//--------------//
	
	public Game() {
		data = new Data();
		pi = new PlayerInputs();
		tex = new Texture();
		menu = new Menu();
		soundfx = new SoundEffects();
		handler = new Handler();
		ui = new Handler();

		mouse = new MouseInput(handler);
		mouseUI = new MouseInput(ui);
		
		this.addKeyListener(new KeyInput(handler));
		this.addKeyListener(new KeyInput(ui));
		
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(mouseUI);
		this.addMouseMotionListener(mouseUI);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		
		new Window(WIDTH, HEIGHT,"Space Suckers",this);
		
		switch(gameState) {
			case Menu :
//						soundfx.loop(SoundEffects.bgmenuClip);
						break;
			case Game : 
						gamestart("single");
						break;	
			default:
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			//--WEW
			DELTA_TIME = delta;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) 
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		switch(gameState) {
		case Splash:
		case Menu : 
		case Multi:
		case Create:
		case Connect:
		case Lobby:
					menu.tick();
					if(networkType == NETWORK_TYPE.Server) {
						if(server != null)
							server.send(server.status, server.names);
					} else if(networkType == NETWORK_TYPE.Client) {
						
					}
					break;
		case Game : 
					if(START) {
						if(networkType == NETWORK_TYPE.Server) {
							server.send(handler.object);
							handler.tick();
						} else if(networkType == NETWORK_TYPE.Client) {
							if(data.handler != null) {
								handler.object = data.handler.object;
							}
							client.send(pi);
							if(pi.key_shoot == 1) {
								pi.key_shoot = 2;
							} else if( pi.mouse_shoot == 1) {
								pi.mouse_shoot = 2;
							}
							//handler.tick();
						} else {
							handler.tick();
						}
					}
					break;	
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0,0,WIDTH, HEIGHT);
		
		g.drawImage(tex.bg, 0, 0, null);
		g.setFont(fnt);
		g.drawString("Score: " + SCORE, 16, 35);
		
		
		switch(gameState) {
		case Splash : 
		case Menu : 
		case Multi:
		case Create:
		case Connect:
		case Lobby:
					menu.render(g);
					break;
		case Game : 	
					handler.render(g);
					break;	
		}
		
		
		ui.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
	
	//--Others
	public static void gamestart(String type) {
		Game.soundfx.stop(SoundEffects.bgmenuClip);
		switch(type){
		case "single":
			soundfx.loop(SoundEffects.bgsingleClip);
			soundfx.loop(soundfx.blackholeBG);
			handler.addObject(new Player(WIDTH, 0,24,12,ID.Player,0,315));
			handler.addObject(new Blackhole(WIDTH/2,HEIGHT/2,16,16,ID.Blackhole));
			handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
			handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
//			ui.addObject(new Gameover(0,0,0,0,ID.))
			START = true;
			break;
		case "multi":
			soundfx.loop(SoundEffects.bgmultiClip);
			soundfx.loop(soundfx.blackholeBG);
			for(int i=0; i<server.currentcount; i++) {
				switch(i) {
				case 0:
						handler.addObject(new Player(0,0,24,12,ID.Player,i,225));
						break;
				case 1:
						handler.addObject(new Player(WIDTH, HEIGHT,24,12,ID.Player,i,45));
						break;
				case 2:
						handler.addObject(new Player(WIDTH, 0,24,12,ID.Player,i,315));
						break;
				case 3:
						handler.addObject(new Player(0,HEIGHT,24,12,ID.Player,i,135));
						break;
				}
			}
			handler.addObject(new Blackhole(WIDTH/2,HEIGHT/2,16,16,ID.Blackhole));
			handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
			handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
			START = true;
			break;
		}
	}
	public static void gameRestart() {
		ui.clearObject();
		handler.clearObject();
		gamestart("single");
	}
}
	
