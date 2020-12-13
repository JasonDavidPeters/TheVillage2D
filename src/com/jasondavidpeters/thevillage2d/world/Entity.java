package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Entity {
	
	protected int x, y;
	
	public Entity(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void render(Renderer renderer) {
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void tick() {
		
	}

}
