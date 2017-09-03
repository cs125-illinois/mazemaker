package edu.illinois.cs.cs125.lib.Zen;

public abstract class ZenShape extends Point {
	private String color;
	
	public ZenShape() {
		super(0, 0);
	}
	
	public ZenShape(int x, int y) {
		super(x, y);
	}
	
	public ZenShape(double x, double y) {
		super(x, y);
	}
	
	public abstract void draw();
	
	public void colorAndDraw() {
		if (color != null)
			Zen.setColor(color);
		this.draw();
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
}
