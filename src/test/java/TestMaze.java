import static org.testng.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.Test;

import edu.illinois.cs.cs125.lib.mazemaker.Maze;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.DimensionException;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.Location;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.LocationException;
import edu.illinois.cs.cs125.lib.mazemaker.Maze.ValidationException;

/**
 * Test the maze class.
 */
public class TestMaze {

    /**
     * Test that we can create a maze.
     *
     * @throws DimensionException
     * @throws ValidationException
     */
    @Test
    public void testMazeCreation() throws ValidationException, DimensionException {

        for (int i = 0; i < 10; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(80, 100);
            int randomY = ThreadLocalRandom.current().nextInt(80, 100);
            Maze maze = new Maze(randomX, randomY);
            Assert.assertNotNull(maze);
        }
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

    /**
     * Test basic location setting and getting.
     *
     * @throws ValidationException the validation exception
     * @throws DimensionException the dimension exception
     * @throws LocationException the location exception
     */
    @Test
    public void testBasicLocations()
            throws ValidationException, DimensionException, LocationException {
        for (int i = 0; i < 10; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(80, 100);
            int randomY = ThreadLocalRandom.current().nextInt(80, 100);

            Maze maze = new Maze(randomX, randomY);

            for (int j = 0; j < 10; j++) {
                maze.startAtZero();
                Assert.assertEquals(maze.getCurrentLocation(), new Location(0, 0));

                int randomLocationX = ThreadLocalRandom.current().nextInt(0, randomX);
                int randomLocationY = ThreadLocalRandom.current().nextInt(0, randomY);
                maze.startAt(randomLocationX, randomLocationY);
                Assert.assertEquals(maze.getCurrentLocation(),
                        new Location(randomLocationX, randomLocationY));

                maze.startAtRandomLocation();
                Assert.assertNotEquals(maze.getCurrentLocation(),
                        new Location(randomLocationX, randomLocationY));

            }
        }
    }
}
