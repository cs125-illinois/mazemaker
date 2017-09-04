package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen oval class.
 */
public class Oval extends ZenShape {

    /** My width and height. */
    private int myWidth, myHeight;

    /**
     * Draw a new black oval at location (0, 0).
     *
     * @param width the width
     * @param height the height
     */
    public Oval(final int width, final int height) {
        this(0, 0, width, height, null);
    }

    /**
     * Draw a new oval at location (0, 0).
     *
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public Oval(final int width, final int height, final String color) {
        this(0, 0, width, height, color);
    }

    /**
     * Draw a new black oval.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     */
    public Oval(final int x, final int y, final int width, final int height) {
        this(x, y, width, height, null);
    }

    /**
     * Draw a new oval.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public Oval(final int x, final int y, final int width, final int height, final String color) {
        setX(x);
        setY(y);
        setColor(color);
        this.setWidth(width);
        this.setHeight(height);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        Zen.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }

    /**
     * Set the oval width and height.
     *
     * @param width the width
     * @param height the height
     */
    public final void setDimensions(final int width, final int height) {
        this.myWidth = width;
        this.myHeight = height;
    }

    /**
     * Get the oval width.
     *
     * @return the width
     */
    public final int getWidth() {
        return myWidth;
    }

    /**
     * Sets the oval width.
     *
     * @param width the new width
     */
    public final void setWidth(final int width) {
        this.myWidth = width;
    }

    /**
     * Get the oval height.
     *
     * @return the height
     */
    public final int getHeight() {
        return myHeight;
    }

    /**
     * Set the oval height.
     *
     * @param height the new height
     */
    public final void setHeight(final int height) {
        this.myHeight = height;
    }
}
