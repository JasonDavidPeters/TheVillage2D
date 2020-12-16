package com.jasondavidpeters.thevillage2d.screen;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.jasondavidpeters.thevillage2d.Game;
import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.ui.Font;
import com.jasondavidpeters.thevillage2d.world.Level;
import com.jasondavidpeters.thevillage2d.world.entities.npc.Player;
import com.jasondavidpeters.thevillage2d.world.gameobjects.GameObject;
import com.jasondavidpeters.thevillage2d.world.tiles.Tile;

public class Renderer extends Canvas {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 140;
	public static final int SCALE = 3;

	private int alpha = 0xffff00ff;

	private int xOffset;
	private int yOffset;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private JFrame frame;

	public Renderer() {
		frame = new JFrame(Game.GAME_TITLE);
		frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		requestFocus();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
//		g.setFont(new Font("Arial", 120, 0));
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		clear();
		bs.show();
		g.dispose();
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSprite(int xa, int ya, Sprite sprite, boolean fixed) {
		if (fixed) {
			xa -= xOffset;
			ya -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yy = ya + y;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xx = xa + x;
				if (xx < 0 || xx >= WIDTH || yy < 0 || yy >= HEIGHT)
					break;
				int col = sprite.getPixels()[(x) + (y) * sprite.getWidth()];
				if (col != alpha) {
					pixels[xx + yy * WIDTH] = col;
				}
			}
		}
	}
	
