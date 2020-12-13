package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.assets.Sprite;

public class Npc extends Entity {

	protected Sprite sprite;
	protected int dir;
	protected int anim;
	protected boolean walking;

	public Npc(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
	}

	public Npc(int x, int y) {
		super(x, y);
	}

	protected void move(int xa, int ya) {
		y+=ya;
		x+=xa;
		if (ya < 0) {
			dir = 0;
		}
		if (ya > 0) {
			dir = 2;
		}
		if (xa > 0) {
			dir = 1;
		}
		if (xa < 0) {
			dir = 3;
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

}
