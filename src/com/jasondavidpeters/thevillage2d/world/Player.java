package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Player extends Npc {

	/*
	 * Get keyboard input and check for directional input
	 */
	private int xa, ya, cycle;

	public Player(int x, int y) {
		super(x + Renderer.WIDTH / 2, +Renderer.HEIGHT / 2);
		sprite = Sprite.PLAYER_FORWARD[0];
	}

	public void render(Renderer renderer) {
		renderer.renderPlayer(x + Renderer.WIDTH / 2, y + Renderer.HEIGHT / 2, x, y, this);
	}

	public void tick() {
		if (anim > 7000)
			anim = 0;
		anim++;
		xa = 0;
		ya = 0;
		if (Keyboard.up)
			ya = -1;
		if (Keyboard.down)
			ya = 1;
		if (Keyboard.left)
			xa = -1;
		if (Keyboard.right)
			xa = 1;
		if (xa < 0 || xa > 0 || ya < 0 || ya > 0) {
			walking=true;
			if (xa < 0 || xa > 0)
				move(xa, 0);
			if (ya < 0 || ya > 0)
				move(0, ya);
		} else {
			walking=false;
		}
		playerMovement();
	}

	private void playerMovement() {
		switch (dir) {
		case 0:
			Sprite[] forward = { Sprite.PLAYER_FORWARD[1], Sprite.PLAYER_FORWARD[2] };
			if (anim % 20 > 18)
				sprite = forward[cycle++];
			if (cycle == forward.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_FORWARD[0];
			break;
		case 1:
			Sprite[] right = { Sprite.PLAYER_RIGHT[1], Sprite.PLAYER_RIGHT[2] };
			if (anim % 20 > 18)
				sprite = right[cycle++];
			if (cycle == right.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_RIGHT[0];
			break;
		case 2:
			Sprite[] backward = { Sprite.PLAYER_BACKWARD[1], Sprite.PLAYER_BACKWARD[2] };
			if (anim % 20 > 18)
				sprite = backward[cycle++];
			if (cycle == backward.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_BACKWARD[0];
			break;
		case 3:
			Sprite[] left = { Sprite.PLAYER_LEFT[1], Sprite.PLAYER_LEFT[2] };
			if (anim % 20 > 18)
				sprite = left[cycle++];
			if (cycle == left.length)
				cycle = 0;
			if (!walking)
				sprite = Sprite.PLAYER_LEFT[0];
			break;
		default:
			break;
		}
	}

}
