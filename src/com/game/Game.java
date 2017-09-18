package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.net.Socket;
import java.util.Random;

import com.main.Menu;

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
		Game,
	}
	private Menu menu;
	
	public static STATE gameState = STATE.Menu;
	
	public static Handler handler; 
	public static Texture tex;
	
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
						handler.addObject(new Player(WIDTH/2,HEIGHT/2,24,12,ID.Player));
						handler.addObject(new Blackhole(WIDTH/2,HEIGHT/2,16,16,ID.Blackhole));
						handler.addObject(new AsteroidController(0,0,0,0,ID.AsteroidController));
						handler.addObject(new Asteroid(0,0,16,16,ID.Asteroid));
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
					menu.tick();
					break;
		case Game : 
					handler.tick();
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
		//g.fillRect(0,0,WIDTH, HEIGHT);
		
		g.drawImage((Image)tex.bg, 0, 0, null);
		g.setFont(fnt);
		g.drawString("Score: " + SCORE, 16, 35);
		
		
		handler.render(g);
		
		switch(gameState) {
		case Splash : 
		case Menu : 
		case Multi:
					menu.render(g);
					break;
		case Game : break;	
		}
		
		g.dispose();
		bs.show();
	}
	
	public static Socket[] client;
	public static int clientSize=0;
	
	public static void main(String args[]) {
		new Game();
	}

}
	
