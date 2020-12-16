package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Entity {
	
	protected double x, y;
	protected Level level;
	protected String name;
	
	public Entity(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void render(Renderer renderer) {
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	public void tick() {
		
	}
	
	public void init(Level level) {
		this.level=level;
	}

}
