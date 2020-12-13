package com.jasondavidpeters.thevillage2d.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	public static boolean left,down,up,right;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) up=true;
		if (e.getKeyCode() == KeyEvent.VK_S) down=true;
		if (e.getKeyCode() == KeyEvent.VK_A) left=true;
		if (e.getKeyCode() == KeyEvent.VK_D) right=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) up=false;
		if (e.getKeyCode() == KeyEvent.VK_S) down=false;
		if (e.getKeyCode() == KeyEvent.VK_A) left=false;
		if (e.getKeyCode() == KeyEvent.VK_D) right=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

}
