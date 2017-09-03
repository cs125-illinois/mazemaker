package edu.illinois.cs.cs125.lib.zen;

public class Image extends ZenShape {
    private String image;

    public Image(final String image) {
        this(0, 0, image);
    }

    public Image(final int x, final int y, final String image) {
        this.image = image;
        this.set(x, y);
    }

    @Override
    public final void draw() {
        Zen.drawImage(image, getX(), getY());
    }

}
