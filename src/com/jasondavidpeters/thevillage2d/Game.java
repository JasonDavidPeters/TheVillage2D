package com.jasondavidpeters.thevillage2d;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Font;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.input.Mouse;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.screen.ui.UIManager;
import com.jasondavidpeters.thevillage2d.util.Debug;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Shopkeeper;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;
	private int ticks;
	private int frames;
	private Renderer renderer;
	private Keyboard keyboard;
	private Mouse mouse;
	public static UIManager UIMANAGER = new UIManager();
	public static final String GAME_TITLE = "The Village 2D";
	private Font font;

	private List<Level> levels = new ArrayList<Level>();

	public static void main(String[] args) {
		Game game = new Game();

		game.start();
	}

	public void tick() {
		for (Level level : levels)
			level.tick();
		UIMANAGER.tick(); // in case of buttons / interactive UI
	}

	public void render() {
		renderer.render();
		for (Level level : levels)
			if (level.currentLevel()) {
				level.render(renderer);
			}
		UIMANAGER.render(renderer);
//		renderer.drawString("abcdefghijklmnop", 100, 70, 0xFF0000, true);

	}

	public void run() {
		keyboard = new Keyboard();
		mouse = new Mouse();
		renderer = new Renderer();
		renderer.addKeyListener(keyboard);
		renderer.addMouseListener(mouse);
		Debug.r = renderer;
		Level.SPAWN_LEVEL.setCurrentLevel(true);
		Level.SPAWN_LEVEL.addPlayer(new Player(Level.SPAWN_LEVEL,"Jason", 17, 14, mouse));
		Level.SPAWN_LEVEL.add(new Shopkeeper(Level.SPAWN_LEVEL,"Shopkeeper", 18, 15, Sprite.SHOPKEEPER));
		levels.add(Level.SPAWN_LEVEL);
		levels.add(Level.CAVE_LEVEL);
		font = new Font();
		long before = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - before) / ns;
			before = now;
			if (delta >= 1) {
				delta--;
				ticks++;
				tick();
			}
			if (System.currentTimeMillis() - timer >= 1000) {
//				System.out.println("ticks: " + ticks + " frames: " + frames);
				renderer.getFrame().setTitle(GAME_TITLE + " " + "ticks: " + ticks + " frames: " + frames);
				timer = System.currentTimeMillis();
				ticks = 0;
				frames = 0;
			}
			render();
			frames++;
		}
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Game Thread");
		thread.run();
	}

	public synchronized void stop() {
		if (running)
			running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
