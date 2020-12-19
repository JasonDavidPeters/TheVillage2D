package com.jasondavidpeters.thevillage2d.world.tiles;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class CaveWallTile extends Tile {
	
	public CaveWallTile(int x, int y, Sprite sprite) {
		super(x,y,sprite);
	}
	
	public boolean isSolid() {
		return true;
	}

}
