package edu.illinois.cs.cs125.lib.zen;

public class Polygon extends ZenShape {
    private int[] x, y;

    public Polygon(final Point... points) {
        this(0, 0, null, points);
    }

    public Polygon(final String color, final Point... points) {
        this(0, 0, color, points);
    }

    public Polygon(final int xpos, final int ypos, final Point... points) {
        this(xpos, ypos, null, points);
    }

    public Polygon(final int xpos, final int ypos, final String color,
            final Point... points) {
        this.setColor(color);
        x = new int[points.length];
        y = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            x[i] = points[i].getX();
            y[i] = points[i].getY();
        }
        setX(xpos);
        setY(ypos);
    }

    @Override
    public final void draw() {
        if (x != null && x.length > 0) {
            Zen.fillPolygon(x, y);
        }
    }

    @Override
    public final void set(final int x, final int y) {
        change(x - this.getX(), y - this.getY());
    }

    @Override
    public final void set(final double x, final double y) {
        change(x - this.getX(), y - this.getY());
    }

    public final void setX(final int x) {
        changeX(x - this.getX());
    }

    public final void setX(final double x) {
        changeX(x - this.getX());
    }

    public final void setY(final int y) {
        changeY(y - this.getY());
    }

    public final void setY(final double y) {
        changeY(y - this.getY());
    }

    @Override
    public final void change(final int dx, final int dy) {
        for (int i = 0; i < x.length; i++) {
            x[i] += dx;
            y[i] += dy;
        }
    }

    @Override
    public final void change(final double dx, final double dy) {
        for (int i = 0; i < x.length; i++) {
            x[i] += dx;
            y[i] += dy;
        }
    }

    @Override
    public final void changeX(final int amount) {
        for (int i = 0; i < x.length; i++) {
            x[i] += amount;
        }
    }

    @Override
    public final void changeY(final int amount) {
        for (int i = 0; i < y.length; i++) {
            y[i] += amount;
        }
    }

    @Override
    public final void changeX(final double amount) {
        for (int i = 0; i < x.length; i++) {
            x[i] += amount;
        }
    }

    @Override
    public final void changeY(final double amount) {
        for (int i = 0; i < y.length; i++) {
            y[i] += amount;
        }
    }

    public final Point getPoint(final int index) {
        if (index >= 0 && index < x.length) {
            return new Point(x[index], y[index]);
        }
        return null;
    }

}
