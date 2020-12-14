package com.jasondavidpeters.thevillage2d.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static Spritesheet WORLD_TILES = new Spritesheet("/spritesheets/tiles/worldtileset.png");
	public static Spritesheet PLAYERSHEET = new Spritesheet("/spritesheets/player/playersheet.png");
	public static Spritesheet UIBUTTONS = new Spritesheet("/spritesheets/ui/uibuttons.png");
	public static Spritesheet ORES = new Spritesheet("/spritesheets/gameobjects/ores.png");
	
	private int[] pixels;
	private int width, height;
	private String url;
	
	public Spritesheet(String url) {
		this.url=url;
		load();
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

}
