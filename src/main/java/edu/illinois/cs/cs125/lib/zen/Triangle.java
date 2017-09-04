package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen triangle class.
 */
public class Triangle extends Polygon {

    /**
     * Draw a new triangle from three points using the current color.
     *
     * @param p1 the first corner point
     * @param p2 the second corner point
     * @param p3 the third corner point
     */
    public Triangle(final Point p1, final Point p2, final Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Draw a new triangle from three points.
     *
     * @param p1 the first corner point
     * @param p2 the second corner point
     * @param p3 the third corner point
     * @param color the color
     */
    public Triangle(final Point p1, final Point p2, final Point p3, final String color) {
        super(color, p1, p2, p3);
    }

    /**
     * Draw a new triangle from six coordinates using the current color.
     *
     * @param x1 the first corner X coordinate
     * @param y1 the first corner Y coordinate
     * @param x2 the second corner X coordinate
     * @param y2 the second corner Y coordinate
     * @param x3 the third corner X coordinate
     * @param y3 the third corner Y coordinate
     */
    public Triangle(final int x1, final int y1, final int x2, final int y2, final int x3,
            final int y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), null);
    }

    /**
     * Draw a new triangle from six coordinates using the current color.
     *
     * @param x1 the first corner X coordinate
     * @param y1 the first corner Y coordinate
     * @param x2 the second corner X coordinate
     * @param y2 the second corner Y coordinate
     * @param x3 the third corner X coordinate
     * @param y3 the third corner Y coordinate
     * @param color the color
     */
    public Triangle(final int x1, final int y1, final int x2, final int y2, final int x3,
            final int y3, final String color) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), color);
    }
}
