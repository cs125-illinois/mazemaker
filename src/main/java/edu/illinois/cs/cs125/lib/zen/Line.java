package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen line class.
 */
public class Line extends ZenShape {

    /** The line end. */
    private Point myEnd;

    /** The line thickness. */
    private int myThickness = 1;

    /**
     * Draw a new black line with thickness 1.
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
     * Draw a new black line.
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
     * Draw a new line with thickness 1.
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
     * Draw a new line.
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
        this.myEnd = new Point(x2, y2);
        this.myThickness = thickness;
        this.setColor(color);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        if (myThickness > 1) {
            double dy = myEnd.rawY() - rawY();
            if (dy != 0) {
                double theta = angleTo(myEnd);
                int xr = (int) (Math.sin(theta) * myThickness / 2),
                        yr = (int) (Math.cos(theta) * myThickness / 2);
                Zen.fillPolygon(
                        new int[]{this.getX() + xr, myEnd.getX() + xr,
                                myEnd.getX() - xr, this.getX() - xr},
                        new int[]{this.getY() - yr, myEnd.getY() - yr,
                                myEnd.getY() + yr, this.getY() + yr});
            } else {
                Zen.fillPolygon(new int[]{this.getX() + myThickness / 2,
                        myEnd.getX() + myThickness / 2, myEnd.getX() - myThickness / 2,
                        this.getX() - myThickness / 2},
                        new int[]{this.getY(), myEnd.getY(), myEnd.getY(),
                                this.getY()});
            }

        } else {
            Zen.drawLine(getX(), getY(), myEnd.getX(), myEnd.getY());
        }
    }

    /**
     * Rotate the line.
     *
     * @param degrees the degrees to rotate
     */
    public final void rotate(final double degrees) {
        double length = distanceTo(myEnd);
        double theta = angleTo(myEnd) + Math.toRadians(degrees);
        myEnd.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    /**
     * Rotate the line to a certain degrees.
     *
     * @param degrees the degrees to rotate to
     */
    public final void rotateTo(final double degrees) {
        double length = distanceTo(myEnd);
        double theta = Math.toRadians(degrees);
        myEnd.set(Math.cos(theta) * length + rawX(),
                Math.sin(theta) * length + rawY());
    }

    /**
     * Get the line angle.
     *
     * @return the double
     */
    public final double angle() {
        return angleTo(myEnd);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeX(int)
     */
    @Override
    public final void changeX(final int amount) {
        setX(getX() + amount);
        myEnd.changeX(amount);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeY(int)
     */
    @Override
    public final void changeY(final int amount) {
        setY(getY() + amount);
        myEnd.changeY(amount);
    }

    /**
     * Get the line end point.
     *
     * @return the end point
     */
    public final Point end() {
        return this.myEnd;
    }

    /**
     * Sets the line end point.
     *
     * @param x2 the x coordinate of the end
     * @param y2 the y coordinate of the end
     */
    public final void setEnd(final int x2, final int y2) {
        this.myEnd.set(x2, y2);
    }

    /**
     * Sets the end X coordinate.
     *
     * @param x2 the new end Y coordinate
     */
    public final void setEndX(final int x2) {
        this.myEnd.setX(x2);
    }

    /**
     * Gets the end X coordinate.
     *
     * @return the end X coordinate
     */
    public final int getEndX() {
        return this.myEnd.getX();
    }

    /**
     * Sets the end Y coordinate.
     *
     * @param y2 the new end Y coordinate
     */
    public final void setEndY(final int y2) {
        this.myEnd.setY(y2);
    }

    /**
     * Gets the end Y coordinate.
     *
     * @return the end Y coordinate
     */
    public final int getEndY() {
        return this.myEnd.getY();
    }
}
