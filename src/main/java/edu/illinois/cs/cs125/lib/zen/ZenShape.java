package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen shape class.
 */
public abstract class ZenShape extends Point {

    /** My color. */
    private String myColor;

    /**
     * Create a new shape at (0, 0).
     */
    public ZenShape() {
        super(0, 0);
    }

    /**
     * Create a new shape at integer coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public ZenShape(final int x, final int y) {
        super(x, y);
    }

    /**
     * Create a new shape at floating point coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public ZenShape(final double x, final double y) {
        super(x, y);
    }

    /**
     * Draw.
     */
    public abstract void draw();

    /**
     * Color and draw.
     */
    public final void colorAndDraw() {
        if (myColor != null) {
            Zen.setColor(myColor);
        }
        this.draw();
    }

    /**
     * Set the shape color.
     *
     * @param color the new color
     */
    public final void setColor(final String color) {
        this.myColor = color;
    }

    /**
     * Get the shape color.
     *
     * @return the color
     */
    public final String getColor() {
        return myColor;
    }
}
