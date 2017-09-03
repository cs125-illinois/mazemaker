package edu.illinois.cs.cs125.lib.zen;

public class Text extends ZenShape {
    private String text, font;
    private int size;

    public Text(String text) {
        this("Helvetica", text, 16, "black");
    }

    public Text(String text, String color) {
        this("Helvetica", text, 16, color);
    }

    public Text(String font, String text, String color) {
        this(font, text, 16, color);
    }

    public Text(String text, int size) {
        this("Helvetica", text, size, "black");
    }

    public Text(String font, String text, int size, String color) {
        this.font = font;
        this.text = text;
        this.size = size;
        setColor(color);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw() {
        Zen.setFont(font, size);
        Zen.drawText(this.text, getX(), getY());
    }

}
