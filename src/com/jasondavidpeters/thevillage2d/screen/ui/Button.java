package com.jasondavidpeters.thevillage2d.screen.ui;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.input.Mouse;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.Player;

public class Button extends Component {

	protected String btnText;
	protected Mouse mouse;
	protected Player player;

	// ADD SPRITE TO BUTTONS, OR ALL COMPONENTS FOR THAT MATTER
	public Button(String btnText, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.btnText = btnText;
	}

	public Button(String btnText, int x, int y, int width, int height, Sprite sprite, Player player) {
		super(x, y, width, height);
		this.btnText = btnText;
		this.sprite = sprite;
		this.player=player;
		this.mouse=player.getMouse();
	}

	public Button(String btnText, int x, int y, int width, int height, int color) {
		super(x, y, width, height);
		this.btnText = btnText;
		this.color = color;
	}

	public Button(String btnText, int x, int y, int width, int height, int color, Mouse mouse) {
		super(x, y, width, height);
		this.btnText = btnText;
		this.color = color;
		this.mouse = mouse;
	}

	public void render(Renderer renderer) {
		if (getParent() != null) {
			if (sprite != null) {
				renderer.renderComponent(getAbsoluteX(), getAbsoluteY(), width, height, sprite);
			} else {
				renderer.renderComponent(getAbsoluteX(), getAbsoluteY(), width, height, color, true);
			}
		} else {
			renderer.renderComponent(x, y, width, height, color, true);
		}
	}
	
	protected void checkClicked() {
		if (!isDisabled()) {
			if (mouse.getMouseB() == 1) {
				if (mouse.getMouseX() >= getAbsoluteX() - width / 2 && mouse.getMouseX() <= getAbsoluteX() + width / 2
						&& mouse.getMouseY() >= getAbsoluteY() - height && mouse.getMouseY() <= getAbsoluteY()) {
					isClicked=true;
					mouse.reset();
				}
			}
		}
	}

	public void tick() {
	}

	public int getAbsoluteX() {
		if (getParent() == null) return x;
		return getParent().getX() + x;
	}

	public int getAbsoluteY() {
		if (getParent() == null) return y;
		return getParent().getY() + y;
	}

}
