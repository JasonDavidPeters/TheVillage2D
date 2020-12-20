package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Panel extends Component {

	private List<Component> components = new ArrayList<Component>();
	private List<Sprite> sprites = new ArrayList<Sprite>();

	private int yOffset=13;
	private int count;
	public Panel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Panel(int x, int y, int width, int height, int col) {
		super(x, y, width, height);
		this.color = col;
	}
	public Panel(int x, int y, int width, int height, Sprite sprite) {
		super(x, y, width, height,sprite);
	}

	public void add(Component c) {
		components.add(c);
		c.setParent(this);

	}

	public void add(Sprite s) {
		sprites.add(s);
	}
	public void clearSprites() {
		if (sprites.size() <=0) return;
		sprites.clear();
	}

	public void remove(Sprite s) {
		sprites.remove(s);
	}

	public void remove(Component c) {
		components.remove(c);
	}

	/*
	 * Will contain a list of components
	 */
	public void tick() {
		for (Component c : components)
			c.tick();
	}
	
	public void setToRender(boolean toRender) {
//		System.out.println(components.size());
		this.toRender=toRender;
		for (Component c : components)
			c.setToRender(toRender);
	}

	public void render(Renderer renderer) {
		if (sprite!=null)
			renderer.renderComponent(x, y, width, height, sprite);
		else
		renderer.renderComponent(x, y, width, height, color, false);
		for (Component c : components)
			if (c.getToRender())
				c.render(renderer);
		
		/*
		 * TODO: instead of sprites, use gameitems  with IDs and set screen coordinates
		 * then it opens up paths to click the inventory items
		 */
		if (sprites.size() > 0) {
			for (int i = 0; i < (sprites.size() < 4 ? sprites.size() : 4); i++)
				renderer.renderSprite(x + (i) * 15, (y+yOffset), sprites.get(i), false);
			for (int i = 4; i < (sprites.size() < 8 ? sprites.size() : 8); i++) {
				renderer.renderSprite(x + (i - i + count) * 15, (y+yOffset)+ 13, sprites.get(i), false);
				count++;
			}
			count = 0;
			for (int i = 8; i < (sprites.size() < 12 ? sprites.size() : 12); i++) {
				renderer.renderSprite(x + (i - i + count) * 15, (y+yOffset)+ 26, sprites.get(i), false);
				count++;
			}
			count = 0;
		}
	}

}
