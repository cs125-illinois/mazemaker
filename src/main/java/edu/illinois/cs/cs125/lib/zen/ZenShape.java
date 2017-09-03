package edu.illinois.cs.cs125.lib.zen;

public abstract class ZenShape extends Point {
    private String color;

    public ZenShape() {
        super(0, 0);
    }

    public ZenShape(final int x, final int y) {
        super(x, y);
    }

    public ZenShape(final double x, final double y) {
        super(x, y);
    }

    public abstract void draw();

    public final void colorAndDraw() {
        if (color != null) {
            Zen.setColor(color);
        }
        this.draw();
    }

    public final void setColor(final String color) {
        this.color = color;
    }

    public final String getColor() {
        return color;
    }
}
