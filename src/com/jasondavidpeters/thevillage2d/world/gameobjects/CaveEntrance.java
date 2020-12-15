package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class CaveEntrance extends GameObject {

	public CaveEntrance(int x, int y, Sprite sprite) {
		super(x, y, sprite);	
	}
	
	public boolean isSolid() {
		return true;
	}
}
