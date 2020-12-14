package com.jasondavidpeters.thevillage2d.world.tiles;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class WoodenPlankTile extends Tile {
	
	public WoodenPlankTile(int x, int y, Sprite sprite) {
		super(x,y,sprite);
	}
	
	public boolean isSolid() {
		return true;
	}

}
