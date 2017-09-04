package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen point class.
 *
 * A point is a precise location in 2D space. It uses doubles to store precise values for the x and
 * y coordinate, and casts them to integers for use in the graphic systems integer plane. This
 * allows for more complex transformations like rotating and scaling to take place.
 */
public class Point {

    /** My X and Y coordinates. */
    private double myX, myY;

    /**
     * Create a point at (0, 0).
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Create a point at integer coordinates (x, y).
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public Point(final int x, final int y) {
        this.myX = x;
        this.myY = y;
    }

    /**
     * Create a point at floating point coordinates (x, y).
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public Point(final double x, final double y) {
        this.myX = x;
        this.myY = y;
    }

    /**
     * Set new integer (x, y) coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public void set(final int x, final int y) {
        setX(x);
        setY(y);
    }

    /**
     * Set new floating point (x, y) coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public void set(final double x, final double y) {
        setX(x);
        setY(y);
    }

    /**
     * Move the point by integers dx and dy.
     *
     * @param dx the shift in the X coordinate
     * @param dy the shift in the Y coordinate
     */
    public void change(final int dx, final int dy) {
        changeX(dx);
        changeY(dy);
    }

    /**
     * Move the point by floating points dx and dy.
     *
     * @param dx the shift in the X coordinate
     * @param dy the shift in the Y coordinate
     */
    public void change(final double dx, final double dy) {
        changeX(dx);
        changeY(dy);
    }

    /**
     * Returns the X coordinate as an integer.
     *
     * @return the X coordinate
     */
    public int getX() {
        return (int) myX;
    }

    /**
     * Returns the X coordinate as a float.
     *
     * @return the X coordinate
     */
    public double rawX() {
        return myX;
    }

    /**
     * Sets the X coordinate as an integer.
     *
     * @param x the new X coordinate
     */
    public void setX(final int x) {
        this.myX = x;
    }

    /**
     * Sets the X coordinate as a float.
     *
     * @param x the new X coordinate
     */
    public void setX(final double x) {
        this.myX = x;
    }

    /**
     * Move the X coordinate by integer dx.
     *
     * @param dx the shift in the X coordinate
     */
    public void changeX(final int dx) {
        this.myX += dx;
    }

    /**
     * Move the X coordinate by float dx.
     *
     * @param dx the shift in the X coordinate
     */
    public void changeX(final double dx) {
        this.myX += dx;
    }

    /**
     * Returns the Y coordinate as an integer.
     *
     * @return the Y coordinate
     */
    public int getY() {
        return (int) myY;
    }

    /**
     * Returns the Y coordinate is a float.
     *
     * @return the Y coordinate
     */
    public double rawY() {
        return myY;
    }

    /**
     * Sets the Y coordinate as an integer.
     *
     * @param y the new Y coordinate
     */
    public void setY(final int y) {
        this.myY = y;
    }

    /**
     * Sets the Y coordinate as a float.
     *
     * @param y the new Y coordinate
     */
    public void setY(final double y) {
        this.myY = y;
    }

    /**
     * Move the Y coordinate by integer dy.
     *
     * @param dy the shift in the Y coordinate
     */
    public void changeY(final int dy) {
        this.myY += dy;
    }

    /**
     * Move the Y coordinate by float dy.
     *
     * @param dy the shift in the Y coordinate
     */
    public void changeY(final double dy) {
        this.myY += dy;
    }

    /**
     * Return the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distanceTo(final Point other) {
        return Math.sqrt(Math.pow(this.myX - other.myX, 2) + Math.pow(this.myY - other.myY, 2));
    }

    /**
     * Return the angle formed by the line from this point to another point.
     *
     * @param other the other point
     * @return the angle formed by the line between the two points
     */
    @SuppressWarnings("checkstyle:avoidinlineconditionals")
    public double angleTo(final Point other) {
        double dy = other.myY - this.myY;
        double dx = other.myX - this.myX;
        return ((dx != 0)
                ? Math.atan(dy / dx) + ((dx < 0) ? Math.PI : ((dy < 0) ? 2 * Math.PI : 0))
                : ((dx > 0) ? Math.PI / 2 : -Math.PI / 2));
    }

    /**
     * Returns whether this point is equal to another point.
     *
     * @param other the other point
     * @return boolean whether the two points are the same
     */
    public boolean equals(final Point other) {
        return this.myX == other.myX && this.myY == other.myY;
    }

    /**
     * Returns a string representation of this point as "(X, Y)".
     *
     * @return the string representation of this point
     */
    public String toString() {
        return "(" + this.myX + ", " + this.myY + ")";
    }
}
