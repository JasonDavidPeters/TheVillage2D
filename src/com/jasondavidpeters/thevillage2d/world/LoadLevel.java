package com.jasondavidpeters.thevillage2d.world;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;
import com.jasondavidpeters.thevillage2d.world.tiles.Tile;

public class LoadLevel extends Level {
	
	public LoadLevel(String levelFile) {
		super(levelFile);
		load();
	}
	public LoadLevel(int width, int height) {
		super(width, height);
	}
	private void load() { 
		try {
			BufferedImage level = ImageIO.read(LoadLevel.class.getResource(levelFile));
			width=level.getWidth();
			height=level.getHeight();
			pixels = level.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(Tile.WATER);
		add(GameObject.GAMEOBJECT_STONE);
	}

}
