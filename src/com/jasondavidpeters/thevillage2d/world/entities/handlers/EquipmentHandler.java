package com.jasondavidpeters.thevillage2d.world.entities.handlers;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class EquipmentHandler {

	/*
	 * Need to decide if an item is wearable
	 * is it a helmet? is it a weapon?
	 * render it at the location depending on where it is
	 * 
	 */
	private int xOffset, yOffset; // offsets to render each equipment at 
	private int dir; // What if the player is facing different directions?
	private int spriteFrame; // which sprite to render?
	
	//TODO: each variable can correspond to the index in the equipment array
	// i.e. hat=equipment[0], weapon=equipment[1]
	public enum EQUIPMENT_TYPE {
			HAT,
			ONEHANDED,
			TWOHANDED,
			BOOTS,
			CHEST,
			LEGS
	}
	public Sprite getSprite(Sprite[] sprites, int dir) {
		return sprites[dir];
	}
	public int getXOffset(int dir) { 
		switch (dir) {
		case 0:
			return 20;
		case 1:
			return 135;
		case 2: 
			return 10;
		case 3:
			return -10;
		}
		return 0;
	}
	public int getYOffset(int dir) {
		switch (dir) {
		case 0:
			return 10;
		case 1:
			return 55;
		case 2: 
			return 150;
		case 3:
			return 55;
		}
		return 0;
	}
}
