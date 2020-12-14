package com.jasondavidpeters.thevillage2d.screen.ui;

import com.jasondavidpeters.thevillage2d.assets.Sprite;
import com.jasondavidpeters.thevillage2d.screen.Renderer;

public class Component {
	
	protected int x, y, width, height;
	protected int color;
	protected Component parent;
	protected boolean isClicked;
	protected boolean disabled;
	protected Sprite sprite;
	protected boolean toRender;
	protected String componentName;
	
	public Component(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public Component(int x, int y, int width, int height, int color) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.color=color;
	}
	
	public void render(Renderer renderer) {
		
	}
	public void tick() { 
		
	}
	public void setParent(Component parent) {
		this.parent=parent;
	}
	public Component getParent() {
		return parent;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isClicked() { 
		return isClicked;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled=disabled;
	}
	public boolean getToRender() {
		return toRender;
	}
	public void setToRender(boolean toRender) {
		this.toRender = toRender;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String name) {
		this.componentName = name;
	}
}
