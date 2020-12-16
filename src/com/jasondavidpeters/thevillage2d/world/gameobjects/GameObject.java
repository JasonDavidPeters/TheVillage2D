package com.jasondavidpeters.thevillage2d.world.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class GameObject {

	public static GameObject GAMEOBJECT_STONE = new Stone(2, 2, Sprite.STONEORE);
	public static GameObject GAMEOBJECT_CAVEENTRANCE = new CaveEntrance(5,5,Sprite.CAVEENTRANCE);
	
	public static List<GameObject> RESPAWN_OBJECTS = new ArrayList<GameObject>(); // Objects that have been removed from level and are set to respawn

	protected Random random = new Random();
	protected int x, y;
	protected Sprite sprite;
	protected int life;
	protected int screenPositionX, screenPositionY;
	protected boolean removed;
	protected int respawnTimer;
	protected int respawnTime;
	protected int ticks;
	protected int dir; // direction from which the Entity is accessing the Game Object from

	public GameObject(int x, int y, Sprite sprite) {
		this.x = x * 16;
		this.y = y * 16;
		this.sprite = sprite;
	}
	
	public void interact(Player p) {
		if (p.getX() >= x) dir = 3;
		if (p.getX() <= x) dir = 1;
	}
	
	public boolean isRespawn() {
		return true;
	}
	
	public void setRemoved(boolean removed) {
		this.removed=removed;
	}
	
	public boolean isRemoved() {
		return removed;
	}

	public boolean isSolid() {
		return false;
	}
	public boolean canInteractFromFar() {
		return false;
	}

	public void render(Renderer renderer) {
		renderer.renderGameObject(x, y, this);
	}

	public void tick() {

	}
	public Sprite getSprite() {
		return sprite;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public int getScreenPositionX() {
		return screenPositionX;
	}
	public int getScreenPositionY() {
		return screenPositionY;
	}
	public void setScreenPosition(int x, int y) {
		screenPositionX = x;
		screenPositionY = y;
	}
	public int getRespawnTimer() {
		return respawnTimer;
	}
	public int getRespawnTime() {
		return respawnTime;
	}
}
