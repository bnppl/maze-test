package test.uk.gov.dwp.maze;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.dwp.maze.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DescribeTheMaze {

    @Test
    public void itShouldLoadTheMazeFromATextFile() throws IOException, IllegalArgumentException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        Maze maze = new Maze(pathToMazeTxtFile);

        String expected = new String(Files.readAllBytes(Paths.get(pathToMazeTxtFile)));
        assertEquals(expected, maze.getMazeAsString());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void itShouldHaveAStartPoint() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "MazeWithoutStartPoint.txt";

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maze must have a start point.");

        Maze maze = new Maze(pathToMazeTxtFile);
    }

    @Test
    public void itShouldHaveAnEndPoint() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "MazeWithoutEndPoint.txt";

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maze must have an end point.");

        Maze maze = new Maze(pathToMazeTxtFile);
    }

    @Test
    public void itShouldConsistOfSpacesAndWallsAndAStartAndEndPointOnly() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "MazeWithInvalidCharacters.txt";

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Maze contains invalid characters.");

        Maze maze = new Maze(pathToMazeTxtFile);
    }

    @Test
    public void itShouldKnowHowManyWallsAreInTheMap() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";

        Maze maze = new Maze(pathToMazeTxtFile);

        assertEquals(149, maze.getNumberOfWalls());
    }

    @Test
    public void itShouldKnowHowManySpacesAreInTheMap() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";

        Maze maze = new Maze(pathToMazeTxtFile);

        assertEquals(74, maze.getNumerOfSpaces());
    }
}
