package edu.illinois.cs.cs125.lib.Zen;

public class Circle extends ZenShape {
	private int diameter;
	
	public Circle() {
		this(0, 0, 1, null);
	}
	
	public Circle(int x, int y) {
		this(x, y, 1, null);
	}
	
	public Circle(int diameter) {
		this(0, 0, diameter, null);
	}
	
	public Circle(int diameter, String color) {
		this(0, 0, diameter, color);
	}
	
	public Circle(int x, int y, String color) {
		this(x, y, 1, color);
	}
	
	public Circle(int x, int y, int diameter) {
		this(x, y, diameter, null);
	}
	
	public Circle(int x, int y, int diameter, String color) {
		setX(x);
		setY(y);
		setColor(color);
		this.setDiameter(diameter);
	}

	public void draw() {
		Zen.fillOval(getX() - getDiameter() / 2, getY() - getDiameter() / 2, getDiameter(), getDiameter());
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	public void setDiameter(double diameter) {
		this.diameter = (int) diameter;
	}
}
