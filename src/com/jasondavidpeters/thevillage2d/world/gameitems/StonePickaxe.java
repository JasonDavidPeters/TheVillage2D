package com.jasondavidpeters.thevillage2d.world.gameitems;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.assets.Spritesheet;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.handlers.EquipmentHandler;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Npc;

public class StonePickaxe extends GameItem {
	
	/*
	 * TODO: set equipment type
	 */
	Sprite[] sprites;

	public StonePickaxe(EquipmentHandler equipmentHandler, Sprite sprite) {
		super(equipmentHandler, sprite);
		sprites = Sprite.toArray(Spritesheet.STONEPICKAXE.subsheet(0, 0, 4*8, 8), 8, 8);

	}

	public void render(Npc n,Renderer r) {
		sprite=equipmentHandler.getSprite(sprites, n.getDir());
		r.renderSprite((int) (((x+equipmentHandler.getXOffset(n.getDir()))/16)+Renderer.WIDTH/2), (int) ((y+equipmentHandler.getYOffset(n.getDir()))/16)+Renderer.HEIGHT/2, sprite, false);
	}

	public void tick() {

	}

	public boolean wearable() {
		return true;
	}

}
