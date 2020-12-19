package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class UIManager {

	/*
	 * store all ui components here render them through this class make one static
	 * instance of the ui manager
	 */
	private List<Component> components = new ArrayList<Component>();

	public UIManager() {
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
//		System.out.println("looking for: " + name);
		for (Component c : components) {
			if (c.getComponentName() == null) continue;
			if (c.getComponentName().equals(name))
				return c;
		}
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
		Button inventoryButton = new InventoryButton("", 0*gamePanel.getWidth()/3, 0, gamePanel.getWidth()/3, 17, Sprite.INVENTORY_BUTTON, p);
		Button equipmentButton = new EquipmentButton("", 1*gamePanel.getWidth()/3, 0, gamePanel.getWidth()/3, 17, Sprite.EQUIPMENT_BUTTON, p);
		Button settingsButton = new SettingsButton("", (2*gamePanel.getWidth()/3)+1, 0, gamePanel.getWidth()/3, 17, Sprite.SETTINGS_BUTTON, p);
		gamePanel.setToRender(true);
		inventoryButton.setToRender(true);
		equipmentButton.setToRender(true);
		settingsButton.setToRender(true);
		gamePanel.add(equipmentButton);
		gamePanel.add(inventoryButton);
		gamePanel.add(settingsButton);
		add(gamePanel);
		
		/*
		 * Drawing inventory and equipment panels 
		 */
		Panel inventory = new Panel(Renderer.WIDTH - 61, 75, 60, 50, 0xFF0000);
		inventory.setComponentName("inventory_panel");
		add(inventory);
		/*
		 * TODO: draw equipment sprite, set the equipment slot to sprite of worn equipment in player array
		 */
		Panel equipment= new Panel(Renderer.WIDTH - 61, 75, 60, 50, 0xffaeff);
		equipment.setComponentName("equipment_panel");
		add(equipment);
	}
}
