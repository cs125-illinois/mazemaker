package edu.illinois.cs.cs125.lib.mazemaker;

/**
 * Zen sprite class.
 */
public abstract class ZenSprite extends Point {

    /** My layer. */
    private int myLayer = 1;

    /**
     * Create a new sprite at (0, 0).
     */
    public ZenSprite() {
        super();
    }

    /**
     * Create a new sprite at integer coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public ZenSprite(final int x, final int y) {
        super(x, y);
    }

    /**
     * Create a new sprite at floating point coordinates.
     *
     * @param x the X coordinate
     * @param y the Y coordinate
     */
    public ZenSprite(final double x, final double y) {
        super(x, y);
    }

    /**
     * Draw.
     */
    public abstract void draw();

    /**
     * Move.
     */
    public abstract void move();

    /**
     * Returns the distance between this sprite and another sprite.
     *
     * @param other the other sprite
     * @return the distance between the two sprites
     */
    public double distanceTo(final ZenSprite other) {
        return Math.sqrt(
                Math.pow(this.rawX() - other.rawX(), 2) + Math.pow(this.rawY() - other.rawY(), 2));
    }

    /**
     * Gets the layer.
     *
     * @return the layer
     */
    public final int getLayer() {
        return myLayer;
    }

    /**
     * Sets the layer.
     *
     * @param layer the new layer
     */
    public final void setLayer(final int layer) {
        this.myLayer = layer;
    }
}
