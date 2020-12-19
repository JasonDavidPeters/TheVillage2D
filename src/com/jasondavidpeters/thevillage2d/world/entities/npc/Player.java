package com.jasondavidpeters.thevillage2d.world.entities.npc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Animation;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.assets.Spritesheet;
import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.input.Mouse;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.screen.ui.Panel;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.Entity;
import com.jasondavidpeters.thevillage2d.world.entities.ground.GroundEntity;
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
	protected Animation[] mining = { new Animation(Spritesheet.PLAYERSHEET.subsheet(4 * 16, 0, 16, 4 * 16), 16, 16),
			new Animation(Spritesheet.PLAYERSHEET.subsheet(4 * 16, 0, 16, 4 * 16), 16, 16),
			new Animation(Spritesheet.PLAYERSHEET.subsheet(4 * 16, 0, 16, 4 * 16), 16, 16),
			new Animation(Spritesheet.PLAYERSHEET.subsheet(4 * 16, 0, 16, 4 * 16), 16, 16) };
	private boolean settingsOpen;
	private boolean equipmentOpen;
	private Map<Integer, Integer> inventory;
	private final int maxInventorySlots = 12;
	private boolean isMining;
	private boolean setPickaxePosition;
	protected Random random = new Random();
	private boolean inventoryFull;

	public Player(Level level,String name, int x, int y, Mouse mouse) {
		super(level,name, x * 16, y * 16);
		this.mouse = mouse;
		animation = new Animation(Spritesheet.ORES.subsheet(0, 0, 4 * 16, 16), 16, 16);
		sprite = Sprite.PLAYER_FORWARD[0];
		width = 9;
		height = 15;
		speed = .8;
		inventory = new HashMap<Integer, Integer>();
		equipment[0] = new StonePickaxe(equipmentHandler, Sprite.STONE_PICKAXE);
		initaliseUI();
	}

	private void initaliseUI() {
		Game.UIMANAGER.drawInitialUI(this);
	}

	public void render(Renderer renderer) {
		super.render(renderer);
		renderer.setOffsets((int) x - Renderer.WIDTH / 2, (int) y - Renderer.HEIGHT / 2);
		renderer.renderPlayer((int) x, (int) y, this);
		if (inventoryOpen) {
			/*
			 * Render the inventory do we make image components?
			 */
			Game.UIMANAGER.getComponent("inventory_panel").setToRender(true);
		} else {
			Game.UIMANAGER.getComponent("inventory_panel").setToRender(false);
		}
		if (equipmentOpen)
			Game.UIMANAGER.getComponent("equipment_panel").setToRender(true);
		else
			Game.UIMANAGER.getComponent("equipment_panel").setToRender(false);
		// render inventory

		if (equipment != null && equipment.length > 0) {
			for (GameItem gItem : equipment)
				if (gItem != null)
					gItem.render(this, renderer);
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

		// if the player is within radius of GroundEntity.GROUNDENTITIES then add it to
		// player inventory
		// and remove it from GroundEntity.GROUNDENTITIES
		if (!inventoryFull()) {
			for (GroundEntity groundEntity : GroundEntity.GROUNDENTITIES) {
				if (getDistanceFromPlayer(groundEntity.getX(), groundEntity.getY(), x, y) <= 8) {
					/*
					 * TODO: if inventory has empty slots addToInventory(GAMEITEM X) { - add to
					 * hashmap - add to panel - same for remove, both must be done at the same time
					 * each time. }
					 * 
					 */
					inventory.put(getNextEmptyInventorySlot(), groundEntity.getItemID());
					((Panel) Game.UIMANAGER.getComponent("inventory_panel")).add(groundEntity.getSprite());
					groundEntity.setRemove(true);
					/*
					 * item picking up
					 */
				}
			}
		}

		gameObjectInteraction();
		npcInteraction();
		/*
		 * TODO: Check clicking compared to gameobjects should I change gameobject list
		 * dynamically to only what we are rendering?
		 */
	}

	private void npcInteraction() {
		if (mouse.getMouseB() == 1 && mouse.getMouseX() >= 0 && mouse.getMouseX() <= Renderer.WIDTH
				&& mouse.getMouseY() >= 0 && mouse.getMouseY() <= Renderer.HEIGHT) {
			for (Entity e : level.getEntities()) {
				if (!(e instanceof Npc))
					return;
				Npc n = (Npc) e;

				int mouseXo = mouse.getMouseX();
				int mouseYo = mouse.getMouseY();
				if ((mouseXo >= n.getScreenXPosition()
						&& mouseXo <= n.getScreenXPosition() + n.getSprite().getWidth() / 2)
						&& (mouseYo >= n.getScreenYPosition() - n.getSprite().getHeight() / 2
								&& mouseYo <= n.getScreenYPosition() + n.getSprite().getHeight() / 2)) {
					if (!n.canInteractFromAfar()) {
						double radius = ((n.getSprite().getWidth()));
						if (getDistanceFromPlayer(n.getX(), n.getY(), x, y) <= radius) {
							n.interact(this);
						}
					} else {
						n.interact(this);
					}
				}
			}
		}
	}

	private void gameObjectInteraction() {
		if (mouse.getMouseB() == 1 && mouse.getMouseX() >= 0 && mouse.getMouseX() <= Renderer.WIDTH
				&& mouse.getMouseY() >= 0 && mouse.getMouseY() <= Renderer.HEIGHT) {
			for (GameObject gameObject : level.getGameObjects()) {
//				Debug.drawRect(gameObject.getX(), gameObject.getY(), gameObject.getSprite().getWidth(),
//						gameObject.getSprite().getHeight());

				int mouseXo = mouse.getMouseX();// need to offset the mouse by the map coordinates
				int mouseYo = mouse.getMouseY();

//				System.out.println("player x: " +(int) x + ", player y: " + (int)y + ", Mouse x: " + mouseXo + ", Gameobject x: " + gameObject.getScreenPositionX() + ", Mouse y: " + mouseYo + ", Gameobject y: " + gameObject.getScreenPositionY());
				if ((mouseXo >= gameObject.getScreenPositionX()
						&& mouseXo <= gameObject.getScreenPositionX() + gameObject.getSprite().getWidth() / 2)
						&& (mouseYo >= gameObject.getScreenPositionY() && mouseYo <= gameObject.getScreenPositionY()
								+ gameObject.getSprite().getHeight() / 2)) {

					if (!gameObject.canInteractFromFar()) {
						double radius = ((gameObject.getSprite().getWidth()));
						if (getDistanceFromPlayer(gameObject.getX(), gameObject.getY(), x, y) <= radius) {
							isMining = true;
							gameObject.interact(this);
							mining[dir].start();
							if (mining[dir].animSprite() != null) {
								sprite = mining[dir].animSprite();
								if (dir == 1)
									sprite.setFlip(1);
								if (isMining) {
									if (!setPickaxePosition) {
										equipment[0].updateY(equipment[0].getY() - 50);
										setPickaxePosition = true;
//										System.out.println(true);
									}
//									System.out.println(isMining);
									equipment[0].updateY((equipment[0].getY()) + 1);
									equipment[0].updateX((equipment[0].getX()) + random.nextGaussian());
									if (equipment[0].getY() >= equipmentHandler.getYOffset(dir)) {
										setPickaxePosition = false;
									}
								}
							}

							// animation.start();
//							mouse.reset();
						}
					} else {
						// if the object can be clicked from any distance
						mouse.reset();
					}
				}
			}
		}
		if (!isMining) {
			equipment[0].updateY(equipmentHandler.getYOffset(dir));
			setPickaxePosition = false;
		}
		if (isMining) {
			isMining = false;
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
	
	public void emptyInventory() { 
		if (inventory.values().size() > 0)
		inventory.clear();
		((Panel) Game.UIMANAGER.getComponent("inventory_panel")).clearSprites();
	}

	public int getNextEmptyInventorySlot() {
		// slot, itemID
		Iterator<Integer> it = inventory.values().iterator();
		int count = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
			count++;
		}

		return count + 1;
	}

	public void setInventoryOpen(boolean inventoryOpen) {
		this.inventoryOpen = inventoryOpen;
	}

	public void setEquipmentOpen(boolean equipmentOpen) {
		this.equipmentOpen = equipmentOpen;
	}

	public void setSettingsOpen(boolean settingsOpen) {
		this.settingsOpen = settingsOpen;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public boolean getInventoryOpen() {
		return inventoryOpen;
	}

	public boolean getEquipmentOpen() {
		return equipmentOpen;
	}

	public boolean getSettingsOpen() {
		return settingsOpen;
	}

	public boolean inventoryFull() {
		if (inventory.values().size() >= maxInventorySlots)
			inventoryFull = true;
		else
			inventoryFull = false;
		return inventoryFull;
	}

}
