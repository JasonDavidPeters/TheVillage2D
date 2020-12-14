package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Stone extends GameObject {
	
	public Stone(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	public boolean isSolid() { 
		return true;
	}

}
