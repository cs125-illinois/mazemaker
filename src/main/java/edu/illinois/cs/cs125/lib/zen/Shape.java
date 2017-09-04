package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen shape class.
 */
public class Shape extends ZenShape {

    /** The shape components. */
    private ZenShape[] myComponents;

    /**
     * Create a new shape centered at (0, 0).
     *
     * @param components the components
     */
    public Shape(final ZenShape... components) {
        this(0, 0, components);
    }

    /**
     * Create a new shape centered at a given point.
     *
     * @param center the center point
     * @param components the components
     */
    public Shape(final Point center, final ZenShape... components) {
        this(center.getX(), center.getY(), components);
    }

    /**
     * Create a new shape centered at given coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param components the components
     */
    public Shape(final int x, final int y, final ZenShape... components) {
        this.myComponents = components;
        this.setX(x);
        this.setY(y);
    }

    /**
     * Create a new shape.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param components the components
     */
    public Shape(final double x, final double y, final ZenShape... components) {
        this.myComponents = components;
        this.setX(x);
        this.setY(y);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#set(int, int)
     */
    @Override
    public final void set(final int x, final int y) {
        this.change(x - this.getX(), y - this.getY());
        this.setX(x);
        this.setY(y);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#change(int, int)
     */
    @Override
    public final void change(final int dx, final int dy) {
        for (ZenShape component : myComponents) {
            component.change(dx, dy);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#set(double, double)
     */
    @Override
    public final void set(final double x, final double y) {
        this.change(x - this.getX(), y - this.getY());
        this.setX(x);
        this.setY(y);
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.Point#change(double, double)
     */
    @Override
    public final void change(final double dx, final double dy) {
        for (ZenShape component : myComponents) {
            component.change(dx, dy);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        for (ZenShape component : myComponents) {
            component.colorAndDraw();
        }
    }
}
