package com.jasondavidpeters.thevillage2d;

import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.screen.ui.UIManager;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.LoadLevel;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;
	private int ticks;
	private int frames;
	private Renderer renderer;
	private Level level;
	private Keyboard keyboard;
	public static UIManager UIMANAGER = new UIManager();
	public static final String GAME_TITLE = "The Village 2D";
	
	public static void main(String[] args) {
		Game game = new Game();
		
		game.start();
	}
	
	public void tick() {
		level.tick();
	}
	public void render() {
		renderer.render();
		level.render(renderer);
		UIMANAGER.render(renderer);
	}
	
	public void run() {
		keyboard = new Keyboard();
		renderer = new Renderer();
		renderer.addKeyListener(keyboard);
		level = new LoadLevel("/levels/spawn.png");
		long before = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta+= (now - before) / ns;
			before=now;
			if (delta>=1) {
				delta--;
				ticks++;
				tick();
			}
			if (System.currentTimeMillis() - timer >= 1000) {
//				System.out.println("ticks: " + ticks + " frames: " + frames);
				renderer.getFrame().setTitle(GAME_TITLE + " " + "ticks: " + ticks + " frames: " + frames);
				timer=System.currentTimeMillis();
				ticks=0;
				frames=0;
			}
			render();
			frames++;
		}
	}
	
	public synchronized void start() { 
		running=true;
		thread = new Thread(this, "Game Thread");
		thread.run();
	}
	public synchronized void stop() {
		if (running) running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
