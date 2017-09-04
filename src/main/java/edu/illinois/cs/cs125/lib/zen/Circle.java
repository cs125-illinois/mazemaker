package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen circle class.
 */
public class Circle extends ZenShape {

    /** The circle's diameter. */
    private int myDiameter;

    /**
     * Draw a new black circle at location (0, 0) with diameter 1.
     */
    public Circle() {
        this(0, 0, 1, null);
    }

    /**
     * Draw a new black circle with diameter 1.
     *
     * @param x the x location of the circle
     * @param y the y location of the circle
     */
    public Circle(final int x, final int y) {
        this(x, y, 1, null);
    }

    /**
     * Draw a new black circle at location (0, 0).
     *
     * @param diameter the diameter of the circle
     */
    public Circle(final int diameter) {
        this(0, 0, diameter, null);
    }

    /**
     * Draw a new circle at location (0, 0).
     *
     * @param diameter the diameter of the circle
     * @param color the color of the circle
     */
    public Circle(final int diameter, final String color) {
        this(0, 0, diameter, color);
    }

    /**
     * Draw a new circle with diameter 1.
     *
     * @param x the x location of the circle
     * @param y the y location of the circle
     * @param color the color of the circle
     */
    public Circle(final int x, final int y, final String color) {
        this(x, y, 1, color);
    }

    /**
     * Draw a new black circle.
     *
     * @param x the x location of the circle
     * @param y the y location of the circle
     * @param diameter the diameter of the circle
     */
    public Circle(final int x, final int y, final int diameter) {
        this(x, y, diameter, null);
    }

    /**
     * Draw a new circle.
     *
     * @param x the x
     * @param y the y
     * @param diameter the diameter
     * @param color the color
     */
    public Circle(final int x, final int y, final int diameter, final String color) {
        setX(x);
        setY(y);
        setColor(color);
        this.setDiameter(diameter);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        Zen.fillOval(getX() - getDiameter() / 2, getY() - getDiameter() / 2, getDiameter(),
                getDiameter());
    }

    /**
     * Gets the diameter.
     *
     * @return the diameter
     */
    public final int getDiameter() {
        return myDiameter;
    }

    /**
     * Sets the diameter.
     *
     * @param diameter the new diameter
     */
    public final void setDiameter(final int diameter) {
        this.myDiameter = diameter;
    }

    /**
     * Sets the diameter.
     *
     * @param diameter the new diameter
     */
    public final void setDiameter(final double diameter) {
        this.myDiameter = (int) diameter;
    }
}
