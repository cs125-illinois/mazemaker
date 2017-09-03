package edu.illinois.cs.cs125.lib.zen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 * Zen library.
 */
@SuppressWarnings("serial")
public class Zen extends JApplet {

    /** The Constant DEFAULT_SIZE. */
    public static final Dimension DEFAULT_SIZE = new Dimension(640, 480);

    /** The Constant DEFAULT_OPTIONS. */
    public static final String DEFAULT_OPTIONS = "";

    /** The Constant VERBOSE. */
    protected static final boolean VERBOSE = false;

    /** The menu initialized. */
    private static boolean initialized = false, menuInitialized = false;

    /** The key map. */
    private static Map<String, Integer> keyMap;

    /** The color map. */
    private static Map<String, Integer> colorMap;

    /** The action map. */
    private static Map<String, Map<String, String>> actionMap;

    /** The action queue. */
    private static ArrayList<String> actionQueue;

    /** The width. */
    private static int myWidth, myHeight;

    /** The window name. */
    private static String windowName;

    /**
     * Creates the.
     *
     * @param width the width
     * @param height the height
     * @return the zen instance
     */
    public static ZenInstance create(final int width, final int height) {
        return create(width, height, "");
    }

    /**
     * Creates the.
     *
     * @param width the width
     * @param height the height
     * @param options the options
     * @return the zen instance
     */
    public static ZenInstance create(final int width, final int height,
            final String options) {
        if (!initialized) {
            initialize();
            Zen.myWidth = width;
            Zen.myHeight = height;
        }
        if (!mustBeAnWebApplet) {
            mustBeAnApplication = true;
        }
        synchronized (Zen.class) {
            ZenInstance instance = instanceMap.get();
            if (instance == null) { // no instance set for this thread
                JFrame frame;
                if (Zen.windowName != null) {
                    frame = new JFrame(Zen.windowName);
                } else {
                    frame = new JFrame("Zen");
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                if (Zen.menuInitialized) {
                    frame.setJMenuBar(Zen.generateMenuBar());
                }
                // System.err.println("Creating Instance");
                Zen zen = new Zen();
                zen.bufferSize = new Dimension(width, height);
                zen.bufferOptions = options;
                instanceMap.set(zen.master);
                Container pane = frame.getContentPane();
                pane.add(zen);
                pane.setSize(zen.getSize());
                pane.setMinimumSize(zen.getSize());
                // frame.getContentPane().setIgnoreRepaint(true);
                zen.init();
                frame.pack();
                frame.setVisible(true);

                zen.start();
                return zen.master;
            }
            return instance;
        }
    }

    /**
     * Generate menu bar.
     *
     * @return the j menu bar
     */
    private static JMenuBar generateMenuBar() {
        JMenuBar bar = new JMenuBar();
        for (Map.Entry<String, Map<String, String>> actionMenu : actionMap
                .entrySet()) {
            JMenu menu = new JMenu(actionMenu.getKey());
            for (Map.Entry<String, String> actionItem : actionMenu.getValue()
                    .entrySet()) {
                JMenuItem item = new JMenuItem(actionItem.getKey());
                item.setActionCommand(actionItem.getValue());
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(final ActionEvent e) {
                        Zen.pushAction(e.getActionCommand());
                    }
                });
                menu.add(item);
            }
            bar.add(menu);
        }
        return bar;
    }

    /**
     * Checks for action.
     *
     * @return true, if successful
     */
    public static boolean hasAction() {
        return Zen.actionQueue.size() > 0;
    }

    /**
     * Gets the action.
     *
     * @return the action
     */
    public static String getAction() {
        if (Zen.actionQueue.size() > 0) {
            return Zen.actionQueue.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Push action.
     *
     * @param action the action
     */
    public static void pushAction(final String action) {
        Zen.actionQueue.add(action);
    }

    /**
     * Menu.
     *
     * @param menu the menu
     * @param name the name
     * @param action the action
     */
    public static void menu(final String menu, final String name,
            final String action) {
        if (!menuInitialized) {
            actionMap = new HashMap<String, Map<String, String>>();
            actionQueue = new ArrayList<String>();
            menuInitialized = true;
        }
        if (menu != null && name != null && action != null) {
            if (!actionMap.containsKey(menu)) {
                actionMap.put(menu, new HashMap<String, String>());
            }
            actionMap.get(menu).put(name, action);
        }
    }

    /**
     * Initialize.
     */
    private static void initialize() {
        keyMap = new HashMap<String, Integer>();
        keyMap.put("space", KeyEvent.VK_SPACE);
        keyMap.put("left", KeyEvent.VK_LEFT);
        keyMap.put("right", KeyEvent.VK_RIGHT);
        keyMap.put("up", KeyEvent.VK_UP);
        keyMap.put("down", KeyEvent.VK_DOWN);
        keyMap.put("escape", KeyEvent.VK_ESCAPE);
        keyMap.put("tab", KeyEvent.VK_TAB);
        keyMap.put("shift", KeyEvent.VK_SHIFT);
        keyMap.put("control", KeyEvent.VK_CONTROL);
        keyMap.put("alt", KeyEvent.VK_ALT);
        keyMap.put("delete", KeyEvent.VK_DELETE);
        keyMap.put("home", KeyEvent.VK_HOME);
        colorMap = new HashMap<String, Integer>();
        addColor("alice blue", 240, 248, 255);
        addColor("antique white", 250, 235, 215);
        addColor("aqua", 0, 255, 255);
        addColor("aquamarine", 127, 255, 212);
        addColor("azure", 240, 255, 255);
        addColor("beige", 245, 245, 220);
        addColor("bisque", 255, 228, 196);
        addColor("black", 0, 0, 0);
        addColor("blanched almond", 255, 235, 205);
        addColor("blue", 0, 0, 255);
        addColor("blue violet", 138, 43, 226);
        addColor("brown", 165, 42, 42);
        addColor("burlywood", 222, 184, 135);
        addColor("cadet blue", 95, 158, 160);
        addColor("chartreuse", 127, 255, 0);
        addColor("chocolate", 210, 105, 30);
        addColor("coral", 255, 127, 80);
        addColor("cornflower blue", 100, 149, 237);
        addColor("cornsilk", 255, 248, 220);
        addColor("cyan", 0, 255, 255);
        addColor("dark blue", 0, 0, 139);
        addColor("dark cyan", 0, 139, 139);
        addColor("dark goldenrod", 184, 134, 11);
        addColor("dark gray", 169, 169, 169);
        addColor("dark green", 0, 100, 0);
        addColor("dark khaki", 189, 183, 107);
        addColor("dark magenta", 139, 0, 139);
        addColor("dark olive green", 85, 107, 47);
        addColor("dark orange", 255, 140, 0);
        addColor("dark orchid", 153, 50, 204);
        addColor("dark red", 139, 0, 0);
        addColor("dark salmon", 233, 150, 122);
        addColor("dark sea green", 143, 188, 143);
        addColor("dark slate blue", 72, 61, 139);
        addColor("dark slate gray", 47, 79, 79);
        addColor("dark turquoise", 0, 206, 209);
        addColor("dark violet", 148, 0, 211);
        addColor("deep pink", 255, 20, 147);
        addColor("deep sky blue", 0, 191, 255);
        addColor("dim gray", 105, 105, 105);
        addColor("dodger blue", 30, 144, 255);
        addColor("firebrick", 178, 34, 34);
        addColor("floral white", 255, 250, 240);
        addColor("forest green", 34, 139, 34);
        addColor("fuschia", 255, 0, 255);
        addColor("gainsboro", 220, 220, 220);
        addColor("ghost white", 255, 250, 250);
        addColor("gold", 255, 215, 0);
        addColor("goldenrod", 218, 165, 32);
        addColor("gray", 128, 128, 128);
        addColor("green", 0, 128, 0);
        addColor("green yellow", 173, 255, 47);
        addColor("honeydew", 240, 255, 240);
        addColor("hot pink", 255, 105, 180);
        addColor("indian red", 205, 92, 92);
        addColor("ivory", 255, 255, 240);
        addColor("khaki", 240, 230, 140);
        addColor("lavender", 230, 230, 250);
        addColor("lavender blush", 255, 240, 245);
        addColor("lawn green", 124, 252, 0);
        addColor("lemon chiffon", 255, 250, 205);
        addColor("light blue", 173, 216, 230);
        addColor("light coral", 240, 128, 128);
        addColor("light cyan", 224, 255, 255);
        addColor("light goldenrod", 238, 221, 130);
        addColor("light goldenrod yellow", 250, 250, 210);
        addColor("light gray", 211, 211, 211);
        addColor("light green", 144, 238, 144);
        addColor("light pink", 255, 182, 193);
        addColor("light salmon", 255, 160, 122);
        addColor("light sea green", 32, 178, 170);
        addColor("light sky blue", 135, 206, 250);
        addColor("light slate blue", 132, 112, 255);
        addColor("light slate gray", 119, 136, 153);
        addColor("light steel blue", 176, 196, 222);
        addColor("light yellow", 255, 255, 224);
        addColor("lime", 0, 255, 0);
        addColor("lime green", 50, 205, 50);
        addColor("linen", 250, 240, 230);
        addColor("magenta", 255, 0, 255);
        addColor("maroon", 128, 0, 0);
        addColor("medium aquamarine", 102, 205, 170);
        addColor("medium blue", 0, 0, 205);
        addColor("medium orchid", 186, 85, 211);
        addColor("medium purple", 147, 112, 219);
        addColor("medium sea green", 60, 179, 113);
        addColor("medium slate blue", 123, 104, 238);
        addColor("medium spring green", 0, 250, 154);
        addColor("medium turquoise", 72, 209, 204);
        addColor("medium violet red", 199, 21, 133);
        addColor("midnight blue", 25, 25, 112);
        addColor("mint cream", 245, 255, 250);
        addColor("misty rose", 255, 228, 225);
        addColor("moccasin", 255, 228, 181);
        addColor("navajo white", 255, 222, 173);
        addColor("navy", 0, 0, 128);
        addColor("old lace", 253, 245, 230);
        addColor("olive", 128, 128, 0);
        addColor("olive drab", 107, 142, 35);
        addColor("orange", 255, 165, 0);
        addColor("orange red", 255, 69, 0);
        addColor("orchid", 218, 112, 214);
        addColor("pale goldenrod", 238, 232, 170);
        addColor("pale green", 152, 251, 152);
        addColor("pale turquoise", 175, 238, 238);
        addColor("pale violet red", 219, 112, 147);
        addColor("papaya whip", 255, 239, 213);
        addColor("peach puff", 255, 218, 185);
        addColor("peru", 205, 133, 63);
        addColor("pink", 255, 192, 203);
        addColor("plum", 221, 160, 221);
        addColor("powder blue", 176, 224, 230);
        addColor("purple", 128, 0, 128);
        addColor("red", 255, 0, 0);
        addColor("rosy brown", 188, 143, 143);
        addColor("royal blue", 65, 105, 225);
        addColor("saddle brown", 139, 69, 19);
        addColor("salmon", 250, 128, 114);
        addColor("sandy brown", 244, 164, 96);
        addColor("sea green", 46, 139, 87);
        addColor("seashell", 255, 245, 238);
        addColor("sienna", 160, 82, 45);
        addColor("silver", 192, 192, 192);
        addColor("sky blue", 135, 206, 235);
        addColor("slate blue", 106, 90, 205);
        addColor("slate gray", 112, 128, 144);
        addColor("snow", 255, 250, 250);
        addColor("spring green", 0, 255, 127);
        addColor("steel blue", 70, 130, 180);
        addColor("tan", 210, 180, 140);
        addColor("teal", 0, 128, 128);
        addColor("thistle", 216, 191, 216);
        addColor("tomato", 255, 99, 71);
        addColor("turquoise", 64, 224, 208);
        addColor("violet", 238, 130, 238);
        addColor("violet red", 208, 32, 144);
        addColor("wheat", 245, 222, 179);
        addColor("white", 255, 255, 255);
        addColor("white smoke", 245, 245, 245);
        addColor("yellow", 255, 255, 0);
        addColor("yellow green", 154, 205, 50);
    }

    /**
     * Adds the color.
     *
     * @param name the name
     * @param red the red
     * @param green the green
     * @param blue the blue
     */
    public static void addColor(final String name, final int red,
            final int green, final int blue) {
        colorMap.put(name, ((red & 0x0ff) << 16) | ((green & 0x0ff) << 8)
                | (blue & 0x0ff));
    }

    /**
     * Sets the window name.
     *
     * @param name the new window name
     */
    public static void setWindowName(final String name) {
        Zen.windowName = name;
    }

    /**
     * Gets the about message.
     *
     * @return the about message
     */
    public static String getAboutMessage() {
        return "Zen Graphics (version 0.1) Copyright Lawrence Angrave, 2010";
    }

    /**
     * Wait for click.
     */
    public static void waitForClick() {
        getInstanceFromThread().waitForClick();
    }

    /**
     * Gets the zen width.
     *
     * @return the zen width
     */
    public static int getZenWidth() {
        return getInstanceFromThread().getZenWidth();
    }

    /**
     * Gets the zen height.
     *
     * @return the zen height
     */
    public static int getZenHeight() {
        return getInstanceFromThread().getZenHeight();
    }

    /**
     * Gets the mouse click X.
     *
     * @return the mouse click X
     */
    public static int getMouseClickX() {
        return getInstanceFromThread().getMouseClickX();
    }

    /**
     * Gets the mouse click Y.
     *
     * @return the mouse click Y
     */
    public static int getMouseClickY() {
        return getInstanceFromThread().getMouseClickY();
    }

    /**
     * Gets the mouse click time.
     *
     * @return the mouse click time
     */
    public static long getMouseClickTime() {
        return getInstanceFromThread().getMouseClickTime();
    }

    /**
     * Sets the edits the text.
     *
     * @param s the new edits the text
     */
    public static void setEditText(final String s) {
        getInstanceFromThread().setEditText(s);
    }

    /**
     * Gets the edits the text.
     *
     * @return the edits the text
     */
    public static String getEditText() {
        return getInstanceFromThread().getEditText();
    }

    /**
     * Gets the mouse buttons and modifier keys.
     *
     * @return the mouse buttons and modifier keys
     */
    public static int getMouseButtonsAndModifierKeys() {
        return getInstanceFromThread().getMouseButtonsAndModifierKeys();
    }

    /**
     * Gets the mouse X.
     *
     * @return the mouse X
     */
    public static int getMouseX() {
        return getInstanceFromThread().getMouseX();
    }

    /**
     * Gets the mouse Y.
     *
     * @return the mouse Y
     */
    public static int getMouseY() {
        return getInstanceFromThread().getMouseY();
    }

    /**
     * Sleep.
     *
     * @param milliseconds the milliseconds
     */
    public static void sleep(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ignored) {
        }
    }

    /**
     * Checks if is key pressed.
     *
     * @param key the key
     * @return true, if is key pressed
     */
    public static boolean isKeyPressed(final String key) {
        if (key == null) {
            return false;
        } else if (keyMap.containsKey(key)) {
            return Zen.isVirtualKeyPressed(keyMap.get(key));
        } else if (key.length() > 0) {
            return Zen.isKeyPressed(key.charAt(0));
        }
        return false;
    }

    /**
     * Checks if is key pressed.
     *
     * @param key the key
     * @return true, if is key pressed
     */
    private static boolean isKeyPressed(final char key) {
        return getInstanceFromThread().isKeyPressed(key);
    }

    /**
     * Checks if is virtual key pressed.
     *
     * @param keyCode the key code
     * @return true, if is virtual key pressed
     */
    public static boolean isVirtualKeyPressed(final int keyCode) {
        return getInstanceFromThread().isVirtualKeyPressed(keyCode);
    }

    /**
     * Checks if is running.
     *
     * @return true, if is running
     */
    public static boolean isRunning() {
        return getInstanceFromThread().isRunning();
    }

    /**
     * Gets the buffer graphics.
     *
     * @return the buffer graphics
     */
    public static Graphics2D getBufferGraphics() {
        return getInstanceFromThread().getBufferGraphics();
    }

    /**
     * Draw.
     *
     * @param shape the shape
     */
    public static void draw(final ZenShape shape) {
        shape.colorAndDraw();
    }

    /**
     * Draw image.
     *
     * @param filename the filename
     * @param x the x
     * @param y the y
     */
    public static void drawImage(final String filename, final int x,
            final int y) {
        getInstanceFromThread().drawImage(filename, x, y);
    }

    /**
     * Draw line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public static void drawLine(final int x1, final int y1, final int x2,
            final int y2) {
        getInstanceFromThread().drawLine(x1, y1, x2, y2);
    }

    /**
     * Draw polygon.
     *
     * @param x the x
     * @param y the y
     */
    public static void drawPolygon(final int[] x, final int[] y) {
        getInstanceFromThread().drawPolygon(x, y);
    }

    /**
     * Draw text.
     *
     * @param text the text
     * @param x the x
     * @param y the y
     */
    public static void drawText(final String text, final int x, final int y) {
        getInstanceFromThread().drawText(text, x, y);

    }

    /**
     * Draw arc.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param startAngle the start angle
     * @param arcAngle the arc angle
     */
    public static void drawArc(final int x, final int y, final int width,
            final int height, final int startAngle, final int arcAngle) {
        getInstanceFromThread().drawArc(x, y, width, height, startAngle,
                arcAngle);
    }

    /**
     * Fill oval.
     *
     * @param minX the min X
     * @param minY the min Y
     * @param width the width
     * @param height the height
     */
    public static void fillOval(final int minX, final int minY, final int width,
            final int height) {
        getInstanceFromThread().fillOval(minX, minY, width, height);
    }

    /**
     * Fill rect.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param width the width
     * @param height the height
     */
    public static void fillRect(final int x1, final int y1, final int width,
            final int height) {
        getInstanceFromThread().fillRect(x1, y1, width, height);
    }

    /**
     * Fill polygon.
     *
     * @param x the x
     * @param y the y
     */
    public static void fillPolygon(final int[] x, final int[] y) {
        getInstanceFromThread().fillPolygon(x, y);
    }

    /**
     * Sets the background.
     *
     * @param color the new background
     */
    public static void setBackground(final String color) {
        if (color != null) {
            Zen.setColor(color);
            Zen.fillRect(0, 0, Zen.myWidth, Zen.myHeight);
        }
    }

    /**
     * Sets the color.
     *
     * @param color the new color
     */
    public static void setColor(final String color) {
        if (color != null && colorMap.containsKey(color.toLowerCase())) {
            int c = colorMap.get(color.toLowerCase());
            Zen.setColor((c >> 16) & 0xff, (c >> 8) & 0xff, c & 0xff);
        } else {
            Zen.setColor(0, 0, 0);
        }
    }

    /**
     * Sets the color.
     *
     * @param red the red
     * @param green the green
     * @param blue the blue
     */
    public static void setColor(final int red, final int green,
            final int blue) {
        getInstanceFromThread().setColor(red, green, blue);
    }

    /**
     * Bound.
     *
     * @param value the value
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int bound(final int value, final int min, final int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Gets the random number.
     *
     * @param max the max
     * @return the random number
     */
    public static int getRandomNumber(final int max) {
        return (int) (Math.random() * max + 1);
    }

    /**
     * Gets the random number.
     *
     * @param min the min
     * @param max the max
     * @return the random number
     */
    public static int getRandomNumber(final int min, final int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    /**
     * Sets the font.
     *
     * @param fontname the fontname
     * @param size the size
     * @return the font
     */
    public static Font setFont(final String fontname, final int size) {
        return Zen.setFont(fontname + "-" + size);
    }

    /**
     * Sets the font.
     *
     * @param fontname the fontname
     * @return the font
     */
    public static Font setFont(final String fontname) {
        return getInstanceFromThread().setFont(fontname);
    }

    /**
     * Gets the cached image.
     *
     * @param filename the filename
     * @return the cached image
     */
    public static Image getCachedImage(final String filename) {
        return getInstanceFromThread().getCachedImage(filename);
    }

    /**
     * Buffer.
     *
     * @param ms the ms
     */
    public static void buffer(final int ms) {
        Zen.flipBuffer();
        Zen.sleep(ms);
    }

    /**
     * Flip buffer.
     */
    public static void flipBuffer() {
        getInstanceFromThread().flipBuffer();
    }

    /**
     * The Class ZenInstance.
     */

    class ZenInstance {
        private final int MOUSE_WAIT_LENGTH_MS = 250;
        public void waitForClick() {
            long t = mouseClickTime;
            // Todo: remove polling and use synchronization wait lock
            while (isRunning && t == mouseClickTime) {
                sleep(MOUSE_WAIT_LENGTH_MS);
            }
        }

        public int getZenWidth() {
            return bufferSize.width;
        }

        public int getZenHeight() {
            return bufferSize.height;
        }

        public int getMouseClickX() {
            return mouseClickX;
        }

        public int getMouseClickY() {
            return mouseClickY;
        }

        public long getMouseClickTime() {
            return mouseClickTime;
        }

        public void setEditText(final String s) {
            editText = new StringBuilder(s);
        }

        public String getEditText() {
            return editText.toString();
        }

        public int getMouseButtonsAndModifierKeys() {
            return mouseButtonsAndModifierKeys;
        }

        public int getMouseX() {
            return mouseX;
        }

        public int getMouseY() {
            return mouseY;
        }

        public boolean isMouseClicked() {
            return isMouseClicked;
        }

        public boolean isKeyPressed(final char key) {
            return key >= 0 && key < keyPressed.length
                    ? keyPressed[key]
                    : false;
        }

        public boolean isVirtualKeyPressed(final int keyCode) {
            return keyCode >= 0 && keyCode < virtualKeyPressed.length
                    ? virtualKeyPressed[keyCode]
                    : false;
        }

        public boolean isRunning() {
            return isRunning;
        }

        public Graphics2D getBufferGraphics() {
            // getSingleton(); // ensure instance created
            while (g == null) {
                System.err
                        .println("Odd... graphics not yet ready! Sleeping...");
                sleep(1000); // race-condition hack ; should never happen if the
                // container is correctly implemented
            }
            return g;
        }

        public void drawImage(final String filename, final int x, final int y) {
            Graphics2D g = getBufferGraphics();
            Image img = getCachedImage(filename);
            if (img != null) {
                g.drawImage(img, x, y, null);
            } else {
                g.drawString(filename + "?", x, y);
            }
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void drawLine(final int x1, final int y1, final int x2,
                final int y2) {
            getBufferGraphics().drawLine(x1, y1, x2, y2);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void drawText(final String text, final int x, final int y) {
            getBufferGraphics().drawString(text, x, y);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void drawPolygon(final int[] x, final int[] y) {
            getBufferGraphics().drawPolygon(x, y, x.length);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void drawArc(final int x, final int y, final int width,
                final int height, final int startAngle, final int arcAngle) {
            getBufferGraphics().drawArc(x, y, width, height, startAngle,
                    arcAngle);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void fillOval(final int minX, final int minY, final int width,
                final int height) {
            getBufferGraphics().fillOval(minX, minY, width, height);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void fillRect(final int x1, final int y1, final int width,
                final int height) {
            getBufferGraphics().fillRect(x1, y1, width, height);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void fillPolygon(final int[] x, final int[] y) {
            getBufferGraphics().fillPolygon(x, y, x.length);
            if (paintImmediately) {
                paintWindowImmediately();
            }
        }

        public void setColor(final int red, final int green, final int blue) {
            currentColor = new Color(bound(red, 0, 255), bound(green, 0, 255),
                    bound(blue, 0, 255));
            getBufferGraphics().setColor(currentColor);
        }

        public Font setFont(final String font) {
            currentFont = Font.decode(font);
            getBufferGraphics().setFont(currentFont);
            return currentFont;
        }

        public Image getCachedImage(final String filename) {
            Image img = nameToImage.get("../" + filename);
            if (img != null) {
                return img;
            }
            try {
                InputStream is = Zen.class
                        .getResourceAsStream("../" + filename);
                img = ImageIO.read(is);
                is.close();
                nameToImage.put(filename, img);
                return img;
            } catch (Exception ex) {
                // System.err.println("Can't load '" + filename + "' : "
                // + ex.getMessage());
                return null;
            }
        }

        public void flipBuffer() {
            // Both flipBuffer and portions of paint() are synchronized
            // on the class object to ensure
            // that both cannot execute at the same time.
            paintImmediately = false; // user has called flipBuffer at least
            // once
            // getSingleton();
            synchronized (Zen.this) {
                Image temp = backImageBuffer;
                backImageBuffer = frontImageBuffer;
                frontImageBuffer = temp;

                if (g != null) {
                    g.dispose();
                }
                paintWindowImmediately(); // paint to Video

                g = (Graphics2D) backImageBuffer.getGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, backImageBuffer.getWidth(null),
                        backImageBuffer.getHeight(null));
                g.setColor(currentColor);
                g.setFont(currentFont);
            }
        }

        void createBuffers(final int width, final int height,
                final String passedOptions) {
            if (g != null) {
                g.dispose();
            }
            if (frontImageBuffer != null) {
                frontImageBuffer.flush();
            }
            if (backImageBuffer != null) {
                backImageBuffer.flush();
            }
            String options = "";
            if (passedOptions != null) {
                options = passedOptions.toLowerCase();
            }
            bufferSize = new Dimension(width, height);
            stretchToFit = options.contains("stretch");

            // if buffers are requested _after_ the window has been realized
            // then faster volatile images are possible
            // BUT volatile images silently fail when tested Vista IE8 and
            // JRE1.6
            boolean useVolatileImages = false;
            if (useVolatileImages) {
                try {
                    // Paint silently fails when tested in IE8 Vista JRE1.6.0.14
                    backImageBuffer = createVolatileImage(width, height);
                    frontImageBuffer = createVolatileImage(width, height);
                } catch (Exception ignored) {

                }
            }
            if (!GraphicsEnvironment.isHeadless()) {
                try {
                    GraphicsConfiguration config = GraphicsEnvironment
                            .getLocalGraphicsEnvironment()
                            .getDefaultScreenDevice().getDefaultConfiguration();
                    backImageBuffer = config.createCompatibleImage(width,
                            height);
                    frontImageBuffer = config.createCompatibleImage(width,
                            height);
                } catch (Exception ignored) {
                }
            }

            // as a fall-back we can still use slower BufferedImage with
            // arbitrary RGB model
            if (frontImageBuffer == null) {
                // System.err.println("Creating BufferedImage buffers");
                backImageBuffer = new BufferedImage(bufferSize.width,
                        bufferSize.height, BufferedImage.TYPE_INT_RGB);
                frontImageBuffer = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
            }
            master.flipBuffer();// set up graphics, including font and color
            // state
            paintImmediately = true; // actually, user has not yet called
            // flipBuffer
        }

    }; // End class ZenMaster

    /** The instance map. */
    /*
     * --------------------- IMPLEMENTATION ----------------------
     */
    private static ThreadLocal<ZenInstance> instanceMap = new ThreadLocal<ZenInstance>();

    /**
     * Gets the instance from thread.
     *
     * @return the instance from thread
     */
    private static synchronized ZenInstance getInstanceFromThread() {
        ZenInstance instance = instanceMap.get();
        return instance != null
                ? instance
                : create(DEFAULT_SIZE.width, DEFAULT_SIZE.height,
                        DEFAULT_OPTIONS);
    }

    /** The must be an application. */
    private static boolean mustBeAnApplication; // true if create called init

    /** The must be an web applet. */
    private static boolean mustBeAnWebApplet; // true if init called before

    /** The master. */
    // create
    private ZenInstance master = new ZenInstance();

    /** The g. */
    private Graphics2D g;

    /** The front image buffer. */
    private Image backImageBuffer, frontImageBuffer;

    /** The name to image. */
    private Map<String, Image> nameToImage = Collections
            .synchronizedMap(new HashMap<String, Image>());

    /** The stretch to fit. */
    private boolean stretchToFit;

    /** The key pressed. */
    private boolean[] keyPressed = new boolean[256];

    /** The virtual key pressed. */
    private boolean[] virtualKeyPressed = new boolean[1024];

    /** The mouse Y. */
    private int mouseX, mouseY, mouseClickX, mouseClickY;

    /** The mouse click time. */
    private long mouseClickTime;

    /** The is mouse clicked. */
    private boolean isMouseClicked;

    /** The edit text. */
    private StringBuilder editText = new StringBuilder();

    /** The buffer size. */
    private Dimension bufferSize = new Dimension(DEFAULT_SIZE);

    /** The buffer options. */
    private String bufferOptions = DEFAULT_OPTIONS;

    /** The current color. */
    private Color currentColor = Color.WHITE;

    /** The current font. */
    private Font currentFont = Font.decode("Times-18");

    /** The mouse buttons and modifier keys. */
    private int mouseButtonsAndModifierKeys;

    /** The is running. */
    private boolean isRunning = true;

    /** The main thread. */
    private Thread mainThread;

    /** The window width. */
    private int paintAtX, paintAtY, windowWidth, windowHeight;

    /** The paint immediately. */
    protected boolean paintImmediately;

    /* (non-Javadoc)
     * @see java.awt.Container#getMinimumSize()
     */
    @Override
    public final Dimension getMinimumSize() {
        return bufferSize;
    }

    /* (non-Javadoc)
     * @see java.awt.Container#getPreferredSize()
     */
    @Override
    public final Dimension getPreferredSize() {
        return getMinimumSize();
    }

    /* (non-Javadoc)
     * @see java.applet.Applet#init()
     */
    // JApplet methods
    @Override
    public final void init() {
        if (!mustBeAnApplication) {
            mustBeAnWebApplet = true;
        }

        instanceMap.set(master);

        setSize(bufferSize);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                addMouseListener(mouseListener);
                addMouseMotionListener(mouseMotionListener);
                addKeyListener(keyListener);
                setFocusTraversalKeysEnabled(false);
                setFocusable(true);
                setVisible(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

            } // run
        });
    }

    /* (non-Javadoc)
     * @see java.applet.Applet#stop()
     */
    @Override
    @SuppressWarnings("deprecation")
    public final void stop() {
        isRunning = false;
        if (mainThread == null) {
            return;
        }
        mainThread.interrupt();
        sleep(500);
        if (mainThread.isAlive()) {
            mainThread.stop();
        }
        mainThread = null;
    }

    /* (non-Javadoc)
     * @see java.applet.Applet#start()
     */
    @Override
    public final void start() {
        master.createBuffers(bufferSize.width, bufferSize.height,
                bufferOptions);
        isRunning = true;
        if (mustBeAnWebApplet) {
            mainThread = new Thread("main") {

                @Override
                public void run() {
                    if (VERBOSE) {
                        System.out.println("Starting main thread...");
                    }
                    try {
                        instanceMap.set(Zen.this.master);
                        String paramKey = "zen-main-class";
                        String classNameToRunForApplet = getParameter(paramKey);
                        if (classNameToRunForApplet == null) {
                            String mesg = paramKey
                                    + " parameter is not set; where is your main method?";
                            System.err.println(mesg);
                            setFont("Courier-12");
                            drawText(mesg, 0, 10);
                            return;
                        }
                        Class<?> clazz = Class.forName(classNameToRunForApplet);
                        String[] argValue = new String[0];
                        Class<?>[] argTypes = {argValue.getClass()};
                        Method main = clazz.getMethod("main", argTypes);
                        // System.err.println(clazz + ".main()...");
                        main.invoke(null, new Object[]{argValue});

                    } catch (ThreadDeath ignored) {
                    } catch (Exception ex) {
                        System.err.println("Exception:" + ex.getMessage());
                        ex.printStackTrace();
                        setFont("Courier-12");
                        drawText(ex.toString(), 0, 12);
                    } finally {
                        instanceMap.remove();
                    }

                } // end method
            };
            mainThread.start();
        }
    }

    /* (non-Javadoc)
     * @see java.applet.Applet#destroy()
     */
    @Override
    public final void destroy() {
        super.destroy();
    }

    /* (non-Javadoc)
     * @see javax.swing.JApplet#update(java.awt.Graphics)
     */
    @Override
    public final void update(final Graphics windowGraphics) {
        paint(windowGraphics);
    }

    /* (non-Javadoc)
     * @see java.awt.Container#paint(java.awt.Graphics)
     */
    @Override
    public final void paint(final Graphics windowGraphics) {
        if (windowGraphics == null) {
            return;
        }
        windowWidth = getWidth();
        windowHeight = getHeight();

        if (frontImageBuffer == null) {
            // no image to display
            windowGraphics.clearRect(0, 0, windowWidth, windowHeight);
            return;
        }
        synchronized (Zen.class) {
            Image image = paintImmediately ? backImageBuffer : frontImageBuffer;
            if (stretchToFit) {
                paintAtX = paintAtY = 0;
                windowGraphics.drawImage(image, 0, 0, windowWidth, windowHeight,
                        this);
            } else { // Blacken unused sides
                int x = windowWidth - bufferSize.width;
                int y = windowHeight - bufferSize.height;
                paintAtX = x / 2;
                paintAtY = y / 2;
                windowGraphics.setColor(Color.BLACK);
                // Notes: Some of the +1's may be unnecessary.
                // Notes: Actually there's some overlap in the 4 corners that
                // could
                // be removed
                if (y > 0) {
                    windowGraphics.fillRect(0, 0, windowWidth + 1, paintAtY);
                    windowGraphics.fillRect(0, windowHeight - paintAtY - 1,
                            windowWidth + 1, paintAtY + 1);
                }
                if (x > 0) {
                    windowGraphics.fillRect(0, 0, paintAtX + 1,
                            windowHeight + 1);
                    windowGraphics.fillRect(windowWidth - paintAtX - 1, 0,
                            paintAtX + 1, windowHeight + 1);
                }
                windowGraphics.drawImage(image, paintAtX, paintAtY, this);
            }
        }
    } // paint

    /**
     * Paint window immediately.
     */
    private void paintWindowImmediately() {
        Graphics windowGraphics = getGraphics();
        // IE8 windowGraphics is not null
        if (VERBOSE) {
            System.err.println(
                    "paintWindowImmediately graphics=" + windowGraphics);
        }
        if (windowGraphics != null) {
            paint(getGraphics());
        } else {
            repaint();
        }
    }

    /** The key listener. */
    private KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(final KeyEvent e) {
            char c = e.getKeyChar(); // may be CHAR_UNDEFINED
            mouseButtonsAndModifierKeys = e.getModifiersEx();
            if (c >= 0 && c < keyPressed.length) {
                keyPressed[c] = true;
            }
            int vk = e.getKeyCode();
            if (vk >= 0 && vk < virtualKeyPressed.length) {
                virtualKeyPressed[vk] = true;
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {
            char c = e.getKeyChar(); // may be CHAR_UNDEFINED
            mouseButtonsAndModifierKeys = e.getModifiersEx();
            if (c >= 0 && c < keyPressed.length) {
                keyPressed[c] = false;
            }
            int vk = e.getKeyCode();
            if (vk >= 0 && vk < virtualKeyPressed.length) {
                virtualKeyPressed[vk] = false;
            }
        }

        @Override
        public void keyTyped(final KeyEvent e) {
            char typed = e.getKeyChar();
            if (!Character.isISOControl(typed)) {
                editText.append(typed);
            } else if (typed == 8 && editText.length() > 0) {
                editText.deleteCharAt(editText.length() - 1);
            }
        }
    }; // KeyListener

    /** The mouse listener. */
    private MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(final MouseEvent me) {
            if (windowWidth == 0 || windowHeight == 0) {
                return; // no display window yet
            }
            mouseClickX = (stretchToFit
                    ? (int) (0.5 + me.getX() * bufferSize.width
                            / (double) windowWidth)
                    : me.getX() - paintAtX);
            mouseClickY = (stretchToFit
                    ? (int) (0.5 + me.getY() * bufferSize.height
                            / (double) windowHeight)
                    : me.getY() - paintAtY);
            mouseClickTime = me.getWhen();
        }

        @Override
        public void mousePressed(final MouseEvent e) {
            isMouseClicked = true;
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            isMouseClicked = false;
        }

    }; // MouseListener

    /** The mouse motion listener. */
    private MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
        @Override
        public void mouseMoved(final MouseEvent me) {
            if (windowWidth == 0 || windowHeight == 0) {
                return; // no display window yet
            }
            mouseX = (stretchToFit
                    ? (int) (0.5 + me.getX() * bufferSize.width
                            / (double) windowWidth)
                    : me.getX() - paintAtX);
            mouseY = (stretchToFit
                    ? (int) (0.5 + me.getY() * bufferSize.height
                            / (double) windowHeight)
                    : me.getY() - paintAtY);
            mouseButtonsAndModifierKeys = me.getModifiersEx();
        }

        @Override
        public void mouseDragged(final MouseEvent e) {
            mouseListener.mouseClicked(e);
            mouseMoved(e);
        }
    };
}
