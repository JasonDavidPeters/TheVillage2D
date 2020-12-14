package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class GameObject {

	public static GameObject GAMEOBJECT_STONE = new Stone(2, 2, Sprite.STONEORE);

	protected int x, y;
	protected Sprite sprite;

	public GameObject(int x, int y, Sprite sprite) {
		this.x = x * 16;
		this.y = y * 16;
		this.sprite = sprite;
	}

	public boolean isSolid() {
		return false;
	}

	public void render(Renderer renderer) {
		renderer.renderGameObject(x, y, sprite);
	}

	public void tick() {

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
