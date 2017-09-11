package edu.illinois.cs.cs125.lib.mazemaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that generates and allows programmatic exploration of a maze.
 */
public class Maze {

    /**
     * Exception for dimension configuration problems.
     */
    public static final class DimensionException extends Exception {

        /**
         * Generate a new dimension exception.
         *
         * @param message the message
         */
        DimensionException(final String message) {
            super(message);
        }
    }

    /**
     * Exception for bad direction inputs.
     */
    public static final class DirectionException extends Exception {

        /**
         * Create a new direction exception.
         *
         * @param message the message
         */
        DirectionException(final String message) {
            super(message);
        }
    }

    /**
     * Exception for errors during maze validation.
     */
    public static final class ValidationException extends Exception {

        /**
         * Create a new validation exception.
         *
         * @param message the message
         */
        ValidationException(final String message) {
            super(message);
        }
    }

    /**
     * Class representing a 2D integer location.
     */
    public static class Location {

        /** My X and Y coordinates. */
        private int x, y;

        /**
         * Get my X coordinate.
         *
         * @return my X coordinate
         */
        public int x() {
            return x;
        }

        /**
         * Sets my X coordinate.
         *
         * @param setX my new X coordinate
         */
        public void setX(final int setX) {
            this.x = setX;
        }

        /**
         * Get my Y coordinate.
         *
         * @return my Y coordinate
         */
        public int y() {
            return y;
        }

        /**
         * Sets my Y coordinate.
         *
         * @param setY my new Y coordinate
         */
        public void setY(final int setY) {
            this.y = setY;
        }

        /**
         * Create a new location.
         *
         * @param setX the X coordinate
         * @param setY the Y coordinate
         */
        Location(final int setX, final int setY) {
            x = setX;
            y = setY;
        }
    }

    /** Possible movement directions. */
    public static final String[] DIRECTIONS = {new String("up"), new String("right"),
            new String("down"), new String("left")};

    /**
     * Class representing a possible movement.
     */
    static class Movement {

        /** The change in the X value. */
        private Location d;

        /**
         * Get the change in location.
         *
         * @return the change in the X value
         */
        public Location getD() {
            return d;
        }

        /**
         * Gets the movement direction.
         *
         * @return the movement direction
         */
        public String getDirection() {
            return direction;
        }

        /**
         * Sets the movement direction.
         *
         * @param newDirection the new movement direction
         */
        public void setDirection(final String newDirection) {
            this.direction = newDirection;
        }

        /** The direction. */
        private String direction;

        /**
         * Instantiates a new movement.
         *
         * @param setD the change in location
         * @param setDirection the set direction
         * @throws DirectionException
         */
        Movement(final Location setD, final String setDirection)
                throws DirectionException {
            d = setD;
            if (!(Arrays.asList(DIRECTIONS).contains(setDirection))) {
                throw new DirectionException(setDirection + " is not a valid direction");
            }
            direction = setDirection;
        }
    }

    /** Map of possible movements. */
    static final Map<String, Movement> MOVEMENTS;

    /** Convenience map of opposite directions. */
    static final Map<String, String> OPPOSITEDIRECTIONS;
    static {
        MOVEMENTS = new HashMap<String, Movement>();
        try {
            MOVEMENTS.put("up", new Movement(new Location(0, 1), "up"));
            MOVEMENTS.put("down", new Movement(new Location(0, -1), "down"));
            MOVEMENTS.put("right", new Movement(new Location(1, 0), "right"));
            MOVEMENTS.put("left", new Movement(new Location(-1, 0), "left"));
        } catch (DirectionException e) {
            e.printStackTrace();
        }

        OPPOSITEDIRECTIONS = new HashMap<String, String>();
        OPPOSITEDIRECTIONS.put("up", "down");
        OPPOSITEDIRECTIONS.put("down", "up");
        OPPOSITEDIRECTIONS.put("left", "right");
        OPPOSITEDIRECTIONS.put("right", "left");
    }

    /**
     * A class representing a cell in the maze.
     */
    class Cell {

        /** The cell's location. */
        private final Location location;

