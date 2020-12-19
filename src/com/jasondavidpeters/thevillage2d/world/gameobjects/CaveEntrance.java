package com.jasondavidpeters.thevillage2d.world.gameobjects;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class CaveEntrance extends GameObject {

	public CaveEntrance(Level level, int x, int y, Sprite sprite) {
		super(level, x, y, sprite);
	}

	public boolean isSolid() {
		return true;
	}

	public void interact(Player p) {
		if (Level.SPAWN_LEVEL.currentLevel()) {
			Level.SPAWN_LEVEL.setCurrentLevel(false);
			Level.SPAWN_LEVEL.removePlayer(p);
			Level.CAVE_LEVEL.setCurrentLevel(true);
			Level.CAVE_LEVEL.addPlayer(p);
			p.setX(3*16);
			p.setY(3*16);
		} else if (Level.CAVE_LEVEL.currentLevel()) {
			Level.CAVE_LEVEL.setCurrentLevel(false);
			Level.CAVE_LEVEL.removePlayer(p);
			Level.SPAWN_LEVEL.setCurrentLevel(true);
			Level.SPAWN_LEVEL.addPlayer(p);
			p.setX(20*16);
			p.setY(16*16);
			
		}
		p.getMouse().reset();
	}
}
