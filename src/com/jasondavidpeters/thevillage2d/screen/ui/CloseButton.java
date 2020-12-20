package com.jasondavidpeters.thevillage2d.screen.ui;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;

public class CloseButton extends Button {

	public CloseButton(String btnText, int x, int y, int width, int height, Sprite sprite, Player player) {
		super(btnText, x, y, width, height, sprite, player);
	}

	public void tick() {
		checkClicked();
		if (isClicked()) {
			parent.setToRender(false);
			mouse.reset();
			isClicked=false;
		} else {
			isClicked=false;
		}
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

}
