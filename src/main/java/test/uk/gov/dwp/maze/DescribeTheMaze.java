package test.uk.gov.dwp.maze;

import org.junit.Test;
import uk.gov.dwp.maze.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
* Maze Tester.
*
* @author <Authors name>
* @since <pre>Oct 4, 2016</pre>
* @version 1.0
*/
public class DescribeTheMaze {

    @Test
    public void itShouldLoadTheMazeFromATextFile() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        Maze maze = new Maze(pathToMazeTxtFile);

        String expected = new String(Files.readAllBytes(Paths.get(pathToMazeTxtFile)));
        assertEquals(expected, maze.getMazeAsString());
    }

}
