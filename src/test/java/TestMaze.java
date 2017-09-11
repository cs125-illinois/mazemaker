import static org.testng.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.Test;

import edu.illinois.cs.cs125.lib.mazemaker.Maze;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.DimensionException;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.ValidationException;

/**
 * Test the maze class.
 */
public class TestMaze {

    /**
     * Test that we can create a maze.
     * @throws DimensionException
     * @throws ValidationException
     */
    @Test
    public void testMazeCreation() throws ValidationException, DimensionException {
        int randomX = ThreadLocalRandom.current().nextInt(80, 100);
        int randomY = ThreadLocalRandom.current().nextInt(80, 100);

        Maze maze = new Maze(randomX, randomY);
        Assert.assertNotNull(maze);
    }

    /**
     * Test bad dimensions.
     */
    @Test
    public void testBadDimensions() {
        Maze maze = null;
        try {
            maze = new Maze(-1, 10);
            fail();
        } catch (ValidationException | DimensionException e) {
            Assert.assertNull(maze);
        }
        try {
            maze = new Maze(10, -1);
            fail();
        } catch (ValidationException | DimensionException e) {
            Assert.assertNull(maze);
        }
        try {
            maze = new Maze(1, 1);
            fail();
        } catch (ValidationException | DimensionException e) {
            Assert.assertNull(maze);
        }
    }
}
