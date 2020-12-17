package com.jasondavidpeters.thevillage2d.assets;

import com.jasondavidpeters.thevillage2d.world.entities.Entity;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;

public class Animation {

	protected int frame;
	protected Sprite[] animations;
	protected int maxFrames;
	protected boolean loop;
	protected Entity entity;
	protected GameObject gameObject;
	protected Sprite animSprite;
	protected boolean stop;
	protected int time = 0;
	
	public Animation(Spritesheet sheet, int width, int height) {
		animations = Sprite.toArray(sheet, width, height);
		maxFrames = animations.length;	
	}
	
	/*
	 * TODO:
	 *  customise animations;
	 *  be able to get frames, set frames etc
	 *  
	 *  disable player cancelling animations in certain events
	 */
	public void start() {
		if (frame < maxFrames) {
			time++;
			if (time % 20 > 18) {
				setAnimSprite(animations[frame++]);
			}
		}
		if (frame == maxFrames) frame=0;
	}
	public void setAnimSprite(Sprite sprite) { 
		this.animSprite=sprite;
	}
	public Sprite animSprite() {
		return animSprite;
	}

	public void stop() {
		frame=0;
	}

	public void cancel() {
		loop = false;
	}

}
