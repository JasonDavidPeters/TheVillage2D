package com.jasondavidpeters.thevillage2d.world;

import java.util.ArrayList;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.util.Debug;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;

public class Npc extends Entity {

	protected Sprite sprite;
	protected int dir;
	protected int anim;
	protected boolean walking;
	protected int width, height;
	protected double speed;

	public Npc(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
	}

	public Npc(int x, int y) {
		super(x, y);
	}

	protected boolean tileCollision(double xp, double yp, double xa, double ya, int npcWidth, int npcHeight) {
		for (int c = 0; c < 4; c++) {
			double xx = ((xp + xa) - c % 2 + npcWidth) / 16; // when c is 3 and 4
			double yy = ((yp + ya) - c / 2 + npcHeight) / 16;
			if (c == 0) {
				xx = Math.floor(xx);
				yy = Math.floor(yy);
			}
//			System.out.println(xx + " " + yy + " " + level.getTile((int) xx, (int) yy) + " " + c);
			if (level.getTile((int) xx, (int) yy).isSolid())
				return true;
		}
		return false;
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
		ArrayList<GameObject> gameObjects = (ArrayList<GameObject>) level.gameObjects;

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
		} else {
			xa = 0;
			ya = 0;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
