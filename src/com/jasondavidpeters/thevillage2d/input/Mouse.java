package com.jasondavidpeters.thevillage2d.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Mouse implements MouseListener {

	private int mouseX = -1, mouseY = -1;
	private int mouseB = -1;

	private boolean pressed;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!pressed) {
			mouseX = e.getX();
			mouseY = e.getY();
			mouseB = e.getButton();
			pressed=true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressed) {
			mouseB = -1;
			pressed=false;
		}
	}
	
	public void reset() {
		mouseB=-1;
	}

	public int getMouseX() {
		return mouseX / Renderer.SCALE;
	}

	public int getMouseY() {
		return mouseY / Renderer.SCALE;
	}

	public int getMouseB() {
		return mouseB;
	}

}
