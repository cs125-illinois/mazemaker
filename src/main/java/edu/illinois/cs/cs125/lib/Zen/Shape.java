package edu.illinois.cs.cs125.lib.Zen;

public class Shape extends ZenShape {
	private ZenShape[] components;
	
	public Shape(ZenShape... components) {
		this(0, 0, components);
	}
	
	public Shape(Point center, ZenShape... components) {
		this(center.getX(), center.getY(), components);
	}
	
	public Shape(int x, int y, ZenShape... components) {
		this.components = components;
		this.setX(x);
		this.setY(y);
	}
	
	public Shape(double x, double y, ZenShape... components) {
		this.components = components;
		this.setX(x);
		this.setY(y);
	}
	
	public void set(int x, int y) {
		this.change(x - this.getX(), y - this.getY());
		this.setX(x);
		this.setY(y);
	}
	
	public void change(int dx, int dy) {
		for (ZenShape component : components)
			component.change(dx, dy);
	}
	
	public void set(double x, double y) {
		this.change(x - this.getX(), y - this.getY());
		this.setX(x);
		this.setY(y);
	}
	
	public void change(double dx, double dy) {
		for (ZenShape component : components)
			component.change(dx, dy);
	}
	
	@Override
	public void draw() {
		for (ZenShape component : components)
			component.colorAndDraw();
	}
}
