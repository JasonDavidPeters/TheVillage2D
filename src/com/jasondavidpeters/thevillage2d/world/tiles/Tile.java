package com.jasondavidpeters.thevillage2d.world.tiles;

import java.util.Random;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Tile {
	
	public static Tile GRASS = new GrassTile(0,0,Sprite.GRASS);
	public static Tile WATER = new WaterTile(0,0,Sprite.WATER[0]);
	public static Tile VOID = new VoidTile(0,0,Sprite.VOID);
	public static Tile DIRT_PAVEMENT = new DirtPavementTile(0,0,Sprite.DIRT_PAVEMENT);
	public static Tile WOODEN_PLANK = new WoodenPlankTile(0,0,Sprite.WOODEN_PLANK);
	public static Tile CAVE_FLOOR = new CaveFloorTile(0,0,Sprite.CAVE_FLOOR);
	public static Tile TOP_LEFT_CORNER = new CaveCornerTile(0,0,Sprite.CAVE_TOPLEFT_CORNER);
	public static Tile TOP_RIGHT_CORNER = new CaveCornerTile(0,0,Sprite.CAVE_TOPRIGHT_CORNER);
	public static Tile BOTTOM_LEFT_CORNER = new CaveCornerTile(0,0,Sprite.CAVE_BOTTOMLEFT_CORNER);
	public static Tile BOTTOM_RIGHT_CORNER = new CaveCornerTile(0,0,Sprite.CAVE_BOTTOMRIGHT_CORNER);
	public static Tile CAVE_WALL = new CaveWallTile(0,0,Sprite.CAVE_WALL);
	public static Tile CAVE_NORTH_WALL = new CaveWallTile(0,0,Sprite.CAVE_NORTH_WALL);
	public static Tile CAVE_SOUTH_WALL = new CaveWallTile(0,0,Sprite.CAVE_SOUTH_WALL);
	
	protected Sprite sprite;
	protected int x, y;
	protected static Random random = new Random();
	
	public Tile(int x, int y, Sprite sprite) {
		this.x=x;
		this.y=y;
		this.sprite=sprite;
	}
	
	public void render(int x, int y, Renderer renderer) {
		renderer.renderTile(x*16, y*16, this);
	}
	public void tick() {
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
