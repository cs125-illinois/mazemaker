package edu.illinois.cs.cs125.lib.Zen;

public class Rectangle extends ZenShape {
	private int width;
	private int height;
	
	public Rectangle(int width, int height) {
		this(0, 0, width, height, null);
	}
	
	public Rectangle(int width, int height, String color) {
		this(0, 0, width, height, color);
	}
	
	public Rectangle(int x, int y, int width, int height) {
		this(x, y, width, height, null);
	}
	
	public Rectangle(int x, int y, int width, int height, String color) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setColor(color);
	}

	public void draw() {
		Zen.fillRect(getX(), getY(), width, height);
	}
	
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
