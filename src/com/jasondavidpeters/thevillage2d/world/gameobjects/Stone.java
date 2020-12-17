package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Animation;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.assets.Spritesheet;
import com.jasondavidpeters.thevillage2d.world.entities.ground.GroundEntity;
import com.jasondavidpeters.thevillage2d.world.entities.ground.StoneGroundEntity;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class Stone extends GameObject {

	Animation animation = new Animation(Spritesheet.ORES.subsheet(0, 0, 4 * 16, 16), 16, 16);

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
		//if (life < 80) {
			animation.start();
			if (animation.animSprite() != null)
				sprite = animation.animSprite();
		//}
		if (respawnTimer > 0) {
			if (ticks % 60 == 0) {
				respawnTimer--;
				if (respawnTimer == 0)
					respawn();
			}
		}
	}
	
	protected void respawn() {
		life=100;
		sprite = Sprite.STONEORE;
	}

	public void interact(Player p) {
		super.interact(p);
		life--;
		if (life <= 0) {
			setRemoved(true);
			respawnTimer = respawnTime;
			GroundEntity.GROUNDENTITIES.add(
					new StoneGroundEntity(x, y + (random.nextInt(10) - 10), 15, y, dir, Sprite.GROUND_STONE_ENTITY));
			// any dropables entities add them here
			// need to be able to add to level list of entities
		}
	}

}
