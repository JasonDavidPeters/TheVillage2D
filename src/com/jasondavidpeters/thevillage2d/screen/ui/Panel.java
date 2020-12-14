package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Panel extends Component {
	
	private List<Component> components = new ArrayList<Component>();
	
	public Panel(int x, int y, int width, int height) {
		super (x,y,width,height);
	}
	public Panel(int x, int y, int width, int height, int col) {
		super (x,y,width,height);
		this.color=col;
	}
	
	public void add(Component c) {
		components.add(c);
		c.setParent(this);
		// each component should be offset by its container
		
	}
	public void remove(Component c) {
		components.remove(c);
	}
	/* 
	 * Will contain a list of components
	 */
	
	public void render(Renderer renderer) {
		renderer.renderComponent(x, y, width, height, color, false);
		for (Component c: components)
			c.render(renderer);
	}
	

}
