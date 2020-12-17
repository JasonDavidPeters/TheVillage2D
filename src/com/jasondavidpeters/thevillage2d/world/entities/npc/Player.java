package com.jasondavidpeters.thevillage2d.world.entities.npc;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Animation;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.assets.Spritesheet;
import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.input.Mouse;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.gameitems.GameItem;
import com.jasondavidpeters.thevillage2d.world.gameitems.StonePickaxe;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;

public class Player extends Npc {

	private double xa, ya;
	/*
	 * Get keyboard input and check for directional input
	 */
	private int cycle;
	private Mouse mouse;

	private boolean inventoryOpen;

	protected Animation animation;

	public Player(String name, int x, int y, Mouse mouse) {
		super(name, x, y);
		this.mouse = mouse;
		animation = new Animation(Spritesheet.ORES.subsheet(0, 0, 4 * 16, 16), 16, 16);
		sprite = Sprite.PLAYER_FORWARD[0];
		width = 9;
		height = 15;
		speed = .8;
		initaliseUI();
		equipment[0] = new StonePickaxe(equipmentHandler,Sprite.STONE_PICKAXE);
	}

	private void initaliseUI() {
		Game.UIMANAGER.drawInitialUI(this);
	}

	public void render(Renderer renderer) {
		super.render(renderer);
		renderer.setOffsets((int) x - Renderer.WIDTH / 2, (int) y - Renderer.HEIGHT / 2);
		renderer.renderPlayer((int) x, (int) y, this);
		if (inventoryOpen)
			Game.UIMANAGER.getComponent("inventory_panel").setToRender(true);
		else
			Game.UIMANAGER.getComponent("inventory_panel").setToRender(false);
		// render inventory
		
		if (equipment != null && equipment.length >0) {
			for (GameItem gItem: equipment)
				if (gItem!=null)
			gItem.render(this,renderer);
		}
	}

	public void tick() {
		if (anim > 7000)
			anim = 0;
		anim++;
		xa = 0;
		ya = 0;
		if (Keyboard.up)
			ya = -1 * speed;
		if (Keyboard.down)
			ya = 1 * speed;
		if (Keyboard.left)
			xa = -1 * speed;
		if (Keyboard.right)
			xa = 1 * speed;
//		System.out.println(xa);
//		System.out.println(mouse);
		if (xa < 0 || xa > 0 || ya < 0 || ya > 0) {
			walking = true;
			if (xa < 0 || xa > 0)
				move(xa, 0);
			if (ya < 0 || ya > 0)
				move(0, ya);
		} else {
			walking = false;
		}
		playerMovement();

		/*
		 * TODO: Check clicking compared to gameobjects should I change gameobject list
		 * dynamically to only what we are rendering?
		 */
		if (mouse.getMouseB() == 1 && mouse.getMouseX() >= 0 && mouse.getMouseX() <= Renderer.WIDTH
				&& mouse.getMouseY() >= 0 && mouse.getMouseY() <= Renderer.HEIGHT) {
			for (GameObject gameObject : level.gameObjects) {
//				Debug.drawRect(gameObject.getX(), gameObject.getY(), gameObject.getSprite().getWidth(),
//						gameObject.getSprite().getHeight());

				int mouseXo = mouse.getMouseX();// need to offset the mouse by the map coordinates
				int mouseYo = mouse.getMouseY();

//				System.out.println("player x: " +(int) x + ", player y: " + (int)y + ", Mouse x: " + mouseXo + ", Gameobject x: " + gameObject.getScreenPositionX() + ", Mouse y: " + mouseYo + ", Gameobject y: " + gameObject.getScreenPositionY());
				if ((mouseXo >= gameObject.getScreenPositionX()
						&& mouseXo <= gameObject.getScreenPositionX() + gameObject.getSprite().getWidth() / 2)
						&& (mouseYo >= gameObject.getScreenPositionY() && mouseYo <= gameObject.getScreenPositionY()
								+ gameObject.getSprite().getHeight() / 2)) {
//					System.out.println();
					if (!gameObject.canInteractFromFar()) {
						double radius = ((gameObject.getSprite().getWidth()));
						if (getDistanceFromPlayer(gameObject.getX(), gameObject.getY(), x, y) <= radius) {
							gameObject.interact(this);
							//animation.start();
							//if (animation.animSprite()!=null)
							//sprite = animation.animSprite();
//							mouse.reset();
						}
					} else {
						// if the object can be clicked from any distance
						mouse.reset();
					}
				}
			}
		}
	}

	private void playerMovement() {
		switch (dir) {
		case 0:
			Sprite[] forward = { Sprite.PLAYER_FORWARD[1], Sprite.PLAYER_FORWARD[2] };
			if (anim % 20 > 18)
				sprite = forward[cycle++];
			if (cycle == forward.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_FORWARD[0];
			break;
		case 1:
			Sprite[] right = { Sprite.PLAYER_RIGHT[1], Sprite.PLAYER_RIGHT[2] };
			if (anim % 20 > 18)
				sprite = right[cycle++];
			if (cycle == right.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_RIGHT[0];
			break;
		case 2:
			Sprite[] backward = { Sprite.PLAYER_BACKWARD[1], Sprite.PLAYER_BACKWARD[2] };
			if (anim % 20 > 18)
				sprite = backward[cycle++];
			if (cycle == backward.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_BACKWARD[0];
			break;
		case 3:
			Sprite[] left = { Sprite.PLAYER_LEFT[1], Sprite.PLAYER_LEFT[2] };
			if (anim % 20 > 18)
				sprite = left[cycle++];
			if (cycle == left.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_LEFT[0];
			break;
		default:
			break;
		}
	}

	public void setInventoryOpen(boolean inventoryOpen) {
		this.inventoryOpen = inventoryOpen;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public boolean getInventoryOpen() {
		return inventoryOpen;
	}

}
