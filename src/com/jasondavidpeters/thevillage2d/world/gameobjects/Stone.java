package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.world.entities.ground.GroundEntity;
import com.jasondavidpeters.thevillage2d.world.entities.ground.StoneGroundEntity;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class Stone extends GameObject {

	public Stone(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		life = 100;
		respawnTime = 3; // seconds
	}

	public boolean isSolid() {
		return true;
	}

	public void tick() {
		ticks++;
		if (respawnTimer > 0) {
			if (ticks % 60 == 0) {
				respawnTimer--;
				if (respawnTimer == 0)
					life = 100;
			}
		}
	}

	public void interact(Player p) {
		super.interact(p);
		life--;
		if (life <= 0) {
			setRemoved(true);
			respawnTimer = respawnTime;
			GroundEntity.GROUNDENTITIES.add(
					new StoneGroundEntity(x, y + (random.nextInt(10) - 10), 15,y, dir, Sprite.GROUND_STONE_ENTITY));
			// any dropables entities add them here
			// need to be able to add to level list of entities
		}
	}

}
