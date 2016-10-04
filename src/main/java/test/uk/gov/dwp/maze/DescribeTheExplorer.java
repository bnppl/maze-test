package test.uk.gov.dwp.maze;

import org.junit.Test;
import uk.gov.dwp.maze.Explorer;
import uk.gov.dwp.maze.Maze;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class DescribeTheExplorer {

    @Test
    public void itShouldStartAtTheMazeStartPosition() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        Maze maze = new Maze(pathToMazeTxtFile);
        Explorer explorer = new Explorer(maze);

        assertArrayEquals(maze.getStartPoint(), explorer.getLocation());
    }
}
