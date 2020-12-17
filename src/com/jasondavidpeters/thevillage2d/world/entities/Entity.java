package com.jasondavidpeters.thevillage2d.world.entities;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.Level;

public class Entity {
	
	protected double x, y;
	protected Level level;
	protected String name;
	protected boolean isRemoved;
	protected Sprite sprite;
	
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
	public boolean isRemoved() {
		return isRemoved;
	}
	public void setRemove(boolean isRemoved) {
		this.isRemoved=isRemoved;
	}

	public void tick() {
		
	}
	
	public void init(Level level) {
		this.level=level;
	}
	public Sprite getSprite() {
		return sprite;
	}

}
