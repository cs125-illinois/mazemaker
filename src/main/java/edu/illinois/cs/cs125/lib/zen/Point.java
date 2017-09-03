package edu.illinois.cs.cs125.lib.zen;

/**
 * A Point is a precise point in 2D space.
 *
 * It uses doubles to store precise values for the x and y coordinate, and casts
 * them to integers for use in the graphic systems integer plane. This allows
 * for more complex transformations like rotating and scaling to take place.
 */
public class Point {
    private double x, y; // The x, y coordinate of this point

    /**
     * Empty constructor, initializes to the point 0, 0.
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Integer value constructor.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Precise value constructor.
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the position to a new (x, y) coordinate.
     */
    public void set(final int x, final int y) {
        setX(x);
        setY(y);
    }

    /**
     * Set the position to a new (x, y) coordinate.
     */
    public void set(final double x, final double y) {
        setX(x);
        setY(y);
    }

    /**
     * Change the position in the x direction by dx, and the y direction by dy.
     */
    public void change(final int dx, final int dy) {
        changeX(dx);
        changeY(dy);
    }

    /**
     * Change the position in the x direction by dx, and the y direction by dy.
     */
    public void change(final double dx, final double dy) {
        changeX(dx);
        changeY(dy);
    }

    /**
     * Returns the integer part of the x position.
     */
    public int getX() {
        return (int) x;
    }

    /**
     * Returns the precise x position.
     */
    public double rawX() {
        return x;
    }

    /**
     * Sets the x position to the specified value.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Sets the x position to the specified value.
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Changes the x position by the given amount.
     */
    public void changeX(final int amount) {
        this.x += amount;
    }

    /**
     * Changes the x position by the given amount.
     */
    public void changeX(final double amount) {
        this.x += amount;
    }

    /**
     * Returns the integer part of the y value of this point.
     */
    public int getY() {
        return (int) y;
    }

    /**
     * Returns the precise y position of this point.
     */
    public double rawY() {
        return y;
    }

    /**
     * Sets the y position to the specified value.
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * Sets the y position to the specified value.
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Changes the y position by the given amount.
     */
    public void changeY(final int amount) {
        this.y += amount;
    }

    /**
     * Changes the y position by the given amount.
     */
    public void changeY(final double amount) {
        this.y += amount;
    }

    /**
     * Returns the distance between this point and another point.
     */
    public double distanceTo(final Point other) {
        return Math.sqrt(
                Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Returns the angle formed by the line from this point to another point.
     */
    public double angleTo(final Point other) {
        double dy = other.y - this.y;
        double dx = other.x - this.x;
        return ((dx != 0)
                ? Math.atan(dy / dx)
                        + ((dx < 0) ? Math.PI : ((dy < 0) ? 2 * Math.PI : 0))
                : ((dx > 0) ? Math.PI / 2 : -Math.PI / 2));
    }

    /**
     * Returns whether this point represents the same 2D coordinate as another
     * point.
     */
    public boolean equals(final Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns a String representation of this point.
     */
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
