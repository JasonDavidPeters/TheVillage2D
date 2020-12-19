package com.jasondavidpeters.thevillage2d.assets;

public class Sprite {
	/*
	 * If its an animated sprite then it needs to cycle.
	 * 
	 * idea: the animated sprite could be an array that moves position every x
	 * amount of seconds
	 */
	// UI
	public static Sprite INVENTORY_BUTTON = new Sprite(Spritesheet.UIBUTTONS, 0, 0, 16, 16, 0);
	public static Sprite EQUIPMENT_BUTTON = new Sprite(Spritesheet.UIBUTTONS, 1, 0, 16, 16, 0);
	public static Sprite SETTINGS_BUTTON = new Sprite(Spritesheet.UIBUTTONS, 2, 0, 16, 16, 0);

	// Entities
	public static Sprite GROUND_STONE_ENTITY = new Sprite(Spritesheet.ORES, 6, 0, 16, 16, 0);
	public static Sprite GROUND_COPPER_ENTITY = new Sprite(Spritesheet.ORES, 6, 1, 16, 16, 0);
	public static Sprite GROUND_TIN_ENTITY = new Sprite(Spritesheet.ORES, 6, 2, 16, 16, 0);
	// Game Items
	public static Sprite STONE_PICKAXE = new Sprite(Spritesheet.STONEPICKAXE, 0, 0, 8, 8, 0);
	// Game Objects
	public static Sprite STONEORE = new Sprite(Spritesheet.ORES, 0, 0, 16, 16, 0);
	public static Sprite COPPERORE = new Sprite(Spritesheet.ORES, 0, 1, 16, 16, 0);
	public static Sprite TINORE = new Sprite(Spritesheet.ORES, 0, 2, 16, 16, 0);
	public static Sprite CAVEENTRANCE = new Sprite(Spritesheet.CAVES, 0, 0, 32, 32, 0);
	// Tiles
	public static Sprite GRASS = new Sprite(Spritesheet.WORLD_TILES, 0, 0, 16, 16, 0);
	public static Sprite VOID = new Sprite(Spritesheet.WORLD_TILES, 2, 0, 16, 16, 0);
	public static Sprite DIRT_PAVEMENT = new Sprite(Spritesheet.WORLD_TILES, 3, 0, 16, 16, 0);
	public static Sprite WOODEN_PLANK = new Sprite(Spritesheet.WORLD_TILES, 4, 0, 16, 16, 0);
	
	public static Sprite CAVE_FLOOR = new Sprite(Spritesheet.CAVE_TILES, 0, 0, 16, 16, 0);
	public static Sprite CAVE_NORTH_WALL = new Sprite(Spritesheet.CAVE_TILES, 1, 0, 16, 16, 0);
	public static Sprite CAVE_SOUTH_WALL = new Sprite(Spritesheet.CAVE_TILES, 1, 0, 16, 16, 2);
	public static Sprite CAVE_TOPRIGHT_CORNER = new Sprite(Spritesheet.CAVE_TILES, 2, 0, 16, 16, 0);
	public static Sprite CAVE_TOPLEFT_CORNER = new Sprite(Spritesheet.CAVE_TILES, 2, 0, 16, 16, 1);
	public static Sprite CAVE_BOTTOMRIGHT_CORNER = new Sprite(Spritesheet.CAVE_TILES, 2, 0, 16, 16, 2);
	public static Sprite CAVE_BOTTOMLEFT_CORNER = new Sprite(Spritesheet.CAVE_TILES, 2, 0, 16, 16, 3);
	public static Sprite CAVE_WALL = new Sprite(Spritesheet.CAVE_TILES, 3, 0, 16, 16, 0);
	
	public static Sprite[] WATER = { new Sprite(Spritesheet.WORLD_TILES, 1, 0, 16, 16, 0),
			new Sprite(Spritesheet.WORLD_TILES, 1, 1, 16, 16, 0), new Sprite(Spritesheet.WORLD_TILES, 1, 2, 16, 16, 0)

	};
	
	//NPC
	public static Sprite SHOPKEEPER = new Sprite(Spritesheet.SHOPKEEPERSHEET, 0, 0, 16, 16, 0);
	
	public static Sprite PLAYER_FORWARD[] = { new Sprite(Spritesheet.PLAYERSHEET, 0, 0, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 0, 1, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 0, 1, 16, 16, 1) };

	public static Sprite PLAYER_BACKWARD[] = { new Sprite(Spritesheet.PLAYERSHEET, 2, 0, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 2, 1, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 2, 1, 16, 16, 1) };

	public static Sprite PLAYER_LEFT[] = { new Sprite(Spritesheet.PLAYERSHEET, 1, 0, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 1, 16, 16, 0),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 2, 16, 16, 0) };

	public static Sprite PLAYER_RIGHT[] = { new Sprite(Spritesheet.PLAYERSHEET, 1, 0, 16, 16, 1),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 1, 16, 16, 1),
			new Sprite(Spritesheet.PLAYERSHEET, 1, 2, 16, 16, 1) };

	private int x, y, width, height;
	private Spritesheet sheet;
	private int[] pixels;
	private int size;
	private int flip;

	public Sprite(int[] p, int width, int height) {
		this.width= width;
		this.height=height;
		pixels = new int[width*height];
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = p[i];
		}
	}

	public Sprite(Spritesheet sheet, int x, int y, int width, int height, int flip) {
		this.sheet = sheet;
		this.width = width;
		this.height = height;
		this.x = x * width;
		this.y = y * height;
		this.flip = flip;
		pixels = new int[width * height];
		load();
	}

	private void load() {
		// Loop through spritesheet and find the sprite
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.getPixels()[(this.x + x) + (this.y + y) * sheet.getWidth()];
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
	public void setPixel(int pixel, int p) {
		pixels[pixel] = p;
	}

	public int getFlip() {
		return flip;
	}
	public void setFlip(int flip) {
		this.flip = flip;
	}

	public static Sprite[] toArray(Spritesheet sheet, int width, int height) {
		/*
		 * Loop through the sheet
		 */
		int counter = 0;
		Sprite[] sprites = new Sprite[(sheet.getWidth() / width) *(sheet.getHeight() / height)];
		
		int[] pixels = new int[width * height]; // each individual sprite pixels
//		System.out.println("sheet height: " + sheet.getHeight() + " sheet width: " + sheet.getWidth() + " height: " + height + " width: " + width);
		for (int y = 0; y < sheet.getHeight() / height; y++) {
			for (int x = 0; x < sheet.getWidth() / width; x++) {
				
				for (int yy = 0; yy < height; yy++) { // Loop through each sprite
					for (int xx = 0; xx < width; xx++) {
						// x must be offset by the sprite that it is currently at
						// it must be offset by the width of each sprite
						//
						// 0 ------ 11
						// 1 ------- 11
//						System.out.println(x + " " + y + " " + xx + " " + yy);
//						System.out.println(sheet.getPixels()[x + y * width]);
						int xa = xx + x * width;
						int ya = yy + y* height;
						pixels[xx + yy * width] = sheet.getPixels()[xa + ya * sheet.getWidth()];
					}
				}
				sprites[counter++] = new Sprite(pixels, width, height);
			}
		}
		return sprites;
	}
}
