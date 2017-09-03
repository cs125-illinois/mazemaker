package edu.illinois.cs.cs125.lib.zen;

public class Line extends ZenShape {
    private Point end;
    private int thickness = 1;

    public Line(final int x1, final int y1, final int x2, final int y2) {
        this(x1, y1, x2, y2, 1, null);
    }

    public Line(final int x1, final int y1, final int x2, final int y2,
            final int thickness) {
        this(x1, y1, x2, y2, thickness, null);
    }

    public Line(final int x1, final int y1, final int x2, final int y2,
            final String color) {
        this(x1, y1, x2, y2, 1, color);
    }

    public Line(final int x1, final int y1, final int x2, final int y2,
            final int thickness, final String color) {
        this.setX(x1);
        this.setY(y1);
        this.end = new Point(x2, y2);
        this.thickness = thickness;
        this.setColor(color);
    }

    public final void draw() {
        if (thickness > 1) {
            double dy = end.rawY() - rawY();
            if (dy != 0) {
                double theta = angleTo(end);
                int xr = (int) (Math.sin(theta) * thickness / 2),
                        yr = (int) (Math.cos(theta) * thickness / 2);
                Zen.fillPolygon(
                        new int[]{this.getX() + xr, end.getX() + xr,
                                end.getX() - xr, this.getX() - xr},
                        new int[]{this.getY() - yr, end.getY() - yr,
                                end.getY() + yr, this.getY() + yr});
            } else {
                Zen.fillPolygon(new int[]{this.getX() + thickness / 2,
                        end.getX() + thickness / 2, end.getX() - thickness / 2,
                        this.getX() - thickness / 2},
                        new int[]{this.getY(), end.getY(), end.getY(),
                                this.getY()});
            }

        } else {
            Zen.drawLine(getX(), getY(), end.getX(), end.getY());
        }
    }

    public final void rotate(final double degrees) {
        double length = distanceTo(end);
        double theta = angleTo(end) + Math.toRadians(degrees);
        end.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    public final void rotateTo(final double degrees) {
        double length = distanceTo(end);
        double theta = Math.toRadians(degrees);
        end.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    public final double angle() {
        return angleTo(end);
    }

    public final void changeX(final int amount) {
        setX(getX() + amount);
        end.changeX(amount);
    }

    public final void changeY(final int amount) {
        setY(getY() + amount);
        end.changeY(amount);
    }

    public final Point end() {
        return this.end;
    }

    public final void setEnd(final int x2, final int y2) {
        this.end.set(x2, y2);
    }

    public final void setEndX(final int x2) {
        this.end.setX(x2);
    }

    public final int getEndX() {
        return this.end.getX();
    }

    public final void setEndY(final int y2) {
        this.end.setY(y2);
    }

    public final int getEndY() {
        return this.end.getY();
    }
}
