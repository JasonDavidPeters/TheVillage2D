package com.jasondavidpeters.thevillage2d.assets;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Font {
	
	/*
	 * Load in a font using a sprite array
	 * each index in the array will correspond to a character index from a string
	 */
	public static String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static Spritesheet GAMEFONT = new Spritesheet("/spritesheets/fonts/gamer.png");
	public static Sprite[] GAME_FONT  = Sprite.toArray(GAMEFONT, 11, 11);
	// Sprite[] array will hold each sprite of each font character
	
	public void render(Renderer r) { 
//		r.renderSprite(100, 50, GAME_FONT[0], false);
//		for(int i = 0; i < GAME_FONT[0].getPixels().length; i++) System.out.println(GAME_FONT[0].getPixels()[i]);
	}
}
