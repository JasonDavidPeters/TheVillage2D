package com.jasondavidpeters.thevillage2d.screen.ui;

import java.util.ArrayList;
import java.util.List;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Panel extends Component {

	private List<Component> components = new ArrayList<Component>();
	private List<Sprite> sprites = new ArrayList<Sprite>();

	private int yOffset;
	private int count;

	public Panel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public Panel(int x, int y, int width, int height, int col) {
		super(x, y, width, height);
		this.color = col;
	}

	public void add(Component c) {
		components.add(c);
		c.setParent(this);
		// each component should be offset by its container

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

	public void render(Renderer renderer) {
		renderer.renderComponent(x, y, width, height, color, false);
		for (Component c : components)
			if (c.getToRender())
				c.render(renderer);
		if (sprites.size() > 0) {
			for (int i = 0; i < (sprites.size() < 4 ? sprites.size() : 4); i++)
				renderer.renderSprite(x + (i) * 15, y, sprites.get(i), false);
			for (int i = 4; i < (sprites.size() < 8 ? sprites.size() : 8); i++) {
				renderer.renderSprite(x + (i - i + count) * 15, y + 15, sprites.get(i), false);
				count++;
			}
			count = 0;
			for (int i = 8; i < (sprites.size() < 12 ? sprites.size() : 12); i++) {
				renderer.renderSprite(x + (i - i + count) * 15, y + 30, sprites.get(i), false);
				count++;
			}
			count = 0;
		}
	}

}