        /**
         * Gets cell's location.
         *
         * @return the location
         */
        public Location getLocation() {
            return location;
        }

        /** Whether we have visited the cell during maze construction. */
        private boolean visited;

        /**
         * Checks if the cell has been visited during maze creation.
         *
         * @return true, if it has been visited
         */
        public boolean isVisited() {
            return visited;
        }

        /**
         * Mark the cell as visited.
         */
        public void setAsVisited() {
            this.visited = true;
        }

        /** Map of cell up, right, down, left borders. */
        private Map<String, Boolean> borders;

        /**
         * Gets the borders.
         *
         * @param direction the direction to set
         * @return the borders
         */
        public Boolean getBorder(final String direction) {
            return borders.get(direction);
        }

        /**
         * Clear a borders.
         *
         * @param direction the name of the border to clear
         */
        public void clearBorder(final String direction) {
            this.borders.put(direction, false);
        }

        /** Map of cell up, right, down, left neighboring cells, if they exist. */
        private Map<String, Cell> neighbors;

        /**
         * Get a cell's neighbors.
         *
         * @return the neighbors of the cell
         */
        public Map<String, Cell> getNeighbors() {
            return neighbors;
        }

        /**
         * Set a given neighbors.
         *
         * @param direction the direction to set
         * @param neighbor the cell to set as a neighbor
         */
        public void setNeighbor(final String direction, final Cell neighbor) {
            this.neighbors.put(direction,  neighbor);
        }

        /** Relative direction using during maze construction. */
        private String relativeDirection;

        /**
         * Gets the relative direction. Used during creation.
         *
         * @return the relative direction
         */
        public String getRelativeDirection() {
            return relativeDirection;
        }

        /**
         * Sets the relative direction. Used during creation.
         *
         * @param setRelativeDirection the new relative direction
         */
        public void setRelativeDirection(final String setRelativeDirection) {
            this.relativeDirection = setRelativeDirection;
        }

        /**
         * Instantiates a new cell.
         *
         * @param setLocation the cell's location
         */
        Cell(final Location setLocation) {
            location = setLocation;
            borders = new HashMap<String, Boolean>();
            for (String direction : DIRECTIONS) {
                borders.put(direction, true);
            }
            neighbors = new HashMap<String, Cell>();
        }
    }

    /** The maze. */
    private Cell[][] maze;

    /**
     * Create a new maze object with the specified dimensions.
     *
     * @param x the x
     * @param y the y
     * @return true, if successful
     */

    private boolean validCell(final int x, final int y) {
        return (x >= 0 && x < myXDimension && y >= 0 && y < myYDimension);
    }

    /** The x dimension. */
    private final int myXDimension;

    /**
     * Gets the maze X dimension.
     *
     * @return the maze's X dimension
     */
    public int getxDimension() {
        return myXDimension;
    }

    /** The y dimension. */
    private final int myYDimension;

    /**
     * Gets the maze Y dimension.
     *
     * @return the maze's Y dimension
     */
    public int getyDimension() {
        return myYDimension;
    }

