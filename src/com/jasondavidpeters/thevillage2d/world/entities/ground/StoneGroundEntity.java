package com.jasondavidpeters.thevillage2d.world.entities.ground;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class StoneGroundEntity extends GroundEntity {

	public StoneGroundEntity(double  x, double y, int aliveTime,  Sprite sprite) {
		super(x, y, aliveTime, sprite);
	}
	public StoneGroundEntity(double x, double y, int aliveTime, double groundLevel, int dir,Sprite sprite) {
		super(x, y, aliveTime, groundLevel, dir, sprite);
	}
	public void render(Renderer r) {
		r.renderSprite((int) x, (int) y, sprite, true);
	}

}
