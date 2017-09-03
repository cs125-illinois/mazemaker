package edu.illinois.cs.cs125.lib.zen;

public class Text extends ZenShape {
    private String text, font;
    private int size;

    public Text(final String text) {
        this("Helvetica", text, 16, "black");
    }

    public Text(final String text, final String color) {
        this("Helvetica", text, 16, color);
    }

    public Text(final String font, final String text, final String color) {
        this(font, text, 16, color);
    }

    public Text(final String text, final int size) {
        this("Helvetica", text, size, "black");
    }

    public Text(final String font, final String text, final int size,
            final String color) {
        this.font = font;
        this.text = text;
        this.size = size;
        setColor(color);
    }

    public final void setText(final String text) {
        this.text = text;
    }

    public final void setFont(final String font) {
        this.font = font;
    }

    public final void setSize(final int size) {
        this.size = size;
    }

    @Override
    public final void draw() {
        Zen.setFont(font, size);
        Zen.drawText(this.text, getX(), getY());
    }

}
