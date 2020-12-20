package com.jasondavidpeters.thevillage2d.world.gameitems;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.input.Mouse;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.handlers.EquipmentHandler;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Npc;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class GameItem {

	protected Sprite sprite;
	protected double x, y;
	protected EquipmentHandler equipmentHandler;
	protected double screenPositionX, screenPositionY;
	protected boolean inInventory;
	protected static int id;
	protected Mouse mouse;
	protected boolean isClicked;
	protected Player p;
	protected boolean remove;

	public GameItem(Player p) {
		mouse = p.getMouse();
		this.p = p;
	}

	public GameItem(Sprite sprite) {
		this.sprite = sprite;
	}

	public GameItem(double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public GameItem(EquipmentHandler equipmentHandler, Sprite sprite) {
		this.sprite = sprite;
		this.equipmentHandler = equipmentHandler;
	}

	public GameItem(EquipmentHandler equipmentHandler, double x, double y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.equipmentHandler = equipmentHandler;
	}

	/*
	 * Is it wearable? is it stackable?
	 */
	protected void checkClicked() {
		if (mouse.getMouseB() == 1) {
			if (mouse.getMouseX() >= screenPositionX - sprite.getWidth() / 3
					&& mouse.getMouseX() <= screenPositionX + sprite.getWidth() / 2
					&& mouse.getMouseY() >= screenPositionY - sprite.getHeight()
					&& mouse.getMouseY() <= screenPositionY) {
				isClicked = true;
				mouse.reset();
			}
		}
	}

	public void tick() {
		checkClicked();
		if (isClicked() && p.isInShop()) {
			setRemove(true);
			isClicked = false;
		}
	}

	public boolean remove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove=remove;
	}

	public void render(Renderer r) {
	}

	public void render(Npc n, Renderer r) { // Which entity to render to if wearable

	}

	public boolean wearable() {
		return false;
	}

	public boolean stackable() {
		return false;
	}

	public boolean inInventory() {
		return false;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setInInventory(boolean inInventory) {
		this.inInventory = inInventory;
	}

	public void updatePosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void updateX(double x) {
		this.x = x;
	}

	public void updateY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setScreenPosition(double x, double y) {
		this.screenPositionX = x;
		this.screenPositionY = y;
	}

	public double getScreenPositionX() {
		return this.screenPositionX;
	}

	public double getScreenPositionY() {
		return this.screenPositionY;
	}

	public static GameItem getByID(Player p, int id) {
		switch (id) {
		case 0:
			return new StoneOre(p);
		case 1:
			return new CopperOre(p);
		case 2:
			return new TinOre(p);
		default:
			return null;
		}
	}

}
