package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.gameitems.GameItem;

public class InventoryPanel extends Panel {

	private List<Component> components = new ArrayList<Component>();
	private List<GameItem> gameItems = new ArrayList<GameItem>();

	private int yOffset = 13;
	private int count;

	public InventoryPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public InventoryPanel(int x, int y, int width, int height, int col) {
		super(x, y, width, height);
		this.color = col;
	}

	public InventoryPanel(int x, int y, int width, int height, Sprite sprite) {
		super(x, y, width, height, sprite);
	}

	public void add(GameItem g) {
		// find the first instance that is null
		int emptySlot = 0;
		if (gameItems.size() > 0) {
			for (int i = 0; i < gameItems.size(); i++) {
				if (gameItems.get(i) == null) {
					emptySlot = i;
					break;
				} else {
					emptySlot++;
				}
			}
		}
		if (emptySlot < gameItems.size())
			if (gameItems.get(emptySlot) == null)
				gameItems.set(emptySlot, g);
			else
				gameItems.add(emptySlot, g);
		else
			gameItems.add(g); // add at count
	}

	public void clearSprites() {
		if (gameItems.size() <= 0)
			return;
		gameItems.clear();
	}

	public void remove(GameItem g) {
		gameItems.remove(g);
	}

	/*
	 * Will contain a list of components
	 */
	public void tick() {
		for (Component c : components)
			c.tick();

		if (gameItems.size() > 0)
			for (GameItem o : gameItems)
				if (o != null)
					o.tick();

//		for (GameItem g : gameItems) {
//			if (g.remove()) {
//				gameItems.remove(g);
//				break;
//			}
//		}
		for (int i = 0; i < gameItems.size(); i++) {
			if (gameItems.get(i) != null)
				if (gameItems.get(i).remove()) {
					gameItems.set(i, null);
					break;
				}
		}
	}

	public void setToRender(boolean toRender) {
//		System.out.println(components.size());
		this.toRender = toRender;
		for (Component c : components)
			c.setToRender(toRender);
	}

	public void render(Renderer renderer) {
		if (sprite != null)
			renderer.renderComponent(x, y, width, height, sprite);
		else
			renderer.renderComponent(x, y, width, height, color, false);
		for (Component c : components)
			if (c.getToRender())
				c.render(renderer);

		/*
		 * TODO: instead of sprites, use gameitems with IDs and set screen coordinates
		 * then it opens up paths to click the inventory items
		 */

		if (gameItems.size() > 0) {
			/*
			 * game items size is not changing we shouldnt render game items based on size
			 * of array if we want dynamic access to game items
			 */
			for (int i = 0; i < (gameItems.size() < 4 ? gameItems.size() : 4); i++) {
				if (gameItems.get(i) == null) {
//					System.out.println(i + " is a null entry");
					continue;
				}
				renderer.renderGameItem(x + (i) * 15, (y + yOffset), gameItems.get(i), false);
			}
			for (int i = 4; i < (gameItems.size() < 8 ? gameItems.size() : 8); i++) {
				if (gameItems.get(i) == null) {
//					System.out.println(i + " is a null entry");
					count++;
					continue;
				}
//				System.out.println(i + " is not a null entry");
				renderer.renderGameItem(x + (i - i + count) * 15, (y + yOffset) + 13, gameItems.get(i), false);
				count++;
			}
			count = 0;
			for (int i = 8; i < (gameItems.size() < 12 ? gameItems.size() : 12); i++) {
				if (gameItems.get(i) == null) {
//					System.out.println(i + " is a null entry");
					count++;
					continue;
				}
				renderer.renderGameItem(x + (i - i + count) * 15, (y + yOffset) + 26, gameItems.get(i), false);
				count++;
			}
			count = 0;
		}
	}

}
