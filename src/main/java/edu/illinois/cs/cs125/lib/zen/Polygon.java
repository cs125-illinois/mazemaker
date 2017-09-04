package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen polygon class.
 */
public class Polygon extends ZenShape {

    /** My X and Y coordinates. */
    private int[] myX, myY;

    /**
     * Draw a new black polygon at (0, 0) from an array of points.
     *
     * @param points the array of points.
     */
    public Polygon(final Point... points) {
        this(0, 0, null, points);
    }

    /**
     * Draw a new polygon at (0, 0).
     *
     * @param color the color
     * @param points the array of points
     */
    public Polygon(final String color, final Point... points) {
        this(0, 0, color, points);
    }

    /**
     * Draw a new black polygon.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param points the array of points
     */
    public Polygon(final int x, final int y, final Point... points) {
        this(x, y, null, points);
    }

    /**
     * Draw a new polygon.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param color the color
     * @param points the array of points
     */
    public Polygon(final int x, final int y, final String color, final Point... points) {
        this.setColor(color);
        myX = new int[points.length];
        myY = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            myX[i] = points[i].getX();
            myY[i] = points[i].getY();
        }
        setX(x);
        setY(y);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        if (myX != null && myX.length > 0) {
            Zen.fillPolygon(myX, myY);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#set(int, int)
     */
    @Override
    public final void set(final int x, final int y) {
        change(x - this.getX(), y - this.getY());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#set(double, double)
     */
    @Override
    public final void set(final double x, final double y) {
        change(x - this.getX(), y - this.getY());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#setX(int)
     */
    @Override
    public final void setX(final int x) {
        changeX(x - this.getX());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#setX(double)
     */
    @Override
    public final void setX(final double x) {
        changeX(x - this.getX());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#setY(int)
     */
    @Override
    public final void setY(final int y) {
        changeY(y - this.getY());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#setY(double)
     */
    @Override
    public final void setY(final double y) {
        changeY(y - this.getY());
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#change(int, int)
     */
    @Override
    public final void change(final int dx, final int dy) {
        for (int i = 0; i < myX.length; i++) {
            myX[i] += dx;
            myY[i] += dy;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#change(double, double)
     */
    @Override
    public final void change(final double dx, final double dy) {
        for (int i = 0; i < myX.length; i++) {
            myX[i] += dx;
            myY[i] += dy;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeX(int)
     */
    @Override
    public final void changeX(final int amount) {
        for (int i = 0; i < myX.length; i++) {
            myX[i] += amount;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeY(int)
     */
    @Override
    public final void changeY(final int amount) {
        for (int i = 0; i < myY.length; i++) {
            myY[i] += amount;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeX(double)
     */
    @Override
    public final void changeX(final double amount) {
        for (int i = 0; i < myX.length; i++) {
            myX[i] += amount;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#changeY(double)
     */
    @Override
    public final void changeY(final double amount) {
        for (int i = 0; i < myY.length; i++) {
            myY[i] += amount;
        }
    }

    /**
     * Get a point in the polygon by index.
     *
     * @param index the index of the point to return
     * @return the point
     */
    public final Point getPoint(final int index) {
        if (index >= 0 && index < myX.length) {
            return new Point(myX[index], myY[index]);
        }
        return null;
    }

}
