package edu.illinois.cs.cs125.lib.zen;

public abstract class ZenSprite extends Point {

    private int layer = 1;

    public ZenSprite() {
        super();
    }

    public ZenSprite(final int x, final int y) {
        super(x, y);
    }

    public ZenSprite(final double x, final double y) {
        super(x, y);
    }

    public abstract void draw();
    public abstract void move();

    /**
     * Returns the distance between this sprite and another sprite.
     */
    public double distanceTo(final ZenSprite other) {
        return Math.sqrt(Math.pow(this.rawX() - other.rawX(), 2)
                + Math.pow(this.rawY() - other.rawY(), 2));
    }

    public final int getLayer() {
        return layer;
    }

    public final void setLayer(final int layer) {
        this.layer = layer;
    }
}
