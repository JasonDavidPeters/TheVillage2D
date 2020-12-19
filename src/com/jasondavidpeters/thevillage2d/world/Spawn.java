package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.world.entities.Entity;
import com.jasondavidpeters.thevillage2d.world.entities.ground.GroundEntity;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;
import com.jasondavidpeters.thevillage2d.world.tiles.Tile;

public class Spawn extends Level {

	public Spawn(String levelFile) {
		super(levelFile);
	}

	public void tick() {
		if (GameObject.RESPAWN_OBJECTS.size() > 0)
			for (int i = 0; i < GameObject.RESPAWN_OBJECTS.size(); i++)
				if (GameObject.RESPAWN_OBJECTS.get(i).getLevel().equals(this))
				GameObject.RESPAWN_OBJECTS.get(i).tick();

		for (int i = 0; i < GroundEntity.GROUNDENTITIES.size(); i++)
			if (GroundEntity.GROUNDENTITIES.get(i).getLevel().equals(this))
			GroundEntity.GROUNDENTITIES.get(i).tick();

		if (GameObject.RESPAWN_OBJECTS.size() > 0) {
			for (int i = 0; i < GameObject.RESPAWN_OBJECTS.size(); i++) {
				// if the respawn timer is <= 0 then add it back to the gameobjects list
				if (GameObject.RESPAWN_OBJECTS.get(i).getRespawnTimer() <= 0) {
					if (GameObject.RESPAWN_OBJECTS.get(i).getLevel().equals(this)) {
					/*
					 * If you are out of the level longer than the respawn timer then the world is
					 * not rendering and the object is not loaded
					 */
					GameObject.RESPAWN_OBJECTS.get(i).setRemoved(false);
					gameObjects.add(GameObject.RESPAWN_OBJECTS.get(i));
					GameObject.RESPAWN_OBJECTS.remove(i);
					}
				}
			}
		}

		for (int i = 0; i < entities.size(); i++)
			if (entities.get(i).getLevel().equals(this))
			entities.get(i).tick();
//		for (int i = 0; i < tileList.size(); i++)
//			tileList.get(i).tick();
		for (int i = 0; i < gameObjects.size(); i++)
			if (gameObjects.get(i).getLevel().equals(this))
			gameObjects.get(i).tick();
	}

	public void add(Object object) {
		if (object instanceof Tile)
			tileList.add((Tile) object);
		if (object instanceof GroundEntity)
			groundEntities.add((GroundEntity) object);
		if (object instanceof Entity)
			entities.add((Entity) object);
		if (object instanceof GameObject)
			gameObjects.add((GameObject) object);
	}

}
