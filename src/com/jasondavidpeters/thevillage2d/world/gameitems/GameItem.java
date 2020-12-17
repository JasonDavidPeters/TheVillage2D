package com.jasondavidpeters.thevillage2d.world.gameitems;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.handlers.EquipmentHandler;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Npc;

public class GameItem {

	protected Sprite sprite;
	protected double x, y;
	protected EquipmentHandler equipmentHandler;

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

	public void tick() {

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

	public void updatePosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void updateX(double x) {
		this.x=x;
	}
	public void updateY(double y) {
		this.y=y;
	}

}
