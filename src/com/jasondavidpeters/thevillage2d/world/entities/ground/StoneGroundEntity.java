package com.jasondavidpeters.thevillage2d.world.entities.ground;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.Level;

public class StoneGroundEntity extends GroundEntity {

	public StoneGroundEntity(int itemID,Level level,double  x, double y, int aliveTime,  Sprite sprite) {
		super(itemID,level,x, y, aliveTime, sprite);
	}
	public StoneGroundEntity(int itemID,Level level,double x, double y, int aliveTime, double groundLevel, int dir,Sprite sprite) {
		super(itemID,level,x, y, aliveTime, groundLevel, dir, sprite);
	}
	public void render(Renderer r) {
		r.renderSprite((int) x, (int) y, sprite, true);
	}

}
