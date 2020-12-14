package com.jasondavidpeters.thevillage2d.screen.ui;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Button extends Component {

	private String btnText;

	// ADD SPRITE TO BUTTONS, OR ALL COMPONENTS FOR THAT MATTER
	public Button(String btnText, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.btnText = btnText;
	}
	public Button(String btnText, int x, int y, int width, int height, int color) {
		super(x, y, width, height);
		this.btnText = btnText;
		this.color=color;
	}

	public void render(Renderer renderer) {
		if (getParent() != null)
			renderer.renderComponent(getParent().getX() + x, getParent().getY() + y, width, height, color, true);
		else
			renderer.renderComponent(x, y, width, height, color, true);
	}

}
