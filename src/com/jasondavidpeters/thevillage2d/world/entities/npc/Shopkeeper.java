package com.jasondavidpeters.thevillage2d.world.entities.npc;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.ui.Panel;
import com.jasondavidpeters.thevillage2d.world.Level;

public class Shopkeeper extends Npc {

	private Player p;
	
	public Shopkeeper(Level level,String name, int x, int y, Sprite sprite) {
		super(level, name, x*16, y*16, sprite);
	}
	
	public void interact(Player p) {
		this.p=p;
		openShop();
//		p.emptyInventory();
		p.getMouse().reset();
	}
	
	private void openShop() {
		((Panel)Game.UIMANAGER.getComponent("shop_panel")).setToRender(true);
		((Panel)Game.UIMANAGER.getComponent("inventory_panel")).setToRender(true);
		/*
		 * uimanager get shoppanel, set to render true
		 * Open gui for shop
		 * stop the player from moving
		 * add a close button
		 */
	}
}
