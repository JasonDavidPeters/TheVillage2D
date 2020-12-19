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
	protected double screenXPosition,screenYPosition;
	
	public Entity(Level level, double x, double y) {
		this.level=level;
		this.x=x;
		this.y=y;
	}
	
	protected boolean tileCollision(double xp, double yp, double xa, double ya, int npcWidth, int npcHeight) {
		for (int c = 0; c < 4; c++) {
			double xx = ((xp + xa) - c % 2 + npcWidth) / 16; // when c is 3 and 4
			double yy = ((yp + ya) - c / 2 + npcHeight) / 16;
			if (c == 0) {
				xx = Math.floor(xx);
				yy = Math.floor(yy);
			}
			if (level.getTile((int) xx, (int) yy).isSolid())
				return true;
		}
		return false;
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
	public void setX(double x) { 
		this.x=x;
	}
	public void setY(double y) {
		this.y=y;
	}
	public Level getLevel() {
		return level;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setScreenPosition(double x, double y) { 
		screenXPosition=x;
		screenYPosition=y;
	}
	public double getScreenXPosition() {
		return screenXPosition;
	}
	public double getScreenYPosition() {
		return screenYPosition;
	}
	

}
