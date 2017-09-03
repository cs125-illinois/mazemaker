package edu.illinois.cs.cs125.lib.zen;

public class Rectangle extends ZenShape {
    private int width;
    private int height;

    public Rectangle(final int width, final int height) {
        this(0, 0, width, height, null);
    }

    public Rectangle(final int width, final int height, final String color) {
        this(0, 0, width, height, color);
    }

    public Rectangle(final int x, final int y, final int width,
            final int height) {
        this(x, y, width, height, null);
    }

    public Rectangle(final int x, final int y, final int width,
            final int height, final String color) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setColor(color);
    }

    public final void draw() {
        Zen.fillRect(getX(), getY(), width, height);
    }

    public final void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public final int getHeight() {
        return height;
    }

    public final void setHeight(final int height) {
        this.height = height;
    }

    public final int getWidth() {
        return width;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }
}