	public void renderText(int xp, int yp, Sprite sprite, int colour, boolean fixed) {
		if (!fixed) {
			xp-=xOffset;
			yp-=yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= WIDTH || ya < 0 || ya >= HEIGHT) break;
				int col = sprite.getPixels()[x + y * sprite.getWidth()];
				if (col != alpha) {
					sprite.setPixel((x) + (y) * sprite.getWidth(), colour);
					pixels[xa+ya*WIDTH] = col;
				}
			}
		}
	}
	
	public void drawString(String text, int xp, int yp, int colour, boolean fixed) {
		final int yPosition = yp;
		final int xPosition = xp;
		Sprite sprite;
		for (int s = 0 ; s < text.length(); s++) {
			yp=yPosition;
			xp=xPosition;
			char c = text.charAt(s);
			int spacing = s * 6;
			sprite = Font.GAME_FONT[Font.CHARACTERS.indexOf(c)];
			if (c == 'g' || c == 'p') { // adjusting certain characters
				yp += 3;
			}
			if (c == 'g') {
				xp+=-1;
			}
			renderText(xp+spacing,yp, sprite, colour, fixed);
		}
	}

	public void setOffsets(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderTile(int xa, int ya, Tile tile) {
		xa -= xOffset;
		ya -= yOffset;
		for (int y = 0; y < tile.getSprite().getHeight(); y++) {
			int yy = ya + y;
			for (int x = 0; x < tile.getSprite().getWidth(); x++) {
				int xx = x + xa;
				if (xx < 0 || xx >= WIDTH || yy < 0 || yy >= HEIGHT)
					continue;
				pixels[xx + yy * WIDTH] = tile.getSprite().getPixels()[x + y * tile.getSprite().getWidth()];
			}
		}
	}

	public void renderLevel(Level level) {
		for (int y = 0; y < level.getHeight(); y++) {
			int yy = y + yOffset;
			for (int x = 0; x < level.getWidth(); x++) {
				int xx = x + xOffset;
				if (xx < 0 || xx >= WIDTH || yy < 0 || yy >= HEIGHT)
					continue;
				if (x < 0)
					x = 0;
				pixels[xx + yy * WIDTH] = level.getPixels()[(x / 16) + (y / 16) * level.getWidth()];
			}
		}

	}

	public void renderPlayer(int xa, int ya, Player player) {
		xa -= xOffset;
		ya -= yOffset;
		for (int y = 0; y < player.getSprite().getHeight(); y++) {
			int yy = y + ya;
			int ny = (-y + player.getSprite().getHeight()) - 1;
			for (int x = 0; x < player.getSprite().getWidth(); x++) {
				int xx = x + xa;
				int nx = (-x + player.getSprite().getWidth()) - 1;
				if (xx < 0 || xx >= WIDTH || yy < 0 || yy >= HEIGHT)
					break;
				int col = player.getSprite().getPixels()[(player.getSprite().getFlip() != 1 ? x : nx)
						+ (player.getSprite().getFlip() != 2 ? y : ny) * player.getSprite().getWidth()];
				if (col != alpha)
					pixels[xx + yy * WIDTH] = col;
			}
		}

	}

	public void renderComponent(int xp, int yp, int width, int height, Sprite sprite) {
		for (int y = 0; y < sprite.getHeight(); y++) {
			if (y > height)
				continue;
			int yy = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				if (x > width)
					continue;
				int xx = x + xp;
				if (xx < 0 || xx >= WIDTH || yy < 0 || yy >= HEIGHT)
					break;
				int col = sprite.getPixels()[x + y * sprite.getWidth()];
				if (col != alpha)
					pixels[xx + yy * WIDTH] = col;
			}
		}
	}

	public void renderComponent(int xp, int yp, int width, int height, int col, boolean filled) {
		if (xp + width >= WIDTH)
			width = WIDTH;
		if (yp + height >= HEIGHT)
			height = HEIGHT;
		if (!filled) {
			for (int y = yp; y < yp + height; y++) {
				if (y < 0 || y >= HEIGHT)
					break;
				pixels[(xp + y * WIDTH)] = col;
				pixels[((xp + width) + y * WIDTH)] = col;
			}
			for (int x = xp; x <= xp + width; x++) {
				if (x < 0 || x >= WIDTH)
					break;
				pixels[(x + yp * WIDTH)] = col;
				pixels[((x + (yp + height) * WIDTH))] = col;
			}
		}
		if (filled) {
//			System.out.println(xp + " " + yp);
			for (int y = yp; y < yp + height; y++) {
				for (int x = xp; x < xp + width; x++) {
					if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
						break;
					pixels[x + y * WIDTH] = col;
				}
			}
		}
	}

	public void renderGameObject(int xp, int yp, GameObject gameObject) {
		yp -= yOffset;
		xp -= xOffset;
		gameObject.setScreenPosition(xp,yp);
		for (int y = 0; y < gameObject.getSprite().getHeight(); y++) {
			int yy = y + yp;
			for (int x = 0; x < gameObject.getSprite().getWidth(); x++) {
				int xx = x + xp;
				if (xx < 0 || xx > WIDTH || yy < 0 || yy >= HEIGHT)
					break;
				int col = gameObject.getSprite().getPixels()[x + y * gameObject.getSprite().getWidth()];
				if (col != alpha)
					pixels[xx + yy * WIDTH] = col;
			}
		}

	}

	public void drawRectangle(int xp, int yp, int width, int height) {
		xp -= xOffset;
		yp -= yOffset;
		int col = 0xFF0000;
		for (int y = yp; y < yp + height; y++) {
			if (xp < 0 || xp > WIDTH || y < 0 || y > WIDTH || xp + y < 0 || xp + y >= WIDTH)
				break;
			pixels[(xp + y * WIDTH)] = col;
			if ((((xp + width) + y) < 0) || ((xp + width) + y >= WIDTH))
				break;
			pixels[((xp + width) + y * WIDTH)] = col;
		}
		for (int x = xp; x <= xp + width; x++) {
			if (x + yp < 0 || x + yp >= WIDTH || x < 0 || yp < 0 || x >= WIDTH || yp >= WIDTH)
				break;
			pixels[(x + yp * WIDTH)] = col;
			if (x + (yp + height) < 0 || (x + (yp + height) >= WIDTH))
				break;
			pixels[((x + (yp + height) * WIDTH))] = col;
		}

	}

	public int getPixelWidth() {
		return WIDTH * SCALE;
	}

	public int getPixelHeight() {
		return HEIGHT * SCALE;
	}

	public int getYoffset() {
		return yOffset;
	}

	public int getXoffset() {
		return xOffset;
	}

	public JFrame getFrame() {
		return frame;
	}

}
