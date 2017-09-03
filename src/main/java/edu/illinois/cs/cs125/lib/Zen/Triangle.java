package edu.illinois.cs.cs125.lib.zen;

public class Triangle extends Polygon {

    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    public Triangle(Point p1, Point p2, Point p3, String color) {
        super(color, p1, p2, p3);
    }

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), null);
    }

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, String color) {
        this(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3), color);
    }
}
