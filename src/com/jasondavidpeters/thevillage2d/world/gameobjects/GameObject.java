package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class GameObject {

	public static GameObject GAMEOBJECT_STONE = new Stone(2, 2, Sprite.STONEORE);
	public static GameObject GAMEOBJECT_CAVEENTRANCE = new CaveEntrance(5,5,Sprite.CAVEENTRANCE);

	protected int x, y;
	protected Sprite sprite;
	protected int life;
	protected int screenPositionX, screenPositionY;

	public GameObject(int x, int y, Sprite sprite) {
		this.x = x * 16;
		this.y = y * 16;
		this.sprite = sprite;
	}

	public boolean isSolid() {
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
}
