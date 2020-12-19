package com.jasondavidpeters.thevillage2d.world.entities.npc;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.world.Level;

public class Shopkeeper extends Npc {

	public Shopkeeper(Level level,String name, int x, int y, Sprite sprite) {
		super(level, name, x*16, y*16, sprite);
	}
	
	public void interact(Player p) {
		p.emptyInventory();
		System.out.println("interacting with shopkeeper");
		p.getMouse().reset();
	}
	
	private void openShop() {
		
	}
}
