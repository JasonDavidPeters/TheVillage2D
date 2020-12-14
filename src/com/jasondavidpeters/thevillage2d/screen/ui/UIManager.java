package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class UIManager {

	/*
	 * store all ui components here
	 * render them through this class
	 * make one static instance of the ui manager
	 */
	private List<Component> components = new ArrayList<Component>();
	
	public void add(Component c) { 
		components.add(c);
	}
	public void remove(Component c) {
		components.remove(c);
	}
	
	public void render(Renderer renderer) {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).render(renderer);
		}
	}
	public void tick() {
		for (Component c: components)
			c.tick();
	}
}
