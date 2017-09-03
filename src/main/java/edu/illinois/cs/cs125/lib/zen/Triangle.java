package edu.illinois.cs.cs125.lib.zen;

public class Triangle extends Polygon {

    public Triangle(final Point p1, final Point p2, final Point p3) {
        super(p1, p2, p3);
    }

    public Triangle(final Point p1, final Point p2, final Point p3,
            final String color) {
        super(color, p1, p2, p3);
    }

    public Triangle(final int x1, final int y1, final int x2, final int y2,
            final int x3, final int y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), null);
    }

    public Triangle(final int x1, final int y1, final int x2, final int y2,
            final int x3, final int y3, final String color) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), color);
    }
}
