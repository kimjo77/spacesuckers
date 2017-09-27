package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.main.Menu;
import com.test.ClientTest;
import com.test.ServerTest;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6244201119884380285L;
	public final static int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	public static double DELTA_TIME;	
	private Thread thread;
	private boolean running = false;
	public static Font fnt = new Font("04B", Font.PLAIN, 24);
	public static int SCORE = 0;
	
	private MouseInput mouse;
	private Random rand = new Random();
	
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
	public static Texture tex;

	//--Networking--//
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
		
		tex = new Texture();
		
		menu = new Menu();
		
		handler = new Handler();
		mouse = new MouseInput(handler);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		new Window(WIDTH, HEIGHT,"Space Suckers",this);
		
		
		switch(gameState) {
			case Game : 
						handler.addObject(new Blackhole(WIDTH/2,HEIGHT/2,16,16,ID.Blackhole));
						handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
						handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
						handler.addObject(new Player(WIDTH/2,HEIGHT/2,24,12,ID.Player));
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
				System.out.println("FPS: " + frames);
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
					break;
		case Game : 
					handler.tick();
					break;	
		}
		
/*		if(networkType == NETWORK_TYPE.Client) {
			client.send();			
		} else if(networkType == NETWORK_TYPE.Server) {

		} else { }
*/
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
		case Game : break;	
		}
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}

}
	
