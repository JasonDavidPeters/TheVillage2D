package com.jasondavidpeters.thevillage2d.world.entities.npc;

import java.util.ArrayList;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.Entity;
import com.jasondavidpeters.thevillage2d.world.entities.handlers.EquipmentHandler;
import com.jasondavidpeters.thevillage2d.world.gameitems.GameItem;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;

public class Npc extends Entity {

	protected int dir;
	protected int anim;
	protected boolean walking;
	protected int width, height;
	protected double speed;
	protected GameItem equipment[] = new GameItem[3];
	protected EquipmentHandler equipmentHandler = new EquipmentHandler();

	public Npc(Level level,String name, int x, int y, Sprite sprite) {
		super(level, x, y);
		this.sprite = sprite;
		this.name = name;
	}

	public Npc(Level level, String name, int x, int y) {
		super(level, x, y);
		this.name = name;
	}
	
	public void interact(Player p) {
		
	}

	public void render(Renderer r) {
		r.renderNpc((int)x, (int)y, this, false);
//		r.drawString(name, (int) x - 5, (int) y - 12, 0xFF0000, false);
	}

	public double abs(double n) {
		return +n;
	}

	protected double getDistanceFromPlayer(double oX, double oY, double pX, double pY) {
		double distance = Math.sqrt((pY - oY) * (pY - oY) + (pX - oX) * (pX - oX));
		return distance;
	}

	protected boolean gameObjectCollision(double xp, double yp, double xa, double ya, int npcWidth, int npcHeight) {
		/*
		 * loop through game objects and get the middle of the game object if the middle
		 * + width /2 && middle + height/2 is equal to the desired position then the
		 * player is colliding
		 */
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) level.getGameObjects();

		// find multiples of 16 (tile size) and multiply by half
		for (GameObject o : gameObjects) {
			if (o.isSolid()) {
				double middleX = o.getX() + o.getSprite().getWidth() / 4;
				double middleY = o.getY() + o.getSprite().getHeight() / 8;

				double radius = ((o.getSprite().getWidth()) / 2);
//				Debug.drawRect((int) middleX, (int) middleY, (int) radius, (int) radius);
				if (getDistanceFromPlayer(middleX, middleY, (xp + xa), (yp + ya)) < radius) {
					return true;

				}
			}
		}
		return false;

	}

	protected void move(double xa, double ya) {
		if (!tileCollision(x, y, xa, ya, width, height) && (!gameObjectCollision(x, y, xa, ya, width, height))) {
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
			while (Math.abs(xa) > 0) {
				x += abs(xa);
				xa -= abs(xa);
			}
			while (Math.abs(ya) > 0) {
				y += abs(ya);
				ya += -abs(ya);
			}
			if (equipment != null && equipment.length > 0) 
				for (GameItem gItem : equipment)
					if (gItem != null)
						gItem.updatePosition((x/16), y / 16);
		} else {
			xa = 0;
			ya = 0;
		}
	}
	
	public int getDir() {
		return dir;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getSpeed() {
		return speed;
	}
	public boolean canInteractFromAfar() {
		return false;
	}

}
