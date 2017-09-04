package edu.illinois.cs.cs125.lib.zen;

/**
 * The Class Line.
 */
public class Line extends ZenShape {

    /** The end. */
    private Point end;

    /** The thickness. */
    private int myThickness = 1;

    /**
     * Instantiates a new line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(final int x1, final int y1, final int x2, final int y2) {
        this(x1, y1, x2, y2, 1, null);
    }

    /**
     * Instantiates a new line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @param thickness the thickness
     */
    public Line(final int x1, final int y1, final int x2, final int y2,
            final int thickness) {
        this(x1, y1, x2, y2, thickness, null);
    }

    /**
     * Instantiates a new line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @param color the color
     */
    public Line(final int x1, final int y1, final int x2, final int y2,
            final String color) {
        this(x1, y1, x2, y2, 1, color);
    }

    /**
     * Instantiates a new line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @param thickness the thickness
     * @param color the color
     */
    public Line(final int x1, final int y1, final int x2, final int y2,
            final int thickness, final String color) {
        this.setX(x1);
        this.setY(y1);
        this.end = new Point(x2, y2);
        this.myThickness = thickness;
        this.setColor(color);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        if (myThickness > 1) {
            double dy = end.rawY() - rawY();
            if (dy != 0) {
                double theta = angleTo(end);
                int xr = (int) (Math.sin(theta) * myThickness / 2),
                        yr = (int) (Math.cos(theta) * myThickness / 2);
                Zen.fillPolygon(
                        new int[]{this.getX() + xr, end.getX() + xr,
                                end.getX() - xr, this.getX() - xr},
                        new int[]{this.getY() - yr, end.getY() - yr,
                                end.getY() + yr, this.getY() + yr});
            } else {
                Zen.fillPolygon(new int[]{this.getX() + myThickness / 2,
                        end.getX() + myThickness / 2, end.getX() - myThickness / 2,
                        this.getX() - myThickness / 2},
                        new int[]{this.getY(), end.getY(), end.getY(),
                                this.getY()});
            }

        } else {
            Zen.drawLine(getX(), getY(), end.getX(), end.getY());
        }
    }

    /**
     * Rotate.
     *
     * @param degrees the degrees
     */
    public final void rotate(final double degrees) {
        double length = distanceTo(end);
        double theta = angleTo(end) + Math.toRadians(degrees);
        end.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    /**
     * Rotate to.
     *
     * @param degrees the degrees
     */
    public final void rotateTo(final double degrees) {
        double length = distanceTo(end);
        double theta = Math.toRadians(degrees);
        end.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    /**
     * Angle.
     *
     * @return the double
     */
    public final double angle() {
        return angleTo(end);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeX(int)
     */
    @Override
    public final void changeX(final int amount) {
        setX(getX() + amount);
        end.changeX(amount);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeY(int)
     */
    @Override
    public final void changeY(final int amount) {
        setY(getY() + amount);
        end.changeY(amount);
    }

    /**
     * End.
     *
     * @return the point
     */
    public final Point end() {
        return this.end;
    }

    /**
     * Sets the end.
     *
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public final void setEnd(final int x2, final int y2) {
        this.end.set(x2, y2);
    }

    /**
     * Sets the end X.
     *
     * @param x2 the new end X
     */
    public final void setEndX(final int x2) {
        this.end.setX(x2);
    }

    /**
     * Gets the end X.
     *
     * @return the end X
     */
    public final int getEndX() {
        return this.end.getX();
    }

    /**
     * Sets the end Y.
     *
     * @param y2 the new end Y
     */
    public final void setEndY(final int y2) {
        this.end.setY(y2);
    }

    /**
     * Gets the end Y.
     *
     * @return the end Y
     */
    public final int getEndY() {
        return this.end.getY();
    }
}
