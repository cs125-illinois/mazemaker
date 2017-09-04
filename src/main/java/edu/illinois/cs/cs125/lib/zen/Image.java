package edu.illinois.cs.cs125.lib.zen;

/**
 * Zen image class.
 */
public class Image extends ZenShape {

    /** The image. */
    private String myImage;

    /**
     * Draw a new image at (0, 0).
     *
     * @param image the path to the image to draw
     */
    public Image(final String image) {
        this(0, 0, image);
    }

    /**
     * Draw a new image.
     *
     * @param x the x coordinate of the image
     * @param y the y coordinate of the image
     * @param image the path to the image to draw
     */
    public Image(final int x, final int y, final String image) {
        this.myImage = image;
        this.set(x, y);
    }

    /* (non-Javadoc)
     * @see edu.illinois.cs.cs125.lib.zen.ZenShape#draw()
     */
    @Override
    public final void draw() {
        Zen.drawImage(myImage, getX(), getY());
    }

}
