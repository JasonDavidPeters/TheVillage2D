package com.jasondavidpeters.thevillage2d.util;

import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Debug {
	
	public static Renderer r;
	
	public static void drawRect(int x, int y, int w, int h) {
		r.drawRectangle(x, y, w, h);
	}

}
