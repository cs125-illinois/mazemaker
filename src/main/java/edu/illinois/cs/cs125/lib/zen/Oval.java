package edu.illinois.cs.cs125.lib.zen;

public class Oval extends ZenShape {
    private int width, height;

    public Oval(final int width, final int height) {
        this(0, 0, width, height, null);
    }

    public Oval(final int width, final int height, final String color) {
        this(0, 0, width, height, color);
    }

    public Oval(final int x, final int y, final int width, final int height) {
        this(x, y, width, height, null);
    }

    public Oval(final int x, final int y, final int width, final int height, final String color) {
        setX(x);
        setY(y);
        setColor(color);
        this.setWidth(width);
        this.setHeight(height);
    }

    public final void draw() {
        Zen.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2,
                getWidth(), getHeight());
    }

    public final void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public final int getWidth() {
        return width;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }

    public final int getHeight() {
        return height;
    }

    public final void setHeight(final int height) {
        this.height = height;
    }
}
