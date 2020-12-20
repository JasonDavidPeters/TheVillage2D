package com.jasondavidpeters.thevillage2d.world.gameitems;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class TinOre extends GameItem {
	
	public TinOre(Player p) {
		super(p);
		sprite=Sprite.GROUND_TIN_ENTITY;
	}

	public TinOre(double x, double y, Sprite sprite) {
		super(x,y,sprite);
	}
	public TinOre(Sprite sprite) {
		super(sprite);
	}
}
