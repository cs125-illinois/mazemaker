package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen rectangle class.
 */
public class Rectangle extends ZenShape {

    /** My width and height. */
    private int myWidth, myHeight;

    /**
     * Draw a new black rectangle at (0, 0).
     *
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(final int width, final int height) {
        this(0, 0, width, height, null);
    }

    /**
     * Draw a new rectangle at (0, 0).
     *
     * @param width the rectangle's width
     * @param height the rectangle's height
     * @param color the color
     */
    public Rectangle(final int width, final int height, final String color) {
        this(0, 0, width, height, color);
    }

    /**
     * Draw a new black rectangle.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param width the rectangle's width
     * @param height the rectangle's height
     */
    public Rectangle(final int x, final int y, final int width, final int height) {
        this(x, y, width, height, null);
    }

    /**
     * Draw a new rectangle.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param width the rectangle's width
     * @param height the rectangle's height
     * @param color the color
     */
    public Rectangle(final int x, final int y, final int width, final int height,
            final String color) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.setColor(color);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        Zen.fillRect(getX(), getY(), myWidth, myHeight);
    }

    /**
     * Set the rectangle width and height.
     *
     * @param width the rectangle's new width
     * @param height the rectangle's new height
     */
    public final void setDimensions(final int width, final int height) {
        this.myWidth = width;
        this.myHeight = height;
    }

    /**
     * Get the rectangle's height.
     *
     * @return the rectangle's height
     */
    public final int getHeight() {
        return myHeight;
    }

    /**
     * Set the rectangle's height.
     *
     * @param height the rectangle's new height
     */
    public final void setHeight(final int height) {
        this.myHeight = height;
    }

    /**
     * Gets the rectangle's width.
     *
     * @return the rectangle's width
     */
    public final int getWidth() {
        return myWidth;
    }

    /**
     * Set the rectangle's width.
     *
     * @param width the rectangle's new width
     */
    public final void setWidth(final int width) {
        this.myWidth = width;
    }
}
