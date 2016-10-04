package test.uk.gov.dwp.maze;

import org.junit.Test;
import uk.gov.dwp.maze.Explorer;
import uk.gov.dwp.maze.Maze;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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

        protected char[][] getMapAsGrid() {
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
    public void itShouldMoveUpByDefault() throws IOException {
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

    @Test
    public void itShouldMoveUpWhenFaceUpIsCalled() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.faceDown();
        explorer.faceUp();
        explorer.move();

        assertArrayEquals(new int[]{0, 1}, explorer.getLocation());
    }

    @Test
    public void itShouldMoveRightWhenFacingRightAndMoveIsPossible() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.faceRight();
        explorer.move();

        assertArrayEquals(new int[]{1, 2}, explorer.getLocation());
    }

    @Test
    public void itShouldMoveLeftWhenFacingLeftAndMoveIsPossible() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.faceLeft();
        explorer.move();

        assertArrayEquals(explorer.getLocation(), new int[]{1, 0});
    }

    @Test
    public void itShouldNotMoveOntoAWall() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithUpNotAvailable());
        testMaze.setStartPoint(new int[]{1,0});

        Explorer explorer = new Explorer(testMaze);
        explorer.move();

        assertArrayEquals(explorer.getLocation(), new int[]{1, 0});
    }

    @Test
    public void itShouldKnowWhatTileItIsFacing() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapFacingAWall());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);

        assertEquals(explorer.whatIsNextTile(), 'X');
    }

    @Test
    public void itShouldRememberWhereItHasMoved() throws IOException {
        TestMaze testMaze = getTestMaze();
        testMaze.setMazeGrid(getMapWithMoveAvailable());
        testMaze.setStartPoint(new int[]{1,1});

        Explorer explorer = new Explorer(testMaze);
        explorer.move();
        explorer.faceLeft();
        explorer.move();
        explorer.faceRight();
        explorer.move();
        explorer.faceDown();
        explorer.move();

        assertEquals("up, left, right, down", explorer.getMoves());
    }

    private char[][] getMapWithMoveAvailable() {
        return new char[][]
                {
                        {' ', ' ', ' '},
                        {' ', 'S', ' '},
                        {' ', ' ', ' '}
                };
    }

    private char[][] getMapWithUpNotAvailable() {
        return new char[][]
                {
                        {'X', 'X', 'X'},
                        {'X', 'S', 'X'},
                        {'X', 'X', 'X'}
                };
    }

    private char[][] getMapFacingAWall() {
        return new char[][]
                {
                        {' ', 'X', ' '},
                        {' ', 'S', ' '},
                        {' ', ' ', ' '}
                };
    }
}
