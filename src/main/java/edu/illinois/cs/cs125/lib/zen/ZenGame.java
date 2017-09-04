package edu.illinois.cs.cs125.lib.zen;

import java.util.ArrayList;

/**
 * Zen game class.
 */
public abstract class ZenGame {

    /** Default width. */
    private static final int DEFAULT_WIDTH = 500;

    /** Default height. */
    private static final int DEFAULT_HEIGHT = 500;

    /** Default frames-per-second. */
    private static final int DEFAULT_FPS = 30;

    /** My width, height, and frames per second. */
    private int myWidth = DEFAULT_WIDTH, myHeight = DEFAULT_HEIGHT, myFPS = DEFAULT_FPS;

    /** Am I running? */
    private boolean currentlyRunning = false;

    /** My name. */
    private String myName = "Zen";

    /** My sprites. */
    private ArrayList<ZenSprite> mySprites;

    /**
     * Setup.
     */
    public abstract void setup();

    /**
     * Loop.
     */
    public abstract void loop();

    /**
     * Set the game frames-per-second (FPS).
     *
     * @param fps the new FPS
     */
    public final void setFPS(final int fps) {
        this.myFPS = fps;
    }

    /**
     * Set the game name.
     *
     * @param name the new name
     */
    public final void setName(final String name) {
        this.myName = name;
    }

    /**
     * Set the game size.
     *
     * @param width the new width
     * @param height the new height
     */
    public final void setSize(final int width, final int height) {
        this.myWidth = width;
        this.myHeight = height;
    }

    /**
     * Adds a sprite to the game.
     *
     * @param sprite the sprite to add
     */
    public final void addSprite(final ZenSprite sprite) {
        if (mySprites != null) {
            if (mySprites.size() == 0) {
                mySprites.add(sprite);
            } else {
                for (int i = 0; i < mySprites.size(); i++) {
                    if (mySprites.get(i).getLayer() > sprite.getLayer()) {
                        mySprites.add(i, sprite);
                        break;
                    } else if (i + 1 == mySprites.size()) {
                        mySprites.add(sprite);
                        break;
                    }
                }
            }

        }
    }

    /**
     * Clear all game sprites.
     */
    public final void clearSprites() {
        if (mySprites != null) {
            mySprites.clear();
        }
    }

    /**
     * Run the game.
     */
    public final void run() {
        if (!currentlyRunning) {
            mySprites = new ArrayList<ZenSprite>();
            Zen.setWindowName(myName);
            Zen.create(myWidth, myHeight);
            currentlyRunning = true;
            setup();
            while (true) {
                loop();
                for (ZenSprite sprite : mySprites) {
                    sprite.move();
                    sprite.draw();
                }
                Zen.buffer(1000 / myFPS);
            }
        }
    }
}
