package com.jasondavidpeters.thevillage2d.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.Entity;
import com.jasondavidpeters.thevillage2d.world.entities.ground.GroundEntity;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;
import com.jasondavidpeters.thevillage2d.world.gameobjects.CaveEntrance;
import com.jasondavidpeters.thevillage2d.world.gameobjects.Copper;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;
import com.jasondavidpeters.thevillage2d.world.gameobjects.Stone;
import com.jasondavidpeters.thevillage2d.world.gameobjects.Tin;
import com.jasondavidpeters.thevillage2d.world.tiles.Tile;

public class Level {

	protected int[] pixels;
	protected int width, height;

	public static Level SPAWN_LEVEL = new LoadLevel("/levels/spawn.png");
	public static Level CAVE_LEVEL = new LoadLevel("/levels/cave.png");

	public List<Tile> tileList = new ArrayList<Tile>();
	public List<Entity> entities = new ArrayList<Entity>();
	public List<GroundEntity> groundEntities = new ArrayList<GroundEntity>();
	public List<GameObject> gameObjects = new ArrayList<GameObject>();

	private boolean currentLevel;

	protected Random random = new Random();
	protected String levelFile;
	protected Entity player;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public Level(String levelFile) {
		this.levelFile = levelFile;
	}

	public void addPlayer(Player player) {
		if (this.player == null) {
			this.player = player;
			player.init(this);
			add(player);
		}
	}
	public void removePlayer(Player player) {
		if (this.player.equals(player)) {
			entities.remove(player);
			this.player = null;
		}
	}

	public void render(Renderer renderer) {
		int yOffset = renderer.getYoffset();
		int xOffset = renderer.getXoffset();
		int y0 = (yOffset - 16) / 16; // top screen
		int y1 = (yOffset + Renderer.HEIGHT + 16) / 16; // bottom screen
		int x0 = (xOffset - 16) / 16; // left screen
		int x1 = (xOffset + Renderer.WIDTH + 16) / 16; // right screen
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				Tile t = getTile(x, y);
//				System.out.println("rendering: "  +t);
				t.render(x, y, renderer);
				/*
				 * if the pixel at x, y is 0xfijjfjf render game object x
				 */
			}
		}
		if (GameObject.RESPAWN_OBJECTS.size() > 0) {
			for (int i = 0; i < GameObject.RESPAWN_OBJECTS.size(); i++) {
				// if the respawn timer is <= 0 then add it back to the gameobjects list
				if (GameObject.RESPAWN_OBJECTS.get(i).getRespawnTimer() <= 0) {
					GameObject.RESPAWN_OBJECTS.get(i).setRemoved(false);
					gameObjects.add(GameObject.RESPAWN_OBJECTS.get(i));
					GameObject.RESPAWN_OBJECTS.remove(i);
				}
			}
		}
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i).isRemoved()) {
				GameObject.RESPAWN_OBJECTS.add(gameObjects.get(i));
				gameObjects.remove(i);
				continue;
			}
			gameObjects.get(i).render(renderer);
		}

		for (int i = 0; i < entities.size(); i++)
			entities.get(i).render(renderer);

		for (int i = 0; i < GroundEntity.GROUNDENTITIES.size(); i++) {
			if (GroundEntity.GROUNDENTITIES.get(i).isRemoved()) {
				GroundEntity.GROUNDENTITIES.remove(i);
				continue;
			}
			GroundEntity.GROUNDENTITIES.get(i).render(renderer);
		}
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).tick();
		for (int i = 0; i < tileList.size(); i++)
			tileList.get(i).tick();
		for (int i = 0; i < gameObjects.size(); i++)
			gameObjects.get(i).tick();
		if (GameObject.RESPAWN_OBJECTS.size() > 0)
			for (int i = 0; i < GameObject.RESPAWN_OBJECTS.size(); i++)
				GameObject.RESPAWN_OBJECTS.get(i).tick();

		for (int i = 0; i < GroundEntity.GROUNDENTITIES.size(); i++)
			GroundEntity.GROUNDENTITIES.get(i).tick();
	}

	protected void add(Object object) {
		if (object instanceof Tile)
			tileList.add((Tile) object);
		if (object instanceof GroundEntity)
			groundEntities.add((GroundEntity) object);
		if (object instanceof Entity)
			entities.add((Entity) object);
		if (object instanceof GameObject)
			gameObjects.add((GameObject) object);
	}

	// 2D110E - cave walls sides
	// 91362E - cave walls bottom
	// C4473E - cave walls top
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || y >= height || x >= width)
			return Tile.VOID;
		if (pixels[x + y * width] == 0xFF91362E)
			return Tile.CAVE_SOUTH_WALL;
		if (pixels[x + y * width] == 0xFFC4473E)
			return Tile.CAVE_NORTH_WALL;
		if (pixels[x + y * width] == 0xFF2D110E)
			return Tile.CAVE_WALL;
		if (pixels[x + y * width] == 0xFFC44740)
			return Tile.BOTTOM_LEFT_CORNER;
		if (pixels[x + y * width] == 0xFFBF453F)
			return Tile.BOTTOM_RIGHT_CORNER;
		if (pixels[x + y * width] == 0xFFC94942)
			return Tile.TOP_LEFT_CORNER;
		if (pixels[x + y * width] == 0xFFCE4B42)
			return Tile.TOP_RIGHT_CORNER;
		if (pixels[x + y * width] == 0xFF722C24)
			return Tile.CAVE_FLOOR;
		if (pixels[x + y * width] == 0xFF0FF7FF)
			return Tile.WATER;
		if (pixels[x + y * width] == 0xFF1CFF14)
			return Tile.GRASS;
		if (pixels[x + y * width] == 0xFF331604)
			return Tile.DIRT_PAVEMENT;
		if (pixels[x + y * width] == 0xFF87380A)
			return Tile.WOODEN_PLANK;
		
		
		// GAMEOBJECTS
		{
			if (pixels[x + y * width] == 0xFF631A16) // game object tile, make the tile adjacent so it blends in
				return getTile(x-1,y);
			if (pixels[x + y * width] == 0xFF4C3636)
				return getTile(x+1,y);
			if (pixels[x + y * width] == 0xFF493434) 
				return getTile(x+1,y);
			if (pixels[x + y * width] == 0xFF473232) 
				return getTile(x+1,y);
		}
		return Tile.VOID;
	}

	//4C3636 - stone ore
	//493434 - copper ore
	// 473232 - tin ore
	// 
	public GameObject tileToGameObject(int x, int y) {
		if (pixels[x + y * width] == 0xFF631A16) 
			return new CaveEntrance(x, y, Sprite.CAVEENTRANCE);
		if (pixels[x + y * width] == 0xFF4C3636) 
			return new Stone(x, y, Sprite.STONEORE);
		if (pixels[x + y * width] == 0xFF493434) 
			return new Copper(x, y, Sprite.COPPERORE);
		if (pixels[x + y * width] == 0xFF473232) 
			return new Tin(x, y, Sprite.TINORE); 
		return null;
	}

	public boolean currentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(boolean currentLevel) {
		
		this.currentLevel = currentLevel;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return this.pixels;
	}

	public GameObject getGameObject(int x, int y) {
		for (GameObject o : gameObjects) {
			if (x == o.getX() / 16 && y == o.getY() / 16)
				return o;
		}
		return null;
	}
}
