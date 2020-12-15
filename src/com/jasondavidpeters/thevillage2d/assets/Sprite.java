package com.jasondavidpeters.thevillage2d.assets;

public class Sprite {
	/*
	 * If its an animated sprite then it needs to cycle.
	 * 
	 * idea: the animated sprite could be an array that moves position
	 * every x amount of seconds
	 */
	// UI
	public static Sprite INVENTORY_BUTTON = new Sprite(Spritesheet.UIBUTTONS, 0, 0, 16, 16, 0);
	
	//Game Objects
	public static Sprite STONEORE = new Sprite(Spritesheet.ORES, 0, 0, 16, 16,0);
	public static Sprite CAVEENTRANCE = new Sprite(Spritesheet.CAVES, 0, 0, 32, 32, 0);
	// Tiles
	public static Sprite GRASS = new Sprite(Spritesheet.WORLD_TILES, 0, 0, 16, 16,0);
	public static Sprite VOID = new Sprite(Spritesheet.WORLD_TILES, 2, 0, 16, 16, 0);
	public static Sprite DIRT_PAVEMENT = new Sprite(Spritesheet.WORLD_TILES, 3, 0, 16, 16, 0);
	public static Sprite WOODEN_PLANK = new Sprite(Spritesheet.WORLD_TILES, 4, 0, 16, 16, 0);
	
	public static Sprite[] WATER = {
			new Sprite(Spritesheet.WORLD_TILES, 1, 0, 16,16,0),
			new Sprite(Spritesheet.WORLD_TILES, 1, 1, 16,16,0),
			new Sprite(Spritesheet.WORLD_TILES, 1, 2, 16,16,0)
			
	};
	
	public static Sprite PLAYER_FORWARD[] = {new Sprite(Spritesheet.PLAYERSHEET, 0, 0, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 0, 1, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 0, 1, 16 ,16,1)};
	
	public static Sprite PLAYER_BACKWARD[] = {new Sprite(Spritesheet.PLAYERSHEET, 2, 0, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 2, 1, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 2, 1, 16 ,16,1)};
	 
	public static Sprite PLAYER_LEFT[] = {new Sprite(Spritesheet.PLAYERSHEET, 1, 0, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 1, 16 ,16,0),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 2, 16 ,16,0)};
	
	public static Sprite PLAYER_RIGHT[] = {new Sprite(Spritesheet.PLAYERSHEET, 1, 0, 16 ,16,1),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 1, 16 ,16,1),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 2, 16 ,16,1)};
	
	private int x, y, width, height;
	private Spritesheet sheet;
	private int[] pixels;
	private int size;
	private int flip;
	
	public Sprite(Spritesheet sheet, int x, int y, int width, int height, int flip) {
		this.sheet=sheet;
		this.width=width;
		this.height=height;
		this.x=x*width;
		this.y=y*height;
		this.flip=flip;
		pixels=new int[width*height];
		load();
	}
	
	private void load() { 
		// Loop through spritesheet and find the sprite
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x+y*width] = sheet.getPixels()[(this.x+x) + (this.y+y) * sheet.getWidth()];
			}
		}
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int[] getPixels() {
		return pixels;
	}
	public int getFlip() {
		return flip;
	}
}
