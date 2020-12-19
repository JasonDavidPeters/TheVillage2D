package com.jasondavidpeters.thevillage2d.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static Spritesheet WORLD_TILES = new Spritesheet("/spritesheets/tiles/worldtileset.png");
	public static Spritesheet CAVE_TILES = new Spritesheet("/spritesheets/tiles/cavetileset.png");
	public static Spritesheet PLAYERSHEET = new Spritesheet("/spritesheets/player/playersheet.png");
	public static Spritesheet UIBUTTONS = new Spritesheet("/spritesheets/ui/uibuttons.png");
	public static Spritesheet ORES = new Spritesheet("/spritesheets/gameobjects/ores.png");
	public static Spritesheet CAVES = new Spritesheet("/spritesheets/gameobjects/cave.png");
	public static Spritesheet STONEPICKAXE = new Spritesheet("/spritesheets/gameitems/stonepickaxe.png");
	
	private int[] pixels;
	private int width, height;
	private String url;
	
	/*
	 * TODO: Sub sprite sheets
	 */
	int startSpriteY;
	int startSpriteX;
	public Spritesheet(String url, int startSpriteX, int startSpriteY, int width, int height) {
		// have to know the sprite width and sprite height to offset the pixel array
		// 
		this.url=url;
		this.width = width;
		this.height=height;
		this.startSpriteX = startSpriteX;
		this.startSpriteY = startSpriteY;
		loadSubsheet();
	}
	public Spritesheet(String url) {
		this.url=url;
		load();
	}
	private void loadSubsheet() {
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(Spritesheet.class.getResource(url));
			pixels = new int[width*height];
			pixels = sheet.getRGB(startSpriteX, startSpriteY, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void load() {
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(Spritesheet.class.getResource(url));
			width = sheet.getWidth();
			height= sheet.getHeight();
			pixels = new int[width*height];
			pixels = sheet.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int[] getPixels() {
		return pixels;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() { 
		return height;
	}
	public Spritesheet subsheet(int startX, int startY, int width, int height) {
		Spritesheet subsheet = new Spritesheet(url, startX, startY, width, height);
		return subsheet;
	}
}
