package edu.illinois.cs.cs125.lib.mazemaker;

/**
 * Zen text class.
 */
public class Text extends ZenShape {

    /** My text and font. */
    private String myText, myFont;

    /** My text size. */
    private int mySize;

    /**
     * Draw new black text in 16-point Helvetica.
     *
     * @param text the text to draw.
     */
    public Text(final String text) {
        this("Helvetica", text, 16, "black");
    }

    /**
     * Draw new text in 16-point Helvetica.
     *
     * @param text the text to draw
     * @param color the color
     */
    public Text(final String text, final String color) {
        this("Helvetica", text, 16, color);
    }

    /**
     * Draw new text in 16-point font.
     *
     * @param font the font to use
     * @param text the text to draw
     * @param color the color
     */
    public Text(final String font, final String text, final String color) {
        this(font, text, 16, color);
    }

    /**
     * Draw new black text in Helvetica.
     *
     * @param text the text to draw
     * @param size the font size
     */
    public Text(final String text, final int size) {
        this("Helvetica", text, size, "black");
    }

    /**
     * Draw new text.
     *
     * @param font the font to use
     * @param text the text to draw
     * @param size the font size
     * @param color the color
     */
    public Text(final String font, final String text, final int size, final String color) {
        this.myFont = font;
        this.myText = text;
        this.mySize = size;
        setColor(color);
    }

    /**
     * Set the text string.
     *
     * @param text the new text to draw.
     */
    public final void setText(final String text) {
        this.myText = text;
    }

    /**
     * Set the text font.
     *
     * @param font the new font to use
     */
    public final void setFont(final String font) {
        this.myFont = font;
    }

    /**
     * Set the text size.
     *
     * @param size the new text size
     */
    public final void setSize(final int size) {
        this.mySize = size;
    }

    /*
     * (non-Javadoc)
     *
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        Zen.setFont(myFont, mySize);
        Zen.drawText(this.myText, getX(), getY());
    }

}
