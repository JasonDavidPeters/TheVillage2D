package com.jasondavidpeters.thevillage2d.world.gameitems;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class CopperOre extends GameItem {

	public CopperOre(Player p) {
		super(p);
		sprite = Sprite.GROUND_COPPER_ENTITY;
	}

	public CopperOre(double x, double y, Sprite sprite) {
		super(x, y, sprite);
	}

	public CopperOre(Sprite sprite) {
		super(sprite);
	}
}
