package com.jasondavidpeters.thevillage2d.world.tiles;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class WaterTile extends Tile {
	private int cycle;
	private int anim;
	
	public WaterTile(int x, int y, Sprite sprite) {
		super(x,y,sprite);
	}

	public void tick() {
		anim++;
		if (cycle >= Sprite.WATER.length) cycle=0;
		if (anim % 30== 0) {
			sprite = Sprite.WATER[cycle++];
		}
	}
}