    /**
     * Instantiates a new maze.
     *
     * @param mazeXDimension the x dimension
     * @param mazeYDimension the y dimension
     * @throws ValidationException the validation exception
     * @throws DimensionException the dimension exception
     */
    public Maze(final int mazeXDimension, final int mazeYDimension)
            throws ValidationException, DimensionException {

        myXDimension = mazeXDimension;
        myYDimension = mazeYDimension;

        if (myXDimension < 1) {
            throw new DimensionException("xDimension too small");
        }
        if (myYDimension < 1) {
            throw new DimensionException("yDimension too small");
        }
        if (myXDimension * myYDimension <= 1) {
            throw new DimensionException("combined dimensions too small");
        }

        maze = new Cell[myXDimension][myYDimension];
        for (int x = 0; x < myXDimension; x++) {
            for (int y = 0; y < myYDimension; y++) {
                maze[x][y] = new Cell(new Location(x, y));
            }
        }
        for (int x = 0; x < myXDimension; x++) {
            for (int y = 0; y < myYDimension; y++) {
                for (Map.Entry<String, Movement> entry : MOVEMENTS.entrySet()) {
                    Movement movement = entry.getValue();
                    int neighborX = movement.getD().x() + x;
                    int neighborY = movement.getD().y() + y;
                    if (!(validCell(neighborX, neighborY))) {
                        continue;
                    }
                    maze[x][y].setNeighbor(movement.direction, maze[neighborX][neighborY]);
                }
            }
        }

        int randomX = ThreadLocalRandom.current().nextInt(0, myXDimension);
        int randomY = ThreadLocalRandom.current().nextInt(0, myYDimension);
        Cell currentCell = maze[randomX][randomY];
        Stack<Cell> path = new Stack<Cell>();
        path.push(currentCell);
        int unvisitedCount = myXDimension * myYDimension - 1;

        while (unvisitedCount > 0) {
            if (path.empty()) {
                throw new ValidationException("path should not be empty");
            }

            currentCell = path.peek();
            currentCell.setAsVisited();

            ArrayList<Cell> nextCells = new ArrayList<Cell>();
            for (Map.Entry<String, Cell> entry : currentCell.getNeighbors().entrySet()) {
                Cell neighbor = entry.getValue();
                if (!(neighbor.isVisited())) {
                    neighbor.setRelativeDirection(entry.getKey());
                    nextCells.add(neighbor);
                }
            }

            if (nextCells.size() == 0) {
                path.pop();
                continue;
            }

            Cell nextCell = nextCells.get(ThreadLocalRandom.current().nextInt(0, nextCells.size()));
            if (currentCell.getBorder(nextCell.relativeDirection) == false) {
                throw new ValidationException("current cell should not have this border");
            }
            currentCell.clearBorder(nextCell.relativeDirection);
            String oppositeDirection = OPPOSITEDIRECTIONS.get(nextCell.relativeDirection);
            if (nextCell.getBorder(oppositeDirection) == false) {
                throw new ValidationException("next cell should not have this border");
            }
            nextCell.clearBorder(oppositeDirection);

            unvisitedCount--;
            path.add(nextCell);
        }
        for (int x = 0; x < myXDimension; x++) {
            for (int y = 0; y < myYDimension; y++) {
                Cell cell = maze[x][y];
                if (y == myYDimension - 1 && cell.getBorder("up") == false) {
                    throw new ValidationException("top row should have this border");
                }
                if (y == 0 && cell.getBorder("down") == false) {
                    throw new ValidationException("bottom row should have this border");
                }
                if (x == myXDimension - 1 && cell.getBorder("right") == false) {
                    throw new ValidationException("right column should have this border");
                }
                if (x == 0 && cell.getBorder("left") == false) {
                    throw new ValidationException("left column should have this border");
                }
                for (Map.Entry<String, Cell> entry : cell.neighbors.entrySet()) {
                    Cell neighbor = entry.getValue();
                    String direction = entry.getKey();
                    String oppositeDirection = OPPOSITEDIRECTIONS.get(direction);
                    if (cell.getBorder(direction) != neighbor.getBorder(oppositeDirection)) {
                        throw new ValidationException("mismatched neigbor borders");
                    }
                }
            }
        }
    }

    /** The user's current X, Y location. */
    private int myXLocation, myYLocation;

    /**
     * Start the maze at a specific location.
     *
     * @param xLocation the x location
     * @param yLocation the y location
     */
    public void startAt(final int xLocation, final int yLocation) {
        myXLocation = xLocation;
        myYLocation = yLocation;
    }

    /**
     * Start the maze at (0, 0).
     */
    public void startAtZero() {
        myXLocation = 0;
        myYLocation = 0;
    }

    /**
     * Start the maze at a random location.
     */
    public void startAtRandomLocation() {
        int newXLocation, newYLocation;
        do {
            newXLocation = ThreadLocalRandom.current().nextInt(0, myXDimension);
            newYLocation = ThreadLocalRandom.current().nextInt(0, myYDimension);
        } while (newXLocation == myXLocation || newYLocation == myYLocation);
    }
}
