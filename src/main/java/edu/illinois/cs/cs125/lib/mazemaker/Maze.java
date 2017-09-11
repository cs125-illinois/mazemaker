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
    static final class DimensionException extends Exception {

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
    static final class DirectionException extends Exception {

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
    static final class ValidationException extends Exception {

        /**
         * Create a new validation exception.
         *
         * @param message the message
         */
        ValidationException(final String message) {
            super(message);
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
        private int dx;

        /** The change in the Y value. */
        private int dy;

        /**
         * Get the change in X value.
         *
         * @return the change in the X value
         */
        public int getDx() {
            return dx;
        }

        /**
         * Sets the change in the X value.
         *
         * @param newDx the new change in X value
         */
        public void setDx(final int newDx) {
            this.dx = newDx;
        }

        /**
         * Gets the change in Y value.
         *
         * @return the change in Y value
         */
        public int getDy() {
            return dy;
        }

        /**
         * Sets the change in the Y value.
         *
         * @param newDy the new change in Y value
         */
        public void setDy(final int newDy) {
            this.dy = newDy;
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
         * @param setDx the set dx
         * @param setDy the set dy
         * @param setDirection the set direction
         * @throws DirectionException
         */
        Movement(final int setDx, final int setDy, final String setDirection)
                throws DirectionException {
            dx = setDx;
            dy = setDy;
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
            MOVEMENTS.put("up", new Movement(0, 1, "up"));
            MOVEMENTS.put("down", new Movement(0, -1, "down"));
            MOVEMENTS.put("right", new Movement(1, 0, "right"));
            MOVEMENTS.put("left", new Movement(-1, 0, "left"));
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

        /** The cell's X coordinate. */
        private final int x;

        /**
         * Get the cell's X coordinate.
         *
         * @return the X coordinate
         */
        public int getX() {
            return x;
        }

        /** The Y coordinate. */
        private final int y;

        /**
         * Gets the cell's Y coordinate.
         *
         * @return the Y coordinate
         */
        public int getY() {
            return y;
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
         * @param setX the set X
         * @param setY the set Y
         */
        Cell(final int setX, final int setY) {
            x = setX;
            y = setY;
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
        return (x >= 0 && x < xDimension && y >= 0 || y < yDimension);
    }

    /** The x dimension. */
    private final int xDimension;

    /** The y dimension. */
    private final int yDimension;

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

        xDimension = mazeXDimension;
        yDimension = mazeYDimension;

        if (xDimension < 1) {
            throw new DimensionException("xDimension too small");
        }
        if (yDimension < 1) {
            throw new DimensionException("yDimension too small");
        }
        if (xDimension * yDimension < 1) {
            throw new DimensionException("combined dimensions too small");
        }

        maze = new Cell[xDimension][yDimension];
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                for (Map.Entry<String, Movement> entry : MOVEMENTS.entrySet()) {
                    Movement movement = entry.getValue();
                    int neighborX = movement.getDx() + x;
                    int neighborY = movement.getDy() + y;
                    if (!(validCell(neighborX, neighborY))) {
                        continue;
                    }
                    maze[x][y].setNeighbor(movement.direction, maze[neighborX][neighborY]);
                }
            }
        }

        int randomX = ThreadLocalRandom.current().nextInt(0, xDimension);
        int randomY = ThreadLocalRandom.current().nextInt(0, yDimension);
        Cell currentCell = maze[randomX][randomY];
        Stack<Cell> path = new Stack<Cell>();
        path.push(currentCell);
        int unvisitedCount = xDimension * yDimension - 1;

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
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                Cell cell = maze[x][y];
                if (y == yDimension - 1 && cell.getBorder("up") == false) {
                    throw new ValidationException("top row should have this border");
                }
                if (y == 0 && cell.getBorder("down") == false) {
                    throw new ValidationException("bottom row should have this border");
                }
                if (x == xDimension - 1 && cell.getBorder("right") == false) {
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
}
