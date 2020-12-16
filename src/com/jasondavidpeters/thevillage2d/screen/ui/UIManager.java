package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class UIManager {

	/*
	 * store all ui components here render them through this class make one static
	 * instance of the ui manager
	 */
	private List<Component> components = new ArrayList<Component>();
	private boolean drawingInventory;

	public UIManager() {
		drawingInventory = true;
		drawInventory();
	}

	public void add(Component c) {
		components.add(c);
	}

	public void remove(Component c) {
		components.remove(c);
	}

	public void clear() {
		components.clear();
	}

	public Component getComponent(String name) {
		for (Component c : components)
			if (c.getComponentName().equals(name))
				return c;
		return null;
	}

	public void render(Renderer renderer) {
		for (Component c : components)
			if (c.getToRender())
				c.render(renderer);
	}

	public void tick() {
		for (Component c : components)
			c.tick();
	}

	public void drawInitialUI(Player p) {
		Panel gamePanel = new Panel(Renderer.WIDTH - 41, 125, 40, Renderer.HEIGHT - 126, 0xFF0000);
		Button inventoryButton = new InventoryButton("test", 0, 0, 40, 17, Sprite.INVENTORY_BUTTON, p);
		gamePanel.setToRender(true);
		inventoryButton.setToRender(true);
		gamePanel.add(inventoryButton);
		add(gamePanel);
	}

	public void drawInventory() {
		System.out.println(components.size());
		if (drawingInventory) {
			Panel inventory = new Panel(Renderer.WIDTH - 61, 75, 60, 50, 0xFF0000);
			inventory.setComponentName("inventory_panel");
			add(inventory);
			drawingInventory = false;
		}
	}
}
