package edu.illinois.cs.cs125.lib.zen;

public class Circle extends ZenShape {
    private int diameter;

    public Circle() {
        this(0, 0, 1, null);
    }

    public Circle(final int x, final int y) {
        this(x, y, 1, null);
    }

    public Circle(final int diameter) {
        this(0, 0, diameter, null);
    }

    public Circle(final int diameter, final String color) {
        this(0, 0, diameter, color);
    }

    public Circle(final int x, final int y, final String color) {
        this(x, y, 1, color);
    }

    public Circle(final int x, final int y, final int diameter) {
        this(x, y, diameter, null);
    }

    public Circle(final int x, final int y, final int diameter, final String color) {
        setX(x);
        setY(y);
        setColor(color);
        this.setDiameter(diameter);
    }

    public final void draw() {
        Zen.fillOval(getX() - getDiameter() / 2, getY() - getDiameter() / 2, getDiameter(), getDiameter());
    }

    public final int getDiameter() {
        return diameter;
    }

    public final void setDiameter(final int diameter) {
        this.diameter = diameter;
    }

    public final void setDiameter(final double diameter) {
        this.diameter = (int) diameter;
    }
}
