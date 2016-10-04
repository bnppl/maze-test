package test.uk.gov.dwp.maze;

import org.junit.Test;
import uk.gov.dwp.maze.Explorer;
import uk.gov.dwp.maze.Maze;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class DescribeTheExplorer {
    private class TestMaze extends Maze {
        private char[][] mazeGrid;
        private int[] startPoint;

        public TestMaze(String pathToMazeTxtFile) throws IOException, IllegalArgumentException {
            super(pathToMazeTxtFile);
        }

        public void setMazeGrid(char[][] grid) {
            mazeGrid = grid;
        }

        protected char[][] getMazeGrid() {
            return mazeGrid;
        }

        public void setStartPoint(int[] point) {
            startPoint = point;
        }

        public int[] getStartPoint() {
            return startPoint;
        }
    }

    @Test
    public void itShouldStartAtTheMazeStartPosition() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        Maze maze = new Maze(pathToMazeTxtFile);
        Explorer explorer = new Explorer(maze);

        assertArrayEquals(maze.getStartPoint(), explorer.getLocation());
    }

    @Test
    public void itShouldMove() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.move();

        assertArrayEquals(new int[]{0, 1}, explorer.getLocation());
    }

    private TestMaze getTestMaze() throws IOException {
        String pathToMazeTxtFile = Test.class.getClassLoader().getResource("").getPath() + "Maze1.txt";
        return new TestMaze(pathToMazeTxtFile);
    }

    @Test
    public void itShouldMoveDownWhenFacingDownAndMoveIsPossible() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.faceDown();
        explorer.move();

        assertArrayEquals(explorer.getLocation(), new int[]{2, 1});
    }

    private char[][] getMapWithMoveAvailable() {
        return new char[][]
                {
                        {' ', ' ', ' '},
                        {' ', 'S', ' '},
                        {' ', ' ', ' '}
                };
    }
}
