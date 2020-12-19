package com.jasondavidpeters.thevillage2d.world.entities.ground;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.Entity;

public class GroundEntity extends Entity {

	public static List<GroundEntity> GROUNDENTITIES = new ArrayList<GroundEntity>();

	protected int aliveTimer;
	protected int aliveTime;
	protected int ticks;
	protected int dir;
	protected double bounceHeight;
	protected double groundLevel;
	protected boolean goUp;
	protected int itemID;
	protected double xa = 3,ya = 3;

	public GroundEntity(int itemID, Level level,double x, double y, int aliveTime, Sprite sprite) {
		super(level, x, y);
		this.itemID = itemID;
		this.sprite = sprite;
		this.aliveTime = aliveTime;
		this.aliveTimer = aliveTime;
		bounceHeight = y - 15;
	}

	public GroundEntity(int itemID,Level level,double x, double y, int aliveTime, double groundLevel, int dir, Sprite sprite) {
		super(level,x, y);
		this.itemID = itemID;
		this.groundLevel = groundLevel;
		this.dir = dir;
		this.sprite = sprite;
		this.aliveTime = aliveTime;
		this.aliveTimer = aliveTime;
		bounceHeight = y - 15;
	}

	public void tick() {
		// TODO: account for collision with ground entities, reverse the direction
		ticks++;
			if (bounceHeight != groundLevel) {
				if (y > bounceHeight && goUp) {
					y -= ya; // how far the entity will bounce upwards
				}
				if (y <= bounceHeight && goUp)
					goUp = false;

				if (y <= groundLevel && !goUp) {
					y += ya; // how far it will bounce downwards
				}
				if (y >= groundLevel) {
					goUp = true;
					bounceHeight += ya;
					if (bounceHeight <= groundLevel) {
						if (tileCollision(x, y, xa, ya, sprite.getWidth()-13, sprite.getHeight()))
							xa*=-1;
						if (dir == 1)
							x += xa;
						else if (dir == 3)
							x -= xa;
					}
				}
			}
		
		if (ticks % 60 == 0)
			aliveTimer--;
		if (aliveTimer <= 0) {
			setRemove(true);
		}
	}

	public void render(Renderer r) {
	}

	public boolean isPermanent() {
		return false;
	}

	public int getItemID() {
		return itemID;
	}
}
