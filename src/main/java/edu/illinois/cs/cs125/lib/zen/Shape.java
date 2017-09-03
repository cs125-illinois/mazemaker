package edu.illinois.cs.cs125.lib.zen;

public class Shape extends ZenShape {
    private ZenShape[] components;

    public Shape(final ZenShape... components) {
        this(0, 0, components);
    }

    public Shape(final Point center, final ZenShape... components) {
        this(center.getX(), center.getY(), components);
    }

    public Shape(final int x, final int y, final ZenShape... components) {
        this.components = components;
        this.setX(x);
        this.setY(y);
    }

    public Shape(final double x, final double y, final ZenShape... components) {
        this.components = components;
        this.setX(x);
        this.setY(y);
    }

    public final void set(final int x, final int y) {
        this.change(x - this.getX(), y - this.getY());
        this.setX(x);
        this.setY(y);
    }

    public final void change(final int dx, final int dy) {
        for (ZenShape component : components)
            component.change(dx, dy);
    }

    public final void set(final double x, final double y) {
        this.change(x - this.getX(), y - this.getY());
        this.setX(x);
        this.setY(y);
    }

    public final void change(final double dx, final double dy) {
        for (ZenShape component : components)
            component.change(dx, dy);
    }

    @Override
    public final void draw() {
        for (ZenShape component : components)
            component.colorAndDraw();
    }
}
