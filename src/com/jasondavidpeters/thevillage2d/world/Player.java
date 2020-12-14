package com.jasondavidpeters.thevillage2d.world;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.input.Keyboard;
import com.jasondavidpeters.thevillage2d.screen.Renderer;
import com.jasondavidpeters.thevillage2d.screen.ui.Button;
import com.jasondavidpeters.thevillage2d.screen.ui.Panel;

public class Player extends Npc {

	private double xa, ya;
	/*
	 * Get keyboard input and check for directional input
	 */
	private int cycle;

	public Player(int x, int y) {
		super(x,y);
		sprite = Sprite.PLAYER_FORWARD[0];
		width=9;
		height=15;
		speed=.8;
		initaliseUI();
	}
	
	private void initaliseUI() { 
		Panel gamePanel = new Panel(Renderer.WIDTH-41, 125, 40, Renderer.HEIGHT-126,0xFF0000);
		gamePanel.add(new Button("test", 0, 0, 15, 15));
		gamePanel.add(new Button("test", 15, 0, 15, 15, 0xFF0F00));
		gamePanel.add(new Button("test", 30, 0, 15, 15, 0x0F0F0f));
		Game.UIMANAGER.add(gamePanel);
	}

	public void render(Renderer renderer) {
		renderer.setOffsets((int)x-Renderer.WIDTH/2, (int)y-Renderer.HEIGHT/2);
		renderer.renderPlayer((int)x, (int)y, this);
	}

	public void tick() {
		if (anim > 7000)
			anim = 0;
		anim++;
		xa = 0;
		ya = 0;
		if (Keyboard.up)
			ya = -1*speed;
		if (Keyboard.down)
			ya = 1*speed;
		if (Keyboard.left)
			xa = -1*speed;
		if (Keyboard.right)
			xa = 1*speed;
		
//		System.out.println(xa);
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
