package edu.illinois.cs.cs125.lib.zen;

public class Image extends ZenShape {
    private String image;

    public Image(String image) {
        this(0, 0, image);
    }

    public Image(int x, int y, String image) {
        this.image = image;
        this.set(x, y);
    }

    @Override
    public void draw() {
        Zen.drawImage(image, getX(), getY());
    }

}
