package com.jasondavidpeters.thevillage2d.world;

public class RandomLevel extends Level {

	public RandomLevel(int width, int height) {
		super(width, height);
		addRandomTiles();
		generateLevel();
	}
	
	private void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x+y*width] = random.nextInt(2);
			}
		}
	}
	private void addRandomTiles() {
//		add(Tile.GRASS);
//		add(Tile.WATER);
	}
}
